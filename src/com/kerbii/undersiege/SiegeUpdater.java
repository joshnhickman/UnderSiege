package com.kerbii.undersiege;

import android.os.SystemClock;

/**
 * Holds the main update loop for the game
 * 
 * @author Josh Hickman
 *
 */
public class SiegeUpdater implements Runnable {
	
	public SiegeUpdater() {
		
	}
	
	public void run() {
		long finalDelta = 0;
		while (Thread.currentThread() == SiegeVariables.siegeUpdater) {
			if (SiegeVariables.isPaused){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Interruptions here are no big deal.
                }
			} else if (!SiegeVariables.isLoaded) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Interruptions here are no big deal.
                }
			} else {
				final long time = SystemClock.uptimeMillis();
				switch(SiegeVariables.state) {
				case SiegeVariables.STARTING:
					
					break;
				case SiegeVariables.PLAYING:
					
					SiegeVariables.ground.update(finalDelta);
					if (SiegeVariables.castle != null) SiegeVariables.castle.update(finalDelta);
					
					synchronized (SiegeVariables.mobsDead) {
						for (int i = 0; i < SiegeVariables.mobsDead.size(); i++) {
							MobBase mob = (MobBase) SiegeVariables.mobsDead.get(i);
							mob.update(finalDelta);
							if (!mob.draw) {
								SiegeVariables.mobsDead.remove(i);
								i--;
							}
						}
					}
					
					synchronized (SiegeVariables.mobs) {
						for (int i = 0; i < SiegeVariables.mobs.size(); i++) {
							MobBase mob = (MobBase) SiegeVariables.mobs.get(i);
							mob.update(finalDelta);
							if (!mob.draw) {
								SiegeVariables.mobs.remove(i);
								i--;
							}
							if (!mob.alive) {
								float goldAmount;
								goldAmount = Math.round(mob.goldMult * mob.x * (1 + 2 * mob.speedY) / (float) 100);
								SiegeVariables.gold += goldAmount;
								SiegeVariables.killCount++;
								SiegeVariables.mobsKilled++;
							}
						}
					}
					
					synchronized (SiegeVariables.arrows) {
						for (int i = 0; i < SiegeVariables.arrows.size(); i++) {
							GameObject arrow = SiegeVariables.arrows.get(i);
							arrow.update(finalDelta);
							SiegeVariables.hitDetector.hitDetect((ArrowBase) arrow);
							if (!arrow.draw) {
								SiegeVariables.arrows.remove(i);
								i--;
							}
						}
					}
				
					synchronized (SiegeVariables.effectsGround) {
						for (int i = 0; i < SiegeVariables.effectsGround.size(); i++) {
							EffectBase effect = (EffectBase) SiegeVariables.effectsGround.get(i);
							effect.update(finalDelta);
							if (!effect.draw) {
								SiegeVariables.effectsGround.remove(i);
								i--;
							}
						}
					}
				
					synchronized (SiegeVariables.effectsAir) {
						for (int i = 0; i < SiegeVariables.effectsAir.size(); i++) {
							EffectBase effect = (EffectBase) SiegeVariables.effectsAir.get(i);
							effect.update(finalDelta);
							if (!effect.draw) {
								SiegeVariables.effectsAir.remove(i);
								i--;
							}
						}
					}
					
					synchronized (SiegeVariables.archers) {
						for (int i = 0; i < SiegeVariables.archers.size(); i++) {
							GameObject archer = SiegeVariables.archers.get(i);
							archer.update(finalDelta);
						}
					}
					
					//Update helper classes
					if (SiegeVariables.quadTree != null) SiegeVariables.quadTree.update();
					if (SiegeVariables.mobFactory != null) SiegeVariables.mobFactory.update(finalDelta);
					if (SiegeVariables.arrowFactory != null) SiegeVariables.arrowFactory.update(finalDelta);
					
					//Handle level end
					if (SiegeVariables.mobFactory.mobsSpawned >= SiegeVariables.mobsThisLevel) {
							if (SiegeVariables.mobFactory.spawn) SiegeVariables.mobFactory.stopSpawn();
							if (SiegeVariables.mobs.isEmpty() && SiegeVariables.mobsDead.isEmpty()) SiegeVariables.siegeController.finishLevel();
					}
					
					if (SiegeVariables.castleHealth <= 0) {
						//game over! revert to beginning of level
						SiegeVariables.siegeController.gameOver();					
					}
					
					//development stuff
					SiegeVariables.manaDisplay.newInt = (int) SiegeVariables.mana;
					SiegeVariables.manaDisplay.update(finalDelta);
					
					break;
				case SiegeVariables.PAUSED:
					
					break;
				case SiegeVariables.UPGRADING:
					
					break;
				}
				
				SiegeVariables.menuHandler.update(finalDelta);
				
				final long endTime = SystemClock.uptimeMillis() - time;
				if (endTime < 16) {
	                try {
	                    Thread.sleep(16 - endTime);
	                } catch (InterruptedException e) {
	                    // Interruptions here are no big deal.
	                }
				}
				finalDelta = SystemClock.uptimeMillis() - time;
			}
		}
	}
}