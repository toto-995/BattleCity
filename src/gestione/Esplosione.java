package gestione;

import java.awt.Graphics2D;

import oggettiImg.EsplosioneImg;

/**
 * Questa classe serve per creare le animazioni delle esplosioni dei carri armati.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */

public class Esplosione {
	private long tempoInizioExp, tempoUltimaTransiz;
	private int x,y,transiz;
	private EsplosioneImg esp;
	
	/**
	 * Crea un'esplosione, fornite le coordinate.
	 * @param x è un intero che rappresenta l'ascissa.
	 * @param y è un intero che rappresenta l'ordinata.
	 */
	public Esplosione(int x, int y){
		this.setX(x);
		this.setY(y);
		setTempoUltimaTransiz(System.currentTimeMillis());
		setTempoInizioExp(System.currentTimeMillis());
		setTransiz(0);
		esp = new EsplosioneImg(this);
	}
	
	/**
	 * Questo metodo richiama un altro metodo che si occuperà del disegno dell'esplosione.
	 * @param gg è un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg){
		esp.disegna(gg);
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getTempoUltimaTransiz() {
		return tempoUltimaTransiz;
	}

	public void setTempoUltimaTransiz(long tempoUltimaTransiz) {
		this.tempoUltimaTransiz = tempoUltimaTransiz;
	}

	public long getTempoInizioExp() {
		return tempoInizioExp;
	}

	public void setTempoInizioExp(long tempoInizioExp) {
		this.tempoInizioExp = tempoInizioExp;
	}

	public int getTransiz() {
		return transiz;
	}

	public void setTransiz(int transiz) {
		this.transiz = transiz;
	}

}
