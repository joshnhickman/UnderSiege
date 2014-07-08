package com.kerbii.undersiege;

public class EffectLightning extends EffectBase {
	
	float xStart;
	float yStart;
	float xTarget;
	float yTarget;
	
	long life;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowlightningzap0
								};
	
	public static int[] staticTextureIndexArray;
	
	public EffectLightning(float xStart, float yStart, float xTarget, float yTarget) {
		super(xStart, yStart, 0, 40);
		this.xStart = xStart;
		this.yStart = yStart;
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		float xDistance = xTarget - xStart;
		float yDistance = yTarget - yStart;
		width = distance(xTarget, yTarget);
		angle = (float) (Math.atan2(yDistance, xDistance) * (180 / Math.PI));
		
		float[] vertices = {
				0.0f, (height / 2), 0.0f,
				width, (height / 2), 0.0f,
				0.0f, -(height / 2), 0.0f,
				width, -(height / 2), 0.0f
											};
		changeVertices(vertices);
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
		life += deltaTime;
		if (life >= 150) draw = false;
	}
}
