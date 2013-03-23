package net.solace.entity;

import net.solace.gui.HealthBar;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class HealthEntity extends Entity {

	public float health;
	public int hurtTimer;
	public HealthBar healthBar;
    private static final Color hurtColor = new Color(1, .3f, .1f, 1.0f);

	public HealthEntity(int x, int y) {
		super(x, y);

		health = getMaxHealth();
		healthBar = new HealthBar(this);
	}

	public void render(GameContainer container, Graphics g) {
		healthBar.render(container, g);
	}

	public boolean update(GameContainer container, int deltaMS) {
		healthBar.update(container, deltaMS);
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
