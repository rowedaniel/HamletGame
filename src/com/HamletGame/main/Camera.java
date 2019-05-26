package com.HamletGame.main;

import com.HamletGame.main.entities.GameObject;

public class Camera {

	private int x;
	private int y;
	
	private int offX;
	private int offY;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
		offX = 128;
		offY = 128 * 10000 / 16180;
	}
	
	public void follow(GameObject o) {
		x = o.getX()-offX;
		y = o.getY()-offY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
