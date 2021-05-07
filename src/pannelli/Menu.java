package pannelli;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gestione.Mappa;
import gestione.PosizioneMouse;

/**
 * Questa classe serve per creare il menù con i relativi pulsanti.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener {

	private BufferedImage imgSfond; // IMMAGINE SFONDO MENU, SI CARICA NEL
									// COSTRUTTORE DI DEFAULT

	private ImageIcon sfondoBottonePlay = new ImageIcon(getClass().getResource("/img/play.jpg"));
	private ImageIcon sfondoBottoneExit = new ImageIcon(getClass().getResource("/img/exit.jpg"));
	private ImageIcon sfondoBottoneHighscore = new ImageIcon(getClass().getResource("/img/highscore.jpg"));
	private ImageIcon sfondoBottoneHelp = new ImageIcon(getClass().getResource("/img/help.jpg"));

	private JFrame frameHelp, frameHighscore;

	private JButton start = new JButton(sfondoBottonePlay);
	private JButton highscore = new JButton(sfondoBottoneHighscore);
	private JButton help = new JButton(sfondoBottoneHelp);
	private JButton exit = new JButton(sfondoBottoneExit);
	private Mappa mappa;
	private Frame frame;
	public static PosizioneMouse pm;

	private PannelloInfo pannello1;

	/**
	 * Questo metodo posiziona i vari pulsanti sul menù.
	 * 
	 * @param m
	 *            è una Mappa che contiene gli elementi grafici del gioco e i
	 *            vari listener che il gioco supporta.
	 * @param f
	 *            è un Frame che contiene il pannello su cui si disegna.
	 * @throws IOException
	 */
	public Menu(Mappa m, Frame f) throws IOException {

		this.mappa = m;
		this.frame = f;
		imgSfond = ImageIO.read(getClass().getResource("/img/sfondoMenu.jpg"));
		setLayout(null);

		pm = new PosizioneMouse(f);
		pm.start();
		add(start);
		start.addActionListener(this);
		start.setBounds(570, 340, 160, 63);
		start.setBorder(BorderFactory.createEmptyBorder());

		add(highscore);
		highscore.addActionListener(this);
		highscore.setBounds(474, 440, 352, 63);
		highscore.setBorder(BorderFactory.createEmptyBorder());

		add(help);
		help.addActionListener(this);
		help.setBounds(573, 540, 155, 63);
		help.setBorder(BorderFactory.createEmptyBorder());

		add(exit);
		exit.addActionListener(this);
		exit.setBounds(577, 640, 146, 63);
		exit.setBorder(BorderFactory.createEmptyBorder());

	}
	
	/**
	 * Questo metodo disegna il menù.
	 */
	public void paintComponent(Graphics g) {
		setOpaque(false);
		g.drawImage(imgSfond, 0, 0, null);

		super.paintComponent(g);
	}

	/**
	 * Questo metodo gestisce la funzione dei pulsanti.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			this.setVisible(false);
			JPanel sfondo = new JPanel();
			sfondo.setBackground(Color.BLACK);
			frame.add(sfondo);
			pannello1 = new PannelloInfo(mappa);
			pannello1.setLayout(null);
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						mappa.getGestioneGioco().gestore(mappa, frame, pannello1);
						throw new InterruptedException();
					} catch (InterruptedException | IOException e) {
						setVisible(true);
					}
				}
			});
			t.start();
			frame.requestFocus();
			pm.interrupt();
		}
		if (e.getSource() == highscore) {
			// if (!frameHighscore.isFocused()|| frameHighscore!=null)
			//// else{
			try {
				frameHighscore = new JFrame("Classifica");
				frameHighscore.setBounds(350, 50, 600, 670);
				frameHighscore.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameHighscore.setResizable(false);
				JPanel classifica = new Classifica();
				frameHighscore.add(classifica);
				frameHighscore.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// }

		}

		if (e.getSource() == help) {
			try {
				frameHelp = new JFrame("Help");
				frameHelp.setBounds(350, 50, 600, 670);
				frameHelp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameHelp.setResizable(false);
				JPanel pannelloHelp = new PannelloHelp();
				frameHelp.add(pannelloHelp);
				frameHelp.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == exit) {
			System.exit(0);
		}

	}

}
