package dao.mock;

import java.util.ArrayList;
import java.util.List;

import dao.FlashCardDAO;
import mockDB.FlashCardDB;
import mockDB.MockDB;
import model.Argomento;
import model.Collezione;
import model.FlashCard;

public class MockFlashCardDAO implements FlashCardDAO{
	private MockDB db;

    public MockFlashCardDAO() {
        db = MockDB.getInstance();
    }

	@Override
	public int insertFlashCard(String domanda,String risposta,int idA, Collezione collezione) {
		// TODO Auto-generated method stub
	
		int IDF = this.db.addFlashCard(domanda, risposta, idA, collezione.getId());
		return IDF;
	}

	@Override
	public void deleteFlashCard(FlashCard fc) {
		this.db.removeFlashCard(fc.getId());
	}

	@Override
	public void updateFlashCard(FlashCard f) {
		
		int idA;
		if(f.getArgomento() == null) {
			idA = -1;
		}	
		else
			idA = f.getArgomento().getId();
		
		this.db.modificaFlashCard(f.getId(), f.getDomanda(), f.getRisposta(), idA);
	}

	@Override
	public FlashCard getFlashCardByID(int IDFlashCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlashCard> getCarteDaCollezione(int IDCollezione, List<Argomento> argomenti) {
		// TODO Auto-generated method stub
		List<FlashCardDB> flashCardDB = this.db.getFlashCardCollezione(IDCollezione);
		List<FlashCard> result = new ArrayList<>();
		
		for(FlashCardDB f : flashCardDB) {
			
			if(f.getFkArgomento() == -1) //argomento non settato
			{
				result.add(new FlashCard(f.getDomanda(), f.getRisposta(), f.getId()));
			}
			else {
				//Ricerca dell'argomento da associare
				Argomento arg = null;
				for(Argomento a: argomenti) {
					if(a.getId() == f.getFkArgomento()) {
						arg = a;
						break;
					}
				}
				
				result.add(new FlashCard(f.getDomanda(), f.getRisposta(), arg, f.getId()));
			}
			
		}
		
		
		return result;
		
	}

	
}
