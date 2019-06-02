package com.HamletGame.main.entities.battle;

import java.awt.event.KeyEvent;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;

public class BattlePlayer extends BattleObject{

	private SelectMenu selectMenu;
	
	public BattlePlayer(int x, int y, Game game, ID id) {
		super(x, y, "/images/battlePlayer.png", true, game, id);
		selectMenu = new SelectMenu(x-128, y+32, battleScreen, ID.GenericSelectMenu);
		game.getHandler().addObject(selectMenu);
	}

	public void updateInput(int key, boolean state) {
		selectMenu.updateInput(key, state);
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {}
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {}
		if(key == KeyEvent.VK_SPACE) {}
	}
}
