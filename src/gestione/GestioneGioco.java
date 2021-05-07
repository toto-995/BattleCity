package gestione;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import oggetti.Munizioni;
import oggetti.MuroAquila;
import oggetti.Nemico;
import oggetti.Proiettile;
import oggetti.Vite;
import oggetti.GameObj.Direzione;
import pannelli.Menu;
import pannelli.PannelloInfo;
import pannelli.PannelloSalvataggio;
import pannelli.Transizione;

/**
 * Questa classe serve per gestire l'intera logica del gioco, istanziando tutti
 * gli oggetti necessari.
 * 
 * @author Arieta Angelo, Bitonti Luigi, Nevigato Nicolas, Piluso Antonio
 * @version 1.0 02/13/17
 */

public class GestioneGioco {
	private boolean morto;
	private Transizione t;
	private LinkedList<Nemico> copiaNemicoLL;
	private LinkedList<Proiettile> copiaProiettileLL;
	private LinkedList<Nemico> nemiciMorti = new LinkedList<>();
	private static LinkedList<Esplosione> copiaEsplosioneLL;
	private int nemiciTotali, nemiciInAzione;
	private Semaphore pausa = new Semaphore(0);
	private boolean munizionePresente = false, vitaPresente = false, muroAquilaPresente = false;
	private boolean truccoAquila = false, truccoVitaInfinita = false;
	private Mappa m;
	private int frequenza = 0;
	private URL intro = Suono.class.getResource("/suoni/intro.wav");
	private URL gameOver = Suono.class.getResource("/suoni/gameOver.wav");
	private URL victory = Suono.class.getResource("/suoni/victory.wav");
	private URL esplosione = Suono.class.getResource("/suoni/esplosione.wav");
	private URL suonoMunizioni = Suono.class.getResource("/suoni/munizioni.wav");
	private URL suonoCuore = Suono.class.getResource("/suoni/cuore.wav");
	private URL suonoAquilaOro = Suono.class.getResource("/suoni/aquilaOro.wav");
	private URL suonoComparsa = Suono.class.getResource("/suoni/comparsa.wav");
	private URL suonoIndebolito = Suono.class.getResource("/suoni/indebolito.wav");
	private URL suonoTeletrasporto = Suono.class.getResource("/suoni/teletrasporto.wav");

	private Random r = new Random();
	private final int MAX_MAPPA_X = 930;
	private final int MIN_MAPPA_X = 35;
	private final int MAX_MAPPA_Y = 660;
	private final int MIN_MAPPA_Y = 70;
	private int score, livello, comparseVita;
	private long tempoInizioM;
	private long tempoInizioV;
	private long tempoInizioMuro;
	private boolean modificato = false;
	private long tempoAquilaPresa;
	private boolean muroDeveStare = false;

