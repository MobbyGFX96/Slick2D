package net.solace.entity;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Player extends HealthEntity {

	public SpriteSheet playerSheet;
	public Animation[] playerAnim = new Animation[8];
	public int currentAnimation;
	private int aimX, aimY;
	Vector2f playerDirection = new Vector2f(1, 0);
	private Vector2f[] moveDirections = new Vector2f[] {
			new Vector2f(-45 - 90), new Vector2f(45 - 90),
			new Vector2f(-135 - 90), new Vector2f(135 - 90),
			new Vector2f(-90 - 90), new Vector2f(90 - 90),
			new Vector2f(0 - 90), new Vector2f(180 - 90) };
	private final float playerGroudFriction = 1.8f;
	private final float playerAirFriction = 0.9f;
	private float playerFriction = 1.8f;

	public Player(int x, int y) throws SlickException {
		super(x, y);
		playerSheet = new SpriteSheet("res/player1_1.png", 32, 32);

		Image[] imagesUpLeft = new Image[4];
		Image[] imagesUpRight = new Image[4];
		for (int i = 0; i < 4; i++) {
			imagesUpLeft[i] = playerSheet.getSprite(i, 1);
			imagesUpRight[i] = imagesUpLeft[i].getFlippedCopy(true, false);
		}
		playerAnim[0] = new Animation(imagesUpLeft, 200, true);
		playerAnim[1] = new Animation(imagesUpRight, 200, true);

		Image[] imagesDownLeft = new Image[4];
		Image[] imagesDownRight = new Image[4];
		for (int i = 0; i < 4; i++) {
			imagesDownLeft[i] = playerSheet.getSprite(i, 3);
			imagesDownRight[i] = imagesDownLeft[i].getFlippedCopy(true, false);
		}
		playerAnim[2] = new Animation(imagesDownLeft, 200, true);
		playerAnim[3] = new Animation(imagesDownRight, 200, true);

		Image[] imagesLeft = new Image[4];
		Image[] imagesRight = new Image[4];
		for (int i = 0; i < 4; i++) {
			imagesLeft[i] = playerSheet.getSprite(i, 2);
			imagesRight[i] = imagesLeft[i].getFlippedCopy(true, false);
		}
		playerAnim[4] = new Animation(imagesLeft, 200, true);
		playerAnim[5] = new Animation(imagesRight, 200, true);

		Image[] imagesUp = new Image[4];
		Image[] imagesDown = new Image[4];
		for (int i = 0; i < 4; i++) {
			imagesUp[i] = playerSheet.getSprite(i, 0);
			imagesDown[i] = playerSheet.getSprite(i, 4);
		}
		playerAnim[6] = new Animation(imagesUp, 200, true);
		playerAnim[7] = new Animation(imagesDown, 200, true);
	}

	public void render(GameContainer container, Graphics g) {
		Animation a = playerAnim[currentAnimation];
		a.draw(x, y);
	}

	public boolean update(GameContainer container, int deltaMS) {
		Input input = container.getInput();

		acceleration.set(0, 0, 0);
		Vector2f direction = new Vector2f(0, 0);
		boolean isMoving = false;

		if (input.isKeyDown(Input.KEY_W)) {
			direction.y -= 1;
			isMoving = true;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			direction.y += 1;
			isMoving = true;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			direction.x -= 1;
			isMoving = true;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			direction.x += 1;
			isMoving = true;
		}
		// normalise so that moving diagonal isn't faster than orthogonal
		direction.normalise();

		updateFriction(isMoving);
		{
			acceleration.x = -(velocity.x * playerFriction) + direction.x
					* getPlayerSpeed();
			acceleration.y = -(velocity.y * playerFriction) + direction.y
					* getPlayerSpeed();
		}
		acceleration.z = -9.8f;
		velocity.x += acceleration.x * deltaMS * .001f;
		velocity.y += acceleration.y * deltaMS * .001f;
		velocity.z += acceleration.z * deltaMS * .001f;

		x += velocity.x * (deltaMS * .001f) * 20;
		y += velocity.y * (deltaMS * .001f) * 20;
		z += velocity.z * (deltaMS * .001f) * 20;

		this.aimX = input.getMouseX();
		this.aimY = input.getMouseY();
		playerDirection = (new Vector2f(-(x - aimX), -(y - aimY))).normalise();
		currentAnimation = 0;
		double minTheta = 360;
		Vector3f playerDirectionAsVector3 = new Vector3f(playerDirection.x,
				playerDirection.y, 0);
		for (int a = 0; a < 8; ++a) {
			Vector3f asVector3 = new Vector3f(moveDirections[a].x,
					moveDirections[a].y, 0);
			float angle = Vector3f.angle(asVector3, playerDirectionAsVector3);
			if (angle < minTheta) {
				minTheta = angle;
				currentAnimation = a;
			}
		}
		if (!super.update(container, deltaMS)) {
			return false;
		}
		return true;
	}

	private float getPlayerSpeed() {
		return 10 + 2.0f;
	}

	private void updateFriction(boolean isMoving) {
		if (z == 0) {
			playerFriction = playerGroudFriction;
		} else {
			playerFriction = playerAirFriction;
		}
	}

}
