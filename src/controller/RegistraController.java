package controller;

import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import dao.DAOFactory;
import interfaces.IRegistraController;

public class RegistraController implements IRegistraController{

	
	private DAOFactory factory;
	FileWriter writer;
	
	public RegistraController() {
		this.factory = factory.getFactory(1);
		//this.writer = new FileWriter("log.txt");
	}
	
	@Override
	public boolean registraCredenziali(String username, String password) {
		String salt = setSalt();
        System.out.println("Salt generato : " + salt);
        String hashedPassword = getSecurePassword(password, salt);
        System.out.println("Password criptata : " + hashedPassword);
        
        boolean registered;
        registered = factory.getUtenteDAO().insertUtente(username, salt, hashedPassword);
        
        System.out.println("Esito registrazione : " + registered);
        
        
        return registered;
	}

	// Method to generate a salt
    private String setSalt() {
    	byte[] salt = null;
    	try {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        salt = new byte[16];
        sr.nextBytes(salt);
    	}
    	catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	return Base64.getEncoder().encodeToString(salt);
    }

    // Method to generate the hash
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

	@Override
	public void printRegistrazione() {
		// TODO Auto-generated method stub
		
	}

}
