package interfaces;

import java.util.List;
import java.util.Optional;

import model.Argomento;
import model.Collezione;
import model.FlashCard;

public interface IEsercitazioneController {
	List<FlashCard> inizioEsercitazione(Collezione c, int lunghezza, Optional<Argomento> a);
	void fineEsercitazione();
}
