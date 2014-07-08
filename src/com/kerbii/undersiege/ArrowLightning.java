package com.kerbii.undersiege;

public class ArrowLightning extends ArrowBase {
	
	public static long timeBetweenStrikes = 50;
	public long timeSinceLastStrike;
	private MobBase lastStruck;
	
	private int leaps;
	
	public static final int[] TEXTUREARRAY = {
		R.drawable.arrowlightning0,
		R.drawable.arrowlightning1,
		R.drawable.arrowlightning2
									};
	public static int[] staticTextureIndexArray;
	static int[] animationArray;
	
	public ArrowLightning(float x, float y, float xTarget, float yTarget, float speed) {
		super(x, y, xTarget, yTarget, speed, animationArray, SiegeVariables.arrowLightningRange, SiegeVariables.arrowLightningDamage);
		
		leaps = SiegeVariables.arrowLightningLeaps;
		
		arrowType = SiegeVariables.LIGHTNING;
	}
	
	public void update(long deltaTime) {
		
		textureIndex = staticTextureIndexArray[0];
		
		super.update(deltaTime);
		
		if (state == EFFECT) {
			timeSinceLastStrike += deltaTime;
		}
	}
	
	public void hit(MobBase mob, float health) {
		if (state == FLYING) {
			effectCenterX = mob.x + (mob.width / 2);
			effectCenterY = mob.y + (mob.height / 2);
			SiegeVariables.effectsAir.add(new EffectLightningHit(mob.x, mob.y));
			
			width = SiegeVariables.arrowLightningRange;
			height = SiegeVariables.arrowLightningRange;
			x = effectCenterX - (width / 2);
			y = effectCenterY - (height / 2);
			
			lastStruck = mob;
			
			state = EFFECT;
			
			float[] vertices = {
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, 0.0f
									};
			changeVertices(vertices);
		} else if (state == EFFECT) {
			if (leaps > 0) {
				timeSinceLastStrike = 0;
				float effectTargetX = mob.x + (mob.width / 2);
				float effectTargetY = mob.y + (mob.height / 2);
				EffectBase lightning = new EffectLightning(effectCenterX, effectCenterY, effectTargetX, effectTargetY);
				SiegeVariables.effectsGround.add(lightning);
				SiegeVariables.effectsAir.add(new EffectLightningHit(mob.x, mob.y));
				
				effectCenterX = effectTargetX;
				effectCenterY = effectTargetY;
				x = effectCenterX - (width / 2);
				y = effectCenterY - (height / 2);
				
				leaps--;
				if (leaps <= 0) {
					state = BROKEN;
				}
				
				lastStruck = mob;
			}
		}
	}
	
	public ArrayList<MobBase> hitMobs(ArrayList<MobBase> mobsInRange) {
		if (timeSinceLastStrike  >= timeBetweenStrikes) {
			if (mobsInRange.isEmpty()) {
				state = BROKEN;
				return super.hitMobs(mobsInRange);
			} else if (state == EFFECT) {
				MobBase closestMob = null;
				float closestDistance = 800;
//				closestMob = mobsInRange.get(0);
//				closestDistance = distance(closestMob);
				MobBase next;
				for (int i = 0; i < mobsInRange.size(); i++) {
					next = mobsInRange.get(i);
					if (lastStruck != next) {
						if (distance(next) < closestDistance) {
							closestMob = next;
							closestDistance = distance(closestMob);
						}
					}
				}
				mobsInRange.removeAll();
				if (closestMob == null) {
					state = BROKEN;
				} else mobsInRange.add(closestMob);
				return mobsInRange;
			} else {
				return super.hitMobs(mobsInRange);
			}
		} else {
			return super.hitMobs(mobsInRange);
		}
	}
	
	public float damage() {
		if (leaps <= 0) {
			return 0;
		} else return super.damage();
	}
}
