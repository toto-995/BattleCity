package oggetti;

import java.awt.Graphics2D;

import oggettiImg.TeletrasportoImg;
/**
 * Questa classe serve per creare l'oggetto Teletrasporto.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Teletrasporto extends GameObj {
	private Funzione funzione;
	private TeletrasportoImg t;
	
	public static enum Funzione {
		SORGENTE, RICEVENTE;
	};
	
	/**
	 * Questo metodo crea l'oggetto teletrasporto.
	 * @param x è un intero che indica l'ascissa.
	 * @param y è un intero che indica l'ordinata.
	 * @param h è un intero che indica l'altezza dell'immagine.
	 * @param w è un intero che indica la larghezza dell'immagine.
	 * @param d è una direzione che indica il verso in cui è rivolto l'immagine.
	 */
	public Teletrasporto(int x, int y, int h, int w, Direzione d, Funzione f) {
		super(x, y, h, w, d);
		funzione=f;
		t=new TeletrasportoImg(this);
	}
	
	/**
	 * Questo metodo richiama un altro metodo che si occuperà del disegno del teletrasporto.
	 * @param gg è un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg){
		t.disegna(gg);
	}

	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
	}

}
