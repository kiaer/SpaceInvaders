package Model;

import java.io.Serializable;

public class highscore implements Serializable {
	
	private int score;
	private String name;

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public highscore(String name, int score) {
		this.score = score;
		this.name = name;
	}
}
