package gestione;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Questa classe serve a gestire il salvataggio.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */

public class Salvataggio {
	private static Comparatore c = new Comparatore();

	/**
	 * Questo metodo serve a ordinare i punteggi in maniera decrescente.
	 * 
	 * @param destinazione
	 *            è una stringa che indica il path in cui salvare il file con i
	 *            punteggi.
	 * @param punteggio
	 *            è un punteggio da salvare.
	 * @param ll
	 *            è una Linked List e contiene la lista dei vari punteggi.
	 * @throws IOException
	 */
	private static void ordina(String destinazione, Punteggio punteggio, LinkedList<Punteggio> ll) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(destinazione));
		ll.add(punteggio);
		ll.sort(c);
		for (Punteggio p : ll) {
			pw.println(p);
		}
		pw.close();
	}

	/**
	 * Questo metodo serve per prelevare i punteggi già salvati.
	 * 
	 * @param path
	 *            è una stringa che indica il path da cui prendere i punteggi.
	 * @return una Linked List che contiene la lista aggiornata dei vari
	 *         punteggi.
	 * @throws IOException
	 */
	private static LinkedList<Punteggio> ripristina(String path) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		String linea = null;
		StringTokenizer st;
		LinkedList<Punteggio> lista = new LinkedList<>();
		for (;;) {
			linea = br.readLine();
			if (linea == null)
				break;
			st = new StringTokenizer(linea, ":");
			String utente = st.nextToken();
			int punteggio = Integer.parseInt(st.nextToken());
			Punteggio p = new Punteggio(utente, punteggio);
			lista.add(p);
		}
		return lista;
	}

	/**
	 * Questo metodo salva un punteggio in una determinata cartella del file
	 * system, creandola laddove non esista.
	 * 
	 * @param p
	 *            è il punteggio da salvare.
	 * @throws IOException
	 */
	public static void salva(Punteggio p) throws IOException {
		String path = getDataFolder() + "classifica.txt";
		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}
		ordina(path, p, ripristina(path));
	}

	/**
	 * Questo metodo serve a capire in quale sistema operativo si sta eseguendo
	 * il gioco e pertanto a trovare la giusta cartella in cui salvare il file
	 * dei punteggi.
	 * @return una stringa che indica il path in cui andare a salvare il file con i punteggi.
	 */
	public static String getDataFolder() {
		String folder = System.getProperty("user.home");// nome utente
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
			if (os.contains("xp"))
				folder += "\\Application Data\\BattleCity\\";
			else if (os.contains("7") || os.contains("vista") || os.contains("8") || os.contains("10"))
				folder += "\\AppData\\Roaming\\BattleCity\\";
		} else if (os.contains("mac"))
			folder += "/Library/Application Support/BattleCity/";

		new File(folder).mkdirs();// crea la cartella
		return folder;
	}
}
