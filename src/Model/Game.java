package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import Controller.Constants;
import Controller.Controller;
import Controller.Sound;
import Controller.highscoreManager;
import View.Background;
import View.DrawString;
import View.GameFrame;

@SuppressWarnings("serial")
public class Game extends Canvas {
	public static BufferStrategy strategy;
	private ArrayList<GameObjects> Objects = new ArrayList<GameObjects>();
	public ArrayList<GameObjects> Invader = new ArrayList<GameObjects>();
	private ArrayList<Object> DeadObjects = new ArrayList<Object>();
	private ArrayList<GameObjects> Player = new ArrayList<GameObjects>();
	private ArrayList<Object> DeadBlocks = new ArrayList<Object>();
	private ArrayList<GameObjects> Life = new ArrayList<GameObjects>();
	private ArrayList<GG> gameover = new ArrayList<GG>();
	private ArrayList<GG> Win = new ArrayList<GG>();
	private GameObjects ship;
	private GameObjects invader;
	private GameObjects Lives;
	public static GameObjects block12;
	public static GameObjects block13;
	public static GameObjects block14;
	public static GameObjects block22;
	public static GameObjects block23;
	public static GameObjects block24;
	public static GameObjects block32;
	public static GameObjects block33;
	public static GameObjects block34;
	public static GameObjects block42;
	public static GameObjects block43;
	public static GameObjects block44;
	private Sound sound1 = new Sound("sprites/mario.mid");
	private Sound sound2 = new Sound("sprites/MarioDie.wav");
	private Sound sound3 = new Sound("sprites/Win.wav");
	private Sound pop = new Sound("sprites/die.wav");
	private Sound breaks = new Sound("sprites/break.wav");
	private Sound fireball = new Sound("sprites/Fireball.wav");
	private boolean gameRunning = true;
	private boolean Gameover = false;
	public boolean gamestart = true;
	public boolean changeRequired = false;
	public static boolean startgame = false;
	public boolean songplaying = false;
	public boolean Dsongplaying = false;
	public boolean Wsongplaying = false;
	public static boolean BlockHit = false;
	public static boolean IHit = false;
	private long lastFire = 0;
	private double invShooters;
	private double Random;
	private int lifeC = 2;
	private int level = 1;
	public int life = 3;
	public int InvadersHit = 0;
	public int score = 0;

	public Game() {

		new GameFrame();
		GameFrame.startG();
		while (true) {
			if (startgame) {
				if (life >= 3) {
					initializeObjects();
					loop();
				}
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}

	public void initializeObjects() {

		ship = new Player(this, "sprites/Mario.gif", 500, 500, 3);

		Objects.add(ship);
		Player.add(ship);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				invader = new Invaders(this, "sprites/alien.gif",
						100 + (j * 50), 50 + (i * 50), 1);
				Objects.add(invader);
				Invader.add(invader);
			}
		}
		for (int i = 0; i < life; i++) {
			Lives = new Life(this, "sprites/Life.gif", 0 + 30 * i, 10, 1);
			Objects.add(Lives);
			Life.add(Lives);
		}
		block12 = new Block1(this, "sprites/QblockHit1.png", 75, 450, 2);
		block13 = new Block1(this, "sprites/QblockHit2.png", 75, 459, 4);
		block14 = new Block1(this, "sprites/QblockHit3.png", 75, 473, 2);
		Objects.add(block12);
		Objects.add(block13);
		Objects.add(block14);

		block22 = new Block2(this, "sprites/QblockHit1.png", 275, 450, 2);
		block23 = new Block2(this, "sprites/QblockHit2.png", 275, 459, 4);
		block24 = new Block2(this, "sprites/QblockHit3.png", 275, 473, 2);
		Objects.add(block22);
		Objects.add(block23);
		Objects.add(block24);

		block32 = new Block3(this, "sprites/QblockHit1.png", 475, 450, 2);
		block33 = new Block3(this, "sprites/QblockHit2.png", 475, 459, 4);
		block34 = new Block3(this, "sprites/QblockHit3.png", 475, 473, 2);
		Objects.add(block32);
		Objects.add(block33);
		Objects.add(block34);

		block42 = new Block4(this, "sprites/QblockHit1.png", 675, 450, 2);
		block43 = new Block4(this, "sprites/QblockHit2.png", 675, 459, 4);
		block44 = new Block4(this, "sprites/QblockHit3.png", 675, 473, 2);
		Objects.add(block42);
		Objects.add(block43);
		Objects.add(block44);

	}

	public void GameOver() {

		GG gg1 = new GG(this, "sprites/GG1.png", 300, 400, 1);
		Objects.add(gg1);
		gameover.add(gg1);

	}

	public void Win() {

		GG gg = new GG(this, "sprites/Win.png", 0, 0, 1);

		Objects.add(gg);
		Win.add(gg);

	}

	public void shoot() {

		if (System.currentTimeMillis() - lastFire < Constants.ShotInterval) {
			return;
		}
		lastFire = System.currentTimeMillis();
		Shot shot = new Shot(this, "sprites/FB.gif", ship.getX() + 17,
				ship.getY() - 5, 1);
		Objects.add(shot);
		fireball.song.play();

	}

	public void INVshoot() {
		GameObjects INVrandom = Invader.get((int) ((Invader.size() - 1) * Math
				.random()));
		System.currentTimeMillis();
		Invader_Shot INVshot = new Invader_Shot(this, "sprites/INVshot.gif",
				INVrandom.getX() + 10, INVrandom.getY() + 15, 1);
		Objects.add(INVshot);

	}

