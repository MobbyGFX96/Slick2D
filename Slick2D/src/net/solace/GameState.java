package net.solace;

import net.solace.entity.Alien;
import net.solace.entity.Player;
import net.solace.menu.Starfield;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	public Player player;
	public Alien alien;
	public Starfield field;
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		player = new Player(400, 300);
		alien = new Alien(500, 400);
		field = new Starfield(800, 600);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		player.render(container, g);
		alien.render(container, g);
		field.render(container, g);
	}

	public void update(GameContainer container, StateBasedGame game, int deltaMS)
			throws SlickException {
		player.update(container, deltaMS);
		alien.update(container, deltaMS);
		field.update(container, deltaMS);
	}

	public int getID() {
		return 1;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
