package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import model.FlashCard;

public class Risultati {
	
	private Duration tempoImpiegato;
	private int numPositive;
	private int numNegative;
	private String nomeArgomentoPeggiore;
	
	public Risultati(Duration tempoImpiegato, int numPositive, int numNegative, String nomeArgomentoPeggiore) {
		this.tempoImpiegato = tempoImpiegato;
		this.numPositive = numPositive;
		this.numNegative = numNegative;
		this.nomeArgomentoPeggiore = nomeArgomentoPeggiore;
	}
	
	public Duration getTempoImpiegato() {
		return tempoImpiegato;
	}
	public void setTempoImpiegato(Duration tempoImpiegato) {
		this.tempoImpiegato = tempoImpiegato;
	}
	public int getNumPositive() {
		return numPositive;
	}
	public void setNumPositive(int numPositive) {
		this.numPositive = numPositive;
	}
	public int getNumNegative() {
		return numNegative;
	}
	public void setNumNegative(int numNegative) {
		this.numNegative = numNegative;
	}
	public String getNomeArgomentoPeggiore() {
		return nomeArgomentoPeggiore;
	}
	public void setNomeArgomentoPeggiore(String nomeArgomentoPeggiore) {
		this.nomeArgomentoPeggiore = nomeArgomentoPeggiore;
	}
	
	
}
