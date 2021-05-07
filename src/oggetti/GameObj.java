package oggetti;

import java.awt.Rectangle;
/**
 * Questa classe serve per definire l'oggetto generico.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class GameObj {
	private int x, y, h, w;
	private Direzione d;

	public static enum Direzione {
		NORD, SUD, EST, OVEST
	};

	/**
	 * Questo metodo crea l'oggetto GameObj che è la classe padre di tutti gli
	 * oggetti del gioco.
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
	public GameObj(int x, int y, int h, int w, Direzione d) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.d = d;
	}

	public Direzione getD() {
		return d;
	}

	public void setD(Direzione d) {
		this.d = d;
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

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	/**
	 * Questo metodo rileva la collisione tra due oggetti.
	 * 
	 * @param o
	 *            è l'oggetto con cui controllare se si collide.
	 * @param d
	 *            è la Direzione che indica il verso in cui si vuole rilevare la
	 *            collisione.
	 * @return un boolean che dice se è stata rilevata collisione o meno.
	 */

	public boolean collide(GameObj o, Direzione d) {
		Rectangle r1 = null;
		Rectangle r2 = new Rectangle(o.getX(), o.getY(), o.getW(), o.getH());
		if (d != null) {
			switch (d) {
			case NORD:
				r1 = new Rectangle(this.getX() + 1, this.getY(), this.getW() - 1, 1);
				break;
			case SUD:
				r1 = new Rectangle(this.getX() + 1, this.getY() + this.getH() - 1, this.getW() - 1, 1);
				break;
			case EST:
				r1 = new Rectangle(this.getX() + this.getW() - 1, this.getY() + 1, 1, this.getH() - 1);
				break;
			case OVEST:
				r1 = new Rectangle(this.getX(), this.getY() + 1, 1, this.getH() - 1);
				break;
			}
			return r1.intersects(r2);
		}
		return false;
	}

	/**
	 * Questo metodo rileva la collisione con i muri.
	 * 
	 * @param d
	 *            è la Direzione che indica il verso in cui si sta procedendo.
	 * @param world
	 *            è una matrice di interi che indica la posizione dei muri.
	 * @return un booleanche dice se è stata rilevata collisione o meno.
	 */
	public boolean collide(Direzione d, int[][] world) {
		if (d != null) {
			switch (d) {
			case NORD:
				if (((world[(this.getX()) / 31][(this.getY() - 2) / 31] == 0)
						|| (world[(this.getX()) / 31][(this.getY() - 2) / 31] == 4))
						&& ((world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] == 0)
								|| (world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] == 4))
						&& ((world[(this.getX() + this.getW() / 2) / 31][(this.getY() - 2) / 31] == 0)
								|| (world[(this.getX() + this.getW() / 2) / 31][(this.getY() - 2) / 31] == 4)))
					return false;
				break;

			case SUD:
				if (((world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] == 0)
						|| (world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] == 4))
						&& ((world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] == 0)
								|| (world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] == 4))
						&& ((world[(this.getX() + this.getW() / 2) / 31][(this.getY() + this.getH() + 2) / 31] == 0)
								|| (world[(this.getX() + this.getW() / 2) / 31][(this.getY() + this.getH() + 2)
										/ 31] == 4)))

					return false;
				break;

			case EST:
				if (((world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] == 0)
						|| (world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] == 4))
						&& ((world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] == 0)
								|| (world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] == 4))
						&& ((world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH() / 2) / 31] == 0)
								|| (world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH() / 2)
										/ 31] == 4)))

					return false;
				break;

			case OVEST:
				if (((world[(this.getX() - 2) / 31][(this.getY()) / 31] == 0)
						|| (world[(this.getX() - 2) / 31][(this.getY()) / 31] == 4))
						&& ((world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] == 0)
								|| (world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] == 4))
						&& ((world[(this.getX() - 2) / 31][(this.getY() + this.getH() / 2) / 31] == 0)
								|| (world[(this.getX() - 2) / 31][(this.getY() + this.getH() / 2) / 31] == 4)))
					return false;
				break;
			}
			return true;
		}
		return false;
	}

	/**
	 * Questo metodo indica se un oggetto è contenuto in un altro oggetto.
	 * 
	 * @param o
	 *            è un GameObj ed è l'oggetto in cui ci si chiede se l'oggetto
	 *            sul quale si invoca il metodo è contenuto.
	 * @return un boolean che dice se è contenuto o meno.
	 */
	public boolean eContenuto(GameObj o) {
		Rectangle r1 = new Rectangle(this.getX(), this.getY(), this.getW(), this.getH());
		Rectangle r2 = new Rectangle(o.getX(), o.getY(), o.getW(), o.getH());
		return r2.contains(r1);
	}
}
