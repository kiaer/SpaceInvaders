package Model;


public class Block3 extends GameObjects {

	private Game game;

	public Block3(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;

	}

	public void collide(GameObjects other) {
		if (other instanceof Invader_Shot) {
			Game.BlockHit=true;
			this.health = health - 1;
			if (this.health <= 0) {
				game.RemoveBlock(this);
			}
		}
		if (other instanceof Shot) {
			Game.BlockHit=true;
			this.health = health - 1;
			game.RemoveDead(other);
			if (this.health <= 0) {
				game.RemoveBlock(this);
			}
		}
	}
}
