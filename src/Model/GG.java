package Model;


public class GG extends GameObjects {

	public GG(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);
	}


	public void collide(GameObjects other) {

		if (other instanceof Invaders) {
		}

	}
}
