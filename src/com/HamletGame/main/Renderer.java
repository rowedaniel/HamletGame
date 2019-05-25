package com.HamletGame.main;

import java.awt.image.DataBufferInt;

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
			p[i] += i;
		}
	}
	
}
