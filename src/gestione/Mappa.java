package gestione;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import oggetti.Aquila;
import oggetti.Carro;
import oggetti.Munizioni;
import oggetti.MuroAquila;
import oggetti.Nemico;
import oggetti.Proiettile;
import oggetti.Teletrasporto;
import oggetti.Vite;
import oggetti.GameObj.Direzione;

/**
 * Questa classe serve per la gestione della grafica del gioco e le interazioni
 * con l'utente tramite i listener.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
@SuppressWarnings("serial")
public class Mappa extends JPanel implements KeyListener {
	private int[][] matrix = new int[32][23];
	private Direzione muoviCarro = null;
	private long timeP;
	private BufferedImage muro = null;
	private BufferedImage muro2 = null;
	private BufferedImage muro3 = null;
	private BufferedImage cespuglio = null;
	private BufferedImage pausa = null;
	private static Direzione lastD = Direzione.NORD;
	private LinkedList<Proiettile> proiettileLL = new LinkedList<>();
	private LinkedList<Nemico> nemicoLL = new LinkedList<>();
	private LinkedList<Esplosione> esplosioneLL = new LinkedList<>();
	private Teletrasporto teletrasporto[];
	private Carro carro = new Carro(100, 630, 50, 50, Direzione.NORD);
	private Aquila aquila = new Aquila(471, 625, 50, 50, null);
	private GestioneGioco gestioneGioco;
	private Munizioni munizioni = null;
	private MuroAquila muroAquila = null;
	private Vite vite = null;
	private boolean inPausa = false;
	private URL sparo = Suono.class.getResource("/suoni/sparo.wav");
	private StringBuilder easterEggMunizioni = new StringBuilder(), easterEggAquila = new StringBuilder(),
			easterEggVita = new StringBuilder();

	/**
	 * Questo metodo serve per caricare le immagini statiche del gioco.
	 * 
	 * @param g
	 *            riguarda l'intera logica del gioco.
	 */
	public Mappa(GestioneGioco g) {
		this.gestioneGioco = g;
		try {
			muro = ImageIO.read(getClass().getResource("/img/bricks.png"));
			muro2 = ImageIO.read(getClass().getResource("/img/bricks3.png"));
			muro3 = ImageIO.read(getClass().getResource("/img/indis.png"));
			cespuglio = ImageIO.read(getClass().getResource("/img/cesp3.png"));
			pausa = ImageIO.read(getClass().getResource("/img/pausa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Questo metodo disegna gli oggetti utilizzati nel gioco.
	 * 
	 * @param g
	 *            è un Graphics ed è la componente del pannello su cui si
	 *            disegna.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		if (teletrasporto != null)
			for (Teletrasporto t : teletrasporto)
				t.disegna(gg);
		aquila.disegna(gg);
		carro.disegna(gg);
		for (Nemico n : gestioneGioco.getCopiaNemicoLL())
			n.disegna(gg);
		for (Proiettile p : gestioneGioco.getCopiaProiettileLL())
			p.disegna(gg);
		for (Esplosione e : gestioneGioco.getCopiaEsplosioneLL())
			e.disegna(gg);

		for (int i = 0; i < 32; i++)
			for (int j = 0; j < 23; j++)
				if (matrix[i][j] == 1)
					gg.drawImage(muro, i * 31, j * 31, 31, 31, null);
				else if (matrix[i][j] == 2)
					gg.drawImage(muro2, i * 31, j * 31, 31, 31, null);
				else if (matrix[i][j] == 3)
					gg.drawImage(muro3, i * 31, j * 31, 31, 31, null);
				else if (matrix[i][j] == 4)
					gg.drawImage(cespuglio, i * 31, j * 31, 31, 31, null);
		if (munizioni != null)
			munizioni.disegna(gg);
		if (muroAquila != null)
			muroAquila.disegna(gg);
		if (vite != null)
			vite.disegna(gg);
		if (inPausa)
			gg.drawImage(pausa, 322, 304, 348, 104, null);

	}

	public Vite getVite() {
		return vite;
	}

	public void setVite(Vite vite) {
		this.vite = vite;
	}

	public int getMatrix(int x, int y) {
		return matrix[x][y];
	}

	public void setMatrix(int x, int y, int k) {
		matrix[x][y] = k;
	}

	/**
	 * Questo metodo serve per far interagire l'utente con il gioco tramite la
	 * tastiera e il mouse.
	 * 
	 * @param e
	 *            è un KeyEvent e serve a percepire la pressione dei tasti.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			if (!inPausa) {
				inPausa = true;
				gestioneGioco.setTempoInizioM(System.currentTimeMillis() - gestioneGioco.getTempoInizioM());
				gestioneGioco.setTempoInizioV(System.currentTimeMillis() - gestioneGioco.getTempoInizioV());
				gestioneGioco.setTempoInizioMuro(System.currentTimeMillis() - gestioneGioco.getTempoInizioMuro());
			} else {
				inPausa = false;
				gestioneGioco.setTempoInizioM(System.currentTimeMillis() - gestioneGioco.getTempoInizioM());
				gestioneGioco.setTempoInizioV(System.currentTimeMillis() - gestioneGioco.getTempoInizioV());
				gestioneGioco.setTempoInizioMuro(System.currentTimeMillis() - gestioneGioco.getTempoInizioMuro());
				gestioneGioco.getPausa().release();
			}
			break;

		case KeyEvent.VK_UP:
			if (!inPausa)
				muoviCarro = Direzione.NORD;
			break;
		case KeyEvent.VK_DOWN:
			if (!inPausa)
				muoviCarro = Direzione.SUD;
			break;
		case KeyEvent.VK_LEFT:
			if (!inPausa)
				muoviCarro = Direzione.OVEST;
			break;
		case KeyEvent.VK_RIGHT:
			if (!inPausa)
				muoviCarro = Direzione.EST;
			break;
		case KeyEvent.VK_V:
			if (easterEggVita.length() != 0)
				easterEggVita.setLength(0);
			easterEggVita.append("v");
			break;
		case KeyEvent.VK_T:
			if (easterEggVita.length() != 2)
				easterEggVita.setLength(0);
			else
				easterEggVita.append("t");
			break;
		case KeyEvent.VK_C:
			if (easterEggMunizioni.length() != 0)
				easterEggMunizioni.setLength(0);
			easterEggMunizioni.append("c");
			break;
		case KeyEvent.VK_I:
			if (easterEggMunizioni.length() != 3)
				easterEggMunizioni.setLength(0);
			else {
				easterEggMunizioni.append("i");
				String s = easterEggMunizioni.toString();
				if (s.equals("muni")) {
					carro.setScorta(999);
					easterEggMunizioni.setLength(0);
				}
			}
			if (easterEggAquila.length() != 3)
				easterEggAquila.setLength(0);
			else
				easterEggAquila.append("i");
			if (easterEggVita.length() != 1)
				easterEggVita.setLength(0);
			else
				easterEggVita.append("i");
			break;
		case KeyEvent.VK_A:
			if (easterEggMunizioni.length() != 2)
				easterEggMunizioni.setLength(0);
			easterEggMunizioni.append("a");
			if (easterEggAquila.length() != 0)
				easterEggAquila.setLength(0);
			easterEggAquila.append("a");
			if (easterEggVita.length() != 3)
				easterEggVita.setLength(0);
			else
				easterEggVita.append("a");
			String vita = easterEggVita.toString();
			if (vita.equals("vita")) {
				gestioneGioco.setTruccoVitaInfinita(!gestioneGioco.isTruccoVitaInfinita());
				easterEggVita.setLength(0);
			}
			break;
		case KeyEvent.VK_M:
			if (easterEggMunizioni.length() != 0)
				easterEggMunizioni.setLength(0);
			easterEggMunizioni.append("m");
			break;

		case KeyEvent.VK_Q:
			if (easterEggAquila.length() != 1)
				easterEggAquila.setLength(0);
			else
				easterEggAquila.append("q");
			break;
		case KeyEvent.VK_U:
			if (easterEggAquila.length() != 2)
				easterEggAquila.setLength(0);
			else
				easterEggAquila.append("u");

			if (easterEggMunizioni.length() != 1)
				easterEggMunizioni.setLength(0);
			else
				easterEggMunizioni.append("u");
			break;

		case KeyEvent.VK_N:
			if (easterEggMunizioni.length() != 2)
				easterEggMunizioni.setLength(0);
			else
				easterEggMunizioni.append("n");
			break;

		case KeyEvent.VK_L:
			if (easterEggAquila.length() != 4)
				easterEggAquila.setLength(0);
			else
				easterEggAquila.append("l");
			break;
		case KeyEvent.VK_7:
			if (easterEggAquila.length() != 5)
				easterEggAquila.setLength(0);
			else
				easterEggAquila.append("7");
			String x = easterEggAquila.toString();
			if (x.equals("aquil7")) {
				gestioneGioco.setTruccoAquila(!gestioneGioco.isTruccoAquila());
				easterEggAquila.setLength(0);
			}
			break;
		}// switch
		if (e.getKeyCode() == KeyEvent.VK_SPACE && carro.getScorta() > 0 && !inPausa) {
			if (lastD != carro.getD()) { // controlla se non abbiamo sparato un
											// proiettili in quella direzione
				Proiettile p = new Proiettile(carro.getX(), carro.getY(), 10, 10, carro.getD(), carro);
				proiettileLL.add(p);
				carro.setScorta(carro.getScorta() - 1);
				timeP = System.currentTimeMillis();
				lastD = carro.getD();
				gestioneGioco.setScore(gestioneGioco.getScore() - 10);
				Suono.play(sparo);
			} else if ((System.currentTimeMillis() - timeP) > 500) { // controlla
																		// se è
																		// passato
																		// + di
																		// mezzo
																		// secondo
																		// dall'ultimo
																		// sparo
				Proiettile p = new Proiettile(carro.getX(), carro.getY(), 10, 10, carro.getD(), carro);
				proiettileLL.add(p);
				carro.setScorta(carro.getScorta() - 1);
				timeP = System.currentTimeMillis();
				gestioneGioco.setScore(gestioneGioco.getScore() - 10);
				Suono.play(sparo);
			}

		}

	}

	/**
	 * Questo metodo serve per far interagire l'utente con il gioco tramite la
	 * tastiera e il mouse.
	 * 
	 * @param e
	 *            è un KeyEvent usato per percepire il rilascio dei tasti.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (carro.getD() == Direzione.NORD) {
				muoviCarro = null;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (carro.getD() == Direzione.SUD) {
				muoviCarro = null;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (carro.getD() == Direzione.OVEST) {
				muoviCarro = null;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (carro.getD() == Direzione.EST) {
				muoviCarro = null;
			}
			break;
		}
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public Direzione getMuoviCarro() {
		return muoviCarro;
	}

	public void setMuoviCarro(Direzione muoviCarro) {
		this.muoviCarro = muoviCarro;
	}

	public LinkedList<Proiettile> getProiettileLL() {
		return proiettileLL;
	}

	public void setProiettileLL(LinkedList<Proiettile> proiettile) {
		this.proiettileLL = proiettile;
	}

	public LinkedList<Nemico> getNemicoLL() {
		return nemicoLL;
	}

	public void setNemicoLL(LinkedList<Nemico> nemico) {
		this.nemicoLL = nemico;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Munizioni getMunizioni() {
		return munizioni;
	}

	public void setMunizioni(Munizioni m) {
		this.munizioni = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public Aquila getAquila() {
		return aquila;
	}

	public void setAquila(Aquila aquila) {
		this.aquila = aquila;
	}

	public GestioneGioco getGestioneGioco() {
		return gestioneGioco;
	}

	public boolean isInPausa() {
		return inPausa;
	}

	public void setInPausa(boolean inPausa) {
		this.inPausa = inPausa;
	}

	public void setGestioneGioco(GestioneGioco gestioneGioco) {
		this.gestioneGioco = gestioneGioco;
	}

	public MuroAquila getMuroAquila() {
		return muroAquila;
	}

	public void setMuroAquila(MuroAquila muroAquila) {
		this.muroAquila = muroAquila;
	}

	public LinkedList<Esplosione> getEsplosioneLL() {
		return this.esplosioneLL;
	}

	public void setEsplosioneLL(LinkedList<Esplosione> l) {
		this.esplosioneLL = l;
	}

	public Teletrasporto[] getTeletrasporto() {
		return teletrasporto;
	}

	public void setTeletrasporto(Teletrasporto teletrasporto[]) {
		this.teletrasporto = teletrasporto;
	}

}