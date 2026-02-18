package interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Argomento;
import model.Collezione;
import model.FlashCard;
import utils.Risultati;

public interface ISimulazioneController {
	List<FlashCard> inizioSimulazione(Collezione c, int lunghezza, Optional<Argomento> a);
	void aggiungiValutazione(FlashCard f, int value);
	Risultati fineSimulazione();
	Map<Integer, Integer> getMappaValutazioni();
}
