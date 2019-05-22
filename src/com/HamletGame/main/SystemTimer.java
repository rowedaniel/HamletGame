package com.HamletGame.main;

public class SystemTimer {

	private static final int TARGET_FPS = 60; // 60 FPS
	
	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	public static long getTargetTime() {
		return (long) 1000 / TARGET_FPS;
	}
}
