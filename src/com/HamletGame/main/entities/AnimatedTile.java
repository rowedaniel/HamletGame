package com.HamletGame.main.entities;

import com.HamletGame.main.ID;

public class AnimatedTile extends Tile{

	private double idleTime = 0.0f;
	private double reactTime = 10;
	private boolean animate;
	private double animationTime = 0.0f;
	private double animationSpeed = 50.0f; // 1/10 fps
	private double animationFactor = 1.0f;
	
	public AnimatedTile(int x, int y, int r, String imgpath, ID id) {
		super(x, y, r, imgpath, id);
	}

	@Override
	public void update(long delta) {
		
		if(idleTime >= reactTime) {
			idleTime = 0;
			setAnimationState(1);
		}
		
		if(animate) {
			// update animation
			animationX = (int)animationTime;
			animationTime += ((double)delta*animationFactor*animationSpeed/1000.0);
			if(animationX >= image.getTileNoX()) {
				animationX = 0;
				animationTime = 0.0f;
				setAnimationState(0);
			}
		} else {
			idleTime += ((double)delta*animationFactor*animationSpeed/1000.0);
		}
	}
	
	public void setAnimationState(int state) {
		if(state==0) {
			animate = false;
		}
		else {
			animate = true;
		}
	}
	
}
