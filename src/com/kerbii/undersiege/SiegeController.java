package com.kerbii.undersiege;

import java.util.Random;

import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class SiegeController {
	
	public SiegeController(SiegeMain siegeMain, Thread siegeUpdater, SiegeRenderer siegeRenderer, float width, float height) {
		SiegeVariables.siegeMain = siegeMain;
		SiegeVariables.siegeController = this;
		SiegeVariables.siegeUpdater = siegeUpdater;
		SiegeVariables.siegeRenderer = siegeRenderer;
		SiegeVariables.screenWidth = width;
		SiegeVariables.screenHeight = height;
		
		SiegeVariables.isLoaded = false;
		
		SiegeVariables.res = SiegeVariables.siegeMain.getResources();
		SiegeVariables.prefs = SiegeVariables.siegeMain.getPreferences(0);
		
		SiegeVariables.isPaused = true;
		
		SiegeVariables.r = new Random();
		
		SiegeVariables.mobs = new ArrayList<GameObject>();
		SiegeVariables.mobsDead = new ArrayList<GameObject>();
		SiegeVariables.arrows = new ArrayList<GameObject>();
		SiegeVariables.effectsGround = new ArrayList<GameObject>();
		SiegeVariables.effectsAir = new ArrayList<GameObject>();
		SiegeVariables.archers = new ArrayList<GameObject>();
		SiegeVariables.menus = new ArrayList<GameObject>();
		
		SiegeVariables.quadTree = new QuadTree();
		SiegeVariables.menuHandler = new MenuHandler();
		SiegeVariables.hitDetector = new HitDetector();
		
		SiegeVariables.mobFactory = new MobFactory();
		SiegeVariables.arrowFactory = new ArrowFactory();
		
		SiegeVariables.upgrader = new SiegeUpgrader();
		SiegeVariables.arrowMenu = false;
		
		SiegeVariables.menuHandler.load();
		
		SiegeVariables.leto = new Leto();
		SiegeVariables.archers.add(SiegeVariables.leto);
		SiegeVariables.castle = new Castle(0, 0);
		SiegeVariables.ground = new Ground();
		SiegeVariables.archer0 = new Archer(80, 90);
		SiegeVariables.archer1 = new Archer(80, 170);
		SiegeVariables.archer2 = new Archer(80, 320);
		SiegeVariables.archer3 = new Archer(80, 400);
		SiegeVariables.archers.add(SiegeVariables.archer0);
		SiegeVariables.archers.add(SiegeVariables.archer1);
		SiegeVariables.archers.add(SiegeVariables.archer2);
		SiegeVariables.archers.add(SiegeVariables.archer3);
		
		setState(SiegeVariables.STARTING);
		
		load();
		
		//Development stuff
		SiegeVariables.manaDisplay = new TextObject(0,430,25,50,0,0,430,430,ButtonBase.FADEIN);
		SiegeVariables.menus.add(SiegeVariables.manaDisplay);
		SiegeVariables.manaDisplay.active = true;
	}
	
	public void initialize() {
	}
	
	public void load() {
		SharedPreferences prefs = SiegeVariables.prefs;
		
    	//load stat variables
    	SiegeVariables.killCount = prefs.getInt("killCount", 0);
    	SiegeVariables.arrowsFired = prefs.getInt("arrowsFired", 0);
    	SiegeVariables.gold = prefs.getInt("gold", 0);
    	
    	//load level variables
    	SiegeVariables.stage = prefs.getInt("stage", 0);
    	SiegeVariables.level = SiegeVariables.stage / 15;
    	SiegeVariables.mobsThisLevel = SiegeVariables.res.getIntArray(R.array.mobNumbers)[SiegeVariables.stage];
    	
    	//load upgrade variables
    	SiegeVariables.arrowFireRateLevel = prefs.getInt("arrowFireRateLevel", 0);
    	SiegeVariables.arrowFireRate = prefs.getLong("arrowFireRate", 1200);
    	
    	SiegeVariables.arrowFlightSpeedLevel = prefs.getInt("arrowFlightSpeedLevel", 0);
    	SiegeVariables.arrowFlightSpeed = prefs.getFloat("arrowFlightSpeed", 8);
    	
    	SiegeVariables.manaLevel = prefs.getInt("manaLevel", 0);
    	SiegeVariables.manaMax = prefs.getFloat("manaMax", 100);
    	SiegeVariables.mana = prefs.getFloat("mana", SiegeVariables.manaMax);
    	
    	SiegeVariables.arrowFireLevel = prefs.getInt("arrowFireLevel", 0);
    	SiegeVariables.arrowFireRange = prefs.getFloat("arrowFireRange", 80);
    	SiegeVariables.arrowFireDamage = prefs.getFloat("arrowFireDamage", 0.5f);
    	SiegeVariables.arrowFireDuration = prefs.getLong("arrowFireDuration", 4000);
    	SiegeVariables.arrowFireManaCost = 30;
    	
    	SiegeVariables.arrowLightningLevel = prefs.getInt("arrowLightningLevel", 0);
    	SiegeVariables.arrowLightningRange = prefs.getFloat("arrowLightningRange", 80);
    	SiegeVariables.arrowLightningDamage = prefs.getFloat("arrowLightningDamage", 10);
    	SiegeVariables.arrowLightningLeaps = prefs.getInt("arrowLightningLeaps", 2);
    	SiegeVariables.arrowLightningManaCost = 40;
    	
    	SiegeVariables.arrowIceLevel = prefs.getInt("arrowIceLevel", 0);
    	SiegeVariables.arrowIceRange = prefs.getFloat("arrowIceRange", 120);
    	SiegeVariables.arrowIceDamage = prefs.getFloat("arrowIceDamage", 1);
    	SiegeVariables.arrowIceSlowFactor = prefs.getFloat("arrowIceSlowFactor", 2);
    	SiegeVariables.arrowIceDuration = prefs.getLong("arrowIceDuration", 4000);
    	SiegeVariables.arrowIceManaCost = 20;
    	
    	SiegeVariables.castleHealthMaxLevel = prefs.getInt("castleHealthMaxLevel", 0);
    	SiegeVariables.castleHealthMax = prefs.getFloat("castleHealthMax", 500);
    	SiegeVariables.castleHealth = prefs.getFloat("castleHealth", SiegeVariables.castleHealthMax);
    	
    	SiegeVariables.archerFireRateLevel = prefs.getInt("archerFireRateLevel", 0);
    	SiegeVariables.archerFireRate = prefs.getLong("archerFireRate", 3000);
    	SiegeVariables.archerFlightSpeed = prefs.getFloat("archerFlightSpeed", 8);
	}
	
	public void quickSave() {
		SharedPreferences.Editor editor = SiegeVariables.prefs.edit();
		
		editor.putInt("gold", SiegeVariables.gold);
		
		editor.putInt("killCount", SiegeVariables.killCount);
		editor.putInt("arrowsFired", SiegeVariables.arrowsFired);
		
		editor.putInt("stage", SiegeVariables.stage);
		editor.putInt("level", SiegeVariables.level);
				
		editor.commit();
	}
	
	public void fullSave() {
		SharedPreferences.Editor editor = SiegeVariables.prefs.edit();
		
		editor.putInt("arrowFireRateLevel", SiegeVariables.arrowFireRateLevel);
		editor.putLong("arrowFireRate", SiegeVariables.arrowFireRate);
		
		editor.putInt("arrowFlightSpeedLevel", SiegeVariables.arrowFlightSpeedLevel);
		editor.putFloat("arrowFlightSpeed", SiegeVariables.arrowFlightSpeed);
		
		editor.putInt("manaLevel", SiegeVariables.manaLevel);
		editor.putFloat("manaMax", SiegeVariables.manaMax);
		
		editor.putInt("arrowFireLevel", SiegeVariables.arrowFireLevel);
		editor.putFloat("arrowFireRange", SiegeVariables.arrowFireRange);
		editor.putFloat("arrowFireDamage", SiegeVariables.arrowFireDamage);
		editor.putLong("arrowFireDuration", SiegeVariables.arrowFireDuration);
		
		editor.putInt("arrowLightningLevel", SiegeVariables.arrowLightningLevel);
		editor.putFloat("arrowLightningRange", SiegeVariables.arrowLightningRange);
		editor.putFloat("arrowLightningDamage", SiegeVariables.arrowLightningDamage);
		editor.putInt("arrowLightningLeaps", SiegeVariables.arrowLightningLeaps);
		
		editor.putInt("arrowIceLevel", SiegeVariables.arrowIceLevel);
		editor.putFloat("arrowIceRange", SiegeVariables.arrowIceRange);
		editor.putFloat("arrowIceDamage", SiegeVariables.arrowIceDamage);
		editor.putFloat("arrowIceSlowFactor", SiegeVariables.arrowIceSlowFactor);
		editor.putLong("arrowIceDuration", SiegeVariables.arrowIceDuration);
		
		editor.putInt("castleHealthMaxLevel", SiegeVariables.castleHealthMaxLevel);
		editor.putFloat("castleHealthMax", SiegeVariables.castleHealthMax);
		editor.putFloat("castleHealth", SiegeVariables.castleHealth);
		
		editor.putInt("archerFireRateLevel", SiegeVariables.archerFireRateLevel);
		editor.putLong("archerFireRate", SiegeVariables.archerFireRate);
		
		editor.putInt("archerFlightSpeedLevel", SiegeVariables.archerFlightSpeedLevel);
		editor.putFloat("archerFlightSpeed", SiegeVariables.archerFlightSpeed);
		
		editor.commit();
		
		quickSave();
	}
	
	public void resetSave() {
		SharedPreferences.Editor editor = SiegeVariables.prefs.edit();
		editor.clear();
		editor.commit();
		
		load();
		
		SiegeVariables.archer0.active = false;
		SiegeVariables.archer1.active = false;
		SiegeVariables.archer2.active = false;
		SiegeVariables.archer3.active = false;
		
		SiegeVariables.mobFactory.newLevel();
	}
	
	public void setState(int newState) {
		if (newState == SiegeVariables.PAUSED) quickSave();
		SiegeVariables.state = newState;
		SiegeVariables.menuHandler.setState(SiegeVariables.state);
	}
	
	public void onResume() {
		if (SiegeVariables.state != SiegeVariables.STARTING) setState(SiegeVariables.PAUSED);
		SiegeVariables.isPaused = false;
		load();
	}
	
	public void onPause() {
		if (SiegeVariables.state != SiegeVariables.UPGRADING) setState(SiegeVariables.PAUSED);
		SiegeVariables.isPaused = true;
		quickSave();
	}
	
	public void gameOver() {
		quickSave();
		setState(SiegeVariables.GAMEOVER);
	}
	
	private void restart() {
		SiegeVariables.mobsKilled = 0;
		SiegeVariables.mobs.clear();
		SiegeVariables.mobsDead.clear();
		SiegeVariables.arrows.clear();
		SiegeVariables.effectsGround.clear();
		SiegeVariables.effectsAir.clear();
		SiegeVariables.quadTree.clear();
		load();
		setState(SiegeVariables.UPGRADING);
	}
	
	public void finishLevel() {
		SiegeVariables.stage++;
		SiegeVariables.level = SiegeVariables.stage / 15;
		
		fullSave();
		
		SiegeVariables.mobsKilled = 0;
		SiegeVariables.mobs.clear();
		SiegeVariables.mobsDead.clear();
		SiegeVariables.arrows.clear();
		SiegeVariables.effectsGround.clear();
		SiegeVariables.effectsAir.clear();
		
    	SiegeVariables.archer0.reset();
    	SiegeVariables.archer1.reset();
    	SiegeVariables.archer2.reset();
    	SiegeVariables.archer3.reset();
    	
    	if (SiegeVariables.level > 0) {
    		SiegeVariables.archer0.active = true;
    	}
    	if (SiegeVariables.level > 1) {
    		SiegeVariables.archer1.active = true;
    	}
    	if (SiegeVariables.level > 2) {
    		SiegeVariables.archer2.active = true;
    	}
    	if (SiegeVariables.level > 3) {
    		SiegeVariables.archer3.active = true;
    	}
    	
    	SiegeVariables.quadTree.clear();
//    	SiegeVariables.upgrader.startUpgrade();
		setState(SiegeVariables.UPGRADING);
	}
	
	public void loadLevel() {
		load();
		SiegeVariables.mobFactory.newLevel();
		//load
		//load level variables
		//display ready menu
	}
	
	public void quitGame() {
		SiegeVariables.mobs.clear();
		SiegeVariables.mobsDead.clear();
		SiegeVariables.arrows.clear();
		SiegeVariables.effectsGround.clear();
		SiegeVariables.effectsAir.clear();
		SiegeVariables.quadTree.clear();
		SiegeVariables.mobFactory.newLevel();
		
		fullSave();
		
		setState(SiegeVariables.STARTING);
	}
	
	public boolean doTouchEvent(MotionEvent event) {
		float x = (800.0f / SiegeVariables.screenWidth) * event.getX();
		float y = (480.0f / SiegeVariables.screenHeight) * event.getY();
		
		MenuHandler menuHandler = SiegeVariables.menuHandler;
		
		int buttonCode;
		switch(SiegeVariables.state) {
		case SiegeVariables.STARTING:
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				buttonCode = menuHandler.buttonPress(x, y);
				switch(buttonCode) {
				case SiegeVariables.START:
					setState(SiegeVariables.READYING);
					break;
				case SiegeVariables.RESET:
					resetSave();
					menuHandler.setLevels();
					break;
				case SiegeVariables.QUIT:
					SiegeVariables.siegeMain.quit();
					break;
				}
			}
			break;
		case SiegeVariables.PLAYING:
			if (!SiegeVariables.arrowMenu) {
				if ((event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)) {
					if (x > 110) {
						synchronized (SiegeVariables.leto) {
							SiegeVariables.leto.rotate(x, y);
						}
						SiegeVariables.arrowFactory.shoot(x, y);
					}
				}
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (x < 100) {
						SiegeVariables.arrowMenu = true;
					}
				}
			} else {
				if (event.getAction() == MotionEvent.ACTION_UP){
					buttonCode = menuHandler.buttonPress(x, y);
					if (buttonCode == MenuHandler.GENERIC) {
						SiegeVariables.arrowFactory.arrowType = SiegeVariables.GENERIC;
					} else if (buttonCode == MenuHandler.FIRE) {
						SiegeVariables.arrowFactory.arrowType = SiegeVariables.FIRE;
					} else if (buttonCode == MenuHandler.LIGHTNING) {
						SiegeVariables.arrowFactory.arrowType = SiegeVariables.LIGHTNING;
					} else if (buttonCode == MenuHandler.ICE) {
						SiegeVariables.arrowFactory.arrowType = SiegeVariables.ICE;
					}
					SiegeVariables.arrowMenu = false;
				}
			}
			break;
		case SiegeVariables.UPGRADING:
			if (event.getAction() == MotionEvent.ACTION_DOWN) { 
				buttonCode = menuHandler.buttonPress(x, y);
				
				SiegeUpgrader upgrader = SiegeVariables.upgrader;
					
				int level;
				switch (buttonCode) {
				case MenuHandler.BACK:
					
					break;
				case MenuHandler.CONTINUE:
					fullSave();
					loadLevel();
					setState(SiegeVariables.READYING);
					break;
				case MenuHandler.UPGRADEHERO:
					menuHandler.setSpecState(MenuHandler.HERO);
					break;
				case MenuHandler.UPGRADECASTLE:
					menuHandler.setSpecState(MenuHandler.CASTLE);
					break;
				case MenuHandler.UPGRADEHEROSTATS:
					menuHandler.setSpecState(MenuHandler.HEROSTATS);
					break;
				case MenuHandler.UPGRADEARROWS:
					menuHandler.setSpecState(MenuHandler.ARROWS);
					break;
				case MenuHandler.UPGRADECASTLESTATS:
					menuHandler.setSpecState(MenuHandler.CASTLESTATS);
					break;
				case MenuHandler.UPGRADEARCHERS:
					menuHandler.setSpecState(MenuHandler.ARCHERS);
					break;
				case MenuHandler.UPGRADEARROWFIRERATE:
					level = upgrader.upgrade(MenuHandler.UPGRADEARROWFIRERATE);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEARROWFIRERATE, level);
					break;
				case MenuHandler.UPGRADEARROWSPEED:
					level = upgrader.upgrade(MenuHandler.UPGRADEARROWSPEED);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEARROWSPEED, level);
					break;
				case MenuHandler.UPGRADEMANA:
					level = upgrader.upgrade(MenuHandler.UPGRADEMANA);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEMANA, level);
					break;
				case MenuHandler.UPGRADEFIRE:
					level = upgrader.upgrade(MenuHandler.UPGRADEFIRE);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEFIRE, level);
					break;
				case MenuHandler.UPGRADELIGHTNING:
					level = upgrader.upgrade(MenuHandler.UPGRADELIGHTNING);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADELIGHTNING, level);
					break;
				case MenuHandler.UPGRADEICE:
					level = upgrader.upgrade(MenuHandler.UPGRADEICE);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEICE, level);
					break;
				case MenuHandler.UPGRADEHEALTH:
					level = upgrader.upgrade(MenuHandler.UPGRADEHEALTH);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEHEALTH, level);
					break;
				case MenuHandler.UPGRADEHEAL:
					level = upgrader.upgrade(MenuHandler.UPGRADEHEAL);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEHEAL, level);
					break;
				case MenuHandler.UPGRADEIDUNNO:
					level = upgrader.upgrade(MenuHandler.UPGRADEIDUNNO);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEIDUNNO, level);
					break;
				case MenuHandler.UPGRADEARCHERRATE:
					level = upgrader.upgrade(MenuHandler.UPGRADEARCHERRATE);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEARCHERRATE, level);
					break;
				case MenuHandler.UPGRADEARCHERSPEED:
					level = upgrader.upgrade(MenuHandler.UPGRADEARCHERSPEED);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEARCHERSPEED, level);
					break;
				case MenuHandler.UPGRADEIDUNNO2:
					level = upgrader.upgrade(MenuHandler.UPGRADEIDUNNO2);
					if (level != -1) menuHandler.upgrade(MenuHandler.UPGRADEIDUNNO2, level);
					break;
				}
