package com.kerbii.undersiege;


public class ArrowIce extends ArrowBase {
	
	public boolean hasSlowed;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric
								};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public ArrowIce(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray, SiegeVariables.arrowIceRange, SiegeVariables.arrowIceDamage);
		hasSlowed = false;
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
		super.update(deltaTime);
		if (hasSlowed) state = BROKEN;
	}
	
	public void hit(MobBase next, float health) {
		if (state == FLYING) {
			effectCenterX = next.x + (next.width / 2);
			effectCenterY = next.y + (next.height / 2);
			
			width = SiegeVariables.arrowIceRange;
			height = width;
			x = effectCenterX - (width / 2);
			y = effectCenterY - (width / 2);
			
			//drop ice patch
			SiegeVariables.effectsGround.add(new EffectFire(x, y, width, SiegeVariables.arrowIceDuration));
			
			state = EFFECT;
			
			float[] vertices = {
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f
									};
			changeVertices(vertices);
		} else if (state == EFFECT) {
			//drop chilled effect
			next.slow(SiegeVariables.arrowIceSlowFactor, SiegeVariables.arrowIceDuration);
		}
	}
	
	public ArrayList<MobBase> hitMobs(ArrayList<MobBase> mobsInRange) {
		if (!hasSlowed) {
			hasSlowed = true;
			return mobsInRange;
		} else return super.hitMobs(mobsInRange);
	}
}
