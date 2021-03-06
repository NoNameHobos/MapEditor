package main.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import main.map.Map;
import main.map.TileSet;

import static main.Main.ABS_PATH;

public class ResourceLoader {

	public static final int TILE_WIDTH = 128;
	public static final int TILE_HEIGHT = 128;
	public static final int FONT_SIZE = 60;

	public static final HashMap<String, Image> SPRITES = new HashMap<String, Image>();
	public static final HashMap<String, SpriteSheet> SPRITE_SHEETS = new HashMap<String, SpriteSheet>();

	public static final HashMap<String, Map> MAPS = new HashMap<String, Map>();
	public static final HashMap<String, TileSet> TILE_SETS = new HashMap<String, TileSet>();
	public static final HashMap<String, TrueTypeFont> FONTS = new HashMap<String, TrueTypeFont>();
	
	
	public static Image missing;
	public static SpriteSheet missingSS;

	public static final int thingsToLoad = 7;
	private static int loaded = 0;
	
	public static void loadMenuSprites() {
	}
	
	public static void loadTiles() {
		// Load grassLand tile set
		TILE_SETS.put("grass", TileSet.loadTileSet("plains"));
	}

	public static void loadSprites() {
		String path = "res\\sprites\\";
	}

	public static void loadFonts() {
	}
	
	public static void loadMaps() {
	}
	
	public static void load() {
		
		try {
			missing = new Image(ABS_PATH + "\\res\\sprites\\missingtex.png");
			missingSS = new SpriteSheet(ABS_PATH + "\\res\\sprites\\missingtex.png", 48, 48);
		} catch (SlickException e) {
			System.err.println("Failed to load missingtex.png");
			e.printStackTrace();
		}
		// Don't change the load order please
		loadTiles();
	}
	
	public static TrueTypeFont loadFont(String font) {
		InputStream is = org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res\\font\\" + font);
	    TrueTypeFont ttf = null;
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, is);
			f = f.deriveFont(FONT_SIZE * 1f);
			ttf = new TrueTypeFont(f, false);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ttf;
	}
	
	public static Image loadImage(String dir) {
		Image i;
		loaded++;
		try {
			i = new Image(ABS_PATH + dir + ".png");
			System.out.println("Loaded " + dir + ".png");
			return i;
		} catch (SlickException e) {
			System.err.println("Failed to load image at: " + dir);
			e.printStackTrace();
			return missing;
		} catch (RuntimeException e) {
			System.err.println("Failed to load image: " + dir);
			return missing;
		}
	}

	public static Image loadImageFromSS(SpriteSheet ss, int x, int y) {
		ss.startUse();
		Image i = ss.getSprite(x, y);
		ss.endUse();
		loaded++;
		return i;
	}

	public static SpriteSheet loadSpriteSheet(String dir, int tw, int th) {
		SpriteSheet ss;
		loaded++;
		try {
			ss = new SpriteSheet(ABS_PATH + "\\" + dir + ".png", tw, th);
			System.out.println("Loaded " + dir + ".png as Sprite Sheet with Tile: " + tw + "x" + th);
			return ss;
		} catch (SlickException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + ".png (SlickException)");
			e.printStackTrace();
			return missingSS;
		} catch (RuntimeException e) {
			System.err.println("Failed to load Sprite Sheet at: " + dir + ".png (RuntimeException)");
			return missingSS;
		}
	}

	private static void report() {
		System.out.print("Loaded: ");
		System.out.print(loaded);
		System.out.print("/");
		System.out.println(thingsToLoad);
	}

	public static float getLoaded() {
		return loaded / thingsToLoad;
	}
}
