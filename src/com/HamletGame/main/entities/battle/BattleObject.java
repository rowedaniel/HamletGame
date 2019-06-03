package com.HamletGame.main.entities.battle;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.entities.GameObject;
import com.HamletGame.main.graphics.ImageTile;

public class BattleObject extends GameObject{
	
	protected Game game;
	protected BattleScreen battleScreen;
	
	protected ImageTile image;
	protected Healthbar healthbar;
	
	protected int animationX;
	protected int animationY;
	protected double animationTime = 0.0f;
	protected double animationSpeed = 4.0f; //  fps
	protected double animationFactor = 1.0f;

	protected boolean canDoAction;
	
	protected int hp;
	protected int maxhp;
	protected int attack;
	protected int attackBase;
	protected int defense;
	protected int defenseBase;
	protected boolean dead;
	
	protected BattleObject opponent;
	
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
		
		maxhp = 100;
		hp = maxhp;
		attackBase = 10;
		attack = attackBase;
		defenseBase = 10;
		defense = defenseBase;
	}
	
	public void setOpponent(BattleObject opponent) {
		this.opponent = opponent;
	}

	@Override
	public void update(long delta) {
		if(!battleScreen.isActive()) { return; }
		if(hp <= 0 && !dead) {
			dead = true;
			//battleScreen.setActive(false);
			setAnimationState(5);
		}
		updateanimation(delta);
		if(canDoAction) {
			healthbar.setValue(hp, maxhp);
		}
	}
	
	public void updateanimation(long delta) {
		// update animation
		animationX = (int)animationTime;
		animationTime += ((double)delta*animationFactor*animationSpeed/1000.0);
		if(animationTime >= image.getTileNoX()) {
			if(dead) {animationTime=image.getTileNoX()-1;return;}
			if(animationY != 0) {
				opponent.setCanDoAction(true);
			}
			animationTime = 0;
			setAnimationState(0);
		}
	}

	@Override
	public void draw(Renderer r) {
		if(!battleScreen.isActive()) { return; }
		r.drawImageTile(image, x+battleScreen.getX(), y+battleScreen.getY(), animationX, animationY, true);
	}

	@Override
	public void updateInput(int key, boolean state) {
		if(!battleScreen.isActive()) { return; }
	}

	
	public void setAnimationState(int state) {
		if(state == 0) {
			animationY = 0;
		} else if(state<=5){
			animationX = 0;
			animationY = state;
			animationTime = 0.0f;
		}
	}
	
	public void attack(BattleObject o) {
		setAnimationState(1);
		int a = Math.max(0, attack + (int)(Math.random()*20-10));
		o.damage(a);
		// attacking lowers defense?
		defense -= a*2/3;
	}
	
	public void heal(BattleObject o) {
		setAnimationState(2);
		hp = Math.min(maxhp, hp+(int)(Math.random()*this.maxhp/2));
		healthbar.setValue(hp, maxhp);
	}
	
	public void contemplate(BattleObject o) {
		setAnimationState(3);
		// I like the idea of contemplate doing nothing
	}
	
	public void defend(BattleObject o) {
		setAnimationState(4);
		defense += (int)(Math.random()*10);
	}

	@Override
	public void interact(GameObject o) {
		if(!battleScreen.isActive()) { return; }
	}
	
	
	public void damage(int power) {
		if(!battleScreen.isActive()) { return; }
		hp = Math.max(0, hp+Math.min(0, defense-power));
	}
	
	
	
	public boolean isCanDoAction() {
		return canDoAction;
	}

	public void setCanDoAction(boolean canDoAction) {
		this.canDoAction = canDoAction;
	}	
}
