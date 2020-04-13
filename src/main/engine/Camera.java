package main.engine;

import static main.util.ResourceLoader.TILE_WIDTH;
import static main.util.ResourceLoader.TILE_HEIGHT;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import main.map.Map;

public class Camera {

	private Point viewPos;
	private Rectangle cameraRect, viewRect;
	
	private Map map;
	
	public Camera(Map map, Point pos, float width, float height) {
		if(pos.getX() < 0) pos.setX(0);
		if(pos.getY() < 0) pos.setY(0);
		viewPos = pos;
		cameraRect = new Rectangle(viewPos.getX(), viewPos.getY(), width + TILE_WIDTH + 1, height + TILE_HEIGHT + 1);
		viewRect = new Rectangle(viewPos.getX(), viewPos.getY(), width, height);
		
		this.map = map;
	}
	
	public void move(float xDir, float yDir) {
		float mapWidth = (map.getMapWidth() + 1) * TILE_WIDTH;
		float mapHeight = (map.getMapHeight() + 1) * TILE_HEIGHT;
		
		boolean safeLeft = (viewPos.getX() + xDir > 0);
		boolean safeRight = (viewPos.getX() + cameraRect.getWidth() + xDir <= mapWidth);
		boolean safeUp = (viewPos.getY() + yDir > 0);
		boolean safeDown = (viewPos.getY() + cameraRect.getHeight() + yDir <= mapHeight);
		
		if(safeLeft && safeRight)
			viewPos.setX(viewPos.getX() + xDir);
		else {
			if(!safeLeft)
				viewPos.setX(0);
			if(!safeRight)
				viewPos.setX(mapWidth - cameraRect.getWidth());
		}
		
		if(safeDown && safeUp)
			viewPos.setY(viewPos.getY() + yDir);
		else {
			if(!safeUp)
				viewPos.setY(0);
			if(!safeDown)
				viewPos.setY(mapHeight - cameraRect.getHeight());
		}
	}
	
	public void update() {
		int mspeed = 10;
		int xDir = 0;
		int yDir = 0;
		
		Input input = Engine.getInput();
		
		if(input.isKeyDown(Input.KEY_S))
			yDir = mspeed;
		else if(input.isKeyDown(Input.KEY_W))
			yDir = -mspeed;
		
		if(input.isKeyDown(Input.KEY_D))
			xDir = mspeed;
		else if(input.isKeyDown(Input.KEY_A))
			xDir = -mspeed;
		move(xDir, yDir);
		cameraRect.setX(viewPos.getX() - TILE_WIDTH - 1);
		cameraRect.setY(viewPos.getY() - TILE_HEIGHT - 1);
	}

	public Point getPos() {
		return viewPos;
	}
	
	public Rectangle getRenderRect() {
		return cameraRect;
	}
	
	public Rectangle getCamView() {
		return viewRect;
	}
	
}
