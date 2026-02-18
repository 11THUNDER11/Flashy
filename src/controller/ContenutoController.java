package controller;

import java.io.FileWriter;
import java.util.List;

import dao.DAOFactory;
import interfaces.IContenutoController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;
import model.Utente;

public class ContenutoController implements IContenutoController{

	private DAOFactory factory;
	FileWriter writer;
	
	public ContenutoController() {
		this.factory = DAOFactory.getFactory(1);
		
	}
	
	
	@Override
	public FlashCard nuovaCarta(Collezione c, String domanda, String risposta, int a) {
		int id = this.factory.getFlashCardDAO().insertFlashCard(domanda, risposta, a, c);
		Argomento argomento = c.getArgomentoconId(a);
		FlashCard f =  new FlashCard(domanda, risposta, argomento, id);
		c.aggiungiFlashCard(f);
		return f;
		
	}

	@Override
	public void rimuoviCarta(Collezione c, int idCarta) {
		FlashCard fc = c.getCartaconId(idCarta);
		c.rimuoviFlashCard(fc);
		this.factory.getFlashCardDAO().deleteFlashCard(fc);
	}

	@Override
	public void modificaCarta(FlashCard f, String d, String r, Argomento a) {
		// modifica del modello
		f.setArgomento(a);
		f.setDomanda(d);
		f.setRisposta(r);
		
		// modifca del DB
		this.factory.getFlashCardDAO().updateFlashCard(f);
	}

	@Override
	public Argomento nuovoArgomento(Collezione c, String titolo) {
		int id =  this.factory.getArgomentoDAO().insertArgomento(titolo, c.getId());
		Argomento a = new Argomento(titolo, id);
		c.aggiungiArgomento(a);
		return a;
		
	}

	@Override
	public void rimuoviArgomento(Collezione c, int idA) {
		Argomento a = c.getArgomentoconId(idA);
		c.rimuoviArgomento(a);
		this.factory.getArgomentoDAO().deleteArgomento(idA);
	}

	@Override
	public void modificaArgomento(Argomento a ,String titolo) {
		// modifica del modello
		a.setTitolo(titolo);
		
		// modifica del DB
		this.factory.getArgomentoDAO().updateArgomento(a);
	}

	
	@Override
	public void scaricaContenutoCollezione(Collezione c) {
		
		List<Argomento> argomenti = factory.getArgomentoDAO().getArgomentiDaCollezione(c.getId());
		List<FlashCard> carte = factory.getFlashCardDAO().getCarteDaCollezione(c.getId(),argomenti);
		
		c.setArgomenti(argomenti);
		c.setCarte(carte);
		
		
	}
	
	@Override
	public void printModifica() {
		// TODO Auto-generated method stub
		
	}

}
