package com.HamletGame.main;

import java.awt.event.KeyListener;

import com.HamletGame.main.entities.GameObject;

import java.awt.event.KeyEvent;

public class KeyInput implements KeyListener{
	
	private Game game;
	
	public KeyInput(Game game) {
		this.game = game;
		game.getWindow().getCanvas().addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < game.getHandler().entities.size(); i++) {
			GameObject o = game.getHandler().entities.get(i);
			
			if(o.getID() == ID.Player) {
				o.updateInput(key, true);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i = 0; i < game.getHandler().entities.size(); i++) {
			GameObject o = game.getHandler().entities.get(i);
			
			if(o.getID() == ID.Player) {
				o.updateInput(key, false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
