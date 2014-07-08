package com.kerbii.undersiege;

import android.os.SystemClock;

public class ArrowFactory {
	
	public int arrowType;
	
	public long letoArrowLastFired;
	
	public static float letoArrowFlightSpeed;
	
	public ArrowFactory() {
		arrowType = SiegeVariables.GENERIC;
		letoArrowLastFired = 0;
	}
	
	public void update(long deltaTime) {
		
	}
	
	public void shoot(float x, float y) {
		if (SystemClock.uptimeMillis() - letoArrowLastFired > SiegeVariables.arrowFireRate) {
			if (arrowType == SiegeVariables.GENERIC) {
				letoArrowLastFired = SystemClock.uptimeMillis();
				shoot(SiegeVariables.leto.x, SiegeVariables.leto.y - 5, x, y, SiegeVariables.arrowFlightSpeed, SiegeVariables.GENERIC);
				SiegeVariables.leto.fire();
			} else if (arrowType == SiegeVariables.FIRE && SiegeVariables.mana >= SiegeVariables.arrowFireManaCost) {
				SiegeVariables.mana -= SiegeVariables.arrowFireManaCost;
				letoArrowLastFired = SystemClock.uptimeMillis();
				shoot(SiegeVariables.leto.x, SiegeVariables.leto.y - 5, x, y, SiegeVariables.arrowFlightSpeed, SiegeVariables.FIRE);
				SiegeVariables.leto.fire();
			} else if (arrowType == SiegeVariables.LIGHTNING && SiegeVariables.mana >= SiegeVariables.arrowLightningManaCost) {
				SiegeVariables.mana -= SiegeVariables.arrowIceManaCost;
				letoArrowLastFired = SystemClock.uptimeMillis();
				shoot(SiegeVariables.leto.x, SiegeVariables.leto.y - 5, x, y, SiegeVariables.arrowFlightSpeed, SiegeVariables.LIGHTNING);
				SiegeVariables.leto.fire();
			} else if (arrowType == SiegeVariables.ICE && SiegeVariables.mana >= SiegeVariables.arrowIceManaCost) {
				SiegeVariables.mana -= SiegeVariables.arrowIceManaCost;
				letoArrowLastFired = SystemClock.uptimeMillis();
				shoot(SiegeVariables.leto.x, SiegeVariables.leto.y - 5, x, y, SiegeVariables.arrowFlightSpeed, SiegeVariables.ICE);
				SiegeVariables.leto.fire();
			}
		}
	}
	
	public void shoot(float x, float y, float xTarget, float yTarget, float flightSpeed, int arrowType) {
		ArrowBase arrow;
		if (arrowType == SiegeVariables.GENERIC) {
			arrow = new ArrowGeneric(x, y, xTarget, yTarget, flightSpeed);
		} else if (arrowType == SiegeVariables.FIRE) {
			arrow = new ArrowFire(x, y, xTarget, yTarget, flightSpeed);
		} else if (arrowType == SiegeVariables.LIGHTNING) {
			arrow = new ArrowLightning(x, y, xTarget, yTarget, flightSpeed);
		} else if (arrowType == SiegeVariables.ICE) {
			arrow = new ArrowIce(x, y, xTarget, yTarget, flightSpeed);
		} else if (arrowType == SiegeVariables.ARCHER) {
			arrow = new ArrowArcher(x, y, xTarget, yTarget, flightSpeed);
		} else { //arrow == MOB
			arrow = new ArrowMob(x, y, xTarget, yTarget, flightSpeed);
		}
		SiegeVariables.quadTree.add(arrow);
		SiegeVariables.arrows.add(arrow);
	}
}