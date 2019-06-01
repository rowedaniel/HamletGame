package com.HamletGame.main.entities;

import com.HamletGame.main.ID;
import com.HamletGame.main.Renderer;
import com.HamletGame.main.graphics.ImageTile;

public class Tile extends GameObject{
	
	protected int animationX;
	protected int animationY;
	protected ImageTile image;

	public Tile(int x, int y, int r, String imgpath, ID id) {
		super(x, y, id);
		image = new ImageTile(imgpath, 32, 32);
		animationX = 0;
		animationY = r;
	}

	@Override
	public void update(long delta) {
	}

	@Override
	public void draw(Renderer r) {
		r.drawImageTile(image, x, y, animationX, animationY, false);
	}
	
	@Override
	public void updateInput(int key, boolean state) {}
}
