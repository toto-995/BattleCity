package oggetti;

import java.awt.Graphics2D;
import oggettiImg.MuroAquilaImg;

/**
 * Questa classe serve per creare l'icona che permette di avere i muri attorno
 * all'aquila indistruttibili.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class MuroAquila extends GameObj {
	private MuroAquilaImg muroAquilaImg;
	/**
	 * Questo metodo crea l'oggetto MuroAquila.
	 * @param x � un intero che indica l'ascissa.
	 * @param y � un intero che indica l'ordinata.
	 * @param h � un intero che indica l'altezza dell'immagine.
	 * @param w � un intero che indica la larghezza dell'immagine.
	 * @param d � una direzione che indica il verso in cui � rivolto l'immagine.
	 */
	public MuroAquila(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		muroAquilaImg = new MuroAquilaImg(this);
	}
	
	/**
	 * Questo metodo richiama un altro metodo che si occuper� del disegno del muro attorno all'aquila.
	 * @param gg � un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg) {
		muroAquilaImg.disegna(gg);
	}
}