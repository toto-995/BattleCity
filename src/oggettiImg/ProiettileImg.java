package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetti.Proiettile;

/**
 * Questa classe serve per disegnare i proiettili sulla mappa.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class ProiettileImg {

	private Proiettile proiettile;
	private BufferedImage image;

	/**
	 * Questo metodo serve a caricare l'immagine del proiettile.
	 * @param p
	 */
	public ProiettileImg(Proiettile p) {
		this.proiettile = p;
		try {
			image = ImageIO.read(getClass().getResource("/img/Pro.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		gg.drawImage(image, proiettile.getX(), proiettile.getY(), proiettile.getH(), proiettile.getW(), null);
	}// disegna

}
