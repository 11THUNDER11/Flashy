package dao.mock;

import java.util.ArrayList;
import java.util.List;

import dao.ArgomentoDAO;
import mockDB.ArgomentoDB;
import mockDB.MockDB;
import model.Argomento;

public class MockArgomentoDAO implements ArgomentoDAO{

	
	private MockDB db;

    public MockArgomentoDAO() {
        db = MockDB.getInstance();
    }

	
	@Override
	public int insertArgomento(String nomeArgomento,int fkCollezione) {
		// TODO Auto-generated method stub
		
		int ID = this.db.addArgomento(nomeArgomento, fkCollezione);
		return ID;
	}

	@Override
	public Argomento deleteArgomento(int argomento) {
		// TODO Auto-generated method stub
		ArgomentoDB a = this.db.removeArgomento(argomento);
		return new Argomento(a.getNome(), a.getId());
		
	}

	@Override
	public void updateArgomento(Argomento a) {
		this.db.updateArgomento(a.getId(), a.getTitolo());
	}

	@Override
	public Argomento getArgomentoByID(int IDArgomento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Argomento> getArgomentiDaCollezione(int IDCollezione) {
		// TODO Auto-generated method stub
		List<Argomento> result = new ArrayList<>(); 
		List<ArgomentoDB> argomenti = this.db.getArgomentiCollezione(IDCollezione);
		
		for(ArgomentoDB a : argomenti) {
			result.add(new Argomento(a.getNome(), a.getId()));
		}
		
		
		return result;

	}

}
