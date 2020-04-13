package main;

import java.io.File;

import main.engine.Engine;

public class Main {
	
	public static String ABS_PATH;
	
	public static void main (String[] args) {
		ABS_PATH = new File("").getAbsolutePath();
		System.setProperty("org.lwjgl.librarypath", ABS_PATH);
		
		new Engine(1280,720,"Map Editor");
	}

}
