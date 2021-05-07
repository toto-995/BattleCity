package oggetti;

import java.awt.Graphics2D;
import oggettiImg.*;
/**
 * Questa classe serve per creare l'oggetto Carro.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Carro extends CarroAstratto {
	private int scorta;
	private CarroImg carroImg;

	/**
	 * Questo metodo crea l'oggetto carro.
	 * @param x � un intero che indica l'ascissa.
	 * @param y � un intero che indica l'ordinata.
	 * @param h � un intero che indica l'altezza dell'immagine.
	 * @param w � un intero che indica la larghezza dell'immagine.
	 * @param d � una direzione che indica il verso in cui � rivolto l'immagine.
	 */
	public Carro(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		carroImg = new CarroImg(this);
	}
	
	/**
	 * Questo metodo richiama un altro metodo che si occuper� del disegno del carro.
	 * @param gg � un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg){
		carroImg.disegna(gg);
	}

	public int getScorta() {
		return scorta;
	}

	public void setScorta(int scorta) {
		this.scorta = scorta;
	}

}
