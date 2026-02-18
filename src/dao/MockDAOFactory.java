package dao;
import dao.mock.*;

public class MockDAOFactory extends DAOFactory {
    public FlashCardDAO getFlashCardDAO() {
        return new MockFlashCardDAO();
    }

    public UtenteDAO getUtenteDAO() {
        return new MockUtenteDAO();
    }

    public CollezioneDAO getCollezioneDAO() {
        return new MockCollezioneDAO();
    }

    public ArgomentoDAO getArgomentoDAO() {
        return new MockArgomentoDAO();
    }
}