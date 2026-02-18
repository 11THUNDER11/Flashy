package dao;
import java.util.List;

import model.*;

public interface FlashCardDAO {
    public int insertFlashCard(String domanda,String risposta,int idA,Collezione collezione);
    public void deleteFlashCard(FlashCard flashCard);
    public void updateFlashCard(FlashCard flashCard);
    public FlashCard getFlashCardByID(int IDFlashCard);
    public List<FlashCard> getCarteDaCollezione(int IDCollezione,List<Argomento> argomenti);
}