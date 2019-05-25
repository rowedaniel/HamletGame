package com.HamletGame.main.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	
	private int w,h;
	private int[] p;
	
	public Image(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e){
			e.printStackTrace();
		}
	
		w = img.getWidth();
		h = img.getHeight();
		p = img.getRGB(0, 0, w, h, null, 0, w);
		
		img.flush();
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}
	
	
}
