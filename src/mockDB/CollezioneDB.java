//Classe che rappresenta una Collezione nel database
package mockDB;

public class CollezioneDB {
    private int id; //PK
    private String nome;
    private String fkAccount; //FK    

    public CollezioneDB(int id, String nome, String fkAccount) {
        this.id = id;
        this.nome = nome;
        this.fkAccount = fkAccount;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAccount() {
        return fkAccount;
    }

}