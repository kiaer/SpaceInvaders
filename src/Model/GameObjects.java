package Model;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObjects {

	protected int x;
	protected int y;
	protected int a;
	protected int b;
	protected int c;
	protected int d;
	protected int e;
	protected int f;
	protected int h;
	protected int i;
	protected Sprite sprite;
	protected double dy;
	protected double dx;
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();
	public int health;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public GameObjects(String ref, int x, int y, int health) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.x = x;
		this.y = y;
		this.health = health;
		
	}

	public void draw(Graphics g) {
		sprite.draw(g, (int) x, (int) y);
	}


	public void setHorizontalMovement(double dx) {
		if (x >= 5 && x <= 750) {
			this.dx = dx;
			x += (dx);
		} else if (x < 5) {
			x = 5;
		} else if (x > 750) {
			x = 750;
		}
	}

	public void move(long delta) {
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
	}


	public void GroupInvaders() {
		x += (dx);
		for (int i = 0; i > 10; i = i + 5) {
		}
	}

	public boolean collided(GameObjects other) {
		me.setBounds((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
		him.setBounds((int) other.x, (int) other.y, other.sprite.getWidth(),
				other.sprite.getHeight());

		return me.intersects(him);
	}

	public boolean change() {
		x += (dx);
		int k = 0;
		for (int i = 0; i > 10; i = i + 5) {
			if (x < 0 || x > 700) {

				k = 1;
				break;
			}
		}
		if (k == 1)
			return true;
		else
			return false;
	}

	public void doChange() {
		dx *= (-1);
		y = y + 25;
	}
	public void MinusHealth(){
		this.health=health-1;
	}

	public abstract void collide(GameObjects other);

}
