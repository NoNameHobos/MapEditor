package main.engine;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.State;
import main.display.Display;
import main.states.EditState;
import main.util.ResourceLoader;

public class Engine implements Game{
	
	public static int WIDTH, HEIGHT;
	public static String TITLE;
	
	private Display display;
	private static Input input;
	
	public final State editState = new EditState();
	private State currentState;
	
	public Engine(int WIDTH, int HEIGHT, String TITLE) {
		
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
	public void init(GameContainer gc) throws SlickException {
		ResourceLoader.load();
		input = gc.getInput();
		currentState = editState;
		editState.init();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(currentState != null)
			currentState.render(g);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(currentState != null)
			currentState.tick();
		
	}
	
	//Getters and Setters
	public Display getDisplay() {
		return display;
	}
	
	public static Input getInput() {
		return input;
	}

}
