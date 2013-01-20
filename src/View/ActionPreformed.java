package View;
import java.awt.*;
import java.awt.event.*;

import Controller.Constants;
import Controller.Controller;
import Model.Game;

public class ActionPreformed extends Frame implements WindowListener,ActionListener {
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		TextField text = new TextField(20);
        Button b;
        public ActionPreformed(String title) {

                super();
                setLayout(new FlowLayout());
                addWindowListener(this);
                b = new Button("Start");
                add(b);
                add(text);
                addKeyListener(new Controller());

        		requestFocus();
                b.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
                text.setText(text.getText());
                Constants.Name=text.getText();
                System.out.println(Constants.Name);
                Game.startgame=true;
                System.out.println(Game.startgame); 
                dispose();
        }

        public void windowClosing(WindowEvent e) {
                dispose();
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {
        }



}