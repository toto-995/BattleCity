package oggetti;

import java.awt.Graphics2D;

import oggettiImg.AquilaImg;

/**
 * Questa classe serve a creare l'oggetto Aquila.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Aquila extends GameObj {

	private boolean vivo = true;
	private AquilaImg a;

	/**
	 * Questo metodo crea l'oggetto Aquila.
	 * 
	 * @param x
	 *            � un intero che indica l'ascissa.
	 * @param y
	 *            � un intero che indica l'ordinata.
	 * @param h
	 *            � un intero che indica l'altezza dell'immagine.
	 * @param w
	 *            � un intero che indica la larghezza dell'immagine.
	 * @param d
	 *            � una direzione che indica il verso in cui � rivolto
	 *            l'immagine.
	 */
	public Aquila(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		a = new AquilaImg(this);
	}

	/**
	 * Questo metodo richiama un altro metodo che si occuper� del disegno
	 * dell'aquila.
	 * 
	 * @param gg
	 *            � un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg) {

		a.disegna(gg);
	}

	public boolean getVivo() {
		return vivo;
	}

	public void setVivo(boolean a) {
		vivo = a;
	}
}
