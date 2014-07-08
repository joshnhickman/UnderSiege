package com.kerbii.undersiege;


public class MenuHandler {
	
	/**
	 * Specific state
	 */
	public static final int BASE = 0, HERO = 1, HEROSTATS = 2, ARROWS = 3, CASTLE = 4, CASTLESTATS = 5, ARCHERS = 6;
	private int specState;
	
	/**
	 * Button Codes
	 */
	public static final int NONE = -1, START = 0, RESET = 1, QUIT = 2, RESUME = 3, MENU = 4,
			READY = 5, RESTART = 6, GENERIC = 7, FIRE = 8, LIGHTNING = 9, ICE = 10;
	public static final int BACK = 0, CONTINUE = 1, UPGRADEHERO = 2, UPGRADECASTLE = 3, UPGRADEHEROSTATS = 4,
			UPGRADEARROWS = 5, UPGRADECASTLESTATS = 6, UPGRADEARCHERS = 7, UPGRADEARROWFIRERATE = 8,
			UPGRADEARROWSPEED = 9, UPGRADEMANA = 10, UPGRADEFIRE = 11, UPGRADELIGHTNING = 12, UPGRADEICE = 13,
			UPGRADEHEALTH = 14, UPGRADEHEAL = 15, UPGRADEIDUNNO = 16, UPGRADEARCHERRATE = 17,
			UPGRADEARCHERSPEED = 18, UPGRADEIDUNNO2 = 19;
	
	private Button buttonStart;
	private Button buttonReset;
	private Button buttonQuit;
	private Button buttonResume;
	private Button buttonMenu;
	private Button buttonReady;
	private Button buttonRestart;
	private ButtonArrow buttonGeneric;
	private ButtonArrow buttonFire;
	private ButtonArrow buttonLightning;
	private ButtonArrow buttonIce;
	private Button buttonUpgradeBack;
	private Button buttonUpgradeContinue;
	private Button buttonUpgradeHero;
	private Button buttonUpgradeCastle;
	private Button buttonUpgradeHeroStats;
	private Button buttonUpgradeArrows;
	private Button buttonUpgradeCastleStats;
	private Button buttonUpgradeArchers;
	
	private Icon heroBackground;
	private Icon arrowBackground;
	private Icon castleBackground;
	private Icon archerBackground;
	
	private ButtonUpgrade buttonUpgradeArrowFireRate;
	private ButtonUpgrade buttonUpgradeArrowSpeed;
	private ButtonUpgrade buttonUpgradeMana;
	private ButtonUpgrade buttonUpgradeFire;
	private ButtonUpgrade buttonUpgradeLightning;
	private ButtonUpgrade buttonUpgradeIce;
	private ButtonUpgrade buttonUpgradeHealth;
	private ButtonUpgrade buttonUpgradeHeal;
	private ButtonUpgrade buttonUpgradeIDunno;
	private ButtonUpgrade buttonUpgradeArcherRate;
	private ButtonUpgrade buttonUpgradeArcherSpeed;
	private ButtonUpgrade buttonUpgradeIDunno2;
	
	public TextObject goldCost0;
	public TextObject goldCost1;
	public TextObject goldCost2;
	
	public TextObject goldCounter;
	public Icon goldIcon;
	public Icon levelIcon;
	public TextObject levelCounter;
	public Icon stageIcon;
	public TextObject stageCounter;

	public MenuHandler() {
		specState = BASE;
	}
	
