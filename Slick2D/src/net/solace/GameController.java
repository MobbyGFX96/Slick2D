package net.solace;

import net.solace.menu.MenuState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameController extends StateBasedGame {

	public GameController() {
		super("GAME");
	}

	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MenuState());
		addState(new GameState());
	}

}
