package net.solace.entity;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Entity {

	public float x, y, z;

	protected Vector3f acceleration = new Vector3f(0, 0, 0);
	protected Vector3f velocity = new Vector3f(0, 0, 0);

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void render(GameContainer container, Graphics g);

	public abstract boolean update(GameContainer container, int deltaMS);

	public void push(Vector3f push) {
		velocity.x += push.x;
		velocity.y += push.y;
		velocity.z += push.z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
