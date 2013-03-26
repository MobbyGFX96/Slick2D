package net.solace.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Starfield {

	public Image[][] starImages = new Image[3][3];
	public List<Star> stars = new ArrayList<Star>();
	private final int width, height;
	public Random random = new Random();

	public Starfield(int width, int height) throws SlickException {
		this.width = width;
		this.height = height;

		starImages[0][0] = new Image("res/bg/smallstar.png");
		starImages[0][1] = new Image("res/bg/smallstar2.png");
		starImages[0][2] = new Image("res/bg/smallstar3.png");
		starImages[1][0] = new Image("res/bg/mediumstar.png");
		starImages[1][1] = new Image("res/bg/mediumstar2.png");
		starImages[1][2] = new Image("res/bg/mediumstar3.png");
		starImages[2][0] = new Image("res/bg/bigstar.png");
		starImages[2][1] = new Image("res/bg/bigstar2.png");
		starImages[2][2] = new Image("res/bg/bigstar3.png");

		for (int i = 0; i < 1000; i++) {
			stars.add(new Star(random.nextInt(width * 2) - width, random
					.nextInt(height * 2) - height, 1 + random.nextInt(3),
					random.nextInt(3), .95f + random.nextFloat() * .1f));
		}

	}
	
    public void render(GameContainer container, Graphics g) {

        for (Star s : stars) {
            Image i = starImages[s.layer - 1][s.type];
            g.drawImage(i, s.x  - (2 << s.layer), s.y - (2 << s.layer));
        }
    }

    public void update(GameContainer container, int deltaMS) {

        for (Star s : stars) {
            s.y -= -.01f * (float) deltaMS * s.speed;
            if (s.y > height) {
                s.y = 0 - random.nextInt(100);
                s.x = random.nextInt(width * 2) - width;
            }
        }

    }

	private class Star {
		private float x, y;
		private int type;
		private int layer;
		private float speed;

		public Star(float x, float y, int layer, int type, float speed) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.layer = layer;
			this.speed = speed * layer * layer;
		}
	}

}
