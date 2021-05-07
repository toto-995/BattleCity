package pannelli;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Questa classe serve per creare il pannello delle istruzioni.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class PannelloHelp extends JPanel {
	private BufferedImage help;

	/**
	 * Questo metodo carica l'immagine del pannello.
	 * 
	 * @throws IOException
	 */
	public PannelloHelp() throws IOException {
		help = ImageIO.read(getClass().getResource("/img/sfondoHelp.png"));
	}

	/**
	 * Questo metodo disegna il pannello.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(help, 0, 0, 600, 670, null);
	}

}
