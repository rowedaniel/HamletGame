package com.HamletGame.main.entities.battle;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.entities.GameObject;
import com.HamletGame.main.graphics.ImageTile;

public class Healthbar extends GameObject{
	
	private BattleScreen battleScreen;
	
	private ImageTile image;
	
	private int animationX;
	private int animationY;
	
	public Healthbar(int x, int y, BattleScreen battleScreen, ID id) {
		super(x, y, id);
		this.battleScreen = battleScreen;
		animationX = 0;
		animationY = 0;
		image = new ImageTile("/images/healthbar.png", 32, 4);
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
	}

	@Override
	public void interact(GameObject o) {
	}
	
	public void setValue(int value, int maxvalue) {
		//animationY = value*(image.getTileNoY())/maxvalue;
		//System.out.println("animationY "+animationY);
	}
	
}