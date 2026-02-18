package model;

public class Argomento {
	private String titolo;
	private int id;

	public Argomento(String titolo, int id) {
		this.titolo = titolo;
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Argomento altroA = (Argomento) obj;
		if(this.id == altroA.id)
			return true;
		else
			return false;
	}

}
