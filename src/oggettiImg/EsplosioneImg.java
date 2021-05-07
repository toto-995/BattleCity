package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gestione.Esplosione;

/**
 * Questa classe serve per disegnare le esplosioni.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class EsplosioneImg {
	private Esplosione esplosione;
	private BufferedImage[] exp = new BufferedImage[7];

	/**
	 * Questo metodo serve per popolare l'array che contiente i fotogrammi
	 * dell'esplosione.
	 * 
	 * @param esp
	 *            è l'oggetto esplosione.
	 */
	public EsplosioneImg(Esplosione esp) {
		this.esplosione = esp;
		try {
			exp[0] = ImageIO.read(getClass().getResource("/img/exp0.png"));
			exp[1] = ImageIO.read(getClass().getResource("/img/exp1.png"));
			exp[2] = ImageIO.read(getClass().getResource("/img/exp2.png"));
			exp[3] = ImageIO.read(getClass().getResource("/img/exp3.png"));
			exp[4] = ImageIO.read(getClass().getResource("/img/exp4.png"));
			exp[5] = ImageIO.read(getClass().getResource("/img/exp5.png"));
			exp[6] = ImageIO.read(getClass().getResource("/img/exp6.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo disegna l'esplosione sulla mappa.
	 * 
	 * @param gg
	 *            è un Graphics2D e rappresenta la parte del pannello su cui si
	 *            disegna.
	 */
	public void disegna(Graphics2D gg) {
		gg.drawImage(exp[esplosione.getTransiz()], esplosione.getX(), esplosione.getY(), 60, 60, null);
	}
}