	public void load() {
		ArrayList<GameObject> menus = SiegeVariables.menus;
		
		buttonStart = new Button(800,50,200,100,580,800,50,50,ButtonBase.LEFT,0);
		menus.add(buttonStart);
		buttonReset = new Button(800,190,200,100,580,800,190,190,ButtonBase.LEFT,1);
		menus.add(buttonReset);
		buttonQuit = new Button(800,330,200,100,580,800,330,330,ButtonBase.LEFT,2);
		menus.add(buttonQuit);
		buttonResume = new Button(150,-200,200,200,150,150,-200,12,ButtonBase.DOWN,3);
		menus.add(buttonResume);
		buttonMenu = new Button(150,480,200,200,150,150,267,480,ButtonBase.UP,4);
		menus.add(buttonMenu);
		buttonReady = new Button(150,150,400,200,150,150,150,150,ButtonBase.FADEIN,7);
		menus.add(buttonReady);
		buttonRestart = new Button(150,150,400,200,150,150,150,150,ButtonBase.FADEIN,8);
		menus.add(buttonRestart);
		buttonGeneric = new ButtonArrow(150,16);
		menus.add(buttonGeneric);
		buttonFire = new ButtonArrow(150,132);
		menus.add(buttonFire);
		buttonLightning = new ButtonArrow(150,248);
		menus.add(buttonLightning);
		buttonIce = new ButtonArrow(150,360);
		menus.add(buttonIce);
		buttonUpgradeBack = new Button(250,480,100,50,250,250,420,480,ButtonBase.UP,6);
		menus.add(buttonUpgradeBack);
		buttonUpgradeContinue = new Button(450,480,100,50,450,450,420,480,ButtonBase.UP,5);
		menus.add(buttonUpgradeContinue);
		buttonUpgradeHero = new Button(-350,50,350,350,-350,30,50,50,ButtonBase.RIGHT,9);
		menus.add(buttonUpgradeHero);
		buttonUpgradeCastle = new Button(800,50,350,350,420,800,50,50,ButtonBase.LEFT,10);
		menus.add(buttonUpgradeCastle);
		buttonUpgradeHeroStats = new Button(-350,50,350,350,-350,30,50,50,ButtonBase.RIGHT,11);
		menus.add(buttonUpgradeHeroStats);
		buttonUpgradeArrows = new Button(800,50,350,350,420,800,50,50,ButtonBase.LEFT,12);
		menus.add(buttonUpgradeArrows);
		buttonUpgradeCastleStats = new Button(-350,50,350,350,-350,30,50,50,ButtonBase.RIGHT,13);
		menus.add(buttonUpgradeCastleStats);
		buttonUpgradeArchers = new Button(800,50,350,350,420,800,50,50,ButtonBase.LEFT,14);
		menus.add(buttonUpgradeArchers);
		
		//Upgrade Backgrounds
		heroBackground = new Icon(0,50,600,350,0,0,100,100,ButtonBase.FADEIN,3);
		menus.add(heroBackground);
		arrowBackground = new Icon(0,50,600,350,0,0,100,100,ButtonBase.FADEIN,4);
		menus.add(arrowBackground);
		castleBackground = new Icon(0,50,600,350,0,0,100,100,ButtonBase.FADEIN,5);
		menus.add(castleBackground);
		archerBackground = new Icon(0,50,600,350,0,0,100,100,ButtonBase.FADEIN,6);
		menus.add(archerBackground);
		
		//Upgrade Buttons
		buttonUpgradeArrowFireRate = new ButtonUpgrade(50, 0, SiegeVariables.arrowFireRateLevel);
		menus.add(buttonUpgradeArrowFireRate);
		buttonUpgradeArrowSpeed = new ButtonUpgrade(170, 1, SiegeVariables.arrowFlightSpeedLevel);
		menus.add(buttonUpgradeArrowSpeed);
		buttonUpgradeMana = new ButtonUpgrade(290, 2, SiegeVariables.manaLevel);
		menus.add(buttonUpgradeMana);
		
		buttonUpgradeFire = new ButtonUpgrade(50, 3, SiegeVariables.arrowFireLevel);
		menus.add(buttonUpgradeFire);
		buttonUpgradeLightning = new ButtonUpgrade(170, 4, SiegeVariables.arrowLightningLevel);
		menus.add(buttonUpgradeLightning);
		buttonUpgradeIce = new ButtonUpgrade(290, 5, SiegeVariables.arrowIceLevel);
		menus.add(buttonUpgradeIce);
		
		buttonUpgradeHealth = new ButtonUpgrade(50, 6, SiegeVariables.castleHealthMaxLevel);
		menus.add(buttonUpgradeHealth);
		buttonUpgradeHeal = new ButtonUpgrade(170, 7, SiegeVariables.castleHealthMaxLevel);
		menus.add(buttonUpgradeHeal);
		buttonUpgradeIDunno = new ButtonUpgrade(290, 8, 1);
		menus.add(buttonUpgradeMana);
		
		buttonUpgradeArcherRate = new ButtonUpgrade(50, 9, SiegeVariables.archerFireRateLevel);
		menus.add(buttonUpgradeArcherRate);
		buttonUpgradeArcherSpeed = new ButtonUpgrade(170, 10, SiegeVariables.archerFlightSpeedLevel);
		menus.add(buttonUpgradeArcherSpeed);
		buttonUpgradeIDunno2 = new ButtonUpgrade(290, 11, 1);
		menus.add(buttonUpgradeMana);
		
		//Gold cost
		goldCost0 = new TextObject(800,80,25,50,600,800,80,80,ButtonBase.LEFT);
		menus.add(goldCost0);
		goldCost1 = new TextObject(800,200,25,50,600,800,200,200,ButtonBase.LEFT);
		menus.add(goldCost1);
		goldCost2 = new TextObject(800,320,25,50,600,800,320,320,ButtonBase.LEFT);
		menus.add(goldCost2);
		
		//Gold displays
		goldCounter = new TextObject(50,0,25,50,50,50,0,0,ButtonBase.FADEIN);
		menus.add(goldCounter);
		goldIcon = new Icon(0,0,50,50,0,0,-100,0,ButtonBase.DOWN,0);
		menus.add(goldIcon);
		levelIcon = new Icon(0,0,100,50,-100,120,50,50,ButtonBase.RIGHT,1);
		menus.add(levelIcon);
		levelCounter = new TextObject(220,0,25,50,220,220,0,0,ButtonBase.FADEIN);
		menus.add(levelCounter);
		stageIcon = new Icon(0,50,100,50,-100,120,50,50,ButtonBase.RIGHT,2);
		menus.add(stageIcon);
		stageCounter = new TextObject(220,50,25,50,220,220,50,50,ButtonBase.FADEIN);
		menus.add(stageCounter);
	}
	
