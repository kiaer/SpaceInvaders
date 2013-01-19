package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.*;
import Controller.Controller;
import Model.Background;
import Model.Block1;
import Model.Block2;
import Model.Block3;
import Model.GG;
import Model.Invader_Shot;
import Model.Invaders;
import Model.Player;
import Model.Shot;
import Model.Life;
import Model.Block4;
import Model.Sound;
import Model.highscoreManager;
import spaceinvaders.*;
import Model.GameObjects;

public class View extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObjects> Objects = new ArrayList<GameObjects>();
	private ArrayList<Shot> Shot = new ArrayList<Shot>();
	private ArrayList<GameObjects> Life = new ArrayList<GameObjects>();
	private ArrayList<Object> DeadObjects = new ArrayList<Object>();
	private ArrayList<GG> Win = new ArrayList<GG>();
	private ArrayList<GG> gameover = new ArrayList<GG>();
	private ArrayList<Object> DeadBlocks = new ArrayList<Object>();
	private ArrayList<GameObjects> INVShot = new ArrayList<GameObjects>();
	private ArrayList<GameObjects> Player = new ArrayList<GameObjects>();
	public ArrayList<GameObjects> Invader = new ArrayList<GameObjects>();
	private BufferStrategy strategy;
	private GameObjects ship;
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
	private GameObjects invader;
	private GameObjects Lives;
	private long lastFire = 0;
	private boolean gameRunning = true;
	private boolean Gameover = false;
	public int InvadersHit = 0;
	private double invShooters;
	public int life = 3;
	public boolean gamestart = true;
	private double Random;
	public boolean changeRequired = false;
	private int lifeC = 2;
	private int level = 1;
	public int score = 0;
	public static String name = "Enter your name";
	public static ActionPreformed panel;
	public static boolean startgame = false;
	public boolean songplaying = false;
	public boolean Dsongplaying = false;
	public boolean Wsongplaying = false;
	private Sound sound1 = new Sound("sprites/mario.mid");
	private Sound fireball = new Sound("sprites/Fireball.wav");
	private Sound sound2 = new Sound("sprites/MarioDie.wav");
	private Sound sound3 = new Sound("sprites/Win.wav");

	public View() {

		GameRunning();
		startG();

		while (true) {

			// System.out.println(startgame);
			if (startgame) {
				if (life >= 3) {

					initializeObjects();
					Life();
					loop();
				}
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}

	public static void startG() {
		panel = new ActionPreformed("Enter name and start game");
		panel.setSize(350, 100);
		panel.setVisible(true);
	}

	public void GameRunning() {
		JFrame container = new JFrame("Mario Invaders");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		setBounds(0, 0, 800, 600);
		panel.add(this);
		panel.setBackground(Color.GRAY);
		setIgnoreRepaint(true);

		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addKeyListener(new Controller());

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

	}

	public void GameOver() {

		GG gg1 = new GG(this, "sprites/GG1.png", 300, 400, 1);
		// GG gg2 = new GG(this, "sprites/GGS.gif", 0, 0);
		Objects.add(gg1);
		gameover.add(gg1);

	}

	public void Win() {

		GG gg = new GG(this, "sprites/Win.png", 0, 0, 1);

		Objects.add(gg);
		Win.add(gg);

	}

	public void initializeObjects() {

		ship = new Player(this, "sprites/Mario.gif", 500, 500, 3);

		Objects.add(ship);
		Player.add(ship);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				invader = new Invaders(this, "sprites/alien.gif",
						100 + (j * 50), 10 + (i * 50), 1);
				Objects.add(invader);
				Invader.add(invader);
			}
		}

		// block11 = new Block1(this, "sprites/QBlock.png", 75, 450, 4);
		block12 = new Block1(this, "sprites/QblockHit1.png", 75, 450, 2);
		block13 = new Block1(this, "sprites/QblockHit2.png", 75, 451, 4);
		block14 = new Block1(this, "sprites/QblockHit3.png", 75, 473, 2);
		// Objects.add(block11);
		// Blocks.add(block11);
		Objects.add(block12);
		Objects.add(block13);
		Objects.add(block14);

		// block21 = new Block2(this, "sprites/QBlock.png", 75, 450, 4);
		block22 = new Block2(this, "sprites/QblockHit1.png", 275, 450, 2);
		block23 = new Block2(this, "sprites/QblockHit2.png", 275, 451, 4);
		block24 = new Block2(this, "sprites/QblockHit3.png", 275, 473, 2);
		// Objects.add(block21);
		// Blocks.add(block21);
		Objects.add(block22);
		Objects.add(block23);
		Objects.add(block24);

		// block31 = new Block3(this, "sprites/QBlock.png", 75, 450, 4);
		block32 = new Block3(this, "sprites/QblockHit1.png", 475, 450, 2);
		block33 = new Block3(this, "sprites/QblockHit2.png", 475, 451, 4);
		block34 = new Block3(this, "sprites/QblockHit3.png", 475, 473, 2);
		// Objects.add(block31);
		// Blocks.add(block31);
		Objects.add(block32);
		Objects.add(block33);
		Objects.add(block34);

		// block41 = new Block4(this, "sprites/QBlock.png", 75, 450, 4);
		block42 = new Block4(this, "sprites/QblockHit1.png", 675, 450, 2);
		block43 = new Block4(this, "sprites/QblockHit2.png", 675, 451, 4);
		block44 = new Block4(this, "sprites/QblockHit3.png", 675, 473, 2);
		// Objects.add(block41);
		// Blocks.add(block41);
		Objects.add(block42);
		Objects.add(block43);
		Objects.add(block44);
	}

	public void Life() {
		for (int i = 0; i < life; i++) {
			Lives = new Life(this, "sprites/Life.gif", 0 + 30 * i, 10, 1);
			Objects.add(Lives);
			Life.add(Lives);
		}
	}

	public void shoot() {

		if (System.currentTimeMillis() - lastFire < Constants.ShotInterval) {
			return;
		}
		lastFire = System.currentTimeMillis();
		Shot shot = new Shot(this, "sprites/FB.gif", ship.getX() + 17,
				ship.getY() - 5, 1);
		Objects.add(shot);
		Shot.add(shot);
		
		fireball.song.play();

	}

	public void INVshoot() {
		GameObjects INVrandom = Invader.get((int) ((Invader.size() - 1) * Math
				.random()));
		System.currentTimeMillis();
		Invader_Shot INVshot = new Invader_Shot(this, "sprites/INVshot.gif",
				INVrandom.getX() + 10, INVrandom.getY() + 15, 1);
		Objects.add(INVshot);
		INVShot.add(INVshot);

	}

	public void loop() {

		highscoreManager hm = new highscoreManager();

		long lastLoopTime = System.currentTimeMillis();

		while (gameRunning) {

			if (!songplaying) {
				
				System.out.println("start playing");
				sound1.song.loop();
				songplaying = true;
			}

			long delta = System.currentTimeMillis() - lastLoopTime;

			lastLoopTime = System.currentTimeMillis();

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			new Background(g, "sprites/MarioWorld.png", 800, 600);

			if (life > 0) {
				System.out.println(DeadBlocks.size());
				
				for (int i = 0; i < Objects.size(); i++) {
					Objects.removeAll(DeadBlocks);
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.draw(g);

				}

				for (int i = 0; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.GroupInvaders();
					objects.move(delta);

					drawString(g, "Level: " + level, 720, -5, 25);
					drawString(g, "Score: " + score, 570, -5, 25);
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
			}if(Controller.ESC){
				System.exit(0);
				break;
				
			}if(Controller.eight){
				Constants.MOVE_SPEED=8;
				Constants.ShotInterval=30;
			}if(Controller.nine){
				Constants.MOVE_SPEED=4;
				Constants.ShotInterval=300;
				
			}
			
			if (life == 0) {

				if (!Dsongplaying) {
					System.out.println("im here sonny");
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
				drawString(g, "HIGHSCORE: ", 5, 5, 40);
				drawString(g, hm.getHighscoreString(), 20, 20, 30);
				Constants.WinBoost = 0.0;
				Constants.ALIEN_MOVE = 3;
				score = 0;
				level = 0;
				DeadBlocks.clear();

			}
			if (Invader.size() == 0) {
				sound1.song.stop();
				
				if (!Wsongplaying) {
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
				
				
				//System.out.println("hey buddy");
			}
			if (Gameover && Controller.Enter) {
				if (life == 0) {
					life = 3;
					lifeC = 2;

				} else {
					System.out.println("op much?");
					life = life + 1;
					lifeC = life - 1;
					score += 100;
					Constants.WinBoost = Constants.WinBoost + 0.005;
					System.out.println(Constants.ALIEN_MOVE);
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
			Objects.removeAll(DeadObjects);
			DeadObjects.clear();

			g.dispose();
			strategy.show();

			ship.setHorizontalMovement(0);

			if ((Controller.rightPressed == true)
					&& (!Controller.leftPressed == true)) {
				ship.setHorizontalMovement(Constants.MOVE_SPEED);

			} else if ((Controller.leftPressed == true)
					&& (!Controller.rightPressed == true)) {
				ship.setHorizontalMovement(-(Constants.MOVE_SPEED));

			}
			if (Controller.firePressed) {

				shoot();
				
				Controller.firePressed = false;

			}
			if (changeRequired == true) {
				for (int i = 0; i < Invader.size(); i++) {
					GameObjects objects = (GameObjects) Invader.get(i);
					objects.doChange();

				}
				changeRequired = false;

			}

			invShooters = (delta * (0.02 + Constants.WinBoost));

			Random = (Math.random() * delta + 0.2);

			if (Invader.size() > 0 && (Random) < (invShooters)) {
				INVshoot();
			}
			try {
				Thread.sleep(10);
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

	private void drawString(Graphics g, String text, int x, int y, int size) {
		Font f = new Font("Impact", Font.PLAIN, size);

		g.setColor(Color.black);
		g.setFont(f);

		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}
