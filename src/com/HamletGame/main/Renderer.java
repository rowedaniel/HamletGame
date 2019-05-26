package com.HamletGame.main;

import java.awt.image.DataBufferInt;

import com.HamletGame.main.graphics.Image;

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
	
	public void setPixel(int x, int y, int value) {
		if((x < 0 || x >= pW || y < 0 || y > pH) || value == 0xffff00ff) { // pink is transparency
			return;
		}
		p[x + y * pW] = value;
	}
	
	public void drawImage(Image image, int offX, int offY) {
		for(int y = 0; y < image.getH(); y++) {
			for(int x = 0; x < image.getW(); x++) {
				setPixel(x+offX, y+offY, image.getP()[x + y*image.getW()]);
			}
		}
	}
}
