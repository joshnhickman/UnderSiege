package com.kerbii.undersiege;


public class ArrowFire extends ArrowBase {
	
	private static final long DAMAGETIME = 200;
	private long timeSinceLastDamage, timeActive;
		
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric
								};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public ArrowFire(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray, SiegeVariables.arrowFireRange, SiegeVariables.arrowFireDamage);
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
		super.update(deltaTime);
		if (state == EFFECT) {
			timeActive += deltaTime;
			if (timeActive >= SiegeVariables.arrowFireDuration) {
				state = BROKEN;
			}
			timeSinceLastDamage += deltaTime;
		}
	}
	
	public void hit(MobBase next, float health) {
		if (state == FLYING) {
			effectCenterX = next.x + (next.width / 2);
			effectCenterY = next.y + (next.height / 2);
			
			width = SiegeVariables.arrowFireRange;
			height = width;
			x = effectCenterX - (width / 2);
			y = effectCenterY - (width / 2);
			
			//drop burning patch
			SiegeVariables.effectsGround.add(new EffectFire(x, y, width, SiegeVariables.arrowFireDuration));
			
			state = EFFECT;
			
			float[] vertices = {
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f
									};
			changeVertices(vertices);
		} else if (state == EFFECT) {
			//drop burn sprite
		}
	}
	
	public ArrayList<MobBase> hitMobs(ArrayList<MobBase> mobsInRange) {
		if (timeSinceLastDamage >= DAMAGETIME) {
			timeSinceLastDamage = 0;
			return mobsInRange;
		} else {
			return super.hitMobs(mobsInRange);
		}
	}
}
