package oggetti;

import java.awt.Graphics2D;
import oggettiImg.ViteImg;
/**
 * Questa classe serve creare l'oggetto Vite.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Vite extends GameObj {
	private ViteImg viteImg = null;
	/**
	 * Questo metodo crea l'oggetto Vite.
	 * @param x è un intero che indica l'ascissa.
	 * @param y è un intero che indica l'ordinata.
	 * @param h è un intero che indica l'altezza dell'immagine.
	 * @param w è un intero che indica la larghezza dell'immagine.
	 * @param d è una direzione che indica il verso in cui è rivolto l'immagine.
	 */
	public Vite(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		viteImg=new ViteImg(this);
		
	}
	/**
	 * Questo metodo richiama un altro metodo che si occuperà del disegno dell'icona delle vite.
	 * @param gg è un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg) {
		viteImg.disegna(gg);
	}
}
