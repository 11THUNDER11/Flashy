package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.css.CSSUnknownRule;

import com.google.gson.Gson;

import controller.SimulazioneController;
import interfaces.ISimulazioneController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;
import utils.Risultati;

public class SimulazioneServlet extends HttpServlet {
	
	private ISimulazioneController simulazioneController;

	public void init() throws ServletException{
		this.simulazioneController = SimulazioneController.getInstance();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Risultati risultati = simulazioneController.fineSimulazione();
		session.setAttribute("risultati", risultati);
		response.sendRedirect("viewStatistica.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
    	Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
    	Map<Integer, Integer> mappaValutazioni = (Map<Integer, Integer>) session.getAttribute("mappaValutazioni");
        
    	
    	String idFS = request.getParameter("idF");
        int idF = Integer.parseInt(idFS);
        FlashCard currCarta = currCollezione.getCartaconId(idF);
        
        String valS = request.getParameter("val");
        int val = valS.equals("SI") ? 1 : -1;
        
        
        this.simulazioneController.aggiungiValutazione(currCarta, val);
        mappaValutazioni.put(currCarta.getId(), val);
        System.out.println("Valutazione inserita per la FlashCard " + currCarta.getDomanda() + " : " + val );
        
        session.setAttribute("mappaValutazioni", mappaValutazioni);
        
        String currInd = request.getParameter("currInd");
        response.sendRedirect("viewSimulazione.jsp?currInd="+currInd);
    }

}
