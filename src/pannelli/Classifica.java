package pannelli;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gestione.Salvataggio;

/**
 * Questa classe serve a visualizzare la classifica.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Classifica extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = Salvataggio.getDataFolder() + "classifica.txt";
	private BufferedReader br;
	private StringBuilder sb;
	private Font f = new Font("font", Font.ITALIC, 40);
	private BufferedImage sfondo = null;

	public Classifica() throws IOException {

		sfondo = ImageIO.read(getClass().getResource("/img/sfondoClassifica.png"));
		classifica();
		this.repaint();
	}

	/**
	 * Questo metodo serve a leggere la classifica e a stamparla a video.
	 * 
	 * @throws IOException
	 */
	public void classifica() throws IOException {
		sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (IOException e) {
			sb.append("Classifica vuota!");
			return;
		}
		String x = br.readLine();
		while (x != null) {
			x = x.replace(":", "   ");
			sb.append(x);
			sb.append(":");
			x = br.readLine();
		}
		br.close();
	}

	/**
	 * Questo metodo stampa la classifica.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		disegna(g2d);
	}

	/**
	 * Questo metodo stampa i primi dieci classificati.
	 * 
	 * @param gg
	 *            è un Graphics2D che rappresenta la parte del pannello su cui
	 *            si disegna
	 */
	public void disegna(Graphics2D gg) {
		gg.setFont(f);
		gg.drawImage(sfondo, 0, 0, 600, 670, null);
		StringTokenizer stk = new StringTokenizer(sb.toString(), ":");
		int cont = 0;
		while (stk.hasMoreTokens()) {
			if (cont == 10)
				break;

			String s = (cont + 1) + ") " + stk.nextToken();
			if (s.equals("1) Classifica vuota!"))
				s = "Classifica vuota!";
			gg.drawString(s, 30, cont * 70 + 40);
			cont++;
		}
	}

}