package Model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
	public AudioClip song;
	public Sound(String string) {

		try

		{
			URL songPath = this.getClass().getClassLoader()
					.getResource(string); // Get the Sound URL

			song = Applet.newAudioClip(songPath); // Load the Sound

		}

		catch (Exception e) {
			System.out.println("No Music mate");
		} // Satisfy the catch


	}

}
