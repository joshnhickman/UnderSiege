package com.kerbii.undersiege;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;

/**
 * Handles rendering
 * 
 * @author Josh
 *
 */
public class SiegeRenderer implements Renderer {
		
	public boolean loaded;
	
	private Context context;
	private int[] textures;
	
	public SiegeRenderer(Context context) {
		this.context = context;
		
		loaded = false;
		
		textures = new int[6];
	}
	
	/**
	 * Called when the surface is created
	 * 
	 * Load textures, enable features
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		//Load textures
		TextureLoader textureLoader = new TextureLoader(context);
		textures = textureLoader.loadTextures(gl);
		
		SiegeVariables.isLoaded = true;
		loaded = true;
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glEnable(GL10.GL_BLEND);					//Enables blending
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);	//Sets alpha channel for blending
	}
	
	/**
	 * Called when the surface is changed
	 * Usually from rotating the screen
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		gl.glOrthof(0, 800.0f, 480.0f, 0, -1.0f, 1.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix		
	}
	
	/**
	 * loops to draw
	 * 
	 * calls each renderable object to render
	 */
	public void onDrawFrame(GL10 gl) {
		final long time = SystemClock.uptimeMillis();
		
		//Clear screen and depth buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glFrontFace(GL10.GL_CW);

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, -0.5f);		//Move into the screen
		SiegeVariables.ground.draw(gl, textures);
		gl.glPopMatrix();
		
		synchronized (SiegeVariables.effectsGround) {
			for (int i = 0; i < SiegeVariables.effectsGround.size(); i++) {
				GameObject next = SiegeVariables.effectsGround.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
			}
		}
		
		SiegeVariables.castle.draw(gl, textures);
		
		synchronized (SiegeVariables.mobsDead) {
			for (int i = 0; i < SiegeVariables.mobsDead.size(); i++) {
				GameObject next = SiegeVariables.mobsDead.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
			}
		}
		
		synchronized (SiegeVariables.arrows) {
			for (int i = 0; i < SiegeVariables.arrows.size(); i++) {
				GameObject next = SiegeVariables.arrows.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
			}
		}
		
		synchronized (SiegeVariables.mobs) {
			for (int i = 0; i < SiegeVariables.mobs.size(); i++) {
				GameObject next = SiegeVariables.mobs.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
				
				//Remove mobs here so that they don't flicker when they die
				MobBase mob = (MobBase) next;
				if (!mob.alive) {
					SiegeVariables.mobs.remove(i);
					i--;
					SiegeVariables.mobsDead.add(mob);
				}
			}
		}
		
		synchronized (SiegeVariables.effectsAir) {
			for (int i = 0; i < SiegeVariables.effectsAir.size(); i++) {
				GameObject next = SiegeVariables.effectsAir.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
			}
		}
		
		synchronized (SiegeVariables.archers) {
			for (int i = 0; i < SiegeVariables.archers.size(); i++) {
				GameObject next = SiegeVariables.archers.get(i);
				gl.glPushMatrix();
				gl.glTranslatef(next.x, next.y, 0.0f);
				gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
				next.draw(gl, textures);
				gl.glPopMatrix();
			}
		}
		
		synchronized (SiegeVariables.menus) {
			for (int i = 0; i < SiegeVariables.menus.size(); i++) {
				GameObject next = SiegeVariables.menus.get(i);
				if (next.draw) {
					gl.glPushMatrix();
					gl.glTranslatef(next.x, next.y, 0.0f);
					gl.glRotatef(next.angle, 0.0f, 0.0f, 1.0f);
					next.draw(gl, textures);
					gl.glPopMatrix();
				}
			}
		}
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		final long endTime = SystemClock.uptimeMillis() - time;
		if (endTime < 16) {
            try {
                Thread.sleep(16 - endTime);
            } catch (InterruptedException e) {
                // Interruptions here are no big deal.
            }
		}
	}
}
