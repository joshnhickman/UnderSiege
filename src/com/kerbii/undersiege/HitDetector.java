package com.kerbii.undersiege;

public class HitDetector {
	
//	private QuadTree quadTree;
	
	public HitDetector() {
//		this.quadTree = quadTree;
	}
	
	public void hitDetect(ArrowBase arrow) {
		if (arrow.state == ArrowBase.FLYING) { 
			ArrayList<MobBase> mobList;
			mobList = SiegeVariables.quadTree.query(arrow.node);
			if (mobList != null && !mobList.isEmpty()) {
				MobBase mob;
				for (int i = 0; i < mobList.size(); i++) {
					mob = mobList.get(i);
					if (mob.alive && arrow.y > mob.y && arrow.y < mob.y + mob.width) {
						if (arrow.x > mob.x && arrow.x < mob.x + mob.width) {
							float damage = arrow.damage();
							float health = mob.health();
							mob.hit(arrow, damage);
							arrow.hit(mob, health);
							if (arrow.state == ArrowBase.EMBEDDED || arrow.state == ArrowBase.BROKEN) break;
						}
					}
				}
			}
		} else if (arrow.state == ArrowBase.EFFECT) {
			ArrayList<MobBase> mobList;
			mobList = SiegeVariables.quadTree.queryArea(arrow.node);
			if (mobList != null && !mobList.isEmpty()) {
				ArrayList<MobBase> mobsInRange = new ArrayList<MobBase>();
				MobBase mob;
				for (int i = 0; i < mobList.size(); i++) {
					mob = mobList.get(i);
					if (mob.alive && mob.x < arrow.x + arrow.width && mob.x + mob.width > arrow.x) {
						if (mob.y < arrow.y + arrow.height && mob.y + mob.height > arrow.y) {
							mobsInRange.add(mob);
						}
					}
				}
				mobsInRange = arrow.hitMobs(mobsInRange);
				for (int i = 0; i < mobsInRange.size(); i++) {
					mob = mobsInRange.get(i);
					float damage = arrow.damage();
					float health = mob.health();
					mob.hit(arrow, damage);
					arrow.hit(mob, health);
				}
			}
		}
	}
}
