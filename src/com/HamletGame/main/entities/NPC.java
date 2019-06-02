package com.HamletGame.main.entities;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class NPC extends GameObject{
	
	private Game game;
	
	private ImageTile image;
	
	private int dialogueNo = 1;
	private double dialogueTime = 1.0f; // once every 1 seconds
	private double dialogueRefresh = 0.0f; // how long we have
	
	private int animationX;
	private int animationY;

	public NPC(int x, int y, Game game, ID id) {
		super(x, y, id);
		this.game = game;
		// TODO: make image for Gertrude
		image = new ImageTile("/com/HamletGame/main/graphics/Player.png", 32, 32);
		interactable =  true;
	}

	@Override
	public void update(long delta) {
		
		if(dialogueRefresh > 0.0f) {
			dialogueRefresh -= ((double)delta/1000.0);
		}
		
	}

	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x, y, animationX, animationY, false);
	}

	@Override
	public void updateInput(int key, boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(GameObject o) {
		TextBox box = (TextBox)game.getHandler().getObjectByID(ID.TextBox);
		if(dialogueRefresh <= 0.0f) {
			System.out.println("yeeater");
			if(!box.isActive()) {
				System.out.println("hello");
				box.setText(dialogueNo);
				dialogueNo ++;
				dialogueRefresh = dialogueTime;
			} else {
				System.out.println(box.isActive());
				box.setActive(false);
			}
		}
	}
}
