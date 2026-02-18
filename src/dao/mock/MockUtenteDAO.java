package dao.mock;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import dao.*;
import model.*;
import utils.Autenticazione;
import mockDB.*;


public class MockUtenteDAO implements UtenteDAO {
    
    private MockDB db;

    public MockUtenteDAO() {
        db = MockDB.getInstance();
    }

    public boolean insertUtente(String username, String salt, String hashpassword) {
        return db.addAccount(username, salt, hashpassword);
        
    }

    public void deleteUtente(Utente utente) {
        db.removeAccount(utente.getUsername());
    }

    public Utente getUtente(String username) {
    	//String salt = this.getSalt(username);
    	
    	
        /*AccountDB account = db.getAccount(username, password);
        if (account == null) {
            return null;
        }*/
        return null;
        //return new Utente(account.getUsername(), account.getSalt(), account.getHashpassword(), account.getLastLogin(), account.isAdministrator());
    }

    public List<Utente> getAllUtenti() {
        List<Utente> result = new ArrayList<Utente>();
    	List<AccountDB> accounts = db.getAllAccounts();
    	for(AccountDB a : accounts)
    		if(!a.isAdministrator())
    			result.add(new Utente(a.getUsername()));
    	return result;
    }

    public void updateUltimoAccesso(Utente utente) {
        db.updateLastLogin(utente.getUsername());
    }

	@Override
	public Autenticazione verificaUtente(String username, String password) {
		AccountDB account = db.getAccount(username);
		if( account == null)
			return new Autenticazione(false, false);
		
		String verifyPass = this.getSecurePassword(password, account.getSalt());	
		if(verifyPass.equals(account.getHashpassword())) {
			
			return new Autenticazione(true, account.isAdministrator());
			
		}
		else {
			return new Autenticazione(false, false);
		}
	}

    
  
    private String getSecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        byte[] realSalt = salt.getBytes();
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add salt to the digest
            md.update(realSalt);
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            // Convert it to a Base64 string
            generatedPassword = Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}