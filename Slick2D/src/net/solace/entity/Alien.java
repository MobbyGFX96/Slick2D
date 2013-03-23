package net.solace.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Alien extends HealthEntity {

	public SpriteSheet alienSheet;
	public Animation[] alienAnim = new Animation[8];
	
	public Alien(int x, int y) throws SlickException {
		super(x, y);
		alienSheet = new SpriteSheet("res/alien1.png", 32, 32);
		health = getMaxHealth();
		Image[] img = new Image[8];
		for (int i = 0; i < 8; i++) {
			img[i] = alienSheet.getSprite(i, 0);
		}
		alienAnim[0] = new Animation(img, 200, true);
	}
	
	public void render(GameContainer container, Graphics g) {
		Animation a = alienAnim[0];
		a.setLooping(true);
		a.draw(x, y);
	}

	public boolean update(GameContainer container, int deltaMS) {
		if (!super.update(container, deltaMS)) {
			return false;
		}
		return true;
	}
}
