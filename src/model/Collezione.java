package model;

import java.util.ArrayList;
import java.util.List;

public class Collezione {
	private String nome;
	private int id;

	private List<FlashCard> carte;
	private List<Argomento> argomenti;

	public Collezione(String nome, int id) {
		this.nome = nome;
		this.id = id;
		this.carte = new ArrayList<FlashCard>();
		this.argomenti = new ArrayList<Argomento>();
	}

	public void aggiungiFlashCard(FlashCard f) {
		this.carte.add(f);
	}

	public void rimuoviFlashCard(FlashCard f) {
		this.carte.remove(f);
	}

	public void aggiungiArgomento(Argomento a) {
		this.argomenti.add(a);
	}

	public void rimuoviArgomento(Argomento a) {
		this.argomenti.remove(a);
	}

	public int getLunghezza() {
		return this.carte.size();
	}

	public List<FlashCard> getCarteConArgomento(Argomento a) {
		List<FlashCard> res = new ArrayList<FlashCard>();
		for (FlashCard f : this.carte) {
			if(f.getArgomento() != null)
				if (f.getArgomento().getId() == a.getId()) {
				res.add(f);
			}
		}

		return res;
	}
	
	public FlashCard getCartaconId(int id) {
		FlashCard result = null;
		for (FlashCard f : this.carte) {
			if (f.getId() == id)
				result = f;
		}
		return result;
	}
	
	public Argomento getArgomentoconId(int id) {
		Argomento result = null;
		for (Argomento f : this.argomenti) {
			if (f.getId() == id)
				result = f;
		}
		return result;
	}

	// ----------------------------------------------------------------------------

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<FlashCard> getCarte() {
		return carte;
	}

	public void setCarte(List<FlashCard> carte) {
		this.carte = carte;
	}

	public List<Argomento> getArgomenti() {
		return argomenti;
	}

	public void setArgomenti(List<Argomento> argomenti) {
		this.argomenti = argomenti;
	}

}
