package Model;


public class Block4 extends GameObjects {

	private Game game;

	public Block4(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;

	}

	@Override
	public void collide(GameObjects other) {
		if (other instanceof Invader_Shot) {
			this.health = health - 1;
			System.out.println(this.health);
			if (this.health <= 0) {
				game.RemoveBlock(this);
			}
		}
		if (other instanceof Shot) {
			this.health = health - 1;
			System.out.println(this.health);
			game.RemoveDead(other);
			if (this.health <= 0) {
				game.RemoveBlock(this);
			}
		}
	}
}
