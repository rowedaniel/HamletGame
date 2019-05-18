package com.HamletGame.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8018679086977040689L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH * 10000 / 16180; // aspect ratio must be golden
	
	private Thread thread;
	private boolean running = false;
	
	public Game()
	{
		new Window(WIDTH, HEIGHT, "gamemem", this);
		
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
}
