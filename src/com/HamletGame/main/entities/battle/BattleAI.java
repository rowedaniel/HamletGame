package com.HamletGame.main.entities.battle;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;

public class BattleAI extends BattleObject{

	public BattleAI(int x, int y, Game game, ID id) {
		super(x, y, "/images/battlePolonius.png", false, game, id);
	}
	
	@Override
	public void update(long delta) {
		super.update(delta);
		if(!battleScreen.isActive() || dead) { return; }
		if(canDoAction) {
			canDoAction = false;
			int action = (int)(Math.random()*4);
			if(true) {
				contemplate(opponent);
			} else if(action == 0) {
				attack(opponent);
			} else if(action == 1) {
				heal(opponent);
			} else if(action == 2) {
				contemplate(opponent);
			} else if(action == 3) {
				defend(opponent);
			}
		}
	}
}
