//classe che rappresenta un Argomento nel database
package mockDB;

public class ArgomentoDB {
    private int id; //PK
    private String nome;
    private int fkCollezione; //FK

    public ArgomentoDB(int id, String nome, int fkCollezione) {
        this.id = id;
        this.nome = nome;
        this.fkCollezione = fkCollezione;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCollezione() {
        return  fkCollezione;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}