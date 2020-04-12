package main;

import java.io.File;

public class Main {
	
	public static void main (String[] args) {
		
		
		new Engine(600,400,"The Game");
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		
	}

}
