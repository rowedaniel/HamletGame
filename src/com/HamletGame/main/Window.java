package com.HamletGame.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = 1031273228876517109L;
	
	public Window(int width, int height, String title, Game game)
	{
		// very much work in progress, improve later
		JFrame container = new JFrame(title);
		
		container.setPreferredSize(new Dimension(width, height));
		container.setMaximumSize(new Dimension(width, height));
		container.setMinimumSize(new Dimension(width, height));
		
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setResizable(false); // make this true later
		container.setLocationRelativeTo(null);
		container.add(game);
		container.setVisible(true);
		game.start();

	}
	
}
