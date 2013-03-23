package net.solace.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class HealthEntity extends Entity {

	public float health;
	public int hurtTimer;
    private static final Color hurtColor = new Color(1, .3f, .1f, 1.0f);

	public HealthEntity(int x, int y) {
		super(x, y);

		health = getMaxHealth();

	}

	public void render(GameContainer container, Graphics g) {

	}

	public boolean update(GameContainer container, int deltaMS) {
		hurtTimer -= deltaMS;
		return health > 0;
	}

	public void hurt(float damage) {
		health -= damage;
		if (health < 0) {
			health = 0;
		}
		hurtTimer = 200;
	}

	public void heal(float amount) {
		health += amount;
		if (health > getMaxHealth()) {
			health = getMaxHealth();
		}
	}

	public float getHealth() {
		return health;
	}

	public float getMaxHealth() {
		return 100.0f;
	}

	protected boolean isRecentlyHurt() {
		return hurtTimer > 0;
	}

	protected Color getHurtColor() {
		if (isRecentlyHurt()) {
			return hurtColor;
		}
		return Color.white;
	}

}
