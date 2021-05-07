package oggetti;

import java.awt.Graphics2D;

import oggettiImg.MunizioniImg;
/**
 * Questa classe serve per creare l'oggetto Munizioni.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Munizioni extends GameObj {
	
	private MunizioniImg munizioniImg;
	
	/**
	 * Questo metodo crea l'oggetto munizioni.
	 * @param x � un intero che indica l'ascissa.
	 * @param y � un intero che indica l'ordinata.
	 * @param h � un intero che indica l'altezza dell'immagine.
	 * @param w � un intero che indica la larghezza dell'immagine.
	 * @param d � una direzione che indica il verso in cui � rivolto l'immagine.
	 */
	public Munizioni(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		munizioniImg=new MunizioniImg(this);
	}

	/**
	 * Questo metodo richiama un altro metodo che si occuper� del disegno della munizione.
	 * @param gg � un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg) {
		munizioniImg.disegna(gg);
	}
}
