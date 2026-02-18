package model;

public class FlashCard {
	private String domanda;
	private String risposta;
	private Argomento argomento;
	private int id;

	public FlashCard(String domanda, String risposta, int id) {
		this.domanda = domanda;
		this.risposta = risposta;
		this.argomento = null;
		this.id = id;
	}

	public FlashCard(String domanda, String risposta, Argomento a, int id) {
		this.domanda = domanda;
		this.risposta = risposta;
		this.argomento = a;
		this.id = id;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Argomento getArgomento() {
		return argomento;
	}

	public void setArgomento(Argomento argomento) {
		this.argomento = argomento;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		FlashCard altroA = (FlashCard) obj;
		if(this.id == altroA.id)
			return true;
		else
			return false;
	}

}
