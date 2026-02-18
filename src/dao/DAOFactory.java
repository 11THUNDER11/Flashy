package dao;

public abstract class DAOFactory{
    
    
    public static DAOFactory getFactory(int whichFactory){
        switch(whichFactory){
            //case 0:
                //return new MySqlDAOFactory();
            default:
                return new MockDAOFactory();
        }
    }

    public abstract FlashCardDAO getFlashCardDAO();

    public abstract UtenteDAO getUtenteDAO();

    public abstract CollezioneDAO getCollezioneDAO();

    public abstract ArgomentoDAO getArgomentoDAO();

}