package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CollezioniController;
import interfaces.ICollezioniController;
import model.Collezione;
import model.Utente;

public class AggiungiCollezioneServlet extends HttpServlet {
	
	private ICollezioniController collezioniController;
	
	public void init() throws ServletException{
		this.collezioniController = new CollezioniController();
	}
	
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	     String nomeCollezione = request.getParameter("nomeCollezione");
	    	
	     //Recupero la sessione
	     HttpSession session = request.getSession();
	        
	     if(session.getAttribute("utente")==null)
	    	 response.sendRedirect("login.jsp");
	        
	     Utente utente = (Utente) session.getAttribute("utente");
	     Collezione c = collezioniController.nuovaCollezione(utente, nomeCollezione);
	     // serve -> session.setAttribute("currCollezione", c); ???
	        
	     RequestDispatcher rd = request.getRequestDispatcher("homeUtente.jsp");
	     rd.forward(request, response);
	            
	 }

}
