package com.kerbii.undersiege;

public abstract class ArrowBase extends GameObject {
	
	public static final int FLYING = 0, EMBEDDED = 1, BROKEN = 2, EFFECT = 3, TOCASTLE = 4;
	public int state;
	
//	public static final int GENERIC = 0, FIRE = 1, LIGHTNING = 2, ICE = 3, ARCHER = 4, MOB = 5;
	public int arrowType;
	
	public static float arrowFlightSpeed;
	
	float updateX, updateY;
	float speed;
	
	public float effectCenterX;
	public float effectCenterY;
	
	public float effectRange;
	public float effectDamage;
	
	MobBase embeddedMob;
	
	public int animationIndex;
	
	private static float vertices[] = {
			-40.0f, -4.0f, 0.0f,
			0.0f, -4.0f, 0.0f,
			-40.0f, 4.0f, 0.0f,
			0.0f, 4.0f, 0.0f
							};
	
	public ArrowBase(float x, float y, float xTarget, float yTarget, float speed, int[] animationArray, float effectRange, float effectDamage) {
		super(x, y, 0, 0, vertices);
		this.speed = speed;
		this.effectDamage = effectDamage;
		
		float xChange = -(x - xTarget);
		float yChange = -(y - yTarget);
		float totalDistance = (float) Math.sqrt((xChange * xChange) + (yChange * yChange));
		updateX = (xChange / totalDistance) / 16.0f;
		updateY = (yChange / totalDistance) / 16.0f;
		angle = (float) (Math.atan(yChange / xChange) * (180 / Math.PI));
		state = FLYING;
	}
	
	public ArrowBase(float x, float y, float xTarget, float yTarget, float speed, int[] animationArray) {
		this(x, y, xTarget, yTarget, speed, animationArray, 0, 0);
	}
	
	public void update(long deltaTime) {
		if (state == FLYING) {
			x += updateX * speed * deltaTime;
			y += updateY * speed * deltaTime;
		} else if (state == EMBEDDED) {
			if (!embeddedMob.special()) state = BROKEN;
			if (embeddedMob.state == MobBase.MOVING) {
				x += embeddedMob.speedX * deltaTime;
				y += embeddedMob.speedY * deltaTime;
			} else if (embeddedMob.state == MobBase.DYING || embeddedMob.state == MobBase.DEAD){
				state = BROKEN;
			}
		}
		
		if (state == BROKEN || (state != EFFECT && (x > 850 || y > 530 || y < -50))) {
			draw = false;
		}
		
		if (state == FLYING) {
			millisSinceLastFrame += deltaTime;
			if (millisSinceLastFrame >= millisPerFrame) {
				int animationSkips = (int) (millisSinceLastFrame / millisPerFrame);
				animationIndex += animationSkips;
				if (animationIndex > 2) {
					animationIndex = animationIndex - 3;
				}
				millisSinceLastFrame = 0;
			}
		}
	}
	
	public void hit(MobBase mob, float health) {
		if (state == FLYING) {
			speed -= health;
			if (speed < 8 && state != EMBEDDED) {
				state = BROKEN;
			} else {
				
			}
		}
	}
	
	public ArrayList<MobBase> hitMobs(ArrayList<MobBase> mobsInRange) {
		mobsInRange.removeAll();
		return mobsInRange;
	}
	
	public void embed(MobBase mob) {
		state = EMBEDDED;
		embeddedMob = mob;
	}
	
	public float damage() {
		if (state == EFFECT) {
			return effectDamage;
		} else return speed + effectDamage;
	}
}
