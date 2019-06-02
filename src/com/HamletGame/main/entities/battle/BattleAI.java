package com.HamletGame.main.entities.battle;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;

public class BattleAI extends BattleObject{

	public BattleAI(int x, int y, String imagesrc, boolean canDoAction, Game game, ID id) {
		super(x, y, imagesrc, canDoAction, game, id);
	}
	
	@Override
	public void update(long delta) {
		super.update(delta);
		if(hp<=0) {
			System.out.println("ded");
			battleScreen.setActive(false);
		}
	}
}
