package gestione;

import java.util.Comparator;

/**
 * Questa classe serve per comparare due punteggi.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Comparatore implements Comparator<Punteggio> {
	/**
	 * Questo metodo compara i punteggi e ritorna un intero.
	 * @param p1 è un Punteggio e rappresenta il primo punteggio 
	 * @param p2 è un Punteggio e rappresenta il secondo punteggio
	 * @return un intero come risultato della comparazione.
	 */
	public  int compare(Punteggio p1, Punteggio p2) {
		return p1.compareTo(p2);
	}
}