package com.kerbii.undersiege;

public class Leto extends GameObject {
	
	public static final int READY = 0, DRAWING = 1;
	public int state;
	
	public static long arrowFireRate;
	
	private long millisPerFrame;
	private long millisToHold;
	private long millisSinceLastFrame;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.leto0,
		R.drawable.leto1,
		R.drawable.leto2,
		R.drawable.leto3,
		R.drawable.leto4,
		R.drawable.leto5,
		R.drawable.leto6,
		R.drawable.leto7
							};
	
	public static int[] staticTextureIndexArray;
	public int animationIndex;
	
	private static float vertices[] = {
		-50.0f, 50.0f, 0.0f,
		50.0f, 50.0f, 0.0f,
		-50.0f, -50.0f, 0.0f,
		50.f, -50.f, 0.0f
							};
	
	public Leto() {
		super(80, 240, 100, 100, vertices);
//		textureIndex = staticTextureIndexArray[6];
		millisPerFrame = 30;
		millisToHold = 100;
		state = DRAWING;
	}
	
	public void update(long deltaTime) {
		if (SiegeVariables.mana < SiegeVariables.manaMax) { 
			SiegeVariables.mana += deltaTime * ((float) 1 / 192);
		} else if (SiegeVariables.mana > SiegeVariables.manaMax) SiegeVariables.mana = SiegeVariables.manaMax;
		
		if (state == DRAWING) {
			millisSinceLastFrame += deltaTime;
			if (millisSinceLastFrame >= millisPerFrame) {
				if (animationIndex == 6) {
					animationIndex = 7;
					millisSinceLastFrame = 0;
				} else if (animationIndex == 7) {
					if (millisSinceLastFrame >= millisToHold) {
						animationIndex = 0;
						millisSinceLastFrame = 0;
					}
				} else if (animationIndex < 5) {
					animationIndex++;
					millisSinceLastFrame = 0;
				} else if (animationIndex == 5) {
					state = READY;
					millisSinceLastFrame = 0;
				}
			}
		}
		textureIndex = staticTextureIndexArray[animationIndex];
	}
	
	public void rotate(float xTarget, float yTarget) {
		float xChange = xTarget - x;
		float yChange = yTarget - y;
		angle = (float) (Math.atan(yChange / xChange) * (180 / Math.PI));
	}
	
	public void fire() {
		state = DRAWING;
		animationIndex = 6;
	}
}
