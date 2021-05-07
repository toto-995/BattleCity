package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetti.Munizioni;
/**
 * Questa classe serve per disegnare l'immagine delle munizioni nella mappa.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class MunizioniImg {
	private BufferedImage munizioni = null;
	private Munizioni munizione;
	
	/**
	 * Questo metetodo carica l'immagine delle munizioni.
	 * @param m è l'oggetto munizioni.
	 */
	public MunizioniImg(Munizioni m){
		this.munizione=m;
		try {
			munizioni = ImageIO.read(getClass().getResource("/img/Munizioni.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Questo metodo disegna le munizioni sulla mappa.
	 * @param gg è un Graphics2D ed è la parte del pannello su cui si disegna.
	 */
	public void disegna(Graphics2D gg) {
		gg.drawImage(munizioni, munizione.getX(), munizione.getY(), null);
		
	}

}