	public void update(long deltaTime) {
        switch(SiegeVariables.state) {
        case SiegeVariables.STARTING:
        	
        	break;
        case SiegeVariables.PLAYING:
    		if (SiegeVariables.arrowMenu) {
    			buttonGeneric.active = true;
    			if (SiegeVariables.arrowFireLevel > 0) buttonFire.active = true;
    			if (SiegeVariables.arrowLightningLevel > 0) buttonLightning.active = true;
    			if (SiegeVariables.arrowIceLevel > 0) buttonIce.active = true;
    		} else {
    			buttonGeneric.active = false;
    			buttonFire.active = false;
    			buttonLightning.active = false;
    			buttonIce.active = false;
    		}
        	break;
        case SiegeVariables.UPGRADING:
    		
    		setLevels();
    		goldCounter.newInt = SiegeVariables.gold;
    		
        	switch(specState) {
        	case BASE:
        		if (buttonUpgradeHeroStats.draw == false && buttonUpgradeArrows.draw == false &&
        				buttonUpgradeCastleStats.draw == false && buttonUpgradeArchers.draw == false) {
        			buttonUpgradeHero.active = true;
        			buttonUpgradeCastle.active = true;
        		}
        			
        		break;
        	case HERO:
        		if (buttonUpgradeHero.draw == false && buttonUpgradeCastle.draw == false &&
        				buttonUpgradeArrowFireRate.draw == false && buttonUpgradeArrowSpeed.draw == false &&
        				buttonUpgradeMana.draw == false && buttonUpgradeFire.draw == false &&
        				buttonUpgradeLightning.draw == false && buttonUpgradeIce.draw == false &&
        				goldCost0.draw == false) {
        			buttonUpgradeHeroStats.active = true;
        			buttonUpgradeArrows.active = true;
        		}
        		break;
        	case HEROSTATS:
    			goldCost0.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowFireRateLevel];
    			goldCost1.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowFlightSpeedLevel];
    			goldCost2.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.manaLevel];
    			
