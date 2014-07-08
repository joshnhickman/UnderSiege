package com.kerbii.undersiege;

import java.util.Random;

import android.os.SystemClock;

public class MobFactory {
	
	public boolean spawn, bossSpawned;
	public int mobsSpawned;
	
	private Random r;
	
	private int stage;
	private int mobTypes;
	
	/** Spawn rates */
	private long spawnRate0;
	private long spawnRate1;
	private long spawnRate2;
//	private long spawnRate3;
//	private long spawnRate4;
//	private long spawnRate5;
	
	/** Last spawn times */
	private long lastSpawn0;
	private long lastSpawn1;
	private long lastSpawn2;
//	private long lastSpawn3;
//	private long lastSpawn4;
//	private long lastSpawn5;
	
	public MobFactory() {
		
		newLevel();
		
		r = new Random();
	}
	
	public void update(long deltaTime) {
		if (spawn) {
			
			if (stage <= 15) {
				
				//spawn generic
				if (SystemClock.uptimeMillis() - lastSpawn0 > spawnRate0) {
					lastSpawn0 = SystemClock.uptimeMillis();
					if (r.nextInt(10) > (1 + mobTypes)) {
						spawnGeneric((float) 800, (float) 40 + (40 * r.nextInt(10)));
					} else {
						int rows = 1 + r.nextInt(1 + mobTypes);
						int columns = 2;
						if (mobTypes > 0) columns += r.nextInt(mobTypes);
						if (columns > 5) columns = 5;
						float startX = 800;
						float startY = 40 + (40 * r.nextInt(10 - columns));
						formation0(rows, columns, startX, startY);
					}
				}
				
				//spawn shield
				if (mobTypes >= 1) {
					if (SystemClock.uptimeMillis() - lastSpawn1 > spawnRate1) {
						lastSpawn1 = SystemClock.uptimeMillis();
						if (r.nextInt(10) > (mobTypes)) {
							spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
						} else {
							int rows = 1 + r.nextInt(1 + mobTypes);
							int columns = 2 + r.nextInt(mobTypes);
							if (columns > 5) columns = 5;
							float startX = 800;
							float startY = 40 + (40 * r.nextInt(10 - columns));
							formation1(rows, columns, startX, startY);
						}
					}
				}
				
				//spawn fast
				if (mobTypes >= 2) {
					if (SystemClock.uptimeMillis() - lastSpawn2 > spawnRate2) {
						lastSpawn2 = SystemClock.uptimeMillis();
						spawnFast((float) 800, (float) 40 + (40 * (r.nextInt(10))));
					}
				}
				
				//spawn Nero
				if (mobTypes >= 5) {
					if (!bossSpawned) {
						
					}
				}
			} else {
				//spawn generic mob (always)
				spawn0();
				
				if (mobTypes >= 1) {
					//spawn type 2 mobs
					spawn1();
				}
				
				if (mobTypes >= 2) {
	//				spawn2();
				}
				
				if (mobTypes >= 3) {
	//				spawn3();
				}
				
				if (mobTypes >= 4) {
	//				spawn4();
				}
				
				if (mobTypes >= 5) {
					//spawn boss mob depending on level
					if (!bossSpawned) {
//						if (level == 1) spawnBoss1();
//						else if (level == 2) spawnBoss2();
//						else if (level == 3) spawnBoss3();
//						else if (level == 4) spawnBoss4();
//						else if (level == 5) spawnBoss5();
					}
				}
			}
		}
	}
	
	private void spawn0() {
		if (SystemClock.uptimeMillis() - lastSpawn0 > spawnRate0) {
			lastSpawn0 = SystemClock.uptimeMillis();
			if (r.nextInt(10) > (1 + mobTypes)) {
				spawnGeneric((float) 800, (float) 40 + (40 * r.nextInt(10)));
			} else {
				int rows = 1 + r.nextInt(1 + mobTypes);
				int columns = 2;
				if (mobTypes > 0) columns += r.nextInt(mobTypes);
				if (columns > 5) columns = 5;
				float startX = 800;
				float startY = 40 + (40 * r.nextInt(10 - columns));
				formation0(rows, columns, startX, startY);
			}
		}
	}
	
