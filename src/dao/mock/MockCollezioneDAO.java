package dao.mock;

import java.util.ArrayList;
import java.util.List;

import dao.CollezioneDAO;
import mockDB.CollezioneDB;
import mockDB.MockDB;
import model.Collezione;
import model.Utente;

public class MockCollezioneDAO implements CollezioneDAO{

	private MockDB db;

    public MockCollezioneDAO() {
        db = MockDB.getInstance();
    }

	@Override
	public int insertCollezione(String collezione, Utente u) {
		// TODO Auto-generated method stub
		
		int ID = this.db.addCollezione(collezione, u.getUsername());
		return ID;
	
	}

	@Override
	public void deleteCollezione(Collezione collezione) {
		this.db.removeCollezione(collezione.getId());
	}

	@Override
	public List<Collezione> getAllCollezioniByIDUtente(String username) {
		List<CollezioneDB> collezioniDB = this.db.getCollezioniAccount(username);
		List<Collezione> collezioni = new ArrayList<>();
		
		for( CollezioneDB c : collezioniDB) {
			Collezione collezione = new Collezione(c.getNome(), c.getId());
			collezioni.add(collezione);
		}
		
		return collezioni;
		
		
	}
	
}