	/**
	 * Questo metodo si occupa di gestire le interazioni tra gli oggetti
	 * presenti nel gioco, andando ad aggiore i parametri vitali di ogni singolo
	 * oggetto, gestendo le collisioni tra i vari oggetti e simulando la logica
	 * dei carri armati nemici.
	 * 
	 * @param m
	 *            è una Mappa che contiene gli elementi grafici del gioco e i
	 *            vari listener che il gioco supporta.
	 * @param f
	 *            è un Frame che contiene il pannello su cui si disegna.
	 * @param pan
	 *            è un PannelloInfo che contiene le informazioni sullo stato di
	 *            salute del carro armato dell'utente, sul punteggio, sulle
	 *            munizioni e sui nemici ancora in vita. Su di esso si poggia il
	 *            pannello Mappa.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void gestore(Mappa m, Frame f, PannelloInfo pan) throws InterruptedException, IOException {

		CreazioneLivelli.inizioGioco(m, this);
		this.m = m;

		for (int i = 1; i < 4; i++) {
			livello = i;
			Suono.play(intro);

			f.removeKeyListener(m);
			t = new Transizione(i);
			t.setBounds(0, 0, 1366, 768);

			f.add(t);
			t.repaint();

			Thread.sleep(4000);
			f.remove(t);
			pan.add(m);
			f.add(pan);
			f.validate();

			f.addKeyListener(m);
			morto = false;
			tempoInizioMuro = System.currentTimeMillis();
			tempoInizioM = System.currentTimeMillis();
			tempoInizioV = System.currentTimeMillis();

			while (!morto && nemiciTotali != 0) {
				long lastTime = System.currentTimeMillis();
				copiaEsplosioneLL = new LinkedList<>();
				copiaProiettileLL = new LinkedList<>();
				copiaNemicoLL = new LinkedList<>();
				copiaProiettileLL.addAll(m.getProiettileLL());
				copiaNemicoLL.addAll(m.getNemicoLL());
				copiaEsplosioneLL.addAll(m.getEsplosioneLL());

				if (System.currentTimeMillis() - tempoAquilaPresa > 10000 && muroDeveStare) {
					m.setMatrix(14, 21, 1);
					m.setMatrix(14, 20, 1);
					m.setMatrix(14, 19, 1);
					m.setMatrix(17, 21, 1);
					m.setMatrix(17, 20, 1);
					m.setMatrix(17, 19, 1);
					m.setMatrix(16, 19, 1);
					m.setMatrix(15, 19, 1);
					muroDeveStare = false;
					tempoInizioMuro = System.currentTimeMillis();

					// TRUCCHI
				}
				if (truccoAquila) {
					m.setMatrix(14, 21, 3);
					m.setMatrix(14, 20, 3);
					m.setMatrix(14, 19, 3);
					m.setMatrix(17, 21, 3);
					m.setMatrix(17, 20, 3);
					m.setMatrix(17, 19, 3);
					m.setMatrix(16, 19, 3);
					m.setMatrix(15, 19, 3);
					modificato = true;

				}
				if (modificato && !truccoAquila) {
					m.setMatrix(14, 21, 1);
					m.setMatrix(14, 20, 1);
					m.setMatrix(14, 19, 1);
					m.setMatrix(17, 21, 1);
					m.setMatrix(17, 20, 1);
					m.setMatrix(17, 19, 1);
					m.setMatrix(16, 19, 1);
					m.setMatrix(15, 19, 1);
					modificato = false;
				}
				if (m.isInPausa())
					pausa.acquire();

				// gestione comparsa icona per munizioni
				if (System.currentTimeMillis() - tempoInizioM > 15000 && !munizionePresente) {
					int randomX = r.nextInt(MAX_MAPPA_X - MIN_MAPPA_X + 1) + MIN_MAPPA_X;
					int randomY = r.nextInt(MAX_MAPPA_Y - MIN_MAPPA_Y + 1) + MIN_MAPPA_Y;
					m.setMunizioni(new Munizioni(randomX, randomY, 25, 25, null));
					munizionePresente = true;
					Suono.play(suonoComparsa);
					tempoInizioM = System.currentTimeMillis();
				}
				if (munizionePresente && System.currentTimeMillis() - tempoInizioM > 10000) {
					m.setMunizioni(null);
					munizionePresente = false;
					tempoInizioM = System.currentTimeMillis();
				}

				// gestione comparsa icona per muro aquila
				if (System.currentTimeMillis() - tempoInizioMuro > 30000 && !isMuroAquilaPresente()) {
					int randomX = r.nextInt(MAX_MAPPA_X - MIN_MAPPA_X + 1) + MIN_MAPPA_X;
					int randomY = r.nextInt(MAX_MAPPA_Y - MIN_MAPPA_Y + 1) + MIN_MAPPA_Y;
					m.setMuroAquila(new MuroAquila(randomX, randomY, 25, 25, null));
					setMuroAquilaPresente(true);
					Suono.play(suonoComparsa);
					tempoInizioMuro = System.currentTimeMillis();
				}

				if (isMuroAquilaPresente() && System.currentTimeMillis() - tempoInizioMuro > 30000) {
					m.setMuroAquila(null);
					setMuroAquilaPresente(false);
					tempoInizioMuro = System.currentTimeMillis();
				}

				// gestione comparsa icona vita extra
				if (System.currentTimeMillis() - tempoInizioV > 30000 && !vitaPresente && comparseVita < 3) {
					int randomX = r.nextInt(MAX_MAPPA_X - MIN_MAPPA_X + 1) + MIN_MAPPA_X;
					int randomY = r.nextInt(MAX_MAPPA_Y - MIN_MAPPA_Y + 1) + MIN_MAPPA_Y;
					m.setVite(new Vite(randomX, randomY, 25, 25, null));
					vitaPresente = true;
					Suono.play(suonoComparsa);
					comparseVita++;
					tempoInizioV = System.currentTimeMillis();
				}
				if (vitaPresente && System.currentTimeMillis() - tempoInizioV > 10000) {
					m.setVite(null);
					vitaPresente = false;
					tempoInizioV = System.currentTimeMillis();
				}

				// PROIETTILE
				for (Proiettile pr : copiaProiettileLL) {
					for (Proiettile pr1 : copiaProiettileLL) {
						/*
						 * se due proiettili collidono, si distruggono entrambi
						 */
						if (pr.collide(pr1, pr.getD()) && pr != pr1) {
							m.getProiettileLL().remove(pr);
							m.getProiettileLL().remove(pr1);
							break;
						}
					} // for proiettile-proiettile

					// se si trova agli estremi della mappa
					if (pr.getX() >= 992 - pr.getW() - 3 || pr.getY() >= 713 - pr.getH() - 3) {
						m.getProiettileLL().remove(pr);
					}

					// se collide con le pareti
					if (pr.collide(pr.getD(), m.getMatrix())) {
						pr.removeWall(m.getMatrix());
						m.getProiettileLL().remove(pr);
						break;
					}

					for (Nemico n : copiaNemicoLL) {
						// se spara il nostro carro e colpisce il nemico
						if (pr.collide(n, pr.getD()) && (pr.getO() == m.getCarro())) {
							if (n.getHealth() > 1) {
								Suono.play(suonoIndebolito);
								n.setIndebolito(true);
							}
							n.setHealth(n.getHealth() - 1);
							m.getProiettileLL().remove(pr);
							if (n.getHealth() < 1) {
								m.getNemicoLL().remove(n);
								Suono.play(esplosione);
								m.getEsplosioneLL().add(new Esplosione(n.getX(), n.getY()));
								n.setTempoMorto(System.currentTimeMillis());
								nemiciTotali--;
								nemiciInAzione--;
								nemiciMorti.add(n);
								switch (livello) {
								case 1:
									score += 100;
									break;
								case 2:
									score += 200;
									break;
								case 3:
									score += 300;
									break;
								}

							}
							break;
							// se spara il nemico e colpisce il nemico
						} else if (pr.collide(n, pr.getD()) && (pr.getO() != m.getCarro())) {
							m.getProiettileLL().remove(pr);
							break;
						}
						// se spara il nemico e colpisce il carro
						if (pr.collide(m.getCarro(), pr.getD()) && (pr.getO() != m.getCarro())) {
							if (!truccoVitaInfinita)
								m.getCarro().setSalute(m.getCarro().getSalute() - 1);
							m.getProiettileLL().remove(pr);
							if (m.getCarro().getSalute() == 0) {
								morto = true; // morte del nostro carro
								m.setMuoviCarro(null);
							}
							break;
						}
					} // for copiaNemico

					if (pr.collide(m.getAquila(), pr.getD())) {
						morto = true;
						m.setMuoviCarro(null);
					}

					// if (!collide)
					pr.muovi();

				} // for copiaProiettile

				for (Esplosione e : copiaEsplosioneLL) {
					if (System.currentTimeMillis() - e.getTempoInizioExp() > 550)
						m.getEsplosioneLL().remove(e);
					if (System.currentTimeMillis() - e.getTempoUltimaTransiz() > 70) {
						e.setTempoUltimaTransiz(System.currentTimeMillis());
						if (e.getTransiz() < 6) {
							e.setTransiz(e.getTransiz() + 1);
						}
					}

				}

				// NEMICI
				Random r = new Random();
				for (Nemico n : copiaNemicoLL) {

					// SPRARO DEL NEMICO
					if ((System.currentTimeMillis() - n.getFireLastTime()) > 2000 / frequenza) {// controlla
						// se
						// è
						// passato
						// +
						// di
						// 3
						// secondi
						// dall'ultimo
						// sparo
						int probabilità = r.nextInt(4); // in questo modo
														// alteriamo le
														// probabilità che essi sparino
						if (probabilità != 3 && probabilità != 2) {
							Proiettile p = new Proiettile(n.getX(), n.getY(), 10, 10, n.getD(), n);
							m.getProiettileLL().add(p);
							n.setFireLastTime(System.currentTimeMillis() + probabilità * 500 / frequenza);
							p.muovi();
						}
					}

					// MOVIMENTO DEL NEMICO

					boolean collide = false;

					if (m.getNemicoLL().size() == 1 && !n.collide(n.getD(), m.getMatrix())
							&& !n.collide(m.getCarro(), n.getD())) {
						n.muoviNemico(m.getMatrix());
						break;
					}

					for (Nemico n2 : copiaNemicoLL) {
						// cambio direzione nel caso in cui collide con un
						// nemico
						if (n != n2 && n.collide(n2, n.getD())) {
							n.giraRandom(n.getD(), m.getMatrix());
							collide = true;
							break;
						} 
					} // for n2
					// collisioni con mappa e carro
					if (n.collide(n.getD(), m.getMatrix()) || n.collide(m.getCarro(), n.getD())
							|| n.collide(m.getAquila(), n.getD())) {
						n.giraRandom(n.getD(), m.getMatrix());
						collide = true;
					} 

					if (!collide) {
						n.muoviNemico(m.getMatrix());
					}
				} // for copiaNemico

				// CARRO NOSTRO
				boolean collide = false;
				if (m.getMuoviCarro() != null) {
					if (m.getCarro().collide(m.getMuoviCarro(), m.getMatrix())) {
						m.getCarro().setD(m.getMuoviCarro());
						collide = true;
					}
					for (Nemico n : copiaNemicoLL) {
						if (m.getCarro().collide(n, m.getMuoviCarro())) {
							m.getCarro().setD(m.getMuoviCarro());
							collide = true;
							break;
						}
					}

					if (m.getTeletrasporto() != null) {
						boolean flag1 = false; // serve a controllare se c'è il
												// nemico e se posso procedere
												// al teletrasporto
						boolean flag2 = false; // serve a controllare se il
												// carro è entrato nel
												// teletrasporto
						if (m.getCarro().eContenuto(m.getTeletrasporto()[0])) {
							flag1 = controllaSeCeNemico();
							flag2 = true;
						}
						if (!flag1 && flag2) {

							Suono.play(suonoTeletrasporto);

							m.getCarro().setX(m.getTeletrasporto()[1].getX());
							m.getCarro().setY(m.getTeletrasporto()[1].getY());
						}
					}
					if (m.getCarro().collide(m.getAquila(), m.getCarro().getD())) {
						m.getCarro().setD(m.getMuoviCarro());
						collide = true;
					}
					if (munizionePresente)
						if (m.getCarro().collide(m.getMunizioni(), m.getCarro().getD())) {
							m.getCarro().setScorta(m.getCarro().getScorta() + 10);
							munizionePresente = false;
							Suono.play(suonoMunizioni);
							m.setMunizioni(null);
						}

					if (vitaPresente)
						if (m.getCarro().collide(m.getVite(), m.getCarro().getD())) {
							if (m.getCarro().getSalute() < 5) {
								Suono.play(suonoCuore);
								m.getCarro().setSalute(m.getCarro().getSalute() + 1);
							}
							vitaPresente = false;
							m.setVite(null);
						}

					if (muroAquilaPresente)
						if (m.getCarro().collide(m.getMuroAquila(), m.getCarro().getD())) {
							m.setMatrix(14, 21, 3);
							m.setMatrix(14, 20, 3);
							m.setMatrix(14, 19, 3);
							m.setMatrix(17, 21, 3);
							m.setMatrix(17, 20, 3);
							m.setMatrix(17, 19, 3);
							m.setMatrix(16, 19, 3);
							m.setMatrix(15, 19, 3);
							muroAquilaPresente = false;
							Suono.play(suonoAquilaOro);
							tempoAquilaPresa = System.currentTimeMillis();
							muroDeveStare = true;
							m.setMuroAquila(null);
						}

					if (!collide) {
						m.getCarro().muovi(m.getMuoviCarro());
					}
				} // carroNostro
				if (nemiciTotali - nemiciInAzione > 0 && !nemiciMorti.isEmpty()
						&& (System.currentTimeMillis() - nemiciMorti.getFirst().getTempoMorto()) > 5000) {
					Nemico risorto = nemiciMorti.removeFirst();
					risorto.setX(risorto.getxIniziale());
					risorto.setY(risorto.getyIniziale());
					risorto.setHealth(risorto.getVitaIniziale());
					boolean collideConIVivi = false;
					for (Nemico n : copiaNemicoLL) {
						if (risorto.collide(n, Direzione.EST) || risorto.collide(n, Direzione.OVEST)
								|| risorto.collide(n, Direzione.NORD) || risorto.collide(n, Direzione.SUD)) {
							collideConIVivi = true;
							break;
						}
					}
					if (risorto.collide(m.getCarro(), Direzione.EST) || risorto.collide(m.getCarro(), Direzione.OVEST)
							|| risorto.collide(m.getCarro(), Direzione.SUD)
							|| risorto.collide(m.getCarro(), Direzione.NORD))
						collideConIVivi = true;
					if (!collideConIVivi) {
						m.getNemicoLL().add(risorto);
						nemiciInAzione++;
					} else
						nemiciMorti.addLast(risorto);
				}

				long delta = System.currentTimeMillis() - lastTime;
				try {
					Thread.sleep(9 - delta > 0 ? 9 - delta : 0);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				pan.repaint();
			} // while

			if (morto) {
				f.removeKeyListener(m);
				t = new Transizione(5);
				t.setBounds(0, 0, 1366, 768);
				pan.remove(m);
				pan.add(t);
				pan.repaint();
				Suono.play(gameOver);
				PannelloSalvataggio p = new PannelloSalvataggio();
				p.setBounds(0, 0, 1366, 768);
				Thread.sleep(2000);
				pan.remove(t);
				pan.add(p);
				pan.repaint();
				p.getAcquisito().acquire();
				String utente = p.getNomeUtente();
				Punteggio punteggio = new Punteggio(utente, score);
				Salvataggio.salva(punteggio);
				pan.remove(p);
				Menu.pm = new PosizioneMouse(f);
				Menu.pm.start();
				break;

			}
			if (i==1 || i==2)
				CreazioneLivelli.cambioLivello(i, m, this);
			pan.remove(m);

		} // FOR livelli
		if (!morto) {
			f.removeKeyListener(m);
			t = new Transizione(4);
			t.setBounds(0, 0, 1366, 768);
			pan.remove(m);
			pan.add(t);
			pan.repaint();
			Suono.play(victory);
			PannelloSalvataggio p = new PannelloSalvataggio();
			p.setBounds(0, 0, 1366, 768);
			Thread.sleep(5000);
			pan.remove(t);
			pan.add(p);
			pan.repaint();
			p.getAcquisito().acquire();
			String utente = p.getNomeUtente();
			Punteggio punteggio = new Punteggio(utente, score);
			Salvataggio.salva(punteggio);
			pan.remove(p);
			Menu.pm = new PosizioneMouse(f);
			Menu.pm.start();
		}

	}

	public int getScore() {
		return score;
	}

	public void setScore(int punteggio) {
		this.score = punteggio;
	}

	public LinkedList<Nemico> getCopiaNemicoLL() {
		return copiaNemicoLL;
	}

	public void setCopiaNemicoLL(LinkedList<Nemico> copiaNemico) {
		this.copiaNemicoLL = copiaNemico;
	}

	public LinkedList<Proiettile> getCopiaProiettileLL() {
		return copiaProiettileLL;
	}

	public void setCopiaProiettileLL(LinkedList<Proiettile> copiaProiettileLL) {
		this.copiaProiettileLL = copiaProiettileLL;
	}

	public Semaphore getPausa() {
		return pausa;
	}

	public int getNemiciTotali() {
		return nemiciTotali;
	}

	public void setNemiciTotali(int nemiciTotali) {
		this.nemiciTotali = nemiciTotali;
	}

	public void setPausa(Semaphore pausa) {
		this.pausa = pausa;
	}

	public long getTempoInizioM() {
		return tempoInizioM;
	}

	public void setTempoInizioM(long tempoInizioM) {
		this.tempoInizioM = tempoInizioM;
	}

	public long getTempoInizioV() {
		return tempoInizioV;
	}

	public void setTempoInizioV(long tempoInizioV) {
		this.tempoInizioV = tempoInizioV;
	}

	public boolean isTruccoAquila() {
		return truccoAquila;
	}

	public void setTruccoAquila(boolean truccoAquila) {
		this.truccoAquila = truccoAquila;
	}

	public boolean isTruccoVitaInfinita() {
		return truccoVitaInfinita;
	}

	public void setTruccoVitaInfinita(boolean truccoVitaInfinita) {
		this.truccoVitaInfinita = truccoVitaInfinita;
	}

	public boolean isMuroAquilaPresente() {
		return muroAquilaPresente;
	}

	public void setMuroAquilaPresente(boolean muroAquilaPresente) {
		this.muroAquilaPresente = muroAquilaPresente;
	}

	public boolean isVitaPresente() {
		return vitaPresente;
	}

	public void setVitaPresente(boolean vitaPresente) {
		this.vitaPresente = vitaPresente;
	}

	public int getFrequenza() {
		return frequenza;
	}

	public void setFrequenza(int frequenza) {
		this.frequenza = frequenza;
	}

	public long getTempoInizioMuro() {
		return tempoInizioMuro;
	}

	public void setTempoInizioMuro(long tempoInizioMuro) {
		this.tempoInizioMuro = tempoInizioMuro;
	}

	public LinkedList<Esplosione> getCopiaEsplosioneLL() {
		return copiaEsplosioneLL;
	}

	public void setCopiaEsplosioneLL(LinkedList<Esplosione> l) {
		GestioneGioco.copiaEsplosioneLL = l;
	}

	public void setModificato(boolean b) {
		modificato = b;

	}

	public void setComparseVita(int i) {
		comparseVita = i;

	}

	public LinkedList<Nemico> getNemiciMorti() {
		return nemiciMorti;
	}

	public void setNemiciInAzione(int i) {
		nemiciInAzione = i;

	}

	/**
	 * Questo metodo controlla se è possibile effettuare un teletrasporto senza
	 * sovrapporre i carri armati.
	 * 
	 * @return un boolean che indica l'esito del controllo.
	 */
	private boolean controllaSeCeNemico() {
		boolean flag = false;
		for (Nemico n : copiaNemicoLL)
			if (m.getTeletrasporto()[1].collide(n, Direzione.EST) || m.getTeletrasporto()[1].collide(n, Direzione.OVEST)
					|| m.getTeletrasporto()[1].collide(n, Direzione.SUD)
					|| m.getTeletrasporto()[1].collide(n, Direzione.NORD)) {
				flag = true;
				break;
			}
		return flag;
	}

}
