package pannelli;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gestione.Mappa;

/**
 * Questa classe serve per visualizzare i parametri della partita in corso.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class PannelloInfo extends JPanel {
	private final int OFFSET_PRIMO_CUORE = 1030;
	private final int DISTANZA_CUORI = 60;
	private final int OFFSET_PRIMO_CARRO = 1030;
	private final int DISTANZA_CARRI = 60;
	private BufferedImage sfondoInfo = null;
	private BufferedImage cuore = null;
	private BufferedImage carroNemico = null;
	private BufferedImage munizioni = null;
	private BufferedImage score = null;
	Font f = new Font("font", Font.ITALIC, 40);
	private Mappa mappa;

	/**
	 * Questo metodo carica le immagini utilizzate nel pannello.
	 * 
	 * @param m
	 *            è una Mappa che contiene gli elementi grafici del gioco e i
	 *            vari listener che il gioco supporta.
	 */
	public PannelloInfo(Mappa m) {
		this.mappa = m;
		try {
			sfondoInfo = ImageIO.read(getClass().getResource("/img/sfondoInfo.jpg"));
			cuore = ImageIO.read(getClass().getResource("/img/cuoreok.png"));
			carroNemico = ImageIO.read(getClass().getResource("/img/TankNN.png"));
			munizioni = ImageIO.read(getClass().getResource("/img/Munizioni2.png"));
			score = ImageIO.read(getClass().getResource("/img/score.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Questo metodo disegna il pannello.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		disegna(g2d);

	}

	/**
	 * Questo metodo calcola le posizioni in cui disegnare i parametri del gioco
	 * e li disegna.
	 * 
	 * @param gg
	 *            è un Graphics2D che rappresenta la parte del pannello su cui
	 *            si disegna
	 */
	public void disegna(Graphics2D gg) {
		gg.setFont(f);
		gg.drawImage(sfondoInfo, 0, 0, 1366, 768, null);
		gg.drawImage(munizioni, 1030, 320, 50, 50, null);
		gg.drawImage(score, 1100, 150, 150, 42, null);
		int contC = 0;
		int l = 50;

		for (int i = 0; i < mappa.getCarro().getSalute(); i++) {
			gg.drawImage(cuore, OFFSET_PRIMO_CUORE + DISTANZA_CUORI * contC, l, 50, 50, null);
			contC = (contC + 1) % 5;
		}

		int contN = 0;
		int k = 400;
		for (int i = 0; i < mappa.getGestioneGioco().getNemiciTotali(); i++) {
			gg.drawImage(carroNemico, OFFSET_PRIMO_CARRO + DISTANZA_CARRI * contN, k, 50, 50, null);
			contN = (contN + 1) % 5;
			if (contN % 5 == 0)
				k += 75;
		}

		gg.drawString("= " + mappa.getCarro().getScorta(), 1080, 360);
		gg.drawString("" + mappa.getGestioneGioco().getScore(), 1050, 250);

	}// disegna
}
