package com.kerbii.undersiege;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class TextureLoader {
	
	private Context context;
	
	public TextureLoader(Context context) {
		this.context = context;
	}
	
	public int[] loadTextures(GL10 gl) {
		int totalTextures = 0;
		totalTextures += TextObject.TEXTUREARRAY.length;
		totalTextures += Icon.TEXTUREARRAY.length;
		totalTextures += Leto.TEXTUREARRAY.length;
		totalTextures += Archer.TEXTUREARRAY.length;
		totalTextures += Ground.TEXTUREARRAY.length;
		totalTextures += Castle.TEXTUREARRAY.length;
		totalTextures += MobGeneric.TEXTUREARRAY.length;
		totalTextures += MobShield.TEXTUREARRAY.length;
		totalTextures += MobFast.TEXTUREARRAY.length;
		totalTextures += MobArcher.TEXTUREARRAY.length;
		totalTextures += ArrowGeneric.TEXTUREARRAY.length;
		totalTextures += ArrowFire.TEXTUREARRAY.length;
		totalTextures += ArrowLightning.TEXTUREARRAY.length;
		totalTextures += ArrowIce.TEXTUREARRAY.length;
		totalTextures += ArrowArcher.TEXTUREARRAY.length;
		totalTextures += ArrowMob.TEXTUREARRAY.length;
		totalTextures += EffectFire.TEXTUREARRAY.length;
		totalTextures += EffectLightning.TEXTUREARRAY.length;
		totalTextures += EffectLightningHit.TEXTUREARRAY.length;
		totalTextures += Button.TEXTUREARRAY.length;
		totalTextures += ButtonArrow.TEXTUREARRAY.length;
		totalTextures += ButtonUpgrade.TEXTUREARRAY.length;
		int[] textures = new int[totalTextures];
		int textureIndex = 0;
		
		//Generate texture pointer
		gl.glGenTextures(totalTextures, textures, 0);
		
		textures = loadClassTextures(gl, textures, TextObject.TEXTUREARRAY, textureIndex);
		TextObject.staticTextureIndexArray = new int[TextObject.TEXTUREARRAY.length];
		for (int i = 0; i < TextObject.TEXTUREARRAY.length; i++) {
			TextObject.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Icon.TEXTUREARRAY, textureIndex);
		Icon.staticTextureIndexArray = new int[Icon.TEXTUREARRAY.length];
		for (int i = 0; i < Icon.TEXTUREARRAY.length; i++) {
			Icon.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Leto.TEXTUREARRAY, textureIndex);
		Leto.staticTextureIndexArray = new int[Leto.TEXTUREARRAY.length];
		for (int i = 0; i < Leto.TEXTUREARRAY.length; i++) {
			Leto.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Archer.TEXTUREARRAY, textureIndex);
		Archer.staticTextureIndexArray = new int[Archer.TEXTUREARRAY.length];
		for (int i = 0; i < Archer.TEXTUREARRAY.length; i++) {
			Archer.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Ground.TEXTUREARRAY, textureIndex);
		Ground.staticTextureIndexArray = new int[Ground.TEXTUREARRAY.length];
		for (int i = 0; i < Ground.TEXTUREARRAY.length; i++) {
			Ground.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Castle.TEXTUREARRAY, textureIndex);
		Castle.staticTextureIndexArray = new int[Castle.TEXTUREARRAY.length];
		for (int i = 0; i < Castle.TEXTUREARRAY.length; i++) {
			Castle.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, MobGeneric.TEXTUREARRAY, textureIndex);
		MobGeneric.staticTextureIndexArray = new int[MobGeneric.TEXTUREARRAY.length];
		for (int i = 0; i < MobGeneric.TEXTUREARRAY.length; i++) {
			MobGeneric.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, MobShield.TEXTUREARRAY, textureIndex);
		MobShield.staticTextureIndexArray = new int[MobShield.TEXTUREARRAY.length];
		for (int i = 0; i < MobShield.TEXTUREARRAY.length; i++) {
			MobShield.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, MobFast.TEXTUREARRAY, textureIndex);
		MobFast.staticTextureIndexArray = new int[MobFast.TEXTUREARRAY.length];
		for (int i = 0; i < MobFast.TEXTUREARRAY.length; i++) {
			MobFast.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, MobArcher.TEXTUREARRAY, textureIndex);
		MobArcher.staticTextureIndexArray = new int[MobArcher.TEXTUREARRAY.length];
		for (int i = 0; i < MobArcher.TEXTUREARRAY.length; i++) {
			MobArcher.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowGeneric.TEXTUREARRAY, textureIndex);
		ArrowGeneric.staticTextureIndexArray = new int[ArrowGeneric.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowGeneric.TEXTUREARRAY.length; i++) {
			ArrowGeneric.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowFire.TEXTUREARRAY, textureIndex);
		ArrowFire.staticTextureIndexArray = new int[ArrowFire.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowFire.TEXTUREARRAY.length; i++) {
			ArrowFire.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowLightning.TEXTUREARRAY, textureIndex);
		ArrowLightning.staticTextureIndexArray = new int[ArrowLightning.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowLightning.TEXTUREARRAY.length; i++) {
			ArrowLightning.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowIce.TEXTUREARRAY, textureIndex);
		ArrowIce.staticTextureIndexArray = new int[ArrowIce.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowIce.TEXTUREARRAY.length; i++) {
			ArrowIce.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowArcher.TEXTUREARRAY, textureIndex);
		ArrowArcher.staticTextureIndexArray = new int[ArrowArcher.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowArcher.TEXTUREARRAY.length; i++) {
			ArrowArcher.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ArrowMob.TEXTUREARRAY, textureIndex);
		ArrowMob.staticTextureIndexArray = new int[ArrowMob.TEXTUREARRAY.length];
		for (int i = 0; i < ArrowMob.TEXTUREARRAY.length; i++) {
			ArrowMob.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, EffectFire.TEXTUREARRAY, textureIndex);
		EffectFire.staticTextureIndexArray = new int[EffectFire.TEXTUREARRAY.length];
		for (int i = 0; i < EffectFire.TEXTUREARRAY.length; i++) {
			EffectFire.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, EffectLightning.TEXTUREARRAY, textureIndex);
		EffectLightning.staticTextureIndexArray = new int[EffectLightning.TEXTUREARRAY.length];
		for (int i = 0; i < EffectLightning.TEXTUREARRAY.length; i++) {
			EffectLightning.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, EffectLightningHit.TEXTUREARRAY, textureIndex);
		EffectLightningHit.staticTextureIndexArray = new int[EffectLightningHit.TEXTUREARRAY.length];
		for (int i = 0; i < EffectLightningHit.TEXTUREARRAY.length; i++) {
			EffectLightningHit.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, Button.TEXTUREARRAY, textureIndex);
		Button.staticTextureIndexArray = new int[Button.TEXTUREARRAY.length];
		for (int i = 0; i < Button.TEXTUREARRAY.length; i++) {
			Button.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ButtonArrow.TEXTUREARRAY, textureIndex);
		ButtonArrow.staticTextureIndexArray = new int[ButtonArrow.TEXTUREARRAY.length];
		for (int i = 0; i < ButtonArrow.TEXTUREARRAY.length; i++) {
			ButtonArrow.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		textures = loadClassTextures(gl, textures, ButtonUpgrade.TEXTUREARRAY, textureIndex);
		ButtonUpgrade.staticTextureIndexArray = new int[ButtonUpgrade.TEXTUREARRAY.length];
		for (int i = 0; i < ButtonUpgrade.TEXTUREARRAY.length; i++) {
			ButtonUpgrade.staticTextureIndexArray[i] = textureIndex;
			textureIndex++;
		}
		
		return textures;
	}
	
	/**
	 * Loads textures one class at a time
	 * @param gl
	 * @param textures
	 * @param textureArray
	 * @param textureIndexArray
	 * @param textureIndex
	 * @return
	 */
	public int[] loadClassTextures(GL10 gl, int[] textures, int[] textureArray, int textureIndex) {
		InputStream is;
		Bitmap bitmap;
		for (int i = 0; i < textureArray.length; i++) {
			is = context.getResources().openRawResource(textureArray[i]);
			bitmap = null;
			try {
				//BitmapFactory is an Android graphics utility for images
				bitmap = BitmapFactory.decodeStream(is);
			} finally {
				//Always clear and close
				try {
					is.close();
					is = null;
				} catch (IOException e) {
				}
			}
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureIndex]);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			bitmap.recycle();
			
			textureIndex++;
		}
		return textures;
	}
}
