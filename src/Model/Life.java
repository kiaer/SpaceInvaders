package Model;

import spaceinvaders.*;

import java.awt.Color;
import java.awt.Point;

import View.View;

public class Life extends GameObjects {

	private View view;

	public Life(View view, String ref, int x, int y) {
		super(ref, x, y);

		this.view = view;
	}


	public void collide(GameObjects other) {

		if (other instanceof Invaders) {
		}

	}
}
