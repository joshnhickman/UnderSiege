package com.kerbii.undersiege;

public class ArrowArcher extends ArrowBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric
								};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	static final float DECAYRATE = 1;
	
	public ArrowArcher(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray);
		arrowType = SiegeVariables.GENERIC;
	}
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		
		textureIndex = staticTextureIndexArray[0];
		
		if (x > 400) speed -= deltaTime * DECAYRATE / 20.0f;
		if (speed < 1) state = BROKEN;
	}
}
