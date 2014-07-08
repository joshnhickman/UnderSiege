package com.kerbii.undersiege;

public class MobArcher extends MobBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.mobgenerica0,
		R.drawable.mobgenerica1,
		R.drawable.mobgenerica2,
		R.drawable.mobgenerica3,
		R.drawable.mobgenerica4,
		R.drawable.mobgenerica5,
		R.drawable.mobgenerica6,
		R.drawable.mobgenericd0,
		R.drawable.mobgenericd1,
		R.drawable.mobgenericd2,
		R.drawable.mobgenericd3,
		R.drawable.mobgenericd4,
		R.drawable.mobgenericd5,
		R.drawable.mobgenericd6,
		R.drawable.mobgenericd7,
		R.drawable.mobgenericd8,
		R.drawable.genericattack0,
		R.drawable.genericattack1,
		R.drawable.genericattack2,
		R.drawable.genericattack3,
		R.drawable.genericattack4,
		R.drawable.genericattack5
								};
	
	public static int[] staticTextureIndexArray;
	
	private float range;
	
	public MobArcher(float x, float y, float health) {
		super(x, y, health);
//		textureIndex = staticTextureIndexArray[animationIndex];
		
		damage = 10;
		millisPerAttack = 1800 + SiegeVariables.r.nextInt(4000);
		
		range = 250.0f + SiegeVariables.r.nextInt(100);
	}
	
	public void update(long deltaTime) {
		if (state == MOVING) {
			x += speedX * (float) deltaTime;
			y += speedY * (float) deltaTime;
			if (x < SiegeVariables.castle.x + SiegeVariables.castle.width + range) {
				x = SiegeVariables.castle.x + SiegeVariables.castle.width + range;
				state = ATTACKING;
			}
		} else if (state == ATTACKING) {
			millisSinceLastAttack += deltaTime;
			if (millisSinceLastAttack > millisPerAttack) {
				SiegeVariables.arrowFactory.shoot(x + width / 2, y + width / 2, SiegeVariables.castle.x, y + width / 2, 8, SiegeVariables.MOB);
				millisSinceLastAttack = 0;
			}
		} else if (state == DYING) {
			alive = false;
		} else if (state == DEAD) {
			alive = false;
			draw = false;
		}
		
		if (state == MOVING || state == ATTACKING || state == DYING) {
			millisSinceLastFrame += deltaTime;
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
		
		textureIndex = staticTextureIndexArray[animationIndex];
	}
}
