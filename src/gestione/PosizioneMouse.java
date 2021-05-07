package gestione;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Questa classe serve per gestire il cambiodi icona del mouse quando si trova
 * in determinate zone.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class PosizioneMouse extends Thread {
	private Point PMouse;
	private Rectangle rStart, rHighscore, rHelp, rExit;
	private Frame f;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private BufferedImage cursor1;
	private Cursor c1;

	/**
	 * Questo metodo carica l'icona del mouse da sostituire e definisce le
	 * posizioni in cui cambiare l'icona.
	 * @param f è un Frame che contiene il pannello su cui si disegna.
	 * @throws IOException
	 */
	public PosizioneMouse(Frame f) throws IOException {
		this.f = f;
		cursor1 = ImageIO.read(getClass().getResource("/img/sfondoMouse.png"));
		c1 = toolkit.createCustomCursor(cursor1, new Point(0, 0), "default");
		rStart = new Rectangle(570, 366, 160, 63);
		rHighscore = new Rectangle(474, 466, 352, 63);
		rHelp = new Rectangle(568, 560, 171, 63);
		rExit = new Rectangle(572, 660, 162, 63);
	}
	
	/**
	 * Questo metodo serve a far partire il thread che gestisce il cambio dell'icona del mouse.
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		while (true) {
			try {
				sleep(50);
			} catch (InterruptedException e) {
				f.setCursor(Cursor.DEFAULT_CURSOR);
				break;
			}
			PMouse = MouseInfo.getPointerInfo().getLocation();
			Rectangle xy = new Rectangle((int) PMouse.getX(), (int) PMouse.getY(), 1, 1);
			if (xy.intersects(rStart) || xy.intersects(rHighscore) || xy.intersects(rHelp) || xy.intersects(rExit))
				f.setCursor(c1);
			else
				f.setCursor(Cursor.DEFAULT_CURSOR);
		}
	}
}
