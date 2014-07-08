package com.kerbii.undersiege;

public class ArrowMob extends ArrowBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric
								};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public ArrowMob(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray);
		arrowType = SiegeVariables.GENERIC;
		
		angle = 180;
		
		state = TOCASTLE;
	}
	
	public void update(long deltaTime) {
		
		textureIndex = staticTextureIndexArray[0];
		
		if (x <= SiegeVariables.castle.x + SiegeVariables.castle.width) {
			SiegeVariables.castle.damage(speed);
			state = BROKEN;
		}
		
		if (state == TOCASTLE) {
			x += updateX * speed * deltaTime;
			y += updateY * speed * deltaTime;
		}
		
		if (state == BROKEN || (state != EFFECT && (x > 850 || y > 530 || y < -50))) {
			draw = false;
		}
		
		if (state == TOCASTLE) {
			millisSinceLastFrame += deltaTime;
			if (millisSinceLastFrame >= millisPerFrame) {
				int animationSkips = (int) (millisSinceLastFrame / millisPerFrame);
				animationIndex += animationSkips;
				if (animationIndex > 2) {
					animationIndex = animationIndex - 3;
				}
				millisSinceLastFrame = 0;
			}
		}
	}
}
