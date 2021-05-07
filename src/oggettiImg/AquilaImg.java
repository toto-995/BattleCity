package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import oggetti.Aquila;

/**
 * Questa classe serve per disegnare l'immagine dell'Aquila.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */

public class AquilaImg {
	private Aquila a;
	private BufferedImage aquila;

	/**
	 * Questo metodo serve a caricare l'immagine dell'aquila.
	 * 
	 * @param a
	 *            è un oggetto Aquila.
	 */
	public AquilaImg(Aquila a) {
		this.a = a;
		try {
			aquila = ImageIO.read(getClass().getResource("/img/Aquila.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo serve a disegnare l'oggetto aquila.
	 * 
	 * @param gg
	 *            è un Graphics2D ed è la parte del pannello su cui si disegna.
	 */
	public void disegna(Graphics2D gg) {

		gg.drawImage(aquila, a.getX(), a.getY(), null);
	}
}
