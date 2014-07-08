package com.kerbii.undersiege;

public class Icon extends ButtonBase {

	private int sprite;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.gold,
		R.drawable.iconlevel,
		R.drawable.iconstage,
		R.drawable.upgradeherostatsbackground,
		R.drawable.upgradearrowsbackground,
		R.drawable.upgradecastlestatsbackground,
		R.drawable.upgradearchersbackground
												};
	
	public static int[] staticTextureIndexArray;
	
	public Icon(float x, float y, float width, float height, float xLeft, float xRight,
			float yTop, float yBottom, int startState, int sprite) {
		super(x, y, width, height, xLeft, xRight, yTop, yBottom, startState);
		
		this.sprite = sprite;
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[sprite];
		super.update(deltaTime);
	}
}
