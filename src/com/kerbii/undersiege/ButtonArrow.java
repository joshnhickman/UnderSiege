package com.kerbii.undersiege;

public class ButtonArrow extends ButtonBase {
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgenericmenu,
		R.drawable.arrowgenericmenu,
		R.drawable.arrowlightningmenu,
		R.drawable.arrowgenericmenu
									};
	public static int[] staticTextureIndexArray;
	
	private static float vertices[] = {
			0.0f, 100.0f, 0.0f,
			12.5f, 100.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			12.5f, 0.0f, 0.0f
								};

	public ButtonArrow(float x, float y) {
		super(x, y, 100, 100, x, x, y, y, FADEIN);
		
		super.changeVertices(vertices);
		
		alpha = 0;
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[0];
		super.update(deltaTime);
	}
}