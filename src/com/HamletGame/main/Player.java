package com.HamletGame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends GameObject{

	private boolean LEFT, RIGHT, UP, DOWN;
	private Color color;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		color = Color.white;
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
		if(state == 0) { color = Color.white; }
		else if(state == 1) { color = Color.blue; }
		else if(state == 2) { color = Color.red; }
		else if(state == 3) { color = Color.green; }
		else if(state == 4) { color = Color.yellow; }
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 32, 32);
	}

	public void updateInput(int key, boolean state) {
		if(key == KeyEvent.VK_S) { DOWN=state; }
		if(key == KeyEvent.VK_W) { UP=state;; }
		if(key == KeyEvent.VK_D) { RIGHT=state;; }
		if(key == KeyEvent.VK_A) { LEFT=state;; }
		
	}
	
}
