package Controller;

import java.awt.event.*;

public class Controller extends KeyAdapter {
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
	public static boolean firePressed = false;
	public boolean waitingForKeyPress = true;
	public static boolean ESC = false;
	public static boolean eight=false;
	public static boolean nine=false;
	public static boolean Enter=false;
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
			//System.out.println(rightPressed);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = true;
			//System.out.println(firePressed);
		}else{
			firePressed=false;
		}if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			ESC=true;
			
		}if(e.getKeyCode() == KeyEvent.VK_8){
			eight=true;
			
		}if(e.getKeyCode() == KeyEvent.VK_9){
			nine=true;
			
		}if(e.getKeyCode() == KeyEvent.VK_ENTER){
			Enter=true;
			
		}
	} 
		
		
	public void keyReleased(KeyEvent e) {			
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = false;
		}if(e.getKeyCode() == KeyEvent.VK_8){
			eight=false;
			
		}if(e.getKeyCode() == KeyEvent.VK_9){
			nine=false;
			
		}if(e.getKeyCode() == KeyEvent.VK_ENTER){
			Enter=false;
			
		}if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			ESC=false;
			
		}
	}

	public void keyTyped(KeyEvent e) {
		// if we hit escape, then quit the game
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}
	
	public boolean isWaitingForKeyPress() {
		return waitingForKeyPress;
	}
	
	public void setWaitingForKeyPress(boolean waitingForKeyPress) {
		this.waitingForKeyPress = waitingForKeyPress;
	}
	
}
