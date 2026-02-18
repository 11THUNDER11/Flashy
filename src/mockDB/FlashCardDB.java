//classe che rappresenta una FlashCard nel database
package mockDB;

public class FlashCardDB {
    private int id; //PK
    private String domanda;
    private String risposta;

    private int fkArgomento; //FK
    private int fkCollezione; //FK

    public FlashCardDB(int id, String domanda, String risposta, int fkArgomento, int fkCollezione) {
        this.id = id;
        this.domanda = domanda;
        this.risposta = risposta;
        this.fkArgomento = fkArgomento;
        this.fkCollezione = fkCollezione;
    }

    public FlashCardDB(int id, String domanda, String risposta, int fkCollezione) {
        this.id = id;
        this.domanda = domanda;
        this.risposta = risposta;
        this.fkArgomento = -1;
        this.fkCollezione = fkCollezione;
    }

    public int getId() {
        return id;
    }

    public void setFkArgomento(int fkArgomento) {
		this.fkArgomento = fkArgomento;
	}

	public String getDomanda() {
        return domanda;
    }

    public String getRisposta() {
        return risposta;
    }

    public int getFkArgomento() {
        return fkArgomento;
    }

    public int getFkCollezione() {
        return fkCollezione;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

   

}