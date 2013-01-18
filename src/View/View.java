package View;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import Controller.Controller;
import Model.GG;
import Model.Invader_Shot;
import Model.Invaders;
import Model.Player;
import Model.Shot;
import Model.Life;
import Model.highscoreManager;
import spaceinvaders.*;
import Model.GameObjects;

public class View extends Canvas {

	private ArrayList Objects = new ArrayList();
	private ArrayList Shot = new ArrayList();
	private ArrayList Life = new ArrayList();
	private ArrayList DeadObjects = new ArrayList();
	private ArrayList Win = new ArrayList();
	private ArrayList gameover = new ArrayList();
	private ArrayList<GameObjects> INVShot = new ArrayList();
	private ArrayList<GameObjects> Player = new ArrayList();
	public ArrayList<GameObjects> Invader = new ArrayList();
	private BufferStrategy strategy;
	private GameObjects ship;
	private GameObjects invader;
	private GameObjects I_invader;
	private GameObjects shot;
	private GameObjects Lives;
	private GameObjects GameOver;
	private BufferedImage background;
	private int bgCount = 1;
	private long lastFire = 0;
	private long INV_lastFire = 0;
	private boolean gameRunning = true;
	private boolean Gameover = false;
	private int a;
	private double b;
	public int InvadersHit = 0;
	private double invShooters;
	public int life = 3;
	public boolean gamestart = true;
	private double Random;
	public boolean changeRequired = false;
	private int lifeC = 2;
	private int level = 1;
	public int score = 0;
	private AudioClip song;
	private URL songPath;
	private boolean songPlaying = false;

	public View() {

		GameRunning();
		if (!songPlaying) {
			sound();
			songPlaying = true;
		}

		while (10 > 1) {
			b = Math.random();
			if (b < 0.5) {
				// System.out.println(b);
				a = 1;
			} else {
				a = 0;
			}
			if (life >= 3) {

				initializeObjects();
				Life();
				loop();
			}
		}
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

	public void initializeObjects() {

		ship = new Player(this, "sprites/mario.gif", 500, 500);

		Objects.add(ship);
		Player.add(ship);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				invader = new Invaders(this, "sprites/alien.gif",
						100 + (j * 50), 10 + (i * 50));
				// I_invader = new Invaders(this, "sprites/Ialien.gif",100 + (j
				// * 50), 10 + (i * 50));
				Objects.add(invader);
				// Objects.add(I_invader);
				// Invader.add(I_invader);
				Invader.add(invader);
			}
		}

	}

	public void Life() {
		for (int i = 0; i < life; i++) {
			Lives = new Life(this, "sprites/life.gif", 0 + 30 * i, 10);
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
				ship.getY() - 30);
		Objects.add(shot);
		Shot.add(shot);

	}

	public void INVshoot() {
		GameObjects INVrandom = Invader.get((int) ((Invader.size() - 1) * Math
				.random()));
		INV_lastFire = System.currentTimeMillis();
		Invader_Shot INVshot = new Invader_Shot(this, "sprites/INVshot.gif",
				INVrandom.getX() + 10, INVrandom.getY() + 15);
		Objects.add(INVshot);
		INVShot.add(INVshot);

	}

	public void GameOver() {

		GG gg1 = new GG(this, "sprites/GG.gif", 0, 0);
		GG gg2 = new GG(this, "sprites/GGS.gif", 0, 0);
		Objects.add(gg1);
		gameover.add(gg1);
		Objects.add(gg2);
		gameover.add(gg2);
	}

	public void Win() {

		GG gg = new GG(this, "sprites/WIN.gif", 0, 0);

		Objects.add(gg);
		Win.add(gg);

	}

	public void loop() {

		highscoreManager hm = new highscoreManager();

		long lastLoopTime = System.currentTimeMillis();

		while (gameRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;

			lastLoopTime = System.currentTimeMillis();

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			background(g);

			if (changeRequired == true) {
				for (int i = 0; i < Invader.size(); i++) {
					GameObjects objects = (GameObjects) Invader.get(i);
					objects.doChange();

				}
				changeRequired = false;
				// System.out.println(Invader.size());
			}
			if (Invader.size() == 0) {
				GameObjects objects = (GameObjects) Objects.get(1);
				objects.draw(g);
				// shot.animate(g);

			}

			if (life > 0) {
				for (int i = 0; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.draw(g);
					// shot.animate(g);

				}

				for (int i = 0; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.changeDirection();
					objects.move(delta);

					drawString(g, "Level: " + level, 720, 30, 25);
					drawString(g, "Score: " + score, 570, 30, 25);
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

				Objects.removeAll(DeadObjects);

				DeadObjects.clear();

			}
			if (invader.getY() > 465) {
				life = 0;

			}

			if (life == 0) {

				GameOver();
				GameObjects objects = (GameObjects) gameover.get(a);
				objects.draw(g);
				Gameover = true;
				hm.addScore("Christian", score);
				System.out.println(hm.getHighscoreString());
				drawString(g, "HIGHSCORE: ", 75,70,40 );
				drawString(g, hm.getHighscoreString(), 100, 100, 40);
				Constants.WinBoost = 0.0;
				Constants.ALIEN_MOVE = 3;
				score = 0;
				level = 0;

			}
			if (Invader.size() == 0) {
				Win();
				GameObjects objects1 = (GameObjects) Win.get(0);
				objects1.draw(g);
				Gameover = true;

			}
			// System.out.println(invader.getY());
			if (Gameover && Controller.firePressed) {
				if (life == 0) {
					life = 3;
					lifeC = 2;

				} else {
					life = life + 1;
					lifeC = life - 1;
					score += 100;
				}

				level = level + 1;
				Objects.clear();
				Life.clear();
				Invader.clear();
				Gameover = false;
				Constants.WinBoost = Constants.WinBoost + 0.005;
				System.out.println(Constants.ALIEN_MOVE);
				Constants.ALIEN_MOVE = Constants.ALIEN_MOVE + 0.25;

				break;

			}

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
			invShooters = (delta * (0.02 + Constants.WinBoost));
			// System.out.println(invShooters);
			Random = (Math.random() * delta + 0.2);
			// System.out.println(Invader.size());
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

	public void changeDirection() {
		changeRequired = true;

	}

	public void background(Graphics2D g) {
		URL url = this.getClass().getClassLoader()
				.getResource("sprites/MarioWorld.gif");
		try {
			background = ImageIO.read(url);
		} catch (IOException ex) {
			System.out.println("lol?");
		}
		g.drawImage(background, 0, 0, 800, 600, Color.black, null);

	}

	public void sound() {

		try

		{
			URL songPath = this.getClass().getClassLoader()
					.getResource("sprites/music.mid"); // Get the Sound URL

			song = Applet.newAudioClip(songPath); // Load the Sound

		}

		catch (Exception e) {
			System.out.println("LOL");
		} // Satisfy the catch

		song.loop();

	}

	private void drawString(Graphics g, String text, int x, int y, int size) {
		Font f = new Font("Super Plumber Brothers", Font.PLAIN, size);

		g.setColor(Color.orange);
		g.setFont(f);

		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}
