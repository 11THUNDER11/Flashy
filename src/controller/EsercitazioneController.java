package controller;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import dao.DAOFactory;
import interfaces.IEsercitazioneController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;

public class EsercitazioneController implements IEsercitazioneController{
	
	private DAOFactory factory;
	private FileWriter writer;
	private Random random;
	private List<FlashCard> carteEstratte;
	private LocalDateTime tempoIniziale;
	private static EsercitazioneController instance = null;

	public EsercitazioneController() {
		this.factory = DAOFactory.getFactory(1);
		this.carteEstratte = new ArrayList<FlashCard>();
		this.random = new Random();
	}
	
	public static EsercitazioneController getInstance() {
		if(instance == null) {
			instance = new EsercitazioneController();
		}
		return instance;
	}

	@Override
	public List<FlashCard> inizioEsercitazione(Collezione c, int lunghezza, Optional<Argomento> a) {
		this.carteEstratte = estraiCarte(c, lunghezza, a);
		this.tempoIniziale = LocalDateTime.now();
		
		return this.carteEstratte;
		

	}

	@Override
	public void fineEsercitazione() {
		this.carteEstratte = new ArrayList<FlashCard>();
	}
	
	private List<FlashCard> estraiCarte(Collezione c, int lunghezza, Optional<Argomento> a) {
		List<FlashCard> list = c.getCarte();
        List<FlashCard> temp = new ArrayList<FlashCard>();
        List<FlashCard> result = new ArrayList<FlashCard>();

        
        System.out.println("Lunghezza : " + lunghezza);
        System.out.println("Argomento : " + a.toString());
        
        if(!a.isPresent()) {
            
        	for(int i = 0; i < list.size(); i++) {
                FlashCard f = list.get(i);
                temp.add(new FlashCard(f.getDomanda(), f.getRisposta(), f.getArgomento(), f.getId()));
            }

        }
        else {
        	
        	lunghezza = c.getCarteConArgomento(a.get()).size();
        	System.out.println("Lunghezza modificata : " + lunghezza);
            for(int i = 0; i < list.size(); i++) {
                FlashCard f = list.get(i);
                if(list.get(i).getArgomento() != null)
                	if(list.get(i).getArgomento().getId() == a.get().getId())
                		temp.add(new FlashCard(f.getDomanda(), f.getRisposta(), f.getArgomento(), f.getId()));
            }
        }
        
        
        
        for(int i = 0; i < lunghezza && temp.size() > 0; i++) {
            int r = random.nextInt(temp.size());
            result.add(temp.get(r));
            temp.remove(r);
        }

        return result;
    }
	
}
