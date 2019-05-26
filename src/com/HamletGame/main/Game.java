package com.HamletGame.main;

import java.awt.Canvas;

import com.HamletGame.main.entities.Player;
import com.HamletGame.main.entities.Tile;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8018679086977040689L;
	
	private int Width = 256, Height = Width * 10000 / 16180; // aspect ratio must be golden
	private double scale = 4f;
	private String title = "gamememe";
	
	private Handler handler;
	private Window window;
	private Renderer renderer;
	private KeyInput keyInput;
	
	private Thread thread;
	private boolean running = false;
		
	public Game()
	{
		setIgnoreRepaint(true);
	}
	
	public synchronized void start()
	{
		handler = new Handler();
		// build room
		for(int x = 0; x < 8; x++) {
			handler.addObject(new Tile(x*32, 0, 0, "/images/Wall.png", ID.GenericTile));
		}
		handler.addObject(new Player(Width/2,Height/2,ID.Player));

		window = new Window(this);
		renderer = new Renderer(this);
		keyInput = new KeyInput(this);
		
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
			
			logic(delta);
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
		handler.logic(delta);
	}
	
	public void render(long delta)
	{
		renderer.clear();
		handler.render(renderer);
		// TODO: render game		
		window.update();
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
	
	public Handler getHandler() {
		return handler;
	}

	
	
	

	public static void main(String[] args)
	{
		Game g = new Game();
		g.start();
	}
	
}
