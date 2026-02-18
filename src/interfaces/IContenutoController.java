package interfaces;

import java.util.List;

import model.*;

public interface IContenutoController {
	FlashCard nuovaCarta(Collezione c, String domanda,String risposta,int idA);
	void rimuoviCarta(Collezione c,int idCarta);
	void modificaCarta(FlashCard f, String d, String r, Argomento a);
	Argomento nuovoArgomento(Collezione c,String titolo);
	void rimuoviArgomento(Collezione c, int idA);
	void modificaArgomento(Argomento a,String titolo);
	void scaricaContenutoCollezione(Collezione c);
	void printModifica();
}
