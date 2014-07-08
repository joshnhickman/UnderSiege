package com.kerbii.undersiege;

import javax.microedition.khronos.opengles.GL10;


public class TextObject extends ButtonBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.zero,
		R.drawable.one,
		R.drawable.two,
		R.drawable.three,
		R.drawable.four,
		R.drawable.five,
		R.drawable.six,
		R.drawable.seven,
		R.drawable.eight,
		R.drawable.nine,
								};
	
	public static int[] staticTextureIndexArray;
	
	public int newInt;
	private int currentInt, length;
	private int ones, tens, hundreds, thousands, tenthousands;
	
	public TextObject(float x, float y, float width, float height, float xLeft, float xRight,
			float yTop, float yBottom, int startState) {
		super(x, y, width, height, xLeft, xRight, yTop, yBottom, startState);
		
		currentInt = 0;
		newInt = 0;
		
//		textureIndex = staticTextureIndexArray[0];
	}

	public void update(long deltaTime) {
		if (newInt != currentInt) {
			currentInt = newInt;
			ones = currentInt % 10;
			length = 1;
			if (currentInt >= 10) {
				tens = (currentInt / 10) % 10;
				length = 2;
			}
			if (currentInt >= 100) {
				hundreds = (currentInt / 100) % 10;
				length = 3;
			}
			if (currentInt >= 1000) {
				thousands = (currentInt / 1000) % 10;
				length = 4;
			}
			if (currentInt >= 10000) {
				tenthousands = (currentInt / 10000) % 10;
				length = 5;
			}
		}
		super.update(deltaTime);
	}

	public void draw(GL10 gl, int textures[]) {
		if (length >= 5) {
			draw(gl, textures, tenthousands);
			gl.glTranslatef(width, 0.0f, 0.0f);
		}
		if (length >= 4) {
			draw(gl, textures, thousands);
			gl.glTranslatef(width, 0.0f, 0.0f);
		}
		if (length >= 3) {
			draw(gl, textures, hundreds);
			gl.glTranslatef(width, 0.0f, 0.0f);
		}
		if (length >= 2) {
			draw(gl, textures, tens);
			gl.glTranslatef(width, 0.0f, 0.0f);
		}
		draw(gl, textures, ones);
	}
	
	private void draw(GL10 gl, int textures[], int number) {
		//Bind texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[staticTextureIndexArray[number]]);
		//Point to vertex and texture buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		gl.glColor4f(1, 1, 1, alpha);
		//Draw
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
	}
}
