package main.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.State;
import main.map.Map;
import main.map.MapLoader;

public class EditState extends State {

	private Map map;
	
	public EditState() {
		super("Edit State");
	}

	@Override
	public void init() {
		map = MapLoader.loadMap("maps\\test.map");
		map.init();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		map.renderTiles(g);
		
		//Draw title
		String title = map.getTitle();
		g.setColor(Color.white);
		g.drawString(title, 0, 30);
	}

}
