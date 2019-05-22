package com.HamletGame.main;

import java.awt.Graphics;

public abstract class GameObject {

	protected ID id;
	
	protected int x, y;
	protected int xvel, yvel;
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
		this.xvel = 0;
		this.yvel = 0;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getID() {
		return id;
	}
	public void setVelX(int xvel) {
		this.xvel = xvel;
	}
	public void setVelY(int yvel) {
		this.yvel = yvel;
	}
	public int getVelX() {
		return xvel;
	}
	public int getVelY() {
		return yvel;
	}
}
