package com.HamletGame.main.entities.battle;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.entities.GameObject;
import com.HamletGame.main.graphics.ImageTile;

public class BattleObject extends GameObject{
	
	private Game game;
	private BattleScreen battleScreen;
	
	private ImageTile image;
	private Healthbar healthbar;
	
	private int animationX;
	private int animationY;

	private boolean canDoAction;
	
	private int hp;
	private int maxhp;
	private int attackBase;
	private int defenseBase;
	
	public BattleObject(int x, int y, String imagesrc, boolean canDoAction, Game game, ID id) {
		super(x, y, id);
		this.game = game;
		// very bad practice alert!!! BEEP BEEP BEEP
		this.battleScreen = (BattleScreen)game.getHandler().getObjectByID(ID.BattleScreen);
		this.canDoAction = canDoAction;
		animationX = 0;
		animationY = 0;
		image = new ImageTile(imagesrc, 64, 64);
		healthbar = new Healthbar(x+16, y+64, battleScreen, ID.GenericHealthbar);
		game.getHandler().addObject(healthbar);
		
		maxhp = 10;
		hp = maxhp;
		attackBase = 1;
		defenseBase = 1;
	}

	@Override
	public void update(long delta) {
		healthbar.setValue(hp, maxhp);
	}

	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x+battleScreen.getX(), y+battleScreen.getY(), animationX, animationY, true);
	}

	@Override
	public void updateInput(int key, boolean state) {
		// TODO Auto-generated method stub
	}
	
	public void attack(BattleObject o) {
		
	}

	@Override
	public void interact(GameObject o) {
	}
	
}
