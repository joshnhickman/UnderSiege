package com.kerbii.undersiege;

public class QuadTree {
	
	float x, y;
	float width;
	QuadTreeNode overallRoot;
	
	public QuadTree() {
		x = 0;
		y = -400;
		width = 1280;
		
		overallRoot = new QuadTreeNode(x, y, width, createQuadTree(x, y, width / 2),
				createQuadTree(x + width / 2, y, width / 2), 
				createQuadTree(x + width / 2, y + width / 2, width / 2),
				createQuadTree(x, y + width / 2, width / 2), this);
		
		overallRoot.setParent(null);
		overallRoot.NW.setParent(overallRoot);
		overallRoot.NE.setParent(overallRoot);
		overallRoot.SE.setParent(overallRoot);
		overallRoot.SW.setParent(overallRoot);
		
	}
	
	private QuadTreeNode createQuadTree(float nodeX, float nodeY, float nodeWidth) {
		QuadTreeNode node;
		if (nodeWidth <=40) {
			node = new QuadTreeNode(nodeX, nodeY, nodeWidth);
		} else {
			node = new QuadTreeNode(nodeX, nodeY, nodeWidth, createQuadTree(nodeX, nodeY, nodeWidth / 2),
					createQuadTree(nodeX + nodeWidth / 2, nodeY, nodeWidth / 2), 
					createQuadTree(nodeX + nodeWidth / 2, nodeY + nodeWidth / 2, nodeWidth / 2),
					createQuadTree(nodeX, nodeY + nodeWidth / 2, nodeWidth / 2));
			node.NW.setParent(node);
			node.NE.setParent(node);
			node.SE.setParent(node);
			node.SW.setParent(node);
		}
		return node;
	}
	
	/**
	 * adds a mob to the quadtree
	 * @param mob - the mob to add
	 */
	public void add(MobBase mob) {
		overallRoot.add(mob);
	}
	
	/**
	 * adds an arrow to the quadtree
	 * @param arrow - the arrow to add
	 */
	public void add(ArrowBase arrow) {
		overallRoot.add(arrow);
	}
	
	public void update() {
		overallRoot.update();
	}
	
	public ArrayList<MobBase> query(float x, float y, float width, float height) {
		ArrayList<MobBase> mobList = new ArrayList<MobBase>();
		return overallRoot.query(x, y, width, height, mobList);
	}
	
	public ArrayList<MobBase> query(QuadTreeNode node) {
		ArrayList<MobBase> mobList = new ArrayList<MobBase>();
		if (!node.mobs.isEmpty()) mobList.addAll(node.mobs);
		if (node.parent != null) {
			queryUp(node.parent, mobList);
		}
		return mobList;
	}
	
	public ArrayList<MobBase> queryArea(QuadTreeNode node) {
		ArrayList<MobBase> mobList = new ArrayList<MobBase>();
		if (!node.mobs.isEmpty()) mobList.addAll(node.mobs);
		if (node.parent != null) {
			queryUp(node.parent, mobList);
		}
		if (!node.leaf){
			queryDown(node.NW, mobList);
			queryDown(node.NE, mobList);
			queryDown(node.SE, mobList);
			queryDown(node.SW, mobList);
		}
		return mobList;
	}
	
	private ArrayList<MobBase> queryUp(QuadTreeNode node, ArrayList<MobBase> mobList) {
		if (!node.mobs.isEmpty()) mobList.addAll(node.mobs);
		if (node.parent != null) {
			queryUp(node.parent, mobList);
		}
		return mobList;
	}
	
	private ArrayList<MobBase> queryDown(QuadTreeNode node, ArrayList<MobBase> mobList) {
		if (!node.mobs.isEmpty()) mobList.addAll(node.mobs);
		if (!node.leaf) {
			queryDown(node.NW, mobList);
			queryDown(node.NE, mobList);
			queryDown(node.SE, mobList);
			queryDown(node.SW, mobList);
		}
		return mobList;
	}
	
	public void clear() {
		overallRoot.clear();
	}
}
