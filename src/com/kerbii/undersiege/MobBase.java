package com.kerbii.undersiege;

public abstract class MobBase extends GameObject {
	
	/**
	 * State variables
	 */
	public static final int MOVING = 0, ATTACKING = 1, DYING = 2, DEAD = 3;
	public int state;
	
	/**
	 * Health variables
	 */
	public float health;
	public boolean alive;
	
	/**
	 * Movement variables
	 */
	public float speedX;
	public float speedY;
	public float slowFactor;
	public long slowDuration, timeSlowed;
	
	/**
	 * Attack variables
	 */
	public float damage;
	public long millisPerAttack;
	public long millisSinceLastAttack;
	
	public int goldMult;
	
	public int animationIndex;
	
	private static float vertices[] = {
		0.0f, 40.0f, 0.0f,
		40.0f, 40.0f, 0.0f,
		0.0f, 0.0f, 0.0f,
		40.f, 0.f, 0.0f
						};
	
	public MobBase(float x, float y, float health) {
		super(x, y, 40, 40, vertices);
		this.health = health;
		speedX = -1 / 20.0f;
		speedY = 0 / 20.0f;
		slowFactor = 1;
		goldMult = 1;
		damage = 1;
		state = MOVING;
		
		alive = true;
		
		animationIndex = 0;
		millisSinceLastFrame = 100;
		
		millisPerAttack = 500;
	}
	
	public void update(long deltaTime) {
		if (slowFactor > 1) {
			timeSlowed += deltaTime;
			if (timeSlowed >= slowDuration) unSlow();
		}
		
		if (state == MOVING) {
			x += speedX * (float) deltaTime / slowFactor;
			y += speedY * (float) deltaTime / slowFactor;
			if (x < SiegeVariables.castle.x + SiegeVariables.castle.width) {
				x = SiegeVariables.castle.x + SiegeVariables.castle.width;
				state = ATTACKING;
			}
		} else if (state == ATTACKING) {
			millisSinceLastAttack += deltaTime / slowFactor;
			if (millisSinceLastAttack > millisPerAttack) {
				int attackSkips = (int) (millisSinceLastAttack / millisPerAttack);
				SiegeVariables.castle.damage(damage * attackSkips);
				millisSinceLastAttack = 0;
			}
		} else if (state == DYING) {
			alive = false;
		} else if (state == DEAD) {
			alive = false;
			draw = false;
		}
		
		if (state == MOVING || state == ATTACKING || state == DYING) {
			millisSinceLastFrame += deltaTime / slowFactor;
			if (millisSinceLastFrame >= millisPerFrame) {
//				int animationSkips = (int) (millisSinceLastFrame / millisPerFrame);
//				animationIndex += animationSkips;
				animationIndex++;
				if (state == MOVING) {
					if (animationIndex > 6) animationIndex = animationIndex - 7;
				} else if (state == ATTACKING) {
					if (animationIndex < 16 || animationIndex > 21) animationIndex = 16;
				} else if (state == DYING) {
					if (animationIndex > 15) {
						animationIndex = 15;
						alpha -= 0.05;
						if (alpha <= 0) state = DEAD;
					}
				}
				millisSinceLastFrame = 0;
			}
		}
	}
	
	public float health() {
		return health;
	}
	
	/**
	 * Returns true if the mob's special ability is in effect
	 * (shield, etc)
	 * @return
	 */
	public boolean special() {
		return false;
	}
	
	public void hit(ArrowBase arrow, float damage) {
		if (arrow.arrowType == SiegeVariables.FIRE) unSlow(); 
		health -= damage;
		if (health <= 0) {
			kill();
		}
	}
	
	public void hit(EffectBase effect, float damage) {
		health -= damage;
		if (health <= 0) {
			kill();
		}
	}
	
	public void slow(float slowFactor, long duration) {
		this.slowFactor = slowFactor;
		slowDuration = duration;
		timeSlowed = 0;
	}
	
	private void unSlow() {
		slowFactor = 1;
		slowDuration = 0;
		timeSlowed = 0;
	}
	
	public void kill() {
		state = DYING;
		unSlow();
		animationIndex = 7;
	}
}
