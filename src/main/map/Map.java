package main.map;

import static main.util.ResourceLoader.TILE_HEIGHT;
import static main.util.ResourceLoader.TILE_SETS;
import static main.util.ResourceLoader.TILE_WIDTH;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import main.engine.Camera;
import main.engine.Engine;

public class Map {
	
	private Tile[][] tiles;

	private int mapWidth, mapHeight;

	private String title;

	private TileSet tileset;

	private ArrayList<String> mapData;
	
	private Camera camera;
	
	private Point[] spawns;
	
	public Map(String title, String tileSet, ArrayList<String> mapData) {

		tileset = TILE_SETS.get(tileSet);
		System.out.println(Engine.WIDTH);
		System.out.println(Engine.HEIGHT);
		camera = new Camera(this, new Point(Engine.WIDTH/2, Engine.HEIGHT/2), Engine.WIDTH, Engine.HEIGHT);
		
		mapWidth = mapData.get(0).split(" ").length;
		mapHeight = mapData.size();
		
		System.out.print("Loaded map " + title + " with size: ");
		System.out.print(mapWidth);
		System.out.print(" x ");
		System.out.println(mapHeight);
		this.mapData = mapData;
		this.title = title;
	}
	
	public void init() {
		if(tiles == null) {
			String[][] tileData = new String[mapHeight][mapWidth];
	
			for(int i = 0; i < mapHeight; i++) {
				tileData[i] = mapData.get(i).split(" ");
			}
			loadTiles(tileData);
		}
	}
	
	public void loadTiles(String[][] tileData) {
		
		tiles = new Tile[mapWidth][mapHeight];
		
		for(int i = 0; i < tileData.length; i++) {
			for(int j = 0; j < tileData[i].length; j++) {
				String tileType = "";
                switch (tileData[i][j]) {
                case "0":
                    tileType = "darkGrass";
                    break;
                case "1":
                    tileType = "darkWater";
                    break;
                }
                tiles[j][i] = new Tile(
                		tileset.getTile(tileType)
                		, j * TILE_WIDTH, i * TILE_HEIGHT);
			}
		}
	}

	public TileSet getTileSet() {
		return tileset;
	}

	public void renderTiles(Graphics g) {
		camera.update();
		
		float xOffset = camera.getPos().getX();
		float yOffset = camera.getPos().getY();
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				Point tilePos = tiles[i][j].getPos();
				
				float rendX = tilePos.getX() * (float)camera.getZoom();
				float rendY = tilePos.getY() * (float)camera.getZoom();
				
				boolean inCam = camera.getRenderRect().contains(rendX, rendY);
				if (inCam) {
					Image image = tiles[i][j].getImage();
					
					float x = rendX - xOffset;
					float y = rendY - yOffset;
					
					float width = (float)(image.getWidth() * camera.getZoom());
					float height = (float)(image.getHeight() * camera.getZoom());
					
					image.draw(x, y, width, height);
				}
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public Point[] getSpawns() {
		return spawns;
	}

	public void setSpawns(Point[] spawns) {
		this.spawns = spawns;
	}

}
