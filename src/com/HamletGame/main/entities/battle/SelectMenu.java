package com.HamletGame.main.entities.battle;

import java.awt.event.KeyEvent;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.entities.GameObject;
import com.HamletGame.main.graphics.ImageTile;

public class SelectMenu extends GameObject{
	
	private BattleScreen battleScreen;
	
	private ImageTile image;
	
	private int animationX;
	private int animationY;
	
	public SelectMenu(int x, int y, BattleScreen battleScreen, ID id) {
		super(x, y, id);
		this.battleScreen = battleScreen;
		animationX = 0;
		animationY = 0;
		image = new ImageTile("/images/selectmenu.png", 128, 32);
	}

	@Override
	public void update(long delta) {		
	}

	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x+battleScreen.getX(), y+battleScreen.getY(), animationX, animationY, true);
	}

	@Override
	public void updateInput(int key, boolean state) {
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { animationY = 1;}
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { animationY = 0;}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { animationX = 1;}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { animationX = 0;}
		if(key == KeyEvent.VK_SPACE) {}
	}

	@Override
	public void interact(GameObject o) {
	}
	
}