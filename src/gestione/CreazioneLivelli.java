package gestione;

import oggetti.Nemico;
import oggetti.Teletrasporto;
import oggetti.Teletrasporto.Funzione;
import oggetti.GameObj.Direzione;
/**
 * Questa classe serve per la creazione dei livelli.
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */
public final class CreazioneLivelli {
	/**
	 * Questo metodo crea il primo livello, in quanto inizializza la mappa ed imposta tutti i parametri utilizzati.
	 * @param m è una Mappa che contiene gli elementi grafici del gioco e i vari listener che il gioco supporta.
	 * @param g riguarda l'intera logica del gioco.
	 */
	public static void inizioGioco(Mappa m, GestioneGioco g) {
		g.setFrequenza(1);
		g.setScore(0);
		g.setTruccoVitaInfinita(false);
		g.setTruccoAquila(false);
		g.setModificato(false);
		g.setComparseVita(0);
		g.getNemiciMorti().clear();
		g.setNemiciInAzione(3);
		g.setNemiciTotali(10);
		m.getNemicoLL().clear();
		m.getProiettileLL().clear();
		m.getCarro().setX(100);
		m.getCarro().setY(630);
		m.getCarro().setScorta(30);
		m.getCarro().setSalute(3);
		m.setTeletrasporto(null);
		Nemico nemico1 = new Nemico(50, 50, 50, 50, Direzione.SUD, 1),
				nemico2 = new Nemico(850, 450, 50, 50, Direzione.OVEST, 1),
				nemico3 = new Nemico(850, 50, 50, 50, Direzione.SUD, 1);
		m.getNemicoLL().add(nemico1);
		m.getNemicoLL().add(nemico2);
		m.getNemicoLL().add(nemico3);

		for (int k = 0; k < m.getMatrix().length; k++)
			for (int l = 0; l < m.getMatrix()[k].length; l++)
				m.setMatrix(k, l, 0);

		for (int k = 2; k < 6; k++)
			for (int l = 15; l < 19; l++)
				m.setMatrix(k, l, 4);

		for (int k = 26; k < 30; k++)
			for (int l = 15; l < 19; l++)
				m.setMatrix(k, l, 4);

		for (int k = 13; k < 20; k++)
			for (int l = 1; l < 3; l++)
				m.setMatrix(k, l, 4);

		m.setMatrix(4, 4, 1);
		m.setMatrix(3, 4, 1);
		m.setMatrix(8, 4, 1);
		m.setMatrix(10, 4, 1);
		m.setMatrix(9, 4, 1);
		m.setMatrix(13, 4, 1);
		m.setMatrix(14, 4, 1);
		m.setMatrix(15, 4, 1);
		m.setMatrix(17, 4, 1);
		m.setMatrix(18, 4, 1);
		m.setMatrix(19, 4, 1);
		m.setMatrix(22, 4, 1);
		m.setMatrix(26, 4, 1);
		m.setMatrix(27, 4, 1);
		m.setMatrix(28, 4, 1);
		m.setMatrix(3, 5, 1);
		m.setMatrix(5, 5, 1);
		m.setMatrix(8, 5, 1);
		m.setMatrix(10, 5, 1);
		m.setMatrix(14, 5, 1);
		m.setMatrix(18, 5, 1);
		m.setMatrix(22, 5, 1);
		m.setMatrix(26, 5, 1);
		m.setMatrix(14, 6, 1);
		m.setMatrix(3, 6, 1);
		m.setMatrix(4, 6, 1);
		m.setMatrix(8, 6, 1);
		m.setMatrix(9, 6, 1);
		m.setMatrix(10, 6, 1);
		m.setMatrix(18, 6, 1);
		m.setMatrix(22, 6, 1);
		m.setMatrix(26, 6, 1);
		m.setMatrix(27, 6, 1);
		m.setMatrix(3, 7, 1);
		m.setMatrix(5, 7, 1);
		m.setMatrix(8, 7, 1);
		m.setMatrix(10, 7, 1);
		m.setMatrix(14, 7, 1);
		m.setMatrix(18, 7, 1);
		m.setMatrix(22, 7, 1);
		m.setMatrix(26, 7, 1);
		m.setMatrix(3, 8, 1);
		m.setMatrix(4, 8, 1);
		m.setMatrix(8, 8, 1);
		m.setMatrix(10, 8, 1);
		m.setMatrix(14, 8, 1);
		m.setMatrix(18, 8, 1);
		m.setMatrix(22, 8, 1);
		m.setMatrix(23, 8, 1);
		m.setMatrix(24, 8, 1);
		m.setMatrix(26, 8, 1);
		m.setMatrix(27, 8, 1);
		m.setMatrix(28, 8, 1);
		m.setMatrix(8, 12, 1);
		m.setMatrix(9, 12, 1);
		m.setMatrix(10, 12, 1);
		m.setMatrix(14, 12, 1);
		m.setMatrix(17, 12, 1);
		m.setMatrix(18, 12, 1);
		m.setMatrix(19, 12, 1);
		m.setMatrix(22, 12, 1);
		m.setMatrix(24, 12, 1);
		m.setMatrix(8, 13, 1);
		m.setMatrix(14, 13, 1);
		m.setMatrix(18, 13, 1);
		m.setMatrix(22, 13, 1);
		m.setMatrix(24, 13, 1);
		m.setMatrix(8, 14, 1);
		m.setMatrix(14, 14, 1);
		m.setMatrix(18, 14, 1);
		m.setMatrix(23, 14, 1);
		m.setMatrix(8, 15, 1);
		m.setMatrix(14, 15, 1);
		m.setMatrix(18, 15, 1);
		m.setMatrix(23, 15, 1);
		m.setMatrix(8, 16, 1);
		m.setMatrix(9, 16, 1);
		m.setMatrix(10, 16, 1);
		m.setMatrix(14, 16, 1);
		m.setMatrix(18, 16, 1);
		m.setMatrix(23, 16, 1);

		m.setMatrix(1, 11, 3);
		m.setMatrix(30, 11, 3);

		// Cornice aquila
		m.setMatrix(14, 21, 1);
		m.setMatrix(14, 20, 1);
		m.setMatrix(14, 19, 1);
		m.setMatrix(17, 21, 1);
		m.setMatrix(17, 20, 1);
		m.setMatrix(17, 19, 1);
		m.setMatrix(16, 19, 1);
		m.setMatrix(15, 19, 1);

		// MURA FISSE BORDI
		for (int i = 0; i < m.getMatrix().length; i++)
			for (int j = 0; j < m.getMatrix()[i].length; j++)
				if (i == 0 || j == 0 || i == m.getMatrix().length - 1 || j == m.getMatrix()[i].length - 1)
					m.setMatrix(i, j, 3);
	}
	
