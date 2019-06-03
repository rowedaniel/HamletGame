package com.HamletGame.main;

import java.awt.Canvas;

import com.HamletGame.main.entities.AnimatedTile;
import com.HamletGame.main.entities.NPC;
import com.HamletGame.main.entities.Player;
import com.HamletGame.main.entities.TextBox;
import com.HamletGame.main.entities.Tile;
import com.HamletGame.main.entities.battle.BattleAI;
import com.HamletGame.main.entities.battle.BattlePlayer;
import com.HamletGame.main.entities.battle.BattleScreen;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8018679086977040689L;
	
	private int Width = 256, Height = Width * 10000 / 16180; // aspect ratio must be golden
	private double scale = 4f;
	private String title = "gamememe";
	
	private Handler handler;
	private Window window;
	private Renderer renderer;
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
			
			 // right curtains
			handler.addObject(new Tile(32*11, x*32+64, 3, "/images/curtainTop.png", ID.GenericTile));
			if(x*32+64 != 192) { // all but the one space
				handler.addObject(new Tile(32*10, x*32+64, 3, "/images/curtainBase.png", ID.GenericTile));
			}
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
		
		
		// TODO: ADD CURTAINS
		
		// Example animated tile. One of the curtain tiles should be this.
		handler.addObject(new AnimatedTile(256+64, 192, 3,"/images/curtainBaseAnimated.png", this, ID.PoloniusTile));
		

		handler.addObject(new NPC(200,220,this,ID.Gertrue));
		handler.addObject(new Player(194,194,this,ID.Player));
		
		
		// screen overlay stuff
		handler.addObject(new TextBox(ID.TextBox));
		handler.addObject(new BattleScreen(ID.BattleScreen));
		
		BattlePlayer tempP = new BattlePlayer(170, 80, this, ID.BattlePlayer);
		BattleAI tempA = new BattleAI(20, 20, this, ID.BattlePolonius);
		tempA.setOpponent(tempP);
		tempP.setOpponent(tempA);
		handler.addObject(tempP);
		handler.addObject(tempA);

		window = new Window(this);
		renderer = new Renderer(this);
		new KeyInput(this);
		
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
		int frames = 0;
		long time = 0;
		
		while(running)
		{
			long delta = (SystemTimer.getTime() - prevTime);
			prevTime = SystemTimer.getTime();
			
			logic(delta);
			render(delta);
			
			frames += 1;
			time += delta;
			if(frames>=60) {
				System.out.println("fps: "+frames*1000/time);
				time = 0;
				frames = 0;
			}
			
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
