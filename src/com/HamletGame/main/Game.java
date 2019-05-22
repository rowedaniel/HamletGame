package com.HamletGame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8018679086977040689L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH * 10000 / 16180; // aspect ratio must be golden
	
	private Handler handler;
	
	private Thread thread;
	private boolean running = false;
	
	public Game()
	{
		new Window(WIDTH, HEIGHT, "gamemem", this);
		
		handler = new Handler();
		
		handler.addObject(new Player(10,10,ID.Player));
		
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
		long prevTime = SystemTimer.getTime();
		
		while(running)
		{
			long delta = prevTime - (prevTime = SystemTimer.getTime());
			
			
			logic(delta);
			render(delta);
			
			try {
				Thread.sleep(prevTime + SystemTimer.getTargetTime() - SystemTimer.getTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		stop();
	}
	
	public void logic(long delta)
	{
		handler.logic();
	}
	
	public void render(long delta)
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
}
