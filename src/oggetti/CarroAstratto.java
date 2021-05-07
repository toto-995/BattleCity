package oggetti;

/**
 * Questa classe serve per definire l'oggetto CarroAstratto e i metodi in comune
 * tra carro dell'utente e carro nemico.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public abstract class CarroAstratto extends GameObj {
	private int salute = 3;
	private int xIniziale, yIniziale;

	public int getSalute() {
		return salute;
	}

	public void setSalute(int salute) {
		this.salute = salute;
	}

	/**
	 * Questo metodo crea l'oggetto carro astratto.
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
	public CarroAstratto(int x, int y, int h, int w, Direzione d) {
		super(x, y, h, w, d);
		setxIniziale(x);
		setyIniziale(y);
	}

	/**
	 * Questo è un metodo che si occupa di controllare la direzione in cui va
	 * mosso il carro.
	 * 
	 * @param d
	 *            è una direzione che indica il verso in cui si deve muovere il
	 *            carro.
	 */

	public void muovi(Direzione d) {
		if (d != null) {
			switch (d) {
			case NORD: {
				this.muoviN();
				break;
			}
			case SUD: {
				this.muoviS();
				break;
			}
			case EST: {
				this.muoviDx();
				break;
			}
			case OVEST: {
				this.muoviSx();
				break;
			}
			}
		}
	}// muovi

	public void muoviDx() {
		this.setD(Direzione.EST);
		this.setX(this.getX() + 1);
	}

	public void muoviSx() {
		this.setD(Direzione.OVEST);
		this.setX(this.getX() - 1);
	}

	public void muoviN() {
		this.setD(Direzione.NORD);
		this.setY(this.getY() - 1);
	}

	public void muoviS() {
		this.setD(Direzione.SUD);
		this.setY(this.getY() + 1);
	}

	public int getxIniziale() {
		return xIniziale;
	}

	public void setxIniziale(int xIniziale) {
		this.xIniziale = xIniziale;
	}

	public int getyIniziale() {
		return yIniziale;
	}

	public void setyIniziale(int yIniziale) {
		this.yIniziale = yIniziale;
	}

}
