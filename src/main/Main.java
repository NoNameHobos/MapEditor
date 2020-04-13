package main;

import java.awt.Font;
import java.io.File;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class Main {
	
	public static void main (String[] args) {
		
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		Engine engine = new Engine(600,400,"The Game");
		
		new TextField(engine.getDisplay(),
				new Font("Courier", Font.PLAIN,20), false),
                100,
                100,
                100,
                10);
		
	}

}
