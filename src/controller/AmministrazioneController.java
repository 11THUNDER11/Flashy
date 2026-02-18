package controller;

import java.io.FileWriter;
import java.util.List;

import dao.*;
import interfaces.IAmministrazioneController;
import model.*;

public class AmministrazioneController implements IAmministrazioneController{
	
	private DAOFactory factory;
	FileWriter writer;
	
	public AmministrazioneController() {
		this.factory = DAOFactory.getFactory(1);
		//this.writer = new FileWriter("log.txt");
	}

	@Override
	public List<Utente> elencoUtenti() {
		UtenteDAO ud = factory.getUtenteDAO();
		return ud.getAllUtenti();
	}

	@Override
	public void eliminaUtente(Utente utente) {
		UtenteDAO ud = factory.getUtenteDAO();
		ud.deleteUtente(utente);
	}

}
