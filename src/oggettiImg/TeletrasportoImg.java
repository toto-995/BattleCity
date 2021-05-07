package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetti.Teletrasporto;
import oggetti.Teletrasporto.Funzione;
/**
 * Questa classe serve per disegnare i teletrasporti sulla mappa.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class TeletrasportoImg {

	private Teletrasporto teletrasporto;
	private BufferedImage teletrIn;
	private BufferedImage teletrOut;
	/**
	 * Questo metodo serve a caricare le immagini dei teletrasporti
	 * @param t è un teletrasporto.
	 */
	public TeletrasportoImg(Teletrasporto t) {
		this.teletrasporto = t;
		try {
			teletrIn = ImageIO.read(getClass().getResource("/img/teletrasportoIn.png"));
			teletrOut = ImageIO.read(getClass().getResource("/img/teletrasportoOut.png"));
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
		if (this.teletrasporto.getFunzione() == Funzione.SORGENTE) {
			gg.drawImage(teletrIn, teletrasporto.getX(), teletrasporto.getY(), teletrasporto.getW(),
					teletrasporto.getH(), null);
		}
		else{
			gg.drawImage(teletrOut, teletrasporto.getX(), teletrasporto.getY(), teletrasporto.getW(),
					teletrasporto.getH(), null);
		}
	}
}
