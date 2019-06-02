package com.HamletGame.main.entities;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class Overlay extends GameObject{
	protected int animationX;
	protected int animationY;
	
	protected double speed;
	
	protected boolean active;
	
	protected int activeX;
	protected int activeY;
	
	protected int inactiveX;
	protected int inactiveY;
	
	protected ImageTile image;

	public Overlay(int activeX, int activeY, int inactiveX, int inactiveY, double speed, int imgW, int imgH, String imagesrc, ID id) {
		super(inactiveX, inactiveY, id);
		this.activeX = activeX;
		this.activeY = activeY;
		this.inactiveX = inactiveX;
		this.inactiveY = inactiveY;
		this.speed = speed;
		image = new ImageTile(imagesrc, imgW, imgH);
		animationX = 0;
		animationY = 0;
		active = false;
	}

	@Override
	public void update(long delta) {
		
		updatepos(delta);
		
		
		if(active && !(x==activeX && y==activeY)) {
			if(x < activeX) {
				xvel = 1;
			} else if(x > activeX) {
				xvel = -1;
			} else {
				xvel = 0;
			}
			if(y < activeY) {
				yvel = 1;
			} else if(y > activeY) {
				yvel = -1;
			} else {
				yvel = 0;
			}
		}
		if(!active && !(x==inactiveX && y==inactiveY)) {
			if(x < inactiveX) {
				xvel = 1;
			} else if(x > inactiveX) {
				xvel = -1;
			} else {
				xvel = 0;
			}
			if(y < inactiveY) {
				yvel = 1;
			} else if(y > inactiveY) {
				yvel = -1;
			} else {
				yvel = 0;
			}
		}
	}
	
	public void updatepos(long delta) {
		x += xvel*(delta/1000*speed); // 10**-9 * xvel px/s
		y += yvel*(delta/1000/speed);
		extraX += xvel*(delta/1000.0*speed) - xvel*delta/1000*speed;
		extraY += yvel*(delta/1000.0*speed) - yvel*delta/1000*speed;
		x += (int)extraX;
		y += (int)extraY;
		extraX -= (int)extraX;
		extraY -= (int)extraY;
		
		// hard wired stuff
		y = Math.max(activeY, Math.min(inactiveY, y));
	}

	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x, y, animationX, animationY, true);
	}

	@Override
	public void updateInput(int key, boolean state) {		
	}

	@Override
	public void interact(GameObject o) {
	}
	
	

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setText(int state) {
		if(0 <= state && state < image.getTileNoY()) {
			animationY = state;
			this.active = true;
		}
	}
}