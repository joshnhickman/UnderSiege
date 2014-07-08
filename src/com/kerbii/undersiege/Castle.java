package com.kerbii.undersiege;

import javax.microedition.khronos.opengles.GL10;

public class Castle extends GameObject {
	
	public final static int[] TEXTUREARRAY = {
		R.drawable.wall100,
		R.drawable.wall80,
		R.drawable.wall65,
		R.drawable.wall51,
		R.drawable.wall50p,
		R.drawable.wall41,
		R.drawable.wall40p,
		R.drawable.wall21,
		R.drawable.wall20p,
		R.drawable.wall10,
							};
	
	public static int[] staticTextureIndexArray;
	
	
	private static float vertices[] = {
		0.0f, 480.0f, 0.0f,
		262.0f, 480.0f, 0.0f,
		0.0f, 0.0f, 0.0f,
		262.0f, 0.0f, 0.0f
						};
	
	public Castle(float x, float y) {
		super(x, y, 120, 480, vertices);
	}
	
	public void update(long deltaTime) {
		
	}
	
	public void draw(GL10 gl, int[] textures) {
		float healthPercent = (SiegeVariables.castleHealth / SiegeVariables.castleHealthMax) * 100;
		if (healthPercent >= 80) {
			textureIndex = staticTextureIndexArray[1];
			alpha = 1;
//			alpha = (100 - healthPercent) / (100 - 80);
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[0];
			alpha = (healthPercent - 80) / (100 - 80);
			super.draw(gl, textures);
		} else if (healthPercent >= 65) {
			textureIndex = staticTextureIndexArray[2];
			alpha = 1;
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[1];
			alpha = (healthPercent - 65) / (79 - 65);
			super.draw(gl, textures);
		} else if (healthPercent > 50) {
			textureIndex = staticTextureIndexArray[3];
			alpha = 1;
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[2];
			alpha = (healthPercent - 51) / (64 - 51);
			super.draw(gl, textures);
		} else if (healthPercent > 40) {
			textureIndex = staticTextureIndexArray[5];
			alpha = 1;
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[4];
			alpha = (healthPercent - 41) / (50 - 41);
			super.draw(gl, textures);
		} else if (healthPercent > 21) {
			textureIndex = staticTextureIndexArray[7];
			alpha = 1;
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[6];
			alpha = (healthPercent - 21) / (40 - 21);
			super.draw(gl, textures);
		} else if (healthPercent >= 10) {
			textureIndex = staticTextureIndexArray[9];
			alpha = 1;
			super.draw(gl, textures);
			textureIndex = staticTextureIndexArray[8];
			alpha = (healthPercent - 10) / (20 - 10);
			super.draw(gl, textures);
		} else {
			textureIndex = staticTextureIndexArray[9];
			alpha = 1;
			super.draw(gl, textures);
		}
	}
	
	public void damage(float damage) {
		SiegeVariables.castleHealth -= damage;
		if (SiegeVariables.castleHealth <= 0) {
			draw = false;
		}
	}
}
