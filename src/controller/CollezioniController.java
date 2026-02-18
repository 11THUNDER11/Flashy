package controller;

import java.io.FileWriter;
import java.util.List;

import dao.DAOFactory;
import interfaces.ICollezioniController;
import interfaces.IContenutoController;
import model.Collezione;
import model.Utente;

public class CollezioniController implements ICollezioniController{

	private DAOFactory factory;
	FileWriter writer;
	private IContenutoController contenutoController;
	public CollezioniController() {
		this.factory = DAOFactory.getFactory(1);
		this.contenutoController = new ContenutoController();
		
	}
	
	
	@Override
	public Collezione nuovaCollezione(Utente u, String nomeCollezione) {
		// Inserimento collezione nel DB
		int ID = this.factory.getCollezioneDAO().insertCollezione(nomeCollezione, u);
		
		// Creazione collezione nel dominio
		Collezione c = new Collezione(nomeCollezione, ID);
		u.aggiungiCollezione(c);
		return c;
	}

	@Override
	public void rimuoviCollezione(Collezione c) {
		
		this.factory.getCollezioneDAO().deleteCollezione(c);
		
		
	}

	@Override
	public List<Collezione> inizializzaHome(Utente u) {
		
		 List<Collezione> collezioni = this.factory.getCollezioneDAO().getAllCollezioniByIDUtente(u.getUsername());
		 
		 for(Collezione c : collezioni) {
			 this.contenutoController.scaricaContenutoCollezione(c);
		 }
		 
		 
		 return collezioni;
		 
		 
	}

	@Override
	public void printCreazione() {
		// TODO Auto-generated method stub
		
	}

}
