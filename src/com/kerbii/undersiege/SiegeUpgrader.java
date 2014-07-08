package com.kerbii.undersiege;


public class SiegeUpgrader {
	
	public SiegeUpgrader() {
		
	}
	
	public int upgrade(int upgradeID) {
		switch(upgradeID) {
		case MenuHandler.UPGRADEARROWFIRERATE:
			if (SiegeVariables.arrowFireRateLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowFireRateLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowFireRateLevel];
				SiegeVariables.arrowFireRateLevel++;
				SiegeVariables.arrowFireRate = 1200 - 100 * SiegeVariables.arrowFireRateLevel;
				return SiegeVariables.arrowFireRateLevel;
			}
			break;
		case MenuHandler.UPGRADEARROWSPEED:
			if (SiegeVariables.arrowFlightSpeedLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.arrowSpeed)[SiegeVariables.arrowFlightSpeedLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.arrowSpeed)[SiegeVariables.arrowFlightSpeedLevel];
				SiegeVariables.arrowFlightSpeedLevel++;
				SiegeVariables.arrowFlightSpeed = 8 + SiegeVariables.arrowFlightSpeedLevel;
				return SiegeVariables.arrowFlightSpeedLevel;
			}
			break;
		case MenuHandler.UPGRADEMANA:
			if (SiegeVariables.manaLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.manaLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.manaLevel];
				SiegeVariables.manaLevel++;
				SiegeVariables.manaMax = 100 + 50 * SiegeVariables.manaLevel;
				return SiegeVariables.manaLevel;
			}
			break;
		case MenuHandler.UPGRADEFIRE:
			if (SiegeVariables.arrowFireLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.arrowFire)[SiegeVariables.arrowFireLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.arrowFire)[SiegeVariables.arrowFireLevel];
				SiegeVariables.arrowFireLevel++;
				SiegeVariables.arrowFireRange = 80 + 40 * SiegeVariables.arrowFireLevel;
				SiegeVariables.arrowFireDamage = 0.5f + (0.3f * SiegeVariables.arrowFireLevel);
				SiegeVariables.arrowFireDuration = 4000 + 500 * SiegeVariables.arrowFireLevel; 
				return SiegeVariables.arrowFireLevel;
			}
			break;
		case MenuHandler.UPGRADELIGHTNING:
			if (SiegeVariables.arrowLightningLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.arrowLightning)[SiegeVariables.arrowLightningLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.arrowLightning)[SiegeVariables.arrowLightningLevel];
				SiegeVariables.arrowLightningLevel++;
				SiegeVariables.arrowLightningRange = 80 + 40 * SiegeVariables.arrowLightningLevel;
				SiegeVariables.arrowLightningDamage = 30 + 10 * SiegeVariables.arrowLightningLevel;
				SiegeVariables.arrowLightningLeaps = 2 + SiegeVariables.arrowLightningLevel;
				return SiegeVariables.arrowLightningLevel;
			}
			break;
		case MenuHandler.UPGRADEICE:
			if (SiegeVariables.arrowIceLevel < 12 && SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.arrowIce)[SiegeVariables.arrowIceLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.arrowIce)[SiegeVariables.arrowIceLevel];
				SiegeVariables.arrowIceLevel++;
				SiegeVariables.arrowIceRange = 120 + 40 * SiegeVariables.arrowIceLevel;
				SiegeVariables.arrowIceDamage = 1 + (0.1f * SiegeVariables.arrowIceLevel);
				SiegeVariables.arrowIceSlowFactor = 2.0f + ((float) 1 / 3) * SiegeVariables.arrowIceLevel;
				SiegeVariables.arrowIceDuration = 4000 + 500 * SiegeVariables.arrowIceLevel;
				return SiegeVariables.arrowIceLevel;
			}
			break;
		case MenuHandler.UPGRADEHEALTH:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.castleHealthMax)[SiegeVariables.castleHealthMaxLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.castleHealthMax)[SiegeVariables.castleHealthMaxLevel];
				SiegeVariables.castleHealthMaxLevel++;
				SiegeVariables.castleHealthMax = 500 + 100 * SiegeVariables.castleHealthMaxLevel;
				return SiegeVariables.castleHealthMaxLevel;
			}
			break;
		case MenuHandler.UPGRADEHEAL:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.manaLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.arrowFireRateLevel];
				SiegeVariables.manaLevel++;
				return SiegeVariables.manaLevel;
			}
			break;
		case MenuHandler.UPGRADEIDUNNO:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.manaLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.arrowFireRateLevel];
				SiegeVariables.manaLevel++;
				return SiegeVariables.manaLevel;
			}
			break;
		case MenuHandler.UPGRADEARCHERRATE:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.archerRate)[SiegeVariables.archerFireRateLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.archerRate)[SiegeVariables.archerFireRateLevel];
				SiegeVariables.archerFireRateLevel++;
				SiegeVariables.archerFireRate = 3000 - 200 * SiegeVariables.archerFireRateLevel;
				return SiegeVariables.archerFireRateLevel;
			}
			break;
		case MenuHandler.UPGRADEARCHERSPEED:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.archerSpeed)[SiegeVariables.archerFlightSpeedLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.archerSpeed)[SiegeVariables.archerFlightSpeedLevel];
				SiegeVariables.archerFlightSpeedLevel++;
				SiegeVariables.archerFlightSpeed = 8 + SiegeVariables.archerFlightSpeedLevel;
				return SiegeVariables.archerFlightSpeedLevel;
			}
			break;
		case MenuHandler.UPGRADEIDUNNO2:
			if (SiegeVariables.gold - SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.manaLevel] >= 0) {
				SiegeVariables.gold -= SiegeVariables.res.getIntArray(R.array.mana)[SiegeVariables.arrowFireRateLevel];
				SiegeVariables.manaLevel++;
				return SiegeVariables.manaLevel;
			}
			break;
		}
		return -1;
	}
}
