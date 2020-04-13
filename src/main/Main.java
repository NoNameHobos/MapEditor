package main;

import java.io.File;
import java.util.ArrayList;

public class Main {
	
	public static ArrayList<String> mapData;
	
	public static void main (String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("").getAbsolutePath());
		mapData = MapLoader.loadMap(new File("").getAbsolutePath() + "\\test_map.map");
		for(int i = 0; i < mapData.size(); i++) {
			String line = mapData.get(i);
			System.out.println(line);
		}
		//Engine engine = new Engine(600,400,"The Game");
        
		
	}

}
