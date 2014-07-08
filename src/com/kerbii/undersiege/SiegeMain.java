package com.kerbii.undersiege;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * 
 * @author Josh Hickman
 *
 */
public class SiegeMain extends Activity {
	
	/** The OpenGL view */
	private GLSurfaceView glSurface;
	private SiegeRenderer siegeRenderer;
	/** The Game Updater */
	private Thread siegeUpdater;
	/** The Game Controller */
	private SiegeController siegeController;
	
    /**
     * Called when the activity is first created.
     * Initiate the OpenGL view and set renderer
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Make this activity fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Create an instance with this activity
        glSurface = new GLSurfaceView(this);
        
        //Set renderer
        siegeRenderer = new SiegeRenderer(this);
        glSurface.setRenderer(siegeRenderer);
        
        //Create the SiegeUpdater
        Display display = getWindowManager().getDefaultDisplay();
        siegeUpdater = new Thread(new SiegeUpdater());
        
        siegeController = new SiegeController(this, siegeUpdater, siegeRenderer, display.getWidth(), display.getHeight());
    	siegeUpdater.start();
        
        //Set the GLSurface as view to this activity
        setContentView(glSurface);
    }
    
    /**
     * Called when the activity is started.
     */
    @Override
    public void onStart() {
    	super.onStart();
    }
    
    /**
     * Called when the activity is resumed.
     * Resume the OpenGL view
     */
    @Override
    public void onResume() {
    	super.onResume();
    	siegeController.onResume();
    	glSurface.onResume();
    }
    
    /**
     * Called when the activity is paused.
     * Pause the OpenGL view
     */
    @Override
    public void onPause() {
    	super.onPause();
    	siegeController.onPause();
    	glSurface.onPause();
    }
    
    /**
     * Called when the activity is stopped.
     */
    @Override
    public void onStop() {
    	super.onStop();
    }
    
    /**
     * Called when the activity is destroyed
     */
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	siegeUpdater = null;
    	SiegeVariables.siegeUpdater = siegeUpdater;
    }
    
    /**
     * Called to destroy the activity
     */
    public void quit() {
    	stopThread(siegeUpdater);
    	finish();
//    	System.exit(0);
    }
    
    /** 
     * Called when a button is pressed
     * 
     * Overrides for menu
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
    	return siegeController.doKeyDown(keyCode, msg);
    }
	
	/**
	 * Override touch screen listener
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return siegeController.doTouchEvent(event);
	}
	
	private synchronized void stopThread(Thread thread) {
		thread.interrupt();
//		if (thread != null) {
//			Thread moribund = thread;
//			thread = null;
//			moribund.interrupt();
//		}
	}
}