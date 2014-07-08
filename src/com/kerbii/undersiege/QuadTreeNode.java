package com.kerbii.undersiege;

public class QuadTreeNode {
	
	float x, y;
	float width;
	
	static QuadTree quadTree;
	QuadTreeNode parent;
	QuadTreeNode N, E, S, W;
	QuadTreeNode NW, NE, SE, SW;
	boolean leaf;
	
	ArrayList<MobBase> mobs;
	ArrayList<ArrowBase> arrows;
	ArrayList<EffectBase> effects;
	
	/**
	 * constructs arrow leaf
	 * @param x
	 * @param y
	 * @param width
	 */
	public QuadTreeNode(float x, float y, float width) {
		this(x, y, width, null, null, null, null);
	}
	
	public QuadTreeNode(float x, float y, float width, QuadTreeNode NW, QuadTreeNode NE, QuadTreeNode SE, QuadTreeNode SW, QuadTree quadTree) {
		this(x, y, width, NW, NE, SE, SW);
		QuadTreeNode.quadTree = quadTree;
	}
	
	/**
	 * constructs arrow node
	 * @param x
	 * @param y
	 * @param width
	 * @param NW
	 * @param NE
	 * @param SE
	 * @param SW
	 */
	public QuadTreeNode(float x, float y, float width, QuadTreeNode NW, QuadTreeNode NE, QuadTreeNode SE, QuadTreeNode SW) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.NW = NW;
		this.NE = NE;
		this.SE = SE;
		this.SW = SW;
		leaf = (NW == null && NE == null && SE == null && SW == null);
		
		mobs = new ArrayList<MobBase>();
		arrows = new ArrayList<ArrowBase>();
		effects = new ArrayList<EffectBase>();
	}
	
	public void setParent(QuadTreeNode parent) {
		this.parent = parent;
	}
	
	/**
	 * recursively adds arrow mob to the smallest possible square
	 * @param mob - the mob to add
	 */
	public void add(MobBase mob) {
		if (leaf) {
			mobs.add(mob);
			mob.setNode(this);
		} else if (mob.y + mob.width < NW.y + NW.width) {
			if (mob.x + mob.width < NW.x + NW.width) {
				NW.add(mob);
			} else if (mob.x > NE.x) {
				NE.add(mob);
			} else {
				mobs.add(mob);
				mob.setNode(this);
			}
		} else if (mob.y > SW.y) {
			if (mob.x + mob.width < SW.x + SW.width) {
				SW.add(mob);
			} else if (mob.x > SE.x) {
				SE.add(mob);
			} else {
				mobs.add(mob);
				mob.setNode(this);
			}
		} else {
			mobs.add(mob);
			mob.setNode(this);
		}
	}
	
	/**
	 * recursively adds an arrow to the smallest possible square
	 * @param arrow - the arrow to add
	 */
	public void add(ArrowBase arrow) {
		if (arrow.state == ArrowBase.FLYING) {
			if(leaf) {
				arrows.add(arrow);
				arrow.setNode(this);
			} else if (arrow.y < NW.y + NW.width) {
				if (arrow.x < NW.x + NW.width) {
					NW.add(arrow);
				} else if (arrow.x > NE.x) {
					NE.add(arrow);
				} else {
					arrows.add(arrow);
					arrow.setNode(this);
				}
			} else if (arrow.y > SW.y) {
				if (arrow.x < SW.x + SW.width) {
					SW.add(arrow);
				} else if (arrow.x > SE.x) {
					SE.add(arrow);
				} else {
					arrows.add(arrow);
					arrow.setNode(this);
				}
			} else {
				arrows.add(arrow);
				arrow.setNode(this);
			}
		} else if (arrow.state == ArrowBase.EFFECT) {
			if (leaf) {
				arrows.add(arrow);
				arrow.setNode(this);
			} else if (arrow.y + arrow.width < NW.y + NW.width) {
				if (arrow.x + arrow.width < NW.x + NW.width) {
					NW.add(arrow);
				} else if (arrow.x > NE.x) {
					NE.add(arrow);
				} else {
					arrows.add(arrow);
					arrow.setNode(this);
				}
			} else if (arrow.y > SW.y) {
				if (arrow.x + arrow.width < SW.x + SW.width) {
					SW.add(arrow);
				} else if (arrow.x > SE.x) {
					SE.add(arrow);
				} else {
					arrows.add(arrow);
					arrow.setNode(this);
				}
			} else {
				arrows.add(arrow);
				arrow.setNode(this);
			}
		}
	}
	
	public void update() {
		if (!leaf) {
			NW.update();
			NE.update();
			SE.update();
			SW.update();
		}
		
		//Check and update mobs
		if (!mobs.isEmpty()) {
			MobBase mob;
			for (int i = 0; i < mobs.size(); i++) {
				mob = mobs.get(i);
				if (!mob.alive) {
					mobs.remove(i);
					i--;
				} else if (mob.x < x || mob.y < y || mob.y + mob.width > y + width || mob.x + mob.width > x + width) {
					quadTree.add(mob);
					mobs.remove(i);
					i--;
				}
			}
		}
		
		//Check and update arrows
		if (!arrows.isEmpty()) {
			ArrowBase arrow;
			for (int i = 0; i < arrows.size(); i++) {
				arrow = arrows.get(i);
				if (arrow.state == ArrowBase.FLYING) {
					if (!arrow.draw) {
						arrows.remove(i);
						i--;
					} else if (arrow.x > x + width || arrow.y < y || arrow.y > y + width || arrow.x < x) {
						arrows.remove(i);
						i--;
						quadTree.add(arrow);
					}
				} else if (arrow.state == ArrowBase.EFFECT) {
					if (arrow.width > width || arrow.x > x + width || arrow.x + arrow.width < x ||
							arrow.y > y + width || arrow.y + arrow.height < y) {
						arrows.remove(i);
						i--;
						quadTree.add(arrow);
					}
//					arrows.remove(i);
//					i--;
//					quadTree.add(arrow);
				} else {
					arrows.remove(i);
					i--;
				}
			}
		}
	}
	
	public void clear() {
		mobs.clear();
		arrows.clear();
		if (!leaf) {
			NW.clear();
			NE.clear();
			SE.clear();
			SW.clear();
		}
	}
	
	public ArrayList<MobBase> query(float x, float y, float width, float height, ArrayList<MobBase> mobList) {
		if (leaf) {
			mobList.addAll(mobs);
		} else if (y + height < NW.y + NW.width) {
			if (x + width < NW.x + NW.width) {
				NW.query(x, y, width, height, mobList);
			} else if (x > NE.x) {
				NE.query(x, y, width, height, mobList);
			} else {
				mobList.addAll(mobs);
			}
		} else if (y > SW.y) {
			if (x + width < SW.x + SW.width) {
				SW.query(x, y, width, height, mobList);
			} else if (x > SE.x) {
				SE.query(x, y, width, height, mobList);
			} else {
				mobList.addAll(mobs);
			}
		}
		mobList.addAll(mobs);
		return mobList;
	}
}
