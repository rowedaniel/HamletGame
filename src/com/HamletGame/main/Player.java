package com.HamletGame.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		setX(100);
		
		setVelX(10);
		setVelY(5);
	}
	
	public void update() {
		x += xvel;
		y += yvel;
		
		if(x<0 || x>800-32) { xvel = -xvel; }
		if(y<0 || y>400-32) { yvel = -yvel; }
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	
	
}
