package pannelli;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questa classe serve creare il pannello del salvataggio.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class PannelloSalvataggio extends JPanel implements ActionListener {
	private String nomeUtente;
	private BufferedImage sfondo;
	private JTextField text = new JTextField();
	private ImageIcon sfondoBottoneOK = new ImageIcon(getClass().getResource("/img/btnOK.png"));
	private JButton bOK = new JButton(sfondoBottoneOK);
	private Semaphore acquisito = new Semaphore(0);

	/**
	 * Questo metodo carica l'immagine del salvataggio.
	 * 
	 * @throws IOException
	 */
	public PannelloSalvataggio() throws IOException {
		this.setLayout(null);
		sfondo = ImageIO.read(getClass().getResource("/img/imgInserisciNome.png"));
		text.setBounds(440, 310, 480, 50);
		bOK.setBounds(970, 290, 120, 70);
		bOK.addActionListener(this);
		add(bOK);
		add(text);
		setVisible(true);
	}

	/**
	 * Questo metodo serve per gestire la conferma dell'inserimento del nome.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bOK) {
			nomeUtente = text.getText();
			if (nomeUtente.equals(""))
				nomeUtente = "Senzanome";
			if (nomeUtente.contains(":"))
				nomeUtente = nomeUtente.replace(":", " ");
			acquisito.release();
			bOK.removeActionListener(this);
			remove(bOK);
			remove(text);

		}
	}

	public Semaphore getAcquisito() {
		return acquisito;
	}

	/**
	 * Questo metodo serve per disegnare il pannello.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(sfondo, 0, 0, 1366, 768, null);
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
}
