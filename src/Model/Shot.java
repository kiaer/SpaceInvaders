package Model;

import spaceinvaders.*;
import View.*;

public class Shot extends GameObjects {
	private View view;
	private boolean used = false;

	public Shot(View view, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.view = view;
		dy = Constants.SHOT_MOVE_SPEED;

	}

	public void move(long delta) {
		super.move(delta);
		if (y < Constants.REMOVE_SHOT) {
			this.view.RemoveDead(this);
		}
	}

	public void collide(GameObjects other) {

		if (used) {
			return;
		}

		if (other instanceof Invaders) {
			view.Invader.remove(other);
			view.RemoveDead(this);
			view.RemoveDead(other);
			view.score += 10;
			used = true;
		}
		if (other instanceof Block1) {

			view.RemoveDead(this);
			used = true;
						
		}
		if (other instanceof Block2) {

			view.RemoveDead(this);
			used = true;
						
		}if (other instanceof Block3) {

			view.RemoveDead(this);
			used = true;
						
		}if (other instanceof Block4) {

			view.RemoveDead(this);
			used = true;
						
		}
	}

}
