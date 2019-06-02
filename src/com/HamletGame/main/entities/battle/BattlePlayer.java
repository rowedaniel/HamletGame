package com.HamletGame.main.entities.battle;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;

public class BattlePlayer extends BattleObject{

	private SelectMenu selectMenu;
	
	public BattlePlayer(int x, int y, Game game, ID id) {
		super(x, y, "/images/battlePlayer.png", true, game, id);
		selectMenu = new SelectMenu(x-128, y+32, battleScreen, ID.GenericSelectMenu);
		game.getHandler().addObject(selectMenu);
	}

}