	private void spawn1() {
		if (SystemClock.uptimeMillis() - lastSpawn1 > spawnRate1) {
			lastSpawn1 = SystemClock.uptimeMillis();
			if (r.nextInt(10) > (mobTypes)) {
				spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
			} else {
				int rows = 1 + r.nextInt(1 + mobTypes);
				int columns = 2 + r.nextInt(mobTypes);
				if (columns > 5) columns = 5;
				float startX = 800;
				float startY = 40 + (40 * r.nextInt(10 - columns));
				formation1(rows, columns, startX, startY);
			}
		}
	}
	
	/*
	private void spawn2() {
		if (SystemClock.uptimeMillis() - lastSpawn2 > spawnRate2) {
			lastSpawn2 = SystemClock.uptimeMillis();
			spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
		}
	}
	
	private void spawn3() {
		if (SystemClock.uptimeMillis() - lastSpawn3 > spawnRate3) {
			lastSpawn3 = SystemClock.uptimeMillis();
			spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
		}
	}
	
	private void spawn4() {
		if (SystemClock.uptimeMillis() - lastSpawn4 > spawnRate4) {
			lastSpawn4 = SystemClock.uptimeMillis();
			spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
		}
	}
	
	private void spawn5() {
		if (SystemClock.uptimeMillis() - lastSpawn5 > spawnRate5) {
			lastSpawn5 = SystemClock.uptimeMillis();
			spawnShield((float) 800, (float) 40 + (40 * (r.nextInt(10))));
		}
	}
	*/
	
	private void formation0(int rows, int columns, float startX, float startY) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (startY < 240) {
					spawnGeneric(startX + (40 * i), startY + (40 * j));
				} else {
					spawnGeneric(startX + (40 * i), startY - (40 * j));
				}
			}
		}
	}
	
	private void formation1(int rows, int columns, float startX, float startY) {
		for (int i = 0; i < columns; i++) {
			if (startY < 240) {
				spawnShield(startX, startY + (40 * i));
			} else {
				spawnShield(startX, startY - (40 * i));
			}
		}
		formation0(rows - 1, columns, startX + 40, startY);
	}
	
	private void spawnGeneric(float x, float y) {
		MobBase mob = new MobGeneric(x, y, 3 + (stage / 6));
		addMob(mob);
	}
	
	private void spawnShield(float x, float y) {
		MobBase mob = new MobShield(x, y, 3 + (stage / 6), 20 + (stage / 2));
		addMob(mob);
	}
	
	private void spawnFast(float x, float y) {
		MobBase mob = new MobFast(x, y, 3 + (stage / 6));
		addMob(mob);
	}
	
	private void addMob(MobBase mob) {
		SiegeVariables.quadTree.add(mob);
		SiegeVariables.mobs.add(mob);
		mobsSpawned++;
	}
	
	public void stopSpawn() {
		spawn = false;
	}
	
	public void newLevel() {
		mobsSpawned = 0;
		spawn = true;
		bossSpawned = false;
		
		mobTypes = SiegeVariables.res.getIntArray(R.array.mobTypes)[SiegeVariables.stage];
		
		spawnRate0 = SiegeVariables.res.getIntArray(R.array.spawnRate0)[SiegeVariables.stage];
		spawnRate1 = SiegeVariables.res.getIntArray(R.array.spawnRate1)[SiegeVariables.stage];
		spawnRate2 = SiegeVariables.res.getIntArray(R.array.spawnRate2)[SiegeVariables.stage];
//		spawnRate3 = SiegeVariables.res.getIntArray(R.array.spawnRate3)[SiegeVariables.stage];
//		spawnRate4 = SiegeVariables.res.getIntArray(R.array.spawnRate4)[SiegeVariables.stage];
//		spawnRate5 = SiegeVariables.res.getIntArray(R.array.spawnRate5)[SiegeVariables.stage];
		
		lastSpawn0 = SystemClock.uptimeMillis();
		lastSpawn1 = SystemClock.uptimeMillis();
		lastSpawn2 = SystemClock.uptimeMillis();
//		lastSpawn3 = SystemClock.uptimeMillis();
//		lastSpawn4 = SystemClock.uptimeMillis();
//		lastSpawn5 = SystemClock.uptimeMillis();
	}
}
