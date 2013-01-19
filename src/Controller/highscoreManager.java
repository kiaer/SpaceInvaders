package Controller;

import java.util.*;
import java.io.*;

import Model.highscore;
import Model.highscoreComparison;

public class highscoreManager {
	// An arraylist of the type "score" we will use to work with the scores
	// inside the class
	private ArrayList<highscore> highscores;

	// The name of the file where the highscores will be saved
	private static final String HIGHSCORE_FILE = "scores.dat";

	// Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;

	public highscoreManager() {
		// initialising the scores-arraylist
		highscores = new ArrayList<highscore>();
	}

	public ArrayList<highscore> getScores() {
		loadScoreFile();
		sort();
		return highscores;
	}

	private void sort() {
		highscoreComparison comparator = new highscoreComparison();
		Collections.sort(highscores, comparator);
	}

	public void addScore(String name, int score) {
		loadScoreFile();
		highscores.add(new highscore(name, score));
		updateScoreFile();
	}

	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(
					HIGHSCORE_FILE));
			highscores = (ArrayList<highscore>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					HIGHSCORE_FILE));
			outputStream.writeObject(highscores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage()
					+ ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	public String getHighscoreString() {
		String highscoreString = "";
		int max = 10;

		ArrayList<highscore> scores;
		scores = getScores();

		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			highscoreString += (i + 1) + " .\t" + scores.get(i).getName()
					+ " \t" + scores.get(i).getScore() + " \n";
			i++;
		}
		return highscoreString;
	}
}
