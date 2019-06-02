package com.HamletGame.main;

import java.util.LinkedList;

import com.HamletGame.main.entities.GameObject;

public class Handler {
	
	LinkedList<GameObject> entities = new LinkedList<GameObject>();
	
	public void logic(long delta)
	{
		for(int i = 0; i < entities.size(); i++) {
			GameObject o = entities.get(i);
			
			o.update(delta);
		}
	}
	
	public void render(Renderer r)
	{
		for(int i = 0; i < entities.size(); i++) {
			GameObject o = entities.get(i);
			
			o.draw(r);
		}
	}
	
	public LinkedList<GameObject> getEntities() {
		return entities;
	}
	
	public GameObject getObjectByID(ID id) {
		for(int i = 0; i < entities.size(); i++) {
			GameObject o = entities.get(i);
			if(o.getID()==id) {
				return o;
			}
		}
		return null;
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