	/**
	 * Questo metodo cambia il livello del gioco, aggiornando la mappa ed i vari parametri utilizzati.
	 * @param x rappresenta i possibili livelli dopo il primo, quindi può assumere due valori che sono 1 oppure 2.
	 * @param m è una Mappa che contiene gli elementi grafici del gioco e i vari listener che il gioco supporta.
	 * @param g riguarda l'intera logica del gioco.
	 */

	public static void cambioLivello(int x, Mappa m, GestioneGioco g) {
		g.setComparseVita(0);
		g.setTruccoVitaInfinita(false);
		g.setTruccoAquila(false);
		g.setModificato(false);
		m.getNemicoLL().clear();
		m.getProiettileLL().clear();
		g.getNemiciMorti().clear();
		m.setMuoviCarro(null);
		for (int i = 0; i < m.getMatrix().length; i++)
			for (int j = 0; j < m.getMatrix()[0].length; j++)
				m.setMatrix(i, j, 0);

		switch (x) {
		case 1: { // passaggio da 1 a 2
			Nemico nemico1 = new Nemico(40, 100, 50, 50, Direzione.SUD, 2),
					nemico2 = new Nemico(471, 390, 50, 50, Direzione.SUD, 2),
					nemico3 = new Nemico(630, 80, 50, 50, Direzione.SUD, 2),
					nemico4 = new Nemico(780, 500, 50, 50, Direzione.SUD, 2);
			g.setNemiciTotali(15);
			g.setNemiciInAzione(4);
			g.setFrequenza(1);
			m.getCarro().setX(40);
			m.getCarro().setY(630);
			m.getCarro().setScorta(30);

			m.setTeletrasporto(new Teletrasporto[2]);
			Teletrasporto t1 = new Teletrasporto(155, 40, 60, 60, Direzione.SUD, Funzione.SORGENTE),
					t2 = new Teletrasporto(630, 500, 60, 60, Direzione.SUD, Funzione.RICEVENTE);
			m.getTeletrasporto()[0] = t1;
			m.getTeletrasporto()[1] = t2;

			m.getNemicoLL().add(nemico1);
			m.getNemicoLL().add(nemico2);
			m.getNemicoLL().add(nemico3);
			m.getNemicoLL().add(nemico4);

			// CORNICI FISSE
			for (int k = 0; k < m.getMatrix().length; k++)
				for (int l = 0; l < m.getMatrix()[k].length; l++)
					if (k == 0 || l == 0 || k == m.getMatrix().length - 1 || l == m.getMatrix()[k].length - 1)
						m.setMatrix(k, l, 3);

			for (int i = 1; i < 5; i++)
				for (int j = 1; j < 3; j++)
					m.setMatrix(i, j, 4);
			for (int i = 1; i < 3; i++)
				for (int j = 3; j < 5; j++)
					m.setMatrix(i, j, 4);

			for (int i = 27; i < 31; i++)
				for (int j = 1; j < 3; j++)
					m.setMatrix(i, j, 4);
			for (int i = 29; i < 31; i++)
				for (int j = 3; j < 5; j++)
					m.setMatrix(i, j, 4);

			for (int i = 10; i < 13; i++)
				for (int j = 19; j < 21; j++)
					m.setMatrix(i, j, 4);

			for (int i = 19; i < 22; i++)
				for (int j = 19; j < 21; j++)
					m.setMatrix(i, j, 4);

			for (int i = 3; i < 5; i++)
				for (int j = 3; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 7; i < 10; i++)
				for (int j = 3; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 7; i < 10; i++)
				for (int j = 3; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 12; i < 15; i++)
				for (int j = 3; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 17; i < 20; i++)
				for (int j = 3; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 22; i < 25; i++)
				for (int j = 3; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 27; i < 29; i++)
				for (int j = 3; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 5; i < 10; i++)
				for (int j = 12; j < 14; j++)
					m.setMatrix(i, j, 1);

			for (int i = 22; i < 27; i++)
				for (int j = 12; j < 14; j++)
					m.setMatrix(i, j, 1);

			for (int i = 17; i < 20; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 1);

			for (int i = 12; i < 15; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 1);

			for (int i = 3; i < 5; i++)
				for (int j = 16; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 7; i < 9; i++)
				for (int j = 16; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 23; i < 25; i++)// +1
				for (int j = 16; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 27; i < 29; i++)
				for (int j = 16; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 12; i < 15; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 17; i < 20; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 15; i < 17; i++)
				for (int j = 16; j < 19; j++)
					m.setMatrix(i, j, 1);

			m.setMatrix(15, 5, 3);
			m.setMatrix(15, 6, 3);
			m.setMatrix(16, 5, 3);
			m.setMatrix(16, 6, 3);
			m.setMatrix(1, 13, 3);
			m.setMatrix(30, 13, 3);

			// Cornice aquila
			m.setMatrix(14, 21, 1);
			m.setMatrix(14, 20, 1);
			m.setMatrix(14, 19, 1);
			m.setMatrix(17, 21, 1);
			m.setMatrix(17, 20, 1);
			m.setMatrix(17, 19, 1);
			m.setMatrix(16, 19, 1);
			m.setMatrix(15, 19, 1);
		}
			break;

		case 2: {// passaggio da 2 a 3
			Nemico nemico1 = new Nemico(100, 35, 50, 50, Direzione.EST, 2),
					nemico2 = new Nemico(900, 35, 50, 50, Direzione.OVEST, 2),
					nemico3 = new Nemico(471, 135, 50, 50, Direzione.SUD, 2),
					nemico4 = new Nemico(800, 420, 50, 50, Direzione.SUD, 2),
					nemico5 = new Nemico(200, 420, 50, 50, Direzione.SUD, 2);
			g.setNemiciTotali(20);
			g.setNemiciInAzione(5);
			g.setFrequenza(2);
			m.getCarro().setX(360);
			m.getCarro().setY(625);
			m.getCarro().setScorta(50);

			m.setTeletrasporto(null);

			m.getNemicoLL().add(nemico1);
			m.getNemicoLL().add(nemico2);
			m.getNemicoLL().add(nemico3);
			m.getNemicoLL().add(nemico4);
			m.getNemicoLL().add(nemico5);

			// CORNICE MAPPA
			for (int k = 0; k < m.getMatrix().length; k++)
				for (int l = 0; l < m.getMatrix()[k].length; l++)
					if (k == 0 || l == 0 || k == m.getMatrix().length - 1 || l == m.getMatrix()[k].length - 1)
						m.setMatrix(k, l, 3);

			// Cornice aquila
			m.setMatrix(14, 21, 1);
			m.setMatrix(14, 20, 1);
			m.setMatrix(14, 19, 1);
			m.setMatrix(17, 21, 1);
			m.setMatrix(17, 20, 1);
			m.setMatrix(17, 19, 1);
			m.setMatrix(16, 19, 1);
			m.setMatrix(15, 19, 1);

			for (int i = 1; i < 10; i++)
				for (int j = 18; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 3; i < 5; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 8; i < 10; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 10; i < 15; i++)
				for (int j = 15; j < 17; j++)
					m.setMatrix(i, j, 1);

			for (int i = 13; i < 15; i++)
				for (int j = 12; j < 15; j++)
					m.setMatrix(i, j, 1);

			for (int i = 17; i < 19; i++)
				for (int j = 12; j < 15; j++)
					m.setMatrix(i, j, 1);

			for (int i = 17; i < 22; i++)
				for (int j = 15; j < 17; j++)
					m.setMatrix(i, j, 1);

			for (int i = 22; i < 24; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 22; i < 31; i++)
				for (int j = 18; j < 20; j++)
					m.setMatrix(i, j, 1);

			for (int i = 27; i < 29; i++)
				for (int j = 14; j < 18; j++)
					m.setMatrix(i, j, 1);

			for (int i = 3; i < 13; i++)
				for (int j = 3; j < 5; j++)
					m.setMatrix(i, j, 1);

			for (int i = 3; i < 5; i++)
				for (int j = 5; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 5; i < 7; i++)
				for (int j = 7; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 5; i < 11; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 1);

			for (int i = 9; i < 11; i++)
				for (int j = 7; j < 11; j++)
					m.setMatrix(i, j, 1);

			for (int i = 11; i < 13; i++)
				for (int j = 5; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 19; i < 29; i++)
				for (int j = 3; j < 5; j++)
					m.setMatrix(i, j, 1);

			for (int i = 19; i < 21; i++)
				for (int j = 5; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 21; i < 23; i++)
				for (int j = 7; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 21; i < 27; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 1);

			for (int i = 25; i < 27; i++)
				for (int j = 7; j < 10; j++)
					m.setMatrix(i, j, 1);

			for (int i = 27; i < 29; i++)
				for (int j = 5; j < 8; j++)
					m.setMatrix(i, j, 1);

			for (int i = 5; i < 11; i++)
				for (int j = 5; j < 7; j++)
					m.setMatrix(i, j, 4);

			for (int i = 7; i < 9; i++)
				for (int j = 7; j < 10; j++)
					m.setMatrix(i, j, 4);

			for (int i = 21; i < 27; i++)
				for (int j = 5; j < 7; j++)
					m.setMatrix(i, j, 4);

			for (int i = 23; i < 26; i++)
				for (int j = 7; j < 10; j++)
					m.setMatrix(i, j, 4);

			for (int i = 15; i < 17; i++)
				for (int j = 13; j < 15; j++)
					m.setMatrix(i, j, 3);

			for (int i = 15; i < 17; i++)
				for (int j = 8; j < 10; j++)
					m.setMatrix(i, j, 3);

			for (int i = 15; i < 17; i++)
				for (int j = 6; j < 8; j++)
					m.setMatrix(i, j, 4);

			for (int i = 1; i < 3; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 4);

			for (int i = 29; i < 31; i++)
				for (int j = 10; j < 12; j++)
					m.setMatrix(i, j, 4);

			m.setMatrix(1, 12, 3);
			m.setMatrix(2, 12, 3);

			m.setMatrix(29, 12, 3);
			m.setMatrix(30, 12, 3);

		}
			break;

		}
	}

}
