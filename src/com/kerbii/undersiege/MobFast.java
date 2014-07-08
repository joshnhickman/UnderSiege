package com.kerbii.undersiege;

public class MobFast extends MobBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.mobfast0,
		R.drawable.mobfast1,
		R.drawable.mobfast2,
		R.drawable.mobfast3,
		R.drawable.mobfast4,
		R.drawable.mobfast5,
		R.drawable.mobfast6,
		R.drawable.mobgenericd0,
		R.drawable.mobgenericd1,
		R.drawable.mobgenericd2,
		R.drawable.mobgenericd3,
		R.drawable.mobgenericd4,
		R.drawable.mobgenericd5,
		R.drawable.mobgenericd6,
		R.drawable.mobgenericd7,
		R.drawable.mobgenericd8,
		R.drawable.genericattack0,
		R.drawable.genericattack1,
		R.drawable.genericattack2,
		R.drawable.genericattack3,
		R.drawable.genericattack4,
		R.drawable.genericattack5
								};
	
	public static int[] staticTextureIndexArray;
	
	public MobFast(float x, float y, float health) {
		super(x, y, health);
//		textureIndex = staticTextureIndexArray[animationIndex];
		
		speedX = -2 / 20.0f;
//		millisPerFrame = millisPerFrame / 2;
	}
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		textureIndex = staticTextureIndexArray[animationIndex];
	}
}
