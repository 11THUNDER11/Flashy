package model;

import java.util.Date;
import java.util.Optional;

public class Esercitazione extends Quiz{
	
	public Esercitazione(Collezione c, Optional<Argomento> a, int lunghezzaScelta, Date istanteInizio) {
		super(c, a, lunghezzaScelta, istanteInizio);
	}
}