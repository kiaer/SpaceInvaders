package Model;


public class Block4 extends GameObjects {

	private Game game;
	//constructor for Block 3
	public Block4(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;

	}

	//collide method checks if block has collided with anything
	public void collide(GameObjects other) {
		if (other instanceof Invader_Shot) {
			this.health = health - 1;
			System.out.println(this.health);
			if (this.health <= 0) {
				Game.BlockHit=true;
				game.RemoveBlock(this);
			}
		}
		if (other instanceof Shot) {
			
			this.health = health - 1;
			System.out.println(this.health);
			game.RemoveDead(other);
			if (this.health <= 0) {
				Game.BlockHit=true;
				game.RemoveBlock(this);
			}
		}
	}
}
