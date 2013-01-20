package Model;

import Controller.Constants;

public class Shot extends GameObjects {
	private Game game;
	private boolean used = false;

	public Shot(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;
		dy = Constants.SHOT_MOVE_SPEED;

	}

	public void move(long delta) {
		super.move(delta);
		if (y < Constants.REMOVE_SHOT) {
			this.game.RemoveDead(this);
		}
	}

	public void collide(GameObjects other) {

		if (used) {
			return;
		}

		if (other instanceof Invaders) {
			Game.IHit=true;
		    game.Invader.remove(other);
			game.RemoveDead(this);
			game.RemoveDead(other);
			game.score += 10;
			used = true;
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
