package dao;
import java.util.List;

import model.*;

public interface CollezioneDAO {

    public int insertCollezione(String collezione,Utente u);
    public void deleteCollezione(Collezione collezione);
    //public void updateCollezione(Collezione collezione);
    //public CollezioneProxy getCollezioneByID(String IDCollezione);
    public List<Collezione> getAllCollezioniByIDUtente(String IDUtente);

}