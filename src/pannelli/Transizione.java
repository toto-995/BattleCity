package pannelli;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Questa classe serve per creare i pannelli introduttivi di ogni livello.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class Transizione extends JPanel {
	private BufferedImage imgTran1, imgTran2, imgTran3, imgGameOver, imgHaiVinto;
	private int i;

	/**
	 * Questo metodo carica le immagini dei pannelli.
	 * 
	 * @param i
	 *            è un intero che indica quale immagine disegnare.
	 * @throws IOException
	 */
	public Transizione(int i) throws IOException {

		imgTran1 = ImageIO.read(getClass().getResource("/img/livelloUno.png"));
		imgTran2 = ImageIO.read(getClass().getResource("/img/livelloDue.png"));
		imgTran3 = ImageIO.read(getClass().getResource("/img/livelloTre.png"));
		imgGameOver = ImageIO.read(getClass().getResource("/img/imgGameOver.png"));
		imgHaiVinto = ImageIO.read(getClass().getResource("/img/imgHaiVinto.png"));
		this.i = i;
	}

	/**
	 * Questo metodo disegna l'immagine relativa al livello in questione.
	 */
	protected void paintComponent(Graphics g) {
		setOpaque(false);
		switch (i) {
		case 1:
			g.drawImage(imgTran1, 0, 0, null);
			break;
		case 2:
			g.drawImage(imgTran2, 0, 0, null);
			break;
		case 3:
			g.drawImage(imgTran3, 0, 0, null);
			break;
		case 4:
			g.drawImage(imgHaiVinto, 0, 0, null);
			break;
		case 5:
			g.drawImage(imgGameOver, 0, 0, null);
			break;
		}

		super.paintComponent(g);
	}

}
