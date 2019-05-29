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
			// top walls
			handler.addObject(new Tile(x*32+64, 0, 0, "/images/Wall.png", ID.GenericTile));
			handler.addObject(new Tile(x*32+64, 32, 0, "/images/WallBridge.png", ID.GenericTile));
			// left walls
			handler.addObject(new Tile(0, x*32+64, 1, "/images/Wall.png", ID.GenericTile));
			handler.addObject(new Tile(32, x*32+64, 1, "/images/WallBridge.png", ID.GenericTile));
			// bottom walls
			handler.addObject(new Tile(x*32+64, 32*11, 2, "/images/Wall.png", ID.GenericTile));
			handler.addObject(new Tile(x*32+64, 32*10, 2, "/images/WallBridge.png", ID.GenericTile));
			// right walls
			handler.addObject(new Tile(32*11, x*32+64, 3, "/images/Wall.png", ID.GenericTile));
			handler.addObject(new Tile(32*10, x*32+64, 3, "/images/WallBridge.png", ID.GenericTile));
			for(int y = 0; y < 8; y++) {
				handler.addObject(new Tile(x*32+64, y*32+64, (x+y)%2, "/images/Floor.png", ID.GenericTile));
			}
		}
		// corners
		handler.addObject(new Tile(256+96,0,0,"/images/WallCorner.png", ID.GenericTile));
		handler.addObject(new Tile(256+64,0,0,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(256+96,32,3,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(256+64,32,0,"/images/WallBridgeCorner.png", ID.GenericTile));
		
		handler.addObject(new Tile(0,0,1,"/images/WallCorner.png", ID.GenericTile));
		handler.addObject(new Tile(32,0,0,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(0,32,1,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(32,32,1,"/images/WallBridgeCorner.png", ID.GenericTile));

		handler.addObject(new Tile(0,256+96,2,"/images/WallCorner.png", ID.GenericTile));
		handler.addObject(new Tile(0,256+64,1,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(32,256+96,2,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(32,256+64,2,"/images/WallBridgeCorner.png", ID.GenericTile));
		
		handler.addObject(new Tile(256+96,256+96,3,"/images/WallCorner.png", ID.GenericTile));
		handler.addObject(new Tile(256+64,256+96,2,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(256+96,256+64,3,"/images/Wall.png", ID.GenericTile));
		handler.addObject(new Tile(256+64,256+64,3,"/images/WallBridgeCorner.png", ID.GenericTile));
		
		
		handler.addObject(new Player(194,194,ID.Player));

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
		renderer.update(handler.getObjectByID(ID.Player));
		handler.render(renderer);
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
