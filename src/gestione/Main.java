package gestione;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pannelli.Menu;
/**
 * Questa classe è il main e serve ad avviare il gioco
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Main {
	private static JPanel menu;
	private static JFrame frame;
	private static Mappa mappa;
	private static GestioneGioco gestioneGioco= new GestioneGioco();
	
	/**
	 * Questo metodo serve a creare il frame che contiene i pannelli, i pannelli stessi e quindi il menù.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		
		frame = new JFrame("Battle City");
		frame.setSize(1366, 768);
		frame.setResizable(false);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mappa = new Mappa(gestioneGioco);
		mappa.setBounds(0, 13, 992, 713);
		mappa.setBackground(Color.BLACK); //CAMBIO SFONDO MAPPA GIOCO

		menu = new Menu(mappa, frame);
	
		frame.add(menu);
		frame.setVisible(true);
		
	
	}
}