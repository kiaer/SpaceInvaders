package Model;

import Controller.Constants;

public class Invader_Shot extends GameObjects {
	private Game game;
	private boolean used = false;

	public Invader_Shot(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;
		dy = Constants.INV_SHOT_MOVE_SPEED;

	}

	public void move(long delta) {
		super.move(delta);
		if (y > Constants.INV_REMOVE_SHOT) {
			this.game.RemoveDead(this);
		}
	}

	public void collide(GameObjects other) {

		if (used) {
			return;
		}

		if (other instanceof Player) {
			game.life = game.life - 1;
			System.out.println(game.life);
			game.RemoveDead(this);
			if (game.life == 0) {
				game.gamestart = false;

				game.RemoveDead(other);

				used = true;
			}
		}
		if (other instanceof Block1) {

			game.RemoveDead(this);
			used = true;
						
		}
		if (other instanceof Block2) {

			game.RemoveDead(this);
			used = true;
						
		}if (other instanceof Block3) {

			game.RemoveDead(this);
			used = true;
						
		}if (other instanceof Block4) {

			game.RemoveDead(this);
			used = true;
						
		}
	}

}