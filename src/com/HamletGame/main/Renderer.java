package com.HamletGame.main;

import java.awt.image.DataBufferInt;

import com.HamletGame.main.graphics.Image;
import com.HamletGame.main.graphics.ImageTile;

public class Renderer {

	private int pW, pH;
	private int[] p;
	
	public Renderer(Game g) {
		pW = g.getWidth();
		pH = g.getHeight();
		p = ((DataBufferInt)g.getWindow().getImage().getRaster().getDataBuffer()).getData();
		
	}
	
	
	public void clear() {
		for(int i = 0; i < p.length; i++) {
			p[i] = 0xff000000;
		}
	}
	
	private void setPixel(int x, int y, int value) {
		if((x < 0 || x >= pW || y < 0 || y > pH) || value == 0xffff00ff) { // pink is transparency
			return;
		}
		p[x + y * pW] = value;
	}
	
	private void drawPixels(int[] p, int startx, int endx, int starty, int endy, int offX1, int offX2, int offY1, int offY2, int yFactor) {
		for(int y = starty; y < endy; y++) {
			for(int x = startx; x < endx; x++) {
				setPixel(x+offX1, y+offY1, p[x+offX2 + (y+offY2)*yFactor]);
			}
		}
	}
	
	
	public void drawImage(Image image, int offX, int offY) {
		
		if(offX > pW) {return;}
		if(offY > pH) {return;}
		if(offX < image.getW()) {return;}
		if(offY < image.getH()) {return;}
		
		int newX = 0;
		int newY = 0;
		int newW = image.getW();
		int newH = image.getH();
		
		if(offX < 0) {newX = -offX;}
		if(offY < 0) {newY = -offY;}
		if(newW + offX > pW) {newW = pW - offX;}
		if(newH + offY > pH) {newH = pH - offY;}
		
		drawPixels(image.getP(), newX, newW, newY, newH, offX, 0, offY, 0, image.getW());
	}
	
	
	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		if(offX > pW) {return;}
		if(offY > pH) {return;}
		if(offX < -image.getTileW()) {return;}
		if(offY < -image.getTileH()) {return;}
		
		int newX = 0;
		int newY = 0;
		int newW = image.getTileW();
		int newH = image.getTileH();
		
		if(offX < 0) {newX = -offX;}
		if(offY < 0) {newY = -offY;}
		if(newW + offX > pW) {newW = pW - offX;}
		if(newH + offY > pH) {newH = pH - offY;}
		
		drawPixels(image.getP(), newX, newW, newY, newH, offX, tileX*image.getTileW(), offY, tileY*image.getTileH(), image.getW());
	}
}
