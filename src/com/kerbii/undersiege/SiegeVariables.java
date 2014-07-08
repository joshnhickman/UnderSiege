package com.kerbii.undersiege;

import java.util.Random;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class SiegeVariables {
	
	public static float screenWidth, screenHeight;
	
	/**
	 * Codes
	 */
	public static final int STARTING = 0, PLAYING = 1, UPGRADING = 2, READYING = 3, GAMEOVER = 4, PAUSED = 5;
	public static final int NONE = -1, START = 0, RESET = 1, QUIT = 2, RESUME = 3, MENU = 4,
			READY = 5, RESTART = 6, GENERIC = 7, FIRE = 8, LIGHTNING = 9, ICE = 10, ARCHER = 11, MOB = 12;
	public static int state;
	public static int previousState;
	
	public static boolean isPaused;
	public static boolean isLoaded;
	
	public static boolean arrowMenu;
	
	public static Random r;
	
	/**
	 * Main classes
	 */
	public static SiegeMain siegeMain;
	public static SiegeController siegeController;
	public static Thread siegeUpdater;
	public static SiegeRenderer siegeRenderer;
	
	/**
	 * Resources / Preferences
	 */
	public static Resources res;
	public static SharedPreferences prefs;
	
	/**
	 * Helper classes
	 */
	public static QuadTree quadTree;
	public static MobFactory mobFactory;
	public static ArrowFactory arrowFactory;
	public static MenuHandler menuHandler;
	public static HitDetector hitDetector;
	public static SiegeUpgrader upgrader;
	
	/**
	 * Array Lists
	 */
	public static ArrayList<GameObject> mobs, mobsDead, arrows, effectsGround, effectsAir, archers, menus;
	
	/**
	 * Specific classes
	 */
	public static Leto leto;
	public static Archer archer0, archer1, archer2, archer3;
	public static Castle castle;
	public static Ground ground;
	
	/**
	 * Stat variables
	 */
	public static int killCount, arrowsFired, gold;
	
	/**
	 * Level variables
	 */
	public static int level, stage, mobsKilled, mobsThisLevel;
	
	/**
	 * Upgrade variables
	 */
	public static int arrowFireRateLevel;
	public static long arrowFireRate;
	public static int arrowFlightSpeedLevel;
	public static float arrowFlightSpeed;
	
	public static int manaLevel;
	public static float manaMax, mana;
	
	public static int arrowFireLevel;
	public static float arrowFireRange, arrowFireDamage;
	public static long arrowFireDuration;
	public static float arrowFireManaCost;
	
	public static int arrowLightningLevel;
	public static float arrowLightningRange, arrowLightningDamage;
	public static int arrowLightningLeaps;
	public static float arrowLightningManaCost;
	
	public static int arrowIceLevel;
	public static float arrowIceRange, arrowIceDamage, arrowIceSlowFactor;
	public static long arrowIceDuration;
	public static float arrowIceManaCost;
	
	public static int castleHealthMaxLevel;
	public static float castleHealthMax, castleHealth;
	
	public static int archerFireRateLevel;
	public static long archerFireRate;
	public static int archerFlightSpeedLevel;
	public static float archerFlightSpeed;
	
	/**
	 * Development Stuff
	 */
	public static TextObject manaDisplay;
	
	private SiegeVariables() {
		
	}
}
