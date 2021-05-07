package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import oggetti.Vite;
/**
 * Questa classe serve per disegnare le icone della vita sulla mappa.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class ViteImg {
	private BufferedImage vite = null;
	private Vite vita;
	/**
	 * Questo metodo serve a caricare la foto dell'icona della vita.
	 * @param v è una vita.
	 */
	public ViteImg(Vite v) {
		this.vita=v;
		try {
			vite = ImageIO.read(getClass().getResource("/img/salute.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Questo metodo disegna i teletrasporti sulla mappa.
	 * 
	 * @param gg
	 *            è un Graphics2D ed è la parte del pannello su cui si disegna.
	 */
	public void disegna(Graphics2D gg) {
		gg.drawImage(vite, vita.getX(), vita.getY(), null);
	}
}
