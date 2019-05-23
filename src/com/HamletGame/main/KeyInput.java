package com.HamletGame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.entities.size(); i++) {
			GameObject o = handler.entities.get(i);
			
			if(o.getID() == ID.Player) {
				o.updateInput(key, true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i = 0; i < handler.entities.size(); i++) {
			GameObject o = handler.entities.get(i);
			
			if(o.getID() == ID.Player) {
				o.updateInput(key, false);
			}
		}
	}
	
}
