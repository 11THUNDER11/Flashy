package controller;

import java.io.FileWriter;

import dao.DAOFactory;
import interfaces.ILoginController;
import utils.Autenticazione;

public class LoginController implements ILoginController{

	private DAOFactory factory;
	FileWriter writer;
	
	public LoginController() {
		// TODO Auto-generated constructor stub
		this.factory = factory.getFactory(1);
	}
	
	
	@Override
	public Autenticazione verificaCredenziali(String username, String password) {
		Autenticazione auth;
        auth = factory.getUtenteDAO().verificaUtente(username, password);
        
        return auth;
	}

	@Override
	public void printLogin() {
		// TODO Auto-generated method stub
		
	}
	


}
