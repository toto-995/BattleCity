package gestione;

import java.net.URL;

import javax.sound.sampled.*;

/**
 * Questa classe serve per la gestione dei suoni.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Suono {

	/**
	 * Questo metodo serve a riprodurre un suono.
	 * 
	 * @param Sound
	 *            è un URL che indica l'indirizzo del suono da riprodurre.
	 */
	public static void play(URL Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();

			// Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.out.println("eccezione");
		}
	}

	/**
	 * Questo metodo serve a interrompere la riproduzione del suono.
	 * 
	 * @param Sound
	 *            è un URL e indica l'indirizzo del suono da interrompere.
	 */
	public static void stop(URL Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.stop();
		} catch (Exception e) {
			System.out.println("eccezione");
		}
	}

	/**
	 * Questo metodo serve a riprodurre in loop un suono.
	 * 
	 * @param Sound
	 *            è un URL e indica l'indirizzo del suono da riprodurre.
	 */
	public static void loop(URL Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("eccezione");
		}
	}
}
