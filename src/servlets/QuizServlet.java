package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EsercitazioneController;
import controller.SimulazioneController;
import interfaces.IEsercitazioneController;
import interfaces.ISimulazioneController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;
import model.Simulazione;

public class QuizServlet extends HttpServlet {
	
	private ISimulazioneController simulazioneController;
	private IEsercitazioneController esercitazioneController;

	public void init() throws ServletException{
		this.simulazioneController = SimulazioneController.getInstance();
		this.esercitazioneController =EsercitazioneController.getInstance();
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
    	Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
        
        String lunghezzaS = request.getParameter("lunghezza");
        String argomentoSceltoS = request.getParameter("argomentoScelto");
        String action = request.getParameter("action");
        
        
        System.out.println("Lunghezza : " + lunghezzaS);
        System.out.println("argomentoScelto : " + argomentoSceltoS);
        System.out.println("action : " + action);
        
        int lunghezza = Integer.parseInt(lunghezzaS);
        Optional<Argomento> a;
        if(!argomentoSceltoS.isEmpty()) {
        	int argomentoScelto = Integer.parseInt(argomentoSceltoS);
        	a = Optional.of(currCollezione.getArgomentoconId(argomentoScelto));
        }
        else {
        	a = Optional.empty();
        }
        
        if(!a.isPresent())
        	System.out.println("Argomento nullo");
    	  
        // Simulazione
        if(action.equals("AVVIA SIMULAZIONE")) {
        	List<FlashCard> list = simulazioneController.inizioSimulazione(currCollezione, lunghezza, a);
        	session.setAttribute("carteEstratte", list);
        	
        	System.out.println("Le carte sono state estratte");
        	
        	//Inizializzo la mappa delle valutazioni
        	session.setAttribute("mappaValutazioni", simulazioneController.getMappaValutazioni());
        	
        	System.out.println("Inizializzata mappa valutazioni");
        	
        	response.sendRedirect("viewSimulazione.jsp");
        }
        // Esercitazione
        else { 
        	List<FlashCard> list = esercitazioneController.inizioEsercitazione(currCollezione, lunghezza, a);
        	session.setAttribute("carteEstratte", list);
        	
        	System.out.println("Le carte sono state estratte");
        	
        	
        	response.sendRedirect("viewEsercitazione.jsp");
        }
    }
}