	public void loop() {

		highscoreManager hm = new highscoreManager();
		long lastLoopTime = System.currentTimeMillis();

		while (gameRunning) {

			if (!songplaying) {

				System.out.println("start playing");
				sound1.song.loop();
				songplaying = true;
			}if (BlockHit) {
				breaks.song.play();
				BlockHit = false;
				
			}if (IHit) {
				pop.song.play();
				IHit = false;
				
			}

			long delta = System.currentTimeMillis() - lastLoopTime;

			lastLoopTime = System.currentTimeMillis();

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			new Background(g, "sprites/MarioWorld.png", 800, 600);
			new DrawString(g, "Level: " + level, 650, 5, 25);
			new DrawString(g, "Score: " + score, 425, 5, 25);

			if (life > 0) {

				for (int i = 0; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.draw(g);

				}
				for (int i = 0; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.GroupInvaders();
					objects.move(delta);

				}

				for (int p = 0; p < Objects.size(); p++) {
					for (int s = p + 1; s < Objects.size(); s++) {
						GameObjects me = (GameObjects) Objects.get(p);
						GameObjects him = (GameObjects) Objects.get(s);

						if (me.collided(him)) {
							me.collide(him);
							him.collide(me);

						}
					}
				}
				for (int d = lifeC; d > (life - 1); d--) {
					RemoveDead(Life.get(d));

				}
			}
			for (int p = 0; p < Invader.size(); p++) {
				if (Invader.get(p).getY() > 465) {
					life = 0;

				}
			}
			if (Controller.ESC) {
				System.exit(0);
				break;

			}
			if (Controller.eight) {
				Constants.MOVE_SPEED = 8;
				Constants.ShotInterval = 30;
			}
			if (Controller.nine) {
				Constants.MOVE_SPEED = 4;
				Constants.ShotInterval = 300;

			}

			if (life <= 0) {

				if (!Dsongplaying) {
					sound1.song.stop();
					sound2.song.play();
					Dsongplaying = true;
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				GameOver();
				GameObjects objects = (GameObjects) gameover.get(0);
				objects.draw(g);
				Gameover = true;
				hm.addScore(Constants.Name + " :", score);
				new DrawString(g, "HIGHSCORE: ", 5, 5, 40);
				new DrawString(g, hm.getHighscoreString(), 5, 40, 30);
				Constants.WinBoost = 0.0;
				Constants.ALIEN_MOVE = 3;
				score = 0;
				level = 0;
			}
			System.out.println(Invader.size());
			if (Invader.size() == 0) {

				if (!Wsongplaying) {
					sound1.song.stop();
					sound3.song.play();
					Wsongplaying = true;
				}
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
				Win();
				GameObjects objects1 = (GameObjects) Win.get(0);
				objects1.draw(g);
				Gameover = true;

			}
			if (Gameover && Controller.Enter) {
				if (life == 0) {
					life = 3;
					lifeC = 2;

				} else {
					life = life + 1;
					lifeC = life - 1;
					score += 100;
					Constants.WinBoost = Constants.WinBoost + 0.005;
					Constants.ALIEN_MOVE = Constants.ALIEN_MOVE + 0.25;
				}
				sound2.song.stop();
				sound3.song.stop();

				songplaying = false;
				Wsongplaying = false;
				Dsongplaying = false;
				level = level + 1;
				Objects.clear();
				Life.clear();
				Invader.clear();
				Gameover = false;
				break;

			}
			if (changeRequired == true) {
				for (int i = 0; i < Invader.size(); i++) {
					GameObjects objects = (GameObjects) Invader.get(i);
					objects.doChange();

				}
				changeRequired = false;
			}

			if (changeRequired == true) {
				for (int i = 0; i < Invader.size(); i++) {
					GameObjects objects = (GameObjects) Invader.get(i);
					objects.doChange();

				}
				changeRequired = false;
			}

			ship.setHorizontalMovement(0);

			if ((Controller.rightPressed == true)
					&& (!Controller.leftPressed == true)) {
				ship.setHorizontalMovement(Constants.MOVE_SPEED);

			} else if ((Controller.leftPressed == true)
					&& (!Controller.rightPressed == true)) {
				ship.setHorizontalMovement(-(Constants.MOVE_SPEED));

			}
			if (changeRequired == true) {
				for (int i = 0; i < Invader.size(); i++) {
					GameObjects objects = (GameObjects) Invader.get(i);
					objects.doChange();

				}
				changeRequired = false;

			}
			if (Controller.firePressed) {

				shoot();
				Controller.firePressed = false;

			}

			invShooters = (delta * (0.02 + Constants.WinBoost));

			Random = (Math.random() * delta + 0.2);

			if (Invader.size() > 0 && (Random) < (invShooters)) {
				INVshoot();
			}
			Objects.removeAll(DeadObjects);
			Objects.removeAll(DeadBlocks);
			DeadObjects.clear();

			g.dispose();
			strategy.show();
			try {
				Thread.sleep(15);
			} catch (Exception e) {
			}

		}
	}

	public void RemoveDead(Object gameobject) {
		DeadObjects.add(gameobject);
	}

	public void RemoveBlock(Object gameobject) {
		DeadBlocks.add(gameobject);
	}

	public void changeDirection() {
		changeRequired = true;

	}

}
