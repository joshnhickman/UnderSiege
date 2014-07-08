package com.kerbii.undersiege;

import javax.microedition.khronos.opengles.GL10;

import android.os.SystemClock;

public class Archer extends GameObject {
	
	public static final int READY = 0, DRAWING = 1;
	public int state;
	
	private long arrowLastFired;
	
	private long millisPerFrame;
	private long millisToHold;
	private long millisSinceLastFrame;
	
	public boolean active;
	
	private static final float RANGE = 300, SCOPE = 200;

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
	
	private MobBase target;
	
	private static float vertices[] = {
		-50.0f, 50.0f, 0.0f,
		50.0f, 50.0f, 0.0f,
		-50.0f, -50.0f, 0.0f,
		50.f, -50.f, 0.0f
							};
	
	public Archer(float x, float y) {
		super(x, y, 100, 100, vertices);
//		textureIndex = staticTextureIndexArray[6];
		millisPerFrame = 30;
		millisToHold = 100;
		state = DRAWING;
		
		active = false;
		
		arrowLastFired = SystemClock.uptimeMillis();
	}
	
	public void update(long deltaTime) {
		if (active) {
			if (target == null) {
				ArrayList<MobBase> mobList = SiegeVariables.quadTree.query(x, y - (SCOPE / 2), RANGE, SCOPE);
				if (!mobList.isEmpty()) {
					for (int i = 0; i < mobList.size(); i++) {
						target = mobList.get(i);
						if (target.x < x + RANGE && target.y > y - (SCOPE / 2) && target.y + target.width < y + (SCOPE / 2)) {
							break;
						} else {
							target = null;
						}
					}
				}
			}
			
			if (target != null) {
				rotate(target.x + target.width / 2, target.y + target.width / 2);
				if (SystemClock.uptimeMillis() - arrowLastFired > SiegeVariables.archerFireRate) {
					arrowLastFired = SystemClock.uptimeMillis() - 500 + SiegeVariables.r.nextInt(1000);
					SiegeVariables.arrowFactory.shoot(x, y, target.x + target.width / 2, target.y + target.width / 2, SiegeVariables.archerFlightSpeed, SiegeVariables.ARCHER);
					fire();
				}
				if (!target.alive) {
					target = null;
				}
			}
			
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
	}

	public void draw(GL10 gl, int textures[]) {
		if (active) {
			super.draw(gl, textures);
		}
	}
	
	public void rotate(float xTarget, float yTarget) {
		float xChange = xTarget - x;
		float yChange = yTarget - y;
		angle = (float) (Math.atan(yChange / xChange) * (180 / Math.PI));
	}
	
	public void fire() {
		if (active) {
			state = DRAWING;
			animationIndex = 6;
		}
	}
	
	public void reset() {
		target = null;
	}
}
