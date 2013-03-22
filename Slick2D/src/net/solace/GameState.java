package net.solace;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public Starfield field;
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		field = new Starfield(800, 600);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		field.render(container, g);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		field.update(container, delta);
	}

	public int getID() {
		return 0;
	}

}
