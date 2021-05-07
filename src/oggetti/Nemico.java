package oggetti;

import java.awt.Graphics2D;
import java.util.Random;
import oggettiImg.NemicoImg;
/**
 * Questa classe serve per creare l'oggetto nemico.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Nemico extends CarroAstratto {
	private long fireLastTime = System.currentTimeMillis()
			+ 4000;/* non sparano appena parte il livello */
	private long timeLastMove = System.currentTimeMillis() + 4000;
	private Direzione lastD = null;
	private int health;
	private long tempoMorto;
	private final int vitaIniziale;
	private NemicoImg nemicoImg;
	private boolean indebolito = false;

	/**
	 * Questo metodo crea l'oggetto nemico.
	 * 
	 * @param x
	 *            è un intero che indica l'ascissa.
	 * @param y
	 *            è un intero che indica l'ordinata.
	 * @param h
	 *            è un intero che indica l'altezza dell'immagine.
	 * @param w
	 *            è un intero che indica la larghezza dell'immagine.
	 * @param d
	 *            è una direzione che indica il verso in cui è rivolto
	 *            l'immagine.
	 */
	public Nemico(int x, int y, int h, int w, Direzione d, int health) {
		super(x, y, h, w, d);
		this.health = health;
		this.vitaIniziale = health;
		nemicoImg = new NemicoImg(this);
		this.setIndebolito(false);
	}

	/**
	 * Questo metodo richiama un altro metodo che si occuperà del disegno del
	 * nemico.
	 * 
	 * @param gg
	 *            è un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg) {
		nemicoImg.disegna(gg);
	}

	/**
	 * Questo metodo inverte la direzione di movimento del carro nemico.
	 * 
	 * @param d
	 *            è la direzione da invertire.
	 * @return la direzione invertita.
	 */
	public Direzione direzioneOpposta(Direzione d) {
		if (d != null) {
			switch (d) {
			case NORD:
				return Direzione.SUD;
			case SUD:
				return Direzione.NORD;
			case EST:
				return Direzione.OVEST;
			case OVEST:
				return Direzione.EST;
			}
		}
		return d;
	}// muoviOpposto

	/**
	 * Questo metodo genera una direzione random per il carro nemico.
	 * 
	 * @param d
	 *            è la direzione attuale che non va scelta.
	 * @param matrix
	 *            è la matrice di interi che indica la posizione dei muri sulla
	 *            mappa.
	 */
	public void giraRandom(Direzione d, int[][] matrix) {
		Random r = new Random();
		boolean flag = true;

		while (flag) {
			switch (r.nextInt(4)) {
			case 0: {
				if (d != Direzione.NORD && !this.collide(Direzione.NORD, matrix)) {
					this.setD(Direzione.NORD);
					flag = false;
				}
				break;
			}
			case 1: {
				if (d != Direzione.SUD && !this.collide(Direzione.SUD, matrix)) {
					this.setD(Direzione.SUD);
					flag = false;
				}
				break;
			}
			case 2: {
				if (d != Direzione.EST && !this.collide(Direzione.EST, matrix)) {
					this.setD(Direzione.EST);
					flag = false;
				}
				break;
			}
			case 3: {
				if (d != Direzione.OVEST && !this.collide(Direzione.OVEST, matrix)) {
					this.setD(Direzione.OVEST);
					flag = false;
				}
				break;
			}
			}// switch
		} // while
	}// muoviRandom

	public long getTimeLastMove() {
		return timeLastMove;
	}

	public void setTimeLastMove(long lastMove) {
		this.timeLastMove = lastMove;
	}
/**
 * Questo metodo serve a far eseguire movimenti random ai nemici.
 * @param matrix è una matrice di interi che indica la posizione dei muri.
 */
	public void muoviNemico(int[][] matrix) {

		if ((System.currentTimeMillis() - this.timeLastMove) > 5000) {
			this.giraRandom(this.getD(), matrix);
			this.muovi(this.getD());
			this.timeLastMove = System.currentTimeMillis();
		} else
			this.muovi(this.getD());
	}

	public long getFireLastTime() {
		return fireLastTime;
	}

	public void setFireLastTime(long fireLastTime) {
		this.fireLastTime = fireLastTime;
	}

	public Direzione getLastD() {
		return lastD;
	}

	public void setLastD(Direzione lastD) {
		this.lastD = lastD;
	}

	public void setHealth(int h) {
		health = h;
	}

	public int getHealth() {
		return health;
	}

	public void setTempoMorto(long t) {
		this.tempoMorto = t;
	}

	public long getTempoMorto() {
		return tempoMorto;
	}

	public int getVitaIniziale() {
		return vitaIniziale;
	}

	public boolean getIndebolito() {
		return indebolito;
	}

	public void setIndebolito(boolean indebolito) {
		this.indebolito = indebolito;
	}
}