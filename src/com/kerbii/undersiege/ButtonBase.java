package com.kerbii.undersiege;

public class ButtonBase extends GameObject {
	
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, FADEIN = 4, FADEOUT = 5;
	public int state;
	
	public float yTop, yBottom, xLeft, xRight;
	
	public float speedX;
	public float speedY;
	
	public boolean active;
	
	//200 width square
	private static float vertices[] = {
			0.0f, 200.0f, 0.0f,
			200.0f, 200.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			200.0f, 0.0f, 0.0f
								};
	
	private int initialState;
	
	public ButtonBase(float x, float y) {
		this(x, y, 200, 200, x, x, y, y, FADEIN);
	}
	
	public ButtonBase(float x, float y, float width, float height, float xLeft, float xRight,
			float yTop, float yBottom, int startState) {
		super(x, y, width, height, vertices);

		vertices[1] = height;
		vertices[3] = width;
		vertices[4] = height;
		vertices[9] = width;
		super.changeVertices(vertices);
		
		this.xLeft = xLeft;
		this.xRight = xRight;
		this.yTop = yTop;
		this.yBottom = yBottom;
		
		initialState = startState;
		
		speedX = 10 / (float) 16;
		speedY = 10 / (float) 16;
		active = false;
		draw = false;
		state = -1;
		
		if (startState == FADEIN) alpha = 0;
	}
	
	public void update(long deltaTime) {
		if (active) {
			draw = true;
			state = initialState;
		} else {
			switch (initialState) {
			case RIGHT:
				state = LEFT;
				if (x == xLeft) draw = false;
				break;
			case LEFT:
				state = RIGHT;
				if (x == xRight) draw = false;
				break;
			case UP:
				state = DOWN;
				if (y == yBottom) draw = false;
				break;
			case DOWN:
				state = UP;
				if (y == yTop) draw = false;
				break;
			case FADEIN:
				state = FADEOUT;
				if (alpha == 0) draw = false;
				break;
			}
		}
		
		if (draw == true) {
			if (state == UP) {
				if (y > yTop) {
					y -= speedY * deltaTime;
					if (y < yTop) y = yTop;
				}
			} else if (state == DOWN) {
				if (y < yBottom) {
					y += speedY * deltaTime;
					if (y > yBottom) y = yBottom;
				}
			} else if (state == LEFT) {
				if (x > xLeft) {
					x -= speedX * deltaTime;
					if (x < xLeft) x = xLeft;
				}
			} else if (state == RIGHT) {
				if (x < xRight) {
					x += speedX * deltaTime;
					if (x > xRight) x = xRight;
				}
			} else if (state == FADEIN) {
				if (alpha < 1) {
					alpha += (0.1 / (float) 16) * deltaTime;
					if (alpha > 1) alpha = 1;
				}
			} else if (state == FADEOUT) {
				if (alpha > 0) {
					alpha -= (0.1 / (float) 16) * deltaTime;
					if (alpha < 0) alpha = 0;
				}
			}
		}
	}
}
