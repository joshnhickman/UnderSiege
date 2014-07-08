package com.kerbii.undersiege;

public class MobNero extends MobBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.mobgenerica0,
		R.drawable.mobgenerica1,
		R.drawable.mobgenerica2,
		R.drawable.mobgenerica3,
		R.drawable.mobgenerica4,
		R.drawable.mobgenerica5,
		R.drawable.mobgenerica6,
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
	
	public MobNero(float x, float y, float health) {
		super(x, y, health);
	}
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		textureIndex = staticTextureIndexArray[animationIndex];
	}
}
