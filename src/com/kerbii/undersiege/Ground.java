package com.kerbii.undersiege;

public class Ground extends GameObject {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.grass
								};
	
	public static int[] staticTextureIndexArray;
	
	private static float vertices[] = {
		0.0f, 512.0f, 0.0f,
		1024.0f, 512.0f, 0.0f,
		0.0f, 0.0f, 0.0f,
		1024.0f, 0.0f, 0.0f
							};
	
	public Ground() {
		super(0, 0, 800, 480, vertices);
		
//		textureIndex = staticTextureIndexArray[0];
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
	}
}
