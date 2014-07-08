package com.kerbii.undersiege;

public abstract class EffectBase extends GameObject {

	public int animationIndex;
	
	private static float initialVertices[] = {
			0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f
							};
	
	public EffectBase(float x, float y, float width, float height) {
		super(x, y, width, height, initialVertices);
	}
	
	public void clearEffect() {
		draw = false;
	}
}
