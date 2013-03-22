package net.solace;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new GameController());
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
