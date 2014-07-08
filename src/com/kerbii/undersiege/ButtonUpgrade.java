package com.kerbii.undersiege;

public class ButtonUpgrade extends ButtonBase{
	
	public int upgradeLevel, upgradeMax, textureNumber;

	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowgeneric,
		R.drawable.arrowlightning0,
		R.drawable.arrowice,
		R.drawable.arrowgeneric,
		R.drawable.arrowlightning0,
		R.drawable.arrowice,
		R.drawable.arrowgeneric,
		R.drawable.arrowlightning0,
		R.drawable.arrowice,
		R.drawable.arrowgeneric,
		R.drawable.arrowlightning0,
		R.drawable.arrowice
								};
	public static int[] staticTextureIndexArray;
	
	private static float vertices[] = {
			0.0f, 110.0f, 0.0f,
			600.0f, 110.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			600.0f, 0.0f, 0.0f
							};

	public ButtonUpgrade(int y, int textureNumber, int upgradeLevel) {
		super(-600, y, 1400, 110, -600, -500, y, y, RIGHT);
		alpha = 1;
		
		super.changeVertices(vertices);	

		this.upgradeLevel = upgradeLevel;
		upgradeMax = 12;
		xLeft = -600;
		xRight = -500 + upgradeLevel * (500 / upgradeMax);
		yTop = y;
		yBottom = y;
		
		this.textureNumber = textureNumber;
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[textureNumber];
		super.update(deltaTime);
	}
	
	public void upgrade(int level) {
		upgradeLevel = level;
		xRight = -500 + upgradeLevel*(500 / upgradeMax);
	}
}
