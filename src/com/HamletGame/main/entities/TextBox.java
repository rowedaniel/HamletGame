package com.HamletGame.main.entities;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class TextBox extends GameObject{
	
	private int animationX;
	private int animationY;
	
	private double speed = 300.0f;
	
	private boolean moving;
	private boolean active;
	
	private static int activeX = 64;
	private static int activeY = 120;
	
	private static int inactiveX = 64;
	private static int inactiveY = 160;
	
	private ImageTile image;

	public TextBox(ID id) {
		super(inactiveX, inactiveY, id);
		image = new ImageTile("/images/TextBox.png", 128, 32);
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