package com.kerbii.undersiege;

public class EffectLightningHit extends EffectBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.lightninghit0,
		R.drawable.lightninghit1,
		R.drawable.lightninghit2
								};
	
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public EffectLightningHit(float x, float y) {
		super(x, y, 40, 40);
		
		float[] vertices = {
				0.0f, height, 0.0f,
				width, height, 0.0f,
				0.0f, 0, 0.0f,
				width, 0, 0.0f
								};
		changeVertices(vertices);
		
		millisPerFrame = 100;
	}
	
	public void update(long deltaTime) {
		millisSinceLastFrame += deltaTime;
		if (millisSinceLastFrame > millisPerFrame) {
			int animationSkips = (int) (millisSinceLastFrame / millisPerFrame);
			animationIndex += animationSkips;
			if (animationIndex > 2) {
				animationIndex = 0;
				draw = false;
			}
			millisSinceLastFrame = 0;
		}
		textureIndex = staticTextureIndexArray[animationIndex];
	}
}
