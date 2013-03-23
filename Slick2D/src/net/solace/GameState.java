package net.solace;

import net.solace.entity.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public Starfield field;
	public Player player;
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		field = new Starfield(800, 600);
		player = new Player(400, 300);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		field.render(container, g);
		player.render(container, g);
	}

	public void update(GameContainer container, StateBasedGame game, int deltaMS)
			throws SlickException {
		field.update(container, deltaMS);
		player.update(container, deltaMS);
	}

	public int getID() {
		return 0;
	}

}
