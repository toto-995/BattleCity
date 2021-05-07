package oggettiImg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetti.Nemico;
/**
 * Questa classe serve per disegnare i carri nemici sulla mappa.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class NemicoImg {
	private BufferedImage nord = null, est = null, sud = null, ovest = null,
	nordI = null, estI = null, sudI = null, ovestI = null;
	private Nemico n;

	/**
	 * Questo metodo serve a caricare le immagini dei carri nemici.
	 * @param n è un nemico.
	 */
	public NemicoImg(Nemico n) {
		this.n = n;
		try {
			nord = ImageIO.read(getClass().getResource("/img/TankNN.png"));
			est = ImageIO.read(getClass().getResource("/img/TankNE.png"));
			sud = ImageIO.read(getClass().getResource("/img/TankNS.png"));
			ovest = ImageIO.read(getClass().getResource("/img/TankNO.png"));
			
			nordI = ImageIO.read(getClass().getResource("/img/TankINN.png"));
			estI = ImageIO.read(getClass().getResource("/img/TankINE.png"));
			sudI = ImageIO.read(getClass().getResource("/img/TankINS.png"));
			ovestI = ImageIO.read(getClass().getResource("/img/TankINO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Questo metodo disegna i carri nemici sulla mappa.
	 * @param gg è un Graphics2D ed è la parte del pannello su cui si disegna.
	 */
	public void disegna(Graphics2D gg) {
		if (n.getHealth() <= 1 && n.getIndebolito()) {
			switch (n.getD()) {
			case NORD:
				gg.drawImage(nordI, n.getX(), n.getY(), null);
				break;
			case SUD:
				gg.drawImage(sudI, n.getX(), n.getY(), null);
				break;
			case EST:
				gg.drawImage(estI, n.getX(), n.getY(), null);
				break;
			case OVEST:
				gg.drawImage(ovestI, n.getX(), n.getY(), null);
				break;
			}

		} else {
			switch (n.getD()) {
			case NORD:
				gg.drawImage(nord, n.getX(), n.getY(), null);
				break;
			case SUD:
				gg.drawImage(sud, n.getX(), n.getY(), null);
				break;
			case EST:
				gg.drawImage(est, n.getX(), n.getY(), null);
				break;
			case OVEST:
				gg.drawImage(ovest, n.getX(), n.getY(), null);
				break;
			}
		}
	}// disegna
}
