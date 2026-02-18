package dao;
import model.*;
import utils.Autenticazione;

import java.util.List;


public interface UtenteDAO {
    public boolean insertUtente(String username, String salt, String hashpassword);
    public void deleteUtente(Utente utente);
    public Utente getUtente(String username);
    
    public Autenticazione verificaUtente(String username, String password);
   
    //public Utente getDatiUtente(String username);
    public List<Utente> getAllUtenti();
    public void updateUltimoAccesso(Utente utente);
}