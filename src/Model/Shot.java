package Model;

import spaceinvaders.*;
import View.*;

public class Shot extends GameObjects {
	private View view;
	private boolean used = false;
	public Shot(View view, String ref, int x, int y) {
		super(ref, x, y);

		this.view = view;
		dy = Constants.SHOT_MOVE_SPEED;
		
	}

	public void move(long delta) {
		super.move(delta);
		if(y<Constants.REMOVE_SHOT){
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
	}


}
