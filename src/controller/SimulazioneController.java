package controller;

import java.io.FileWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import dao.DAOFactory;
import interfaces.ISimulazioneController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;
import model.Simulazione;
import utils.Risultati;

public class SimulazioneController implements ISimulazioneController {
	
	private DAOFactory factory;
	private FileWriter writer;
	private Random random;
	private List<FlashCard> carteEstratte;
	private Map<Integer, Integer> valutazioni;
	private LocalDateTime tempoIniziale;
	private static SimulazioneController instance = null;
	
	public SimulazioneController() {
		this.factory = DAOFactory.getFactory(1);
		this.carteEstratte = new ArrayList<FlashCard>();
		this.valutazioni = new HashMap<Integer, Integer>();
		this.random = new Random();
	}
	
	public static SimulazioneController getInstance() {
		if(instance == null) {
			instance = new SimulazioneController();
		}
		return instance;
	}
	

	@Override
	public List<FlashCard> inizioSimulazione(Collezione c, int lunghezza, Optional<Argomento> a) {
		this.carteEstratte = estraiCarte(c, lunghezza, a);
		this.tempoIniziale = LocalDateTime.now();
		
		for(FlashCard carta : this.carteEstratte) {
    		this.valutazioni.put(carta.getId(), Integer.valueOf(0));
    	}
		
		//this.tempoIniziale = LocalDateTime.now();
		return this.carteEstratte;
		

	}

	@Override
	public void aggiungiValutazione(FlashCard f, int value) {
		this.valutazioni.put(f.getId(), Integer.valueOf(value));
	}
	
	/*@Override
	public void rimuoviValutazione(FlashCard f, double value) {
		valutazioni.remove(f);
	}*/
	
	public Map<Integer, Integer> getMappaValutazioni() {
		return valutazioni;
	}


	@Override
	public Risultati fineSimulazione() {
		LocalDateTime tempoFinale = LocalDateTime.now();
		Duration tempoImpiegato = Duration.between(tempoIniziale, tempoFinale);
		int positivi = 0;
		int negativi = 0;
		
		String argomentoPeggiore="";
		
		for(Integer valutazione : valutazioni.values()) {
			if(valutazione > 0)
				positivi++;
			else
				negativi++;
		}
		
		System.out.println("Mappa :");
		for(Integer i : valutazioni.keySet()) {
			System.out.println("id : " + i);
		}
		
		//Calcolo argomento peggiore
        Map<String, Integer> mappa = new HashMap<String, Integer>();

        for(Integer i : valutazioni.keySet()) {
        	FlashCard f = null;
        	for(FlashCard fc : carteEstratte) {
    			//System.out.println("Carta id : " + fc.getId());
				//System.out.println("Carta id : " + i.intValue());
        		if(fc.getId() == i.intValue()) {
        			f = fc;
        			break;
        			//System.out.println("Carta valutata : " + f.getDomanda());
        		}
        	}
        		
        	Argomento a = f.getArgomento();
            int valutazione = valutazioni.get(i);
            if(valutazione < 0) {

                if(a == null) {
                    if(mappa.get("Nessun argomento") != null) {
                        int numNegative = mappa.get("Nessun argomento");
                        mappa.put("Nessun argomento", numNegative++);
                    }
                    else {
                        mappa.put("Nessun argomento", 1);
                    }
                }
                else {
                    if(mappa.get(a.getTitolo()) != null) {
                        int numNegative = mappa.get(a.getTitolo());
                        mappa.put(a.getTitolo(), numNegative++);
                    }
                    else {
                        mappa.put(a.getTitolo(), 1);
                    }
                }
            }
        }

        //Controllo argomento peggiore
        int peggiore = -1;
        for(String a : mappa.keySet()) {
            if(mappa.get(a) > peggiore) {
                peggiore = mappa.get(a);
                argomentoPeggiore = a;
            }
        }
        
		
        this.carteEstratte = new ArrayList<FlashCard>();
		this.valutazioni = new HashMap<Integer, Integer>();
		return new Risultati(tempoImpiegato, positivi, negativi, argomentoPeggiore);
		
		
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
