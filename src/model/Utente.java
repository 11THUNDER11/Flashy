package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Date;

public class Utente{
	private String username;
    private Date ultimoAccesso;
	private List<Collezione> collezioni;

    public Utente(String username) {
        this.username = username;
        this.ultimoAccesso = new Date();
		this.collezioni = new ArrayList<Collezione>();
    }
    
    public Utente() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void aggiungiCollezione(Collezione c) {
		this.collezioni.add(c);
	}

	public void rimuoviCollezione(Collezione c) {
		this.collezioni.remove(c);
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}
	
	@Override
	public String toString() {
		return "Utente [nomeUtente=" + username + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Utente altroU = (Utente) obj;
		if(this.username.equals(altroU.username))
			return true;
		else
			return false;
	}
	
}
