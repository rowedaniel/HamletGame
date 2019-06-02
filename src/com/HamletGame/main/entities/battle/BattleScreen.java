package com.HamletGame.main.entities.battle;

import com.HamletGame.main.ID;
import com.HamletGame.main.entities.Overlay;

public class BattleScreen extends Overlay{
	public BattleScreen(ID id) {
		super(0, 0, -256, 0, 600.0f, 256, 158, "/images/BattleBackground.png",id);
		active = true;
	}
}