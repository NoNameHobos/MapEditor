package main;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Engine implements Game{
	
	static int WIDTH, HEIGHT;
	static String TITLE;
	static Engine ENGINE;
	Display display;
	
	public Engine(int WIDTH, int HEIGHT, String TITLE) {
		
		Engine.ENGINE = this;
		Engine.WIDTH = WIDTH;
		Engine.HEIGHT = HEIGHT;
		Engine.TITLE = TITLE;
		
		display = new Display(this, WIDTH, HEIGHT);
		
		
	}

	@Override
	public boolean closeRequested() {
		System.exit(0);
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public Display getDisplay() {
	
		return display;
		
	}

}
