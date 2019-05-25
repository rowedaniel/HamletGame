package com.HamletGame.main;

import java.awt.Canvas;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8018679086977040689L;
	
	private int Width = 800, Height = Width * 10000 / 16180; // aspect ratio must be golden
	private double scale = 1f;
	private String title = "gamememe";
	
	//private Handler handler;
	private Window window;
	
	private Thread thread;
	private boolean running = false;
	
	public Game()
	{
		setIgnoreRepaint(true);
	}
	
	public synchronized void start()
	{
		//handler = new Handler();
		//this.addKeyListener(new KeyInput(handler));			
		//handler.addObject(new Player(WIDTH/2,HEIGHT/2,ID.Player));
		
		window = new Window(this);
		
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
			long delta = (prevTime = SystemTimer.getTime() - prevTime);
			
			//logic(delta);
			render(delta);
			
			try {
				Thread.sleep(Math.max(0,prevTime + SystemTimer.getTargetTime() - SystemTimer.getTime()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		stop();
	}
	
	public void logic(long delta)
	{
		//handler.logic();
	}
	
	public void render(long delta)
	{
		window.update();
		//handler.render(window.getGraphics());
	}
	

	public int getWidth() {
		return Width;
	}

	public void setWidth(int Width) {
		this.Width = Width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int Height) {
		this.Height = Height;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Window getWindow() {
		return window;
	}
	
	
	
	

	public static void main(String[] args)
	{
		Game g = new Game();
		g.start();
	}
	
}
