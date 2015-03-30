package com.geekmecrazy.madandarmed.IA;

public class Node {

	private int value; //use for ID (usefull at the reset step for performance)

	//Astar variables
	private float f; //g+h
	private float g; //cout du chemin parcouru
	private float h; //cout restant pour arriver

	//Path variables
	private Node father=null;
	private Node son=null;

    //Les nodes voisines
    //REPERE CARTESIEN (0,0)=bas-gauche
    private Node neighborhoodNodes[][] = new Node[3][3];
    public float neighborhoodNodesCost[][] = new float[3][3];

	//Cordonnees relative dans le tableau des smallNode
	//(pour calcul de distance dans Astar)
	private int x;
	private int y;

	/**********************************************************/

	public Node(int x_, int y_) {
		this.x = x_;
		this.y = y_;
	}
		
	public void init(float maxF_, int value_){
		value=value_;
		f=maxF_;
		father=null;
		son=null;
	}
	
	
	public float getF() {
        if(AstarMap.usedNodes.isUsed(this.x, this.y)) return f;
        return AstarMap.fMax;
    }

	public void setF(float f) {
        AstarMap.usedNodes.setUsed(this.x, this.y); //on set la node comme reperée et utilisée (methode du resetMap par morceau, perf...)
        this.f = f; }

	public float getG() {	return g; }

	public void setG(float g) { this.g = g; }
	
	public float getH() {	return h; }

	public void setH(float h) { this.h = h; }

	public Node getFather() {
        if(AstarMap.usedNodes.isUsed(this.x, this.y)) return father;
        return null;
    }

	public void setFather(Node parent) {
        AstarMap.usedNodes.setUsed(this.x, this.y); //on set la node comme reperée et utilisée (methode du resetMap par morceau, perf...)
        this.father = parent; }
	
	public Node getSon() {
        if(AstarMap.usedNodes.isUsed(this.x, this.y)) return son;
        return null;
    }

	public void setSon(Node son) {
        AstarMap.usedNodes.setUsed(this.x, this.y); //on set la node comme reperée et utilisée (methode du resetMap par morceau, perf...)
        this.son = son; }
	
	public int getValue() {	return value; }

	public void setValue(int value) { this.value = value; }

	public int getX() { return x; }

	public void setX(int x) {	this.x = x;	}

	public int getY() { return y;	}

	public void setY(int y) {	this.y = y; }

    public float getNeighborhoodNodesCost(int x_, int y_){ return neighborhoodNodesCost[x_][y_]; }

    public void setNeighborhoodNodesCost(int x_, int y_, float cost_){ neighborhoodNodesCost[x_][y_] = cost_; }

    public Node getNeighborhoodNodes(int x_, int y_){ return neighborhoodNodes[x_][y_]; }

    public void setNeighborhoodNodes(int x_, int y_, Node node_){ neighborhoodNodes[x_][y_] = node_; }

}
