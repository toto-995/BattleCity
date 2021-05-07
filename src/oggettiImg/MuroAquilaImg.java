package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import oggetti.MuroAquila;

/**
 * Questa classe serve per disegnare l'icona per i muri indistruttibili attorno
 * all'aquila.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class MuroAquilaImg {
	private BufferedImage muro = null;
	private MuroAquila m;

	/**
	 * Questo metodo serve a caricare l'immagine dell'aquila.
	 * 
	 * @param m
	 *            è l'oggetto MuroAquila.
	 */
	public MuroAquilaImg(MuroAquila m) {
		this.m = m;
		try {
			muro = ImageIO.read(getClass().getResource("/img/muroAquila.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo disegna le icone dell'aquila sulla mappa.
	 * 
	 * @param gg
	 *            è un Graphics2D ed è la parte del pannello su cui si disegna.
	 */
	public void disegna(Graphics2D gg) {
		gg.drawImage(muro, m.getX(), m.getY(), null);
	}
}
