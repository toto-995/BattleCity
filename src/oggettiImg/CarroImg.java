package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import oggetti.Carro;

/**
 * Questa classe serve per disegnare l'immagine del carro nella mappa.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class CarroImg {
	private BufferedImage nord = null, est = null, sud = null, ovest = null;
	private Carro carro;
	
	/**
	 * Questo metodo carica le immagini del carro.
	 * @param carro è l'oggetto carro.
	 */
	public CarroImg(Carro carro) {
		this.carro = carro;
		try {
			nord = ImageIO.read(getClass().getResource("/img/TankN.png"));
			est = ImageIO.read(getClass().getResource("/img/TankE.png"));
			sud = ImageIO.read(getClass().getResource("/img/TankS.png"));
			ovest = ImageIO.read(getClass().getResource("/img/TankO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo disegna il carro dell'utente sulla mappa.
	 * 
	 * @param gg
	 *            è un Graphics2D che rappresenta la parte del pannello su cui
	 *            si disegna
	 */
	public void disegna(Graphics2D gg) {

		switch (carro.getD()) {
		case NORD:
			gg.drawImage(nord, carro.getX(), carro.getY(), 50, 50, null);
			break;
		case SUD:
			gg.drawImage(sud, carro.getX(), carro.getY(), 50, 50, null);
			break;
		case EST:
			gg.drawImage(est, carro.getX(), carro.getY(), 50, 50, null);
			break;
		case OVEST:
			gg.drawImage(ovest, carro.getX(), carro.getY(), 50, 50, null);
			break;
		}
	}
}
