package com.kerbii.undersiege;

public class EffectFire extends EffectBase {
	
	private long duration, life;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.firepatch0
								};
	
	public static int[] staticTextureIndexArray;
	
	public EffectFire(float x, float y, float width, long duration) {
		super(x, y, width, width);
		
		this.duration = duration;
		
		float[] vertices = {
				0.0f, width, 0.0f,
				width, width, 0.0f,
				0.0f, 0.0f, 0.0f,
				width, 0.0f, 0.0f
									};
		changeVertices(vertices);
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
		
		life += deltaTime;
		if (life >= duration) draw = false;
	}
}
