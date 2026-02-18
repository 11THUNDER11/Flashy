package interfaces;

import java.util.List;

import model.Utente;

public interface IAmministrazioneController {
	public List<Utente> elencoUtenti();
	public void eliminaUtente(Utente utente);
}
