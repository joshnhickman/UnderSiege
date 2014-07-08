package com.kerbii.undersiege;

public class ArrowGeneric extends ArrowBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric
								};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public ArrowGeneric(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray);
		arrowType = SiegeVariables.GENERIC;
	}
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		
		textureIndex = staticTextureIndexArray[0];
	}
}
