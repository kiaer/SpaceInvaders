package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Model.Game;

@SuppressWarnings("serial")
public class GameFrame extends Canvas{
	public static ActionPreformed panel;
	public GameFrame() {
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
		Game.strategy = getBufferStrategy();

	}public static void startG() {
		panel = new ActionPreformed("Enter name and start game");
		panel.setSize(350, 100);
		panel.setVisible(true);
	}

}