        		if (buttonUpgradeHeroStats.draw == false && buttonUpgradeArrows.draw == false) {
        			buttonUpgradeArrowFireRate.active = true;
        			buttonUpgradeArrowSpeed.active = true;
        			buttonUpgradeMana.active = true;
        			heroBackground.active = true;
        			goldCost0.active = true;
        			goldCost1.active = true;
        			goldCost2.active = true;
        		}
        		break;
        	case ARROWS:
    			goldCost0.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowFireLevel];
    			goldCost1.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowLightningLevel];
    			goldCost2.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.arrowIceLevel];
    			
        		if (buttonUpgradeHeroStats.draw == false && buttonUpgradeArrows.draw == false) {
        			buttonUpgradeFire.active = true;
        			buttonUpgradeLightning.active = true;
        			buttonUpgradeIce.active = true;
        			arrowBackground.active = true;
        			goldCost0.active = true;
        			goldCost1.active = true;
        			goldCost2.active = true;
        		}
        		break;
        	case CASTLE:
        		if (buttonUpgradeHero.draw == false && buttonUpgradeCastle.draw == false &&
        				buttonUpgradeHealth.draw == false && buttonUpgradeHeal.draw == false &&
        				buttonUpgradeIDunno.draw == false && buttonUpgradeArcherRate.draw == false &&
        				buttonUpgradeArcherSpeed.draw == false && buttonUpgradeIDunno2.draw == false &&
        				goldCost0.draw == false) {
        			buttonUpgradeCastleStats.active = true;
        			buttonUpgradeArchers.active = true;
        		}
        		break;
        	case CASTLESTATS:
    			goldCost0.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.castleHealthMaxLevel];
    			goldCost1.newInt = 0;
    			goldCost2.newInt = 0;
    			
        		if (buttonUpgradeCastleStats.draw == false && buttonUpgradeArchers.draw == false) {
        			buttonUpgradeHealth.active = true;
        			buttonUpgradeHeal.active = true;
        			buttonUpgradeIDunno.active = true;
        			castleBackground.active = true;
        			goldCost0.active = true;
        			goldCost1.active = true;
        			goldCost2.active = true;
        		}
        		break;
        	case ARCHERS:
    			goldCost0.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.archerFireRateLevel];
    			goldCost1.newInt = SiegeVariables.res.getIntArray(R.array.arrowRate)[SiegeVariables.archerFlightSpeedLevel];
    			goldCost2.newInt = 0;
    			
        		if (buttonUpgradeCastleStats.draw == false && buttonUpgradeArchers.draw == false) {
        			buttonUpgradeArcherRate.active = true;
        			buttonUpgradeArcherSpeed.active = true;
        			buttonUpgradeIDunno2.active = true;
        			archerBackground.active = true;
        			goldCost0.active = true;
        			goldCost1.active = true;
        			goldCost2.active = true;
        		}
        		break;
        	}
        	break;
        case SiegeVariables.READYING:
        	if (checkClear()) {
    			levelIcon.active = true;
    			levelCounter.active = true;
    			stageIcon.active = true;
    			stageCounter.active = true;
    			buttonReady.active = true;
        	}
        	break;
        case SiegeVariables.GAMEOVER:
        	if (checkClear()) {
        		buttonRestart.active = true;
        	}
        	break;
        case SiegeVariables.PAUSED:
        	
        	break;
        }
        
        if (buttonStart != null) buttonStart.update(deltaTime);
        if (buttonReset != null) buttonReset.update(deltaTime);
        if (buttonQuit != null) buttonQuit.update(deltaTime);
        if (buttonResume != null) buttonResume.update(deltaTime);
        if (buttonMenu != null) buttonMenu.update(deltaTime);
        if (buttonReady != null) buttonReady.update(deltaTime);
        if (buttonRestart != null) buttonRestart.update(deltaTime);
        if (buttonGeneric != null) buttonGeneric.update(deltaTime);
        if (buttonFire != null) buttonFire.update(deltaTime);
        if (buttonLightning != null) buttonLightning.update(deltaTime);
        if (buttonIce != null) buttonIce.update(deltaTime);
        if (buttonUpgradeBack != null) buttonUpgradeBack.update(deltaTime);
        if (buttonUpgradeContinue != null) buttonUpgradeContinue.update(deltaTime);
        if (buttonUpgradeHero != null) buttonUpgradeHero.update(deltaTime);
        if (buttonUpgradeCastle != null) buttonUpgradeCastle.update(deltaTime);
        if (buttonUpgradeHeroStats != null) buttonUpgradeHeroStats.update(deltaTime);
        if (buttonUpgradeArrows != null) buttonUpgradeArrows.update(deltaTime);
        if (buttonUpgradeCastleStats != null) buttonUpgradeCastleStats.update(deltaTime);
        if (buttonUpgradeArchers != null) buttonUpgradeArchers.update(deltaTime);
		
        if (heroBackground != null) heroBackground.update(deltaTime);
        if (arrowBackground != null) arrowBackground.update(deltaTime);
        if (castleBackground != null) castleBackground.update(deltaTime);
        if (archerBackground != null) archerBackground.update(deltaTime);
		
        if (buttonUpgradeArrowFireRate != null) buttonUpgradeArrowFireRate.update(deltaTime);
        if (buttonUpgradeArrowSpeed != null) buttonUpgradeArrowSpeed.update(deltaTime);
        if (buttonUpgradeMana != null) buttonUpgradeMana.update(deltaTime);
        if (buttonUpgradeFire != null) buttonUpgradeFire.update(deltaTime);
        if (buttonUpgradeLightning != null) buttonUpgradeLightning.update(deltaTime);
        if (buttonUpgradeIce != null) buttonUpgradeIce.update(deltaTime);
        if (buttonUpgradeHealth != null) buttonUpgradeHealth.update(deltaTime);
        if (buttonUpgradeHeal != null) buttonUpgradeHeal.update(deltaTime);
        if (buttonUpgradeIDunno != null) buttonUpgradeIDunno.update(deltaTime);
        if (buttonUpgradeArcherRate != null) buttonUpgradeArcherRate.update(deltaTime);
        if (buttonUpgradeArcherSpeed != null) buttonUpgradeArcherSpeed.update(deltaTime);
        if (buttonUpgradeIDunno2 != null) buttonUpgradeIDunno2.update(deltaTime);
		
        if (goldCost0 != null) goldCost0.update(deltaTime);
        if (goldCost1 != null) goldCost1.update(deltaTime);
        if (goldCost2 != null) goldCost2.update(deltaTime);
		
        if (goldCounter != null) goldCounter.update(deltaTime);
        if (goldIcon != null) goldIcon.update(deltaTime);
        if (levelIcon != null) levelIcon.update(deltaTime);
        if (levelCounter != null) levelCounter.update(deltaTime);
        if (stageIcon != null) stageIcon.update(deltaTime);
        if (stageCounter != null) stageCounter.update(deltaTime);
	}
	
	public int buttonPress(float x, float y) {
		switch(SiegeVariables.state) {
		case SiegeVariables.STARTING:
			if (hitButton(x, y, buttonStart)) {
				return START;
			}else if (hitButton(x, y, buttonReset)) {
				return RESET;
			} else if (hitButton(x, y, buttonQuit)) {
				return QUIT;
			}
			break;
		case SiegeVariables.PLAYING:
			
			break;
		case SiegeVariables.UPGRADING:
			if (hitButton(x, y, buttonUpgradeBack)) {
				return back();
			} else if (hitButton(x, y, buttonUpgradeContinue)) {
				return CONTINUE;
			} else if (hitButton(x, y, buttonUpgradeHero)) {
				return UPGRADEHERO;
			} else if (hitButton(x, y, buttonUpgradeCastle)) {
				return UPGRADECASTLE;
			} else if (hitButton(x, y, buttonUpgradeHeroStats)) {
				return UPGRADEHEROSTATS;
			} else if (hitButton(x, y, buttonUpgradeArrows)) {
				return UPGRADEARROWS;
			} else if (hitButton(x, y, buttonUpgradeCastleStats)) {
				return UPGRADECASTLESTATS;
			} else if (hitButton(x, y, buttonUpgradeArchers)) {
				return UPGRADEARCHERS;
			}
			
			if (specState == HEROSTATS) {
				if (hitButton(x, y, buttonUpgradeArrowFireRate)) {
					return UPGRADEARROWFIRERATE;
				} else if (hitButton(x, y, buttonUpgradeArrowSpeed)) {
					return UPGRADEARROWSPEED;
				} else if (hitButton(x, y, buttonUpgradeMana)) {
					return UPGRADEMANA;
				}
			}
			
			if (specState == ARROWS) {
				if (hitButton(x, y, buttonUpgradeFire)) {
					return UPGRADEFIRE;
				} else if (hitButton(x, y, buttonUpgradeLightning)) {
					return UPGRADELIGHTNING;
				} else if (hitButton(x, y, buttonUpgradeIce)) {
					return UPGRADEICE;
				}
			}
			
			if (specState == CASTLESTATS) {
				if (hitButton(x, y, buttonUpgradeHealth)) {
					return UPGRADEHEALTH;
				} else if (hitButton(x, y, buttonUpgradeHeal)) {
					return UPGRADEHEAL;
				} else if (hitButton(x, y, buttonUpgradeIDunno)) {
					return UPGRADEIDUNNO;
				}
			}
			
			if (specState == ARCHERS) {
				if (hitButton(x, y, buttonUpgradeArcherRate)) {
					return UPGRADEARCHERRATE;
				} else if (hitButton(x, y, buttonUpgradeArcherSpeed)) {
					return UPGRADEARCHERSPEED;
				} else if (hitButton(x, y, buttonUpgradeIDunno2)) {
					return UPGRADEIDUNNO2;
				}
			}
			break;
		case SiegeVariables.PAUSED:
			if (hitButton(x, y, buttonResume)) {
				return RESUME;
			} else if (hitButton(x, y, buttonReset)) {
				return RESET;
			} else if (hitButton(x, y, buttonMenu)) {
				return MENU;
			}
			break;
		case SiegeVariables.READYING:
			if (hitButton(x, y, buttonReady)) {
				return READY;
			}
			break;
		case SiegeVariables.GAMEOVER:
			if (hitButton(x, y, buttonRestart)) {
				return RESTART;
			}
			break;
		}
		if (SiegeVariables.arrowMenu) {
			if (hitButton(x, y, buttonGeneric)) {
				return GENERIC;
			} else if (hitButton(x, y, buttonFire)) {
				return FIRE;
			} else if (hitButton(x, y, buttonLightning)) {
				return LIGHTNING;
			} else if (hitButton(x, y, buttonIce)) {
				return ICE;
			}
		}
		return NONE;
	}
	
	public void setState(int newState) {
		switch(newState) {
		case SiegeVariables.STARTING:
			buttonStart.active = true;
			buttonReset.active = true;
			buttonQuit.active = true;
			buttonResume.active = false;
			buttonMenu.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			
			goldCounter.active = false;
			goldIcon.active = false;
			levelIcon.active = false;
			levelCounter.active = false;
			stageIcon.active = false;
			stageCounter.active = false;
			break;
		case SiegeVariables.PLAYING:
			buttonStart.active = false;
			buttonReset.active = false;
			buttonQuit.active = false;
			buttonResume.active = false;
			buttonMenu.active = false;
			buttonReady.active = false;
			buttonRestart.active = false;
			buttonGeneric.active = false;
			buttonFire.active = false;
			buttonLightning.active = false;
			buttonIce.active = false;
			buttonUpgradeBack.active = false;
			buttonUpgradeContinue.active = false;
			buttonUpgradeHero.active = false;
			buttonUpgradeCastle.active = false;
			buttonUpgradeArrowFireRate.active = false;
			buttonUpgradeArrowSpeed.active = false;
			buttonUpgradeMana.active = false;
			buttonUpgradeFire.active = false;
			buttonUpgradeLightning.active = false;
			buttonUpgradeIce.active = false;
			buttonUpgradeHealth.active = false;
			buttonUpgradeHeal.active = false;
			buttonUpgradeIDunno.active = false;
			buttonUpgradeArcherRate.active = false;
			buttonUpgradeArcherSpeed.active = false;
			buttonUpgradeIDunno2.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			
			goldCounter.active = false;
			goldIcon.active = false;
			levelIcon.active = false;
			levelCounter.active = false;
			stageIcon.active = false;
			stageCounter.active = false;
			setSpecState(BASE);
			break;
		case SiegeVariables.UPGRADING:
			buttonUpgradeBack.active = true;
			buttonUpgradeContinue.active = true;
			buttonResume.active = false;
			buttonMenu.active = false;
			buttonRestart.active = false;
			
			goldCounter.newInt = SiegeVariables.gold;
			goldCounter.active = true;
			goldIcon.active = true;
			break;
		case SiegeVariables.READYING:
//			buttonReady.active = true;
			buttonStart.active = false;
			buttonReset.active = false;
			buttonQuit.active = false;
			buttonResume.active = false;
			buttonMenu.active = false;
			buttonGeneric.active = false;
			buttonFire.active = false;
			buttonLightning.active = false;
			buttonIce.active = false;
			buttonUpgradeBack.active = false;
			buttonUpgradeContinue.active = false;
			buttonUpgradeHero.active = false;
			buttonUpgradeCastle.active = false;
			buttonUpgradeArrowFireRate.active = false;
			buttonUpgradeArrowSpeed.active = false;
			buttonUpgradeMana.active = false;
			buttonUpgradeFire.active = false;
			buttonUpgradeLightning.active = false;
			buttonUpgradeIce.active = false;
			buttonUpgradeHealth.active = false;
			buttonUpgradeHeal.active = false;
			buttonUpgradeIDunno.active = false;
			buttonUpgradeArcherRate.active = false;
			buttonUpgradeArcherSpeed.active = false;
			buttonUpgradeIDunno2.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			
			levelCounter.newInt = SiegeVariables.level;
			stageCounter.newInt = SiegeVariables.stage;
			
			goldCounter.active = false;
			goldIcon.active = false;
//			levelIcon.active = true;
//			levelCounter.active = true;
//			stageIcon.active = true;
//			stageCounter.active = true;
			setSpecState(BASE);
			break;
		case SiegeVariables.GAMEOVER:
//			buttonRestart.active = true;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			break;
		case SiegeVariables.PAUSED:
			if (buttonResume != null) buttonResume.active = true;
			if (buttonMenu != null) buttonMenu.active = true;
			if (buttonUpgradeBack != null) buttonUpgradeBack.active = false;
			if (buttonUpgradeContinue != null) buttonUpgradeContinue.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			break;
		}
	}
	
	public void setSpecState(int newSpecState) {
		specState = newSpecState;
		switch(specState) {
		case BASE:
			buttonUpgradeHeroStats.active = false;
			buttonUpgradeArrows.active = false;
			buttonUpgradeCastleStats.active = false;
			buttonUpgradeArchers.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			break;
		case HERO:
			buttonUpgradeHero.active = false;
			buttonUpgradeCastle.active = false;
			buttonUpgradeArrowFireRate.active = false;
			buttonUpgradeArrowSpeed.active = false;
			buttonUpgradeMana.active = false;
			buttonUpgradeFire.active = false;
			buttonUpgradeLightning.active = false;
			buttonUpgradeIce.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			break;
		case HEROSTATS:
			buttonUpgradeHeroStats.active = false;
			buttonUpgradeArrows.active = false;
			break;
		case ARROWS:
			buttonUpgradeHeroStats.active = false;
			buttonUpgradeArrows.active = false;
			break;
		case CASTLE:
			buttonUpgradeHero.active = false;
			buttonUpgradeCastle.active = false;
			buttonUpgradeHealth.active = false;
			buttonUpgradeHeal.active = false;
			buttonUpgradeIDunno.active = false;
			buttonUpgradeArcherRate.active = false;
			buttonUpgradeArcherSpeed.active = false;
			buttonUpgradeIDunno2.active = false;
			
			heroBackground.active = false;
			arrowBackground.active = false;
			castleBackground.active = false;
			archerBackground.active = false;
			goldCost0.active = false;
			goldCost1.active = false;
			goldCost2.active = false;
			break;
		case CASTLESTATS:
			buttonUpgradeCastleStats.active = false;
			buttonUpgradeArchers.active = false;
			break;
		case ARCHERS:
			buttonUpgradeCastleStats.active = false;
			buttonUpgradeArchers.active = false;
			break;
		}
	}
	
	public void setLevels() {
		buttonUpgradeArrowFireRate.upgrade(SiegeVariables.arrowFireRateLevel);
		buttonUpgradeArrowSpeed.upgrade(SiegeVariables.arrowFlightSpeedLevel);
		buttonUpgradeMana.upgrade(SiegeVariables.manaLevel);
		buttonUpgradeFire.upgrade(SiegeVariables.arrowFireLevel);
		buttonUpgradeLightning.upgrade(SiegeVariables.arrowLightningLevel);
		buttonUpgradeIce.upgrade(SiegeVariables.arrowIceLevel);
		buttonUpgradeHealth.upgrade(SiegeVariables.castleHealthMaxLevel);
		buttonUpgradeHeal.upgrade(1);
		buttonUpgradeIDunno.upgrade(1);
		buttonUpgradeArcherRate.upgrade(SiegeVariables.archerFireRateLevel);
		buttonUpgradeArcherSpeed.upgrade(SiegeVariables.archerFlightSpeedLevel);
		buttonUpgradeIDunno2.upgrade(1);
	}
	
	public void upgrade(int upgradeID, int level) {
		switch (upgradeID) {
		case UPGRADEARROWFIRERATE:
			buttonUpgradeArrowFireRate.upgrade(level);
			break;
		case UPGRADEARROWSPEED:
			buttonUpgradeArrowSpeed.upgrade(level);
			break;
		case UPGRADEMANA:
			buttonUpgradeMana.upgrade(level);
			break;
		case UPGRADEFIRE:
			buttonUpgradeFire.upgrade(level);
			break;
		case UPGRADELIGHTNING:
			buttonUpgradeLightning.upgrade(level);
			break;
		case UPGRADEICE:
			buttonUpgradeIce.upgrade(level);
			break;
		case UPGRADEHEALTH:
			buttonUpgradeHealth.upgrade(level);
			break;
		case UPGRADEHEAL:
			buttonUpgradeHeal.upgrade(level);
			break;
		case UPGRADEIDUNNO:
			buttonUpgradeIDunno.upgrade(level);
			break;
		case UPGRADEARCHERRATE:
			buttonUpgradeArcherRate.upgrade(level);
			break;
		case UPGRADEARCHERSPEED:
			buttonUpgradeArcherSpeed.upgrade(level);
			break;
		case UPGRADEIDUNNO2:
			buttonUpgradeIDunno2.upgrade(level);
			break;
		}
	}
	
	public boolean checkClear() {
		return (buttonStart.draw == false && buttonReset.draw == false && buttonQuit.draw == false &&
				buttonResume.draw == false && buttonMenu.draw == false && buttonReady.draw == false &&
				buttonRestart.draw == false && buttonUpgradeBack.draw == false &&
				buttonUpgradeContinue.draw == false && buttonUpgradeHero.draw == false &&
				buttonUpgradeCastle.draw == false && buttonUpgradeHeroStats.draw == false &&
				buttonUpgradeCastleStats.draw == false && buttonUpgradeArchers.draw == false &&
				buttonUpgradeArrowFireRate.draw == false && buttonUpgradeArrowSpeed.draw == false &&
				buttonUpgradeMana.draw == false && buttonUpgradeFire.draw == false &&
				buttonUpgradeLightning.draw == false && buttonUpgradeIce.draw == false &&
				buttonUpgradeHealth.draw == false && buttonUpgradeHeal.draw == false &&
				buttonUpgradeIDunno.draw == false && buttonUpgradeArcherRate.draw == false &&
				buttonUpgradeArcherSpeed.draw == false && buttonUpgradeIDunno2.draw == false);
				
	}
	
	public int back() {
		if (specState == BASE) {
			return CONTINUE;
		} else if (specState == HERO || specState == CASTLE) {
			setSpecState(BASE);
		} else if (specState == HEROSTATS || specState == ARROWS) {
			setSpecState(HERO);
		} else if (specState == CASTLESTATS || specState == ARCHERS) {
			setSpecState(CASTLE);
		}
		return NONE;
	}
	
	public boolean hitButton(float x, float y, ButtonBase button) {
		if (button.active) {
			if (x > button.x && x < button.x + button.width) {
				if (y > button.y && y < button.y + button.height){
					return true;
				}
			}
		}
		return false;
	}
}