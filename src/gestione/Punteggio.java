package gestione;
/**
 * Questa classe serve a creare un punteggio.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public class Punteggio  {
	private String utente;
	private int punteggio;

	/**
	 * Questo metodo per associare un utente a un punteggio.
	 * @param utente è una stringa che rappresenta un utente.
	 * @param punteggio è un intero che rappresenta il punteggio.
	 */
	public Punteggio(String utente, int punteggio) {
		this.utente = utente;
		this.punteggio = punteggio;
	}
	
	public int compareTo(Punteggio p){
		if(this.punteggio <= p.punteggio) return 1;
		return -1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Punteggio))
			return false;
		Punteggio p = (Punteggio) obj;
		if (utente == null) {
			if (p.utente != null)
				return false;
		} else if (!utente.equals(p.utente))
			return false;
		return true;
	}
	
	public String toString(){
		return utente+ ":" + punteggio;
	}
	
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
}
