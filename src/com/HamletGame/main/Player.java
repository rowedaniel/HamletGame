package com.HamletGame.main;

import java.awt.event.KeyEvent;

import com.HamletGame.main.graphics.Image;

public class Player extends GameObject{

	private boolean LEFT, RIGHT, UP, DOWN;
	private Image image;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		image = new Image("/images/Floor0.png");
	}
	
	public void update() {
		x += xvel;
		y += yvel;
		
		 if(LEFT) {
			 setAnimationState(1);
			 setVelX(-10);
		 } else if(RIGHT) {
			 setAnimationState(2);
			 setVelX(10);
		 } else {
			 setVelX(0);
		 }
		 if(UP) {
			 setAnimationState(3);
			 setVelY(-10);
		 } else if(DOWN) {
			 setAnimationState(4);
			 setVelY(10);
		 } else {
			 setVelY(0);
		 }
		 
		 if(!(UP || DOWN || LEFT || RIGHT)) {
			 setAnimationState(0);
		 }
	}
	
	public void setAnimationState(int state) {
	}
	
	public void draw(Renderer r) {
		r.drawImage(image, x, y);
	}

	public void updateInput(int key, boolean state) {
		if(key == KeyEvent.VK_S) { DOWN=state; }
		if(key == KeyEvent.VK_W) { UP=state;; }
		if(key == KeyEvent.VK_D) { RIGHT=state;; }
		if(key == KeyEvent.VK_A) { LEFT=state;; }
		
	}
	
}
