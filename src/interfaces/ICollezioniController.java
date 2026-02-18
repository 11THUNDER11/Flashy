package interfaces;

import java.util.List;

import model.*;

public interface ICollezioniController {
	Collezione nuovaCollezione(Utente u, String nomeCollezione);
	void rimuoviCollezione(Collezione c);
	List<Collezione> inizializzaHome(Utente u);
	void printCreazione();
}
