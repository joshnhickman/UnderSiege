package com.kerbii.undersiege;

public class Button extends ButtonBase {
	
	private int sprite;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.buttonstart,
		R.drawable.buttonreset,
		R.drawable.buttonquit,
		R.drawable.buttonresume,
		R.drawable.buttonmenu,
		R.drawable.buttoncontinue,
		R.drawable.buttonback,
		R.drawable.buttonready,
		R.drawable.buttongameover,
		R.drawable.buttonupgradehero,
		R.drawable.buttonupgradecastle,
		R.drawable.buttonupgradeherostats,
		R.drawable.buttonupgradearrows,
		R.drawable.buttonupgradecastlestats,
		R.drawable.buttonupgradearchers
										};
	
	public static int[] staticTextureIndexArray;

	public Button(float x, float y, float width, float height, float xLeft, float xRight,
			float yTop, float yBottom, int startState, int sprite) {
		super(x, y, width, height, xLeft, xRight, yTop, yBottom, startState);
		
		this.sprite = sprite;
//		textureIndex = staticTextureIndexArray[sprite];
	}
	
	public void update(long deltaTime) {
		textureIndex = staticTextureIndexArray[sprite];
		super.update(deltaTime);
	}
}