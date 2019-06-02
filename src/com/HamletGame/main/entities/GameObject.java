package com.HamletGame.main.entities;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.Image;

public abstract class GameObject {

	protected ID id;
	
	protected int x, y;
	protected double extraX, extraY;
	protected int xvel, yvel;
	protected boolean interactable = false;
	
	protected Image image;
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
		this.xvel = 0;
		this.yvel = 0;
		
		this.extraX = 0.0f;
		this.extraY = 0.0f;
	}
	
	public abstract void update(long delta);
	
	public abstract void draw(Renderer r);
	
	public abstract void updateInput(int key, boolean state);
	
	public abstract void interact(GameObject o);
	
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
