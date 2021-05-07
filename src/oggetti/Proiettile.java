package oggetti;

import java.awt.Graphics2D;

import oggettiImg.ProiettileImg;
/**
 * Questa classe serve per creare l'oggetto Proiettile.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Proiettile extends GameObj {
	private GameObj o;						//necessario per sapere quale carro spara, e quindi se distruggere i nemici o meno
	private ProiettileImg proiettileImg;
	
	/**
	 * Questo metodo crea l'oggetto proiettile.
	 * @param x è un intero che indica l'ascissa.
	 * @param y è un intero che indica l'ordinata.
	 * @param h è un intero che indica l'altezza dell'immagine.
	 * @param w è un intero che indica la larghezza dell'immagine.
	 * @param d è una direzione che indica il verso in cui è rivolto l'immagine.
	 */
	public Proiettile(int x, int y, int h, int w, Direzione d, GameObj o) {
		super(x, y, h, w, d);
		this.o= o;		
		switch (this.getD()) {
		case NORD:
			this.setX(x + 17);	//17 dato dalla metà del carro (50/2) - la metà del proiettile (16/2)
			break;
		case SUD:
			this.setX(x + 17);
			this.setY(y + 40);
			break;
		case EST:
			this.setX(x + 40);
			this.setY(y + 17);
			break;
		case OVEST:
			this.setY(y + 17);
			break;
		}
		proiettileImg = new ProiettileImg(this);

	}

	public GameObj getO() {
		return o;
	}

	public void setO(GameObj o) {
		this.o = o;
	}

	/**
	 * Questo metodo richiama un altro metodo che si occuperà del disegno del proiettile.
	 * @param gg è un Graphics2D su cui si va a disegnare.
	 */
	public void disegna(Graphics2D gg){
		proiettileImg.disegna(gg);
	}
	/**
	 * Questo metodo serve per far spostare il proiettile.
	 */
	public void muovi (){
		switch (this.getD()) {
		case NORD:
			this.setY(this.getY() - 5);
			break;
		case SUD:
			this.setY(this.getY() + 5);
			break;
		case EST:
			this.setX(this.getX() + 5);
			break;
		case OVEST:
			this.setX(this.getX() - 5);
			break;
		}
	}//muovi
	

	/**
	 * Questo metodo serve a rimuovere i muri una volta che vanno in contatto
	 * con i proiettili.
	 * 
	 * @param d
	 *            è la Direzione che indica il verso in cui si sta procedendo.
	 * @param world
	 *            è una matrice di interi che indica la posizione dei muri.
	 */
	public void removeWall(int[][] world) {
		switch (this.getD()) {
		case NORD:
			if (world[(this.getX()) / 31][(this.getY() - 2) / 31] == 1)
				world[(this.getX()) / 31][(this.getY() - 2) / 31] = 2;
			else if (world[(this.getX()) / 31][(this.getY() - 2) / 31] == 2)
				world[(this.getX()) / 31][(this.getY() - 2) / 31] = 0;

			else if (world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] == 1)
				world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] = 2;
			else if (world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] == 2)
				world[(this.getX() + this.getW()) / 31][(this.getY() - 2) / 31] = 0;
			break;
		case SUD:
			if (world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] == 1)
				world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] = 2;
			else if (world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] == 2)
				world[(this.getX()) / 31][(this.getY() + this.getH() + 2) / 31] = 0;

			else if (world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] == 1)
				world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] = 2;
			else if (world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] == 2)
				world[(this.getX() + this.getW()) / 31][(this.getY() + this.getH() + 2) / 31] = 0;
			break;
		case EST:
			if (world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] == 1)
				world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] = 2;
			else if (world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] == 2)
				world[(this.getX() + this.getW() + 2) / 31][(this.getY()) / 31] = 0;

			else if (world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] == 1)
				world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] = 2;
			else if (world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] == 2)
				world[(this.getX() + this.getW() + 2) / 31][(this.getY() + this.getH()) / 31] = 0;
			break;
		case OVEST:
			if (world[(this.getX() - 2) / 31][(this.getY()) / 31] == 1)
				world[(this.getX() - 2) / 31][(this.getY()) / 31] = 2;
			else if (world[(this.getX() - 2) / 31][(this.getY()) / 31] == 2)
				world[(this.getX() - 2) / 31][(this.getY()) / 31] = 0;

			else if (world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] == 1)
				world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] = 2;
			else if (world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] == 2)
				world[(this.getX() - 2) / 31][(this.getY() + this.getH()) / 31] = 0;
			break;
		}
	}
}
