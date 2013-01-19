package Model;


import Controller.Constants;

public class Invaders extends GameObjects {

	private Game game;

	public Invaders(Game game, String ref, int x, int y, int health) {
		super(ref, x, y, health);

		this.game = game;

		dx = Constants.ALIEN_MOVE;

	}
	
	public void move(long delta) {

		if ((dx < 0) && (x < 10)) {
			
			game.changeDirection();
			
		}
		if ((dx > 0) && (x > 750)) {

			game.changeDirection();
		}

	}

	public void collide(GameObjects other) {
		
	}

}
