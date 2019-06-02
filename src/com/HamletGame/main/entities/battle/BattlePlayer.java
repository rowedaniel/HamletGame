package com.HamletGame.main.entities.battle;

import java.awt.event.KeyEvent;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;

public class BattlePlayer extends BattleObject{

	private SelectMenu selectMenu;
	private int selectedMoveX = 0;
	private int selectedMoveY = 0;
	
	public BattlePlayer(int x, int y, Game game, ID id) {
		super(x, y, "/images/battlePlayer.png", true, game, id);
		selectMenu = new SelectMenu(x-128, y+32, battleScreen, ID.GenericSelectMenu);
		game.getHandler().addObject(selectMenu);
	}
	
	public void attemptAction(int action) {
		if(canDoAction) {
			BattleObject opponent = (BattleObject)game.getHandler().getObjectByID(ID.BattlePolonius);
			if(action == 0) {
				attack(opponent);
			} else if(action == 1) {
				contemplate(opponent);
			} else if(action == 2) {
				heal(opponent);
			} else if(action == 3) {
				defend(opponent);
			}
		}
	}

	@Override
	public void updateInput(int key, boolean state) {
		super.updateInput(key, state);
		selectMenu.updateInput(key, state);
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { selectedMoveY = 1;}
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { selectedMoveY = 0;}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { selectedMoveX = 1;}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { selectedMoveX = 0;}
		if(key == KeyEvent.VK_SPACE) { attemptAction(selectedMoveX+selectedMoveY*2);}
	}
}
