package com.HamletGame.main.entities;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import com.HamletGame.main.Game;
import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class Player extends GameObject{

	private Game game;
	private boolean canMove = true;
	private boolean LEFT, RIGHT, UP, DOWN;
	private double walkSpeed = 40.0f; // px/s
	private ImageTile image;
	private int animationX;
	private int animationY;
	private double animationTime = 0.0f;
	private double animationSpeed = 8.0f; //  fps
	private double animationFactor = 1.0f;
	
	
	
	// plot stuff
	private boolean talkedToGertrude = false;
	
	public Player(int x, int y, Game game, ID id) {
		super(x, y, id);
		this.game = game;
		image = new ImageTile("/com/HamletGame/main/graphics/Player.png", 32, 32);
		animationX = 0;
		animationY = 0;
	}
	
	@Override
	public void update(long delta) {
		

		if(canMove) {
			updatepos(delta);
		}
		
		updateanimation(delta);
		if(LEFT) {
			setAnimationState(2);
			setVelX(-1);
		} else if(RIGHT) {
			setAnimationState(4);
			setVelX(1);
		} else {
			setVelX(0);
		}
		if(UP) {
			setAnimationState(1);
			setVelY(-1);
		} else if(DOWN) {
			setAnimationState(3);
			setVelY(1);
		} else {
			setVelY(0);
		}
		if(RIGHT && DOWN) {
			setAnimationState(5);
        }
        if(LEFT && DOWN) {
        	setAnimationState(6);
        }
        if(RIGHT && UP) {
        	setAnimationState(7);
        }
        if(LEFT && UP) {
        	setAnimationState(8);
        }
		if(!(LEFT || RIGHT || UP || DOWN)) {
			setAnimationState(0);
		}
	}
	
	public void setAnimationState(int state) {
		if(state == 0) {
			if(animationFactor != 0.0) {
				animationFactor = 0.0;
			}
			if(animationX != 0) {
				animationX = 0;
			}
			
		}
		else if(state > 0 && state < 9) {
			if(animationFactor == 0.0) {
				animationFactor = 1.0;
			}
			animationY = state;
		}
	}

	public void updatepos(long delta) {
		x += xvel*(delta/1000*walkSpeed); // 10**-9 * xvel px/s
		y += yvel*(delta/1000/walkSpeed);
		extraX += xvel*(delta/1000.0*walkSpeed) - xvel*delta/1000*walkSpeed;
		extraY += yvel*(delta/1000.0*walkSpeed) - yvel*delta/1000*walkSpeed;
		x += (int)extraX;
		y += (int)extraY;
		extraX -= (int)extraX;
		extraY -= (int)extraY;

		// fake collision
		x = Math.min(300,Math.max(48, x));
		y = Math.min(300,Math.max(48, y));
	}
	
	public void updateanimation(long delta) {
		// update animation
		animationX = (int)animationTime;
		animationTime += ((double)delta*animationFactor*animationSpeed/1000.0);
		animationTime %= 8;
	}
	

	public void examine() {
		LinkedList<GameObject> entities = game.getHandler().getEntities();
		GameObject e;
		for(int i=0; i<entities.size(); i++) {
			e = entities.get(i);
			if (e.id != this.id && e.interactable && ((e.x-this.x)*(e.x-this.x)+(e.y-this.y)*(e.y-this.y) <= 1024)) { // not self and close
				entities.get(i).interact(this);
				break;
			}
		}
	}
	
	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x, y, animationX, animationY, false);
	}

	@Override
	public void updateInput(int key, boolean state) {
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { DOWN=state; }
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { UP=state; }
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { RIGHT=state; }
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { LEFT=state; }
		if(key == KeyEvent.VK_SPACE) { examine(); }
	}

	@Override
	public void interact(GameObject o) {
		// TODO Auto-generated method stub
		
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	public boolean hasTalkedToGertrude() {
		return talkedToGertrude;
	}
	
	public void setHasTalkedToGertrude(boolean state) {
		talkedToGertrude = state;
	}
	
}
