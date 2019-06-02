package com.HamletGame.main.entities;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.entities.battle.BattleScreen;

public class AnimatedTile extends Tile{

	private Game game;
	
	private double idleTime = 0.0f;
	private double reactTime = 20; // 100 seconds
	private boolean animate;
	private double animationTime = 0.0f;
	private double animationSpeed = 5.0f; // 1/10 fps
	private double animationFactor = 1.0f;

	private boolean talking = false;
	private int dialogueNo = 1;
	private double dialogueTime = 0.5f; // once every 0.5 seconds
	private double dialogueRefresh = 0.0f; // how long we have
	
	public AnimatedTile(int x, int y, int r, String imgpath, Game game, ID id) {
		super(x, y, r, imgpath, id);
		this.game = game;
		interactable = true;
	}

	@Override
	public void update(long delta) {
		
		updateAnimation(delta);
		updateDialogue(delta);
	}
	
	public void updateDialogue(long delta) {
		if(dialogueRefresh > 0.0f) {
			dialogueRefresh -= ((double)delta/1000.0);
		}
		
		
		if(talking) {
			TextBox box = (TextBox)game.getHandler().getObjectByID(ID.TextBox);
			if(dialogueRefresh <= 0.0f) {
				if(!box.isActive()) {
					box.setText(dialogueNo);
					dialogueNo ++;
					dialogueRefresh = dialogueTime;
				}
			}
		}
	}
	
	public void updateAnimation(long delta) {
		if(idleTime >= reactTime) {
			idleTime = 0;
			setAnimationState(1);
		}
		
		if(animate) {
			// update animation
			animationX = (int)animationTime;
			animationTime += ((double)delta*animationFactor*animationSpeed/1000.0);
			if(animationX >= image.getTileNoX()) {
				animationX = 0;
				animationTime = 0.0f;
				setAnimationState(0);
			}
		} else {
			idleTime += ((double)delta*animationFactor*animationSpeed/1000.0);
		}
	}
	
	public void setAnimationState(int state) {
		if(state==0) {
			animate = false;
		}
		else {
			animate = true;
		}
	}
	
	@Override
	public void interact(GameObject o) {
		if(dialogueRefresh <= 0.0f) {
			TextBox box = (TextBox)game.getHandler().getObjectByID(ID.TextBox);
			if(talking && box.isActive()) {
				box.setActive(false);
				
				// end conversation condition
				if(dialogueNo == 1) {
					BattleScreen battleScreen = (BattleScreen)game.getHandler().getObjectByID(ID.BattleScreen);
					battleScreen.setActive(true);
					talking = false;
				} else if(dialogueNo > 1) {
					if(o.id==ID.Player) {
						Player p = (Player)o;
						p.setCanMove(true);
					}
					talking = false;
					dialogueNo = 1;
				}
				
			} else if(!talking) {
				talking = true;
				if(o.id==ID.Player) {
					Player p = (Player)o;
					p.setCanMove(false);
				}
			}
			dialogueRefresh = dialogueTime;
		}
		if(o.id == ID.Player) {
			Player p = (Player)o;
			if(p.hasTalkedToGertrude()) {
				dialogueNo = 0;
			} else {
				dialogueNo = 1;
			}
		}
	}
}
