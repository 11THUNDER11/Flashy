package model;

import java.util.Date;
import java.util.Optional;

public abstract class Quiz {
	private int lunghezzaScelta;
	private Date istanteInizio;
	private Collezione c;
	private Optional<Argomento> a;
	
	public Quiz(Collezione c, Optional<Argomento> a, int lunghezzaScelta, Date istanteInizio) {
		this.c = c;
		this.lunghezzaScelta = lunghezzaScelta;
		this.istanteInizio = istanteInizio;
		this.a = a;
	}

	public int getLunghezzaScelta() {
		return lunghezzaScelta;
	}

	public void setLunghezzaScelta(int lunghezzaScelta) {
		this.lunghezzaScelta = lunghezzaScelta;
	}

	public Date getIstanteInizio() {
		return istanteInizio;
	}

	public void setIstanteInizio(Date istanteInizio) {
		this.istanteInizio = istanteInizio;
	}

	public Collezione getC() {
		return c;
	}

	public void setC(Collezione c) {
		this.c = c;
	}

	public Optional<Argomento> getA() {
		return a;
	}

	public void setA(Optional<Argomento> a) {
		this.a = a;
	}
	
	
}
