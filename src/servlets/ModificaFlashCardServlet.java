package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ContenutoController;
import interfaces.IContenutoController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;

public class ModificaFlashCardServlet extends HttpServlet{
	
	private IContenutoController contenutoController;
	
	public void init() throws ServletException{
		this.contenutoController = new ContenutoController();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//Recupero la sessione
        HttpSession session = request.getSession();
    	
    	if(session.getAttribute("utente")==null)
        	response.sendRedirect("login.jsp");
    	
    	
    	String domanda = request.getParameter("domanda");
    	String risposta = request.getParameter("risposta");
    	String idF = request.getParameter("idf");
    	int idFi = Integer.parseInt(idF);
    	String IDArgStr = request.getParameter("argomento");
    	int idA;
    	if(IDArgStr.isEmpty()) {
    		idA = -1;
    	}
    	else {
    		idA = Integer.parseInt(IDArgStr);
    	}
        
    	System.out.println("Domanda : " + domanda);
    	System.out.println("Risposta : " + risposta);
    	System.out.println("Argomento : " + IDArgStr);
    	System.out.println("IDF : " + idFi);
    	
    	Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
		
        //Modifico la carta
        FlashCard cartaDaModificare = currCollezione.getCartaconId(idFi);
        Argomento argomentoCartaModificata = currCollezione.getArgomentoconId(idA);
    	
        if(argomentoCartaModificata == null)
        	System.out.println("Argomento nullo");
        
        contenutoController.modificaCarta(cartaDaModificare, domanda, risposta, argomentoCartaModificata);
        
        session.setAttribute("currCollezione", currCollezione);
        
        System.out.println("Carta modificata ...");
    	
        
        RequestDispatcher rd = request.getRequestDispatcher("viewGestioneCollezione.jsp?idCollezione="+currCollezione.getId());
        rd.forward(request, response);
  
    }

}