//				menuHandler.goldCounter.newInt = upgrader.gold;
			}
			break;
		case SiegeVariables.PAUSED:
			buttonCode = menuHandler.buttonPress(x, y);
			if (buttonCode == MenuHandler.RESUME) {
				setState(SiegeVariables.PLAYING);
			} else if (buttonCode == MenuHandler.MENU) {
				quitGame();
			}
			
			if (x > 400 && x < 600) {
				SiegeVariables.gold = 999999;
				SiegeVariables.stage = 6;
				finishLevel();
			}
			
			if (x > 600) {
	    		SiegeVariables.archer0.active = true;
	    		SiegeVariables.archer1.active = true;
	    		SiegeVariables.archer2.active = true;
	    		SiegeVariables.archer3.active = true;
			}
			break;
		case SiegeVariables.READYING:
			buttonCode = menuHandler.buttonPress(x, y);
			if (buttonCode == MenuHandler.READY) {
				setState(SiegeVariables.PLAYING);
			}
			break;
		case SiegeVariables.GAMEOVER:
			buttonCode = menuHandler.buttonPress(x, y);
			if (buttonCode == MenuHandler.RESTART) {
				restart();
			}
			break;
		}
		return true;
	}
	
	public boolean doKeyDown(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (SiegeVariables.state == SiegeVariables.PLAYING) {
				setState(SiegeVariables.PAUSED);
				quickSave();
			} else  if (SiegeVariables.state == SiegeVariables.PAUSED) {
				setState(SiegeVariables.PLAYING);
				load();
			}
		}
		return true;
	}
}