package com.HamletGame.main.entities;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class NPC extends GameObject{
	
	private Game game;
	
	private ImageTile image;
	
	private boolean talking = false;
	private int dialogueNo = 1;
	private double dialogueTime = 0.5f; // once every 0.5 seconds
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
		if(dialogueRefresh <= 0.0f) {
			TextBox box = (TextBox)game.getHandler().getObjectByID(ID.TextBox);
			if(talking && box.isActive()) {
				box.setActive(false);
				
				// end conversation condition
				if(dialogueNo > 2) {
					dialogueNo = 2;
					talking = false;
					Player p = (Player)o;
					p.setCanMove(true);
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
	}
}
