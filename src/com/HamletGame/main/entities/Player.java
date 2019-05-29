package com.HamletGame.main.entities;

import java.awt.event.KeyEvent;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class Player extends GameObject{

	private boolean LEFT, RIGHT, UP, DOWN;
	private ImageTile image;
	private int animationX;
	private int animationY;
	private double animationTime = 0.0f;
	private long animationSpeed = (long)10000000 * 1000; // 1/10 fps
	private double animationFactor = 1.0f;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		image = new ImageTile("/images/Player.png", 32, 32);
		animationX = 0;
		animationY = 0;
	}
	
	@Override
	public void update(long delta) {
		x += xvel*(delta/1000000000); // 10**-9 * xvel px/s
		y += yvel*(delta/1000000000);
		extraX += xvel*(delta/1000000000.0) - xvel*delta/1000000000;
		extraY += yvel*(delta/1000000000.0) - yvel*delta/1000000000;
		x += (int)extraX;
		y += (int)extraY;
		extraX -= (int)extraX;
		extraY -= (int)extraY;
		
		// update animation
		animationX = (int)animationTime;
		animationTime += ((double)delta*animationFactor/animationSpeed);
		animationTime %= 8;
		
		 if(LEFT) {
			 setAnimationState(2);
			 setVelX(-1);
		 } else if(RIGHT) {
			 setAnimationState(4);
			 setVelX(1);
		 } else {
			 setVelX(0);
		 }
		 if(UP) {
			 setAnimationState(1);
			 setVelY(-1);
		 } else if(DOWN) {
			 setAnimationState(3);
			 setVelY(1);
		 } else {
			 setVelY(0);
		 }
		 if(!(LEFT || RIGHT || UP || DOWN)) {
			 setAnimationState(0);
		 }
	}
	
	public void setAnimationState(int state) {
		if(state == 0) {
			if(animationFactor != 0.0) {
				animationFactor = 0.0;
			}
			if(animationX != 0) {
				animationX = 0;
			}
			
		}
		else if(state > 0 && state < 8) {
			if(animationFactor == 0.0) {
				animationFactor = 1.0;
			}
			animationY = state;
		}
	}
	
	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x, y, animationX, animationY);
	}

	public void updateInput(int key, boolean state) {
		if(key == KeyEvent.VK_S) { DOWN=state; }
		if(key == KeyEvent.VK_W) { UP=state;; }
		if(key == KeyEvent.VK_D) { RIGHT=state;; }
		if(key == KeyEvent.VK_A) { LEFT=state;; }
	}
	
}