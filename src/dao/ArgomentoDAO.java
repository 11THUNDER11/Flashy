package dao;
import java.util.List;

import model.*;

public interface ArgomentoDAO {
    public int insertArgomento(String nomeArgomento, int fkCollezione);
    public Argomento deleteArgomento(int argomentoID);
    public void updateArgomento(Argomento a);
    public Argomento getArgomentoByID(int IDArgomento);
    public List<Argomento> getArgomentiDaCollezione(int IDCollezione);
}