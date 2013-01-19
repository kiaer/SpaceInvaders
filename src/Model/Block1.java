package Model;

import View.View;

public class Block1 extends GameObjects {

	private View view;

	public Block1(View view, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.view = view;

	}

	@Override
	public void collide(GameObjects other) {
		if (other instanceof Invader_Shot) {
			this.health = health - 1;
			System.out.println(this.health);
			if (this.health <= 0) {
				view.RemoveBlock(this);
			}
		}
		if (other instanceof Shot) {
			this.health = health - 1;
			System.out.println(this.health);
			view.RemoveDead(other);
			if (this.health <= 0) {
				view.RemoveBlock(this);
			}
		}
	}
}
