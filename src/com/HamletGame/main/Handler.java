package com.HamletGame.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> entities = new LinkedList<GameObject>();
	
	public void logic()
	{
		for(int i = 0; i < entities.size(); i++) {
			GameObject o = entities.get(i);
			
			o.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < entities.size(); i++) {
			GameObject o = entities.get(i);
			
			o.draw(g);
		}
	}
	
	public void addObject(GameObject o)
	{
		this.entities.add(o);
	}
	
	public void removeObject(GameObject o)
	{
		this.entities.remove(o);
	}
}
