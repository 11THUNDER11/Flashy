package model;

import java.util.Date;
import java.util.Optional;

public class Simulazione extends Quiz{
	
	public Simulazione(Collezione c, Optional<Argomento> a, int lunghezzaScelta, Date istanteInizio) {
		super(c, a, lunghezzaScelta, istanteInizio);
	}
}
