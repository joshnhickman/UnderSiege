package com.kerbii.undersiege;

public class MobShield extends MobBase {
	
	float shieldHealth;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.mobgenerica0,
		R.drawable.mobgenerica1,
		R.drawable.mobgenerica2,
		R.drawable.mobgenerica3,
		R.drawable.mobgenerica4,
		R.drawable.mobgenerica5,
		R.drawable.mobgenerica6,
		R.drawable.mobgenericd0,
		R.drawable.mobgenericd1,
		R.drawable.mobgenericd2,
		R.drawable.mobgenericd3,
		R.drawable.mobgenericd4,
		R.drawable.mobgenericd5,
		R.drawable.mobgenericd6,
		R.drawable.mobgenericd7,
		R.drawable.mobgenericd8,
		R.drawable.genericattack0,
		R.drawable.genericattack1,
		R.drawable.genericattack2,
		R.drawable.genericattack3,
		R.drawable.genericattack4,
		R.drawable.genericattack5
								};
	
	public static int[] staticTextureIndexArray;
	
	public MobShield(float x, float y, float health, float shieldHealth) {
		super(x, y, health);
		this.shieldHealth = shieldHealth;
		goldMult = 2;
	}
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		textureIndex = staticTextureIndexArray[animationIndex];
	}
	
	public float health() {
		if (shieldHealth > 0) return shieldHealth;
		return super.health();
	}
	
	public boolean special() {
		if (shieldHealth > 0) return true;
		return super.special();
	}
	
	public void hit(ArrowBase arrow, float damage) {
		if (shieldHealth > 0) {
			if (arrow.arrowType == SiegeVariables.FIRE || arrow.arrowType == SiegeVariables.LIGHTNING) {
				hitShield(arrow, shieldHealth);
				arrow.state = ArrowBase.BROKEN;
			} else if (arrow.angle == 0) {
				hitShield(arrow, damage);
			} else if (arrow.angle <= 0) {
				if (arrow.y + (arrow.x - x) * Math.tan(-arrow.angle * (Math.PI / 180)) < y + width) {
					if (arrow.x < x + width / 4) {
						hitShield(arrow, damage);
					}
				} else {
					super.hit(arrow, damage);
				}
			} else {
				if (arrow.y - (arrow.x - x) * Math.tan(arrow.angle * (Math.PI / 180)) > y) {
					if (arrow.x < x + width / 4) {
						hitShield(arrow, damage);
					}
				} else {
					super.hit(arrow, damage);
				}
			}
		} else {
			super.hit(arrow, damage);
		}
	}
	
	private void hitShield(ArrowBase arrow, float damage) {
		shieldHealth -= damage;
		if (shieldHealth > 0) {
			arrow.embed(this);
			arrow.x = x + width / 4;
		}
	}
}
