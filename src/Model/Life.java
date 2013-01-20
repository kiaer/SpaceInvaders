package Model;


public class Life extends GameObjects {
	//constructor for Life images
	public Life(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);
	}

	//is needed because collide is an abstract method in GameObjects
	public void collide(GameObjects other) {

		if (other instanceof Invaders) {
		}

	}
}
