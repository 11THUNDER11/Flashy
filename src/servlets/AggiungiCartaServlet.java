package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CollezioniController;
import controller.ContenutoController;
import controller.LoginController;
import dao.DAOFactory;
import dao.UtenteDAO;
import interfaces.ICollezioniController;
import interfaces.IContenutoController;
import interfaces.ILoginController;
import model.Argomento;
import model.Collezione;
import model.FlashCard;
import model.Utente;

import utils.*;

public class AggiungiCartaServlet extends HttpServlet {
	
	
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
    	String IDArgStr = request.getParameter("argomento");
    	int idA;
    	if(IDArgStr.equals("")) {
    		idA = -1;
    	}
    	else {
    		idA = Integer.parseInt(IDArgStr);
    	}
        
    	//System.out.println("Domanda : " + domanda);
    	//System.out.println("Risposta : " + risposta);
    	//System.out.println("Argomento : " + IDArgStr);
    	
    	Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
		
        //Aggiungo la carta
        FlashCard cartaDaAggiungere = this.contenutoController.nuovaCarta(currCollezione, domanda, risposta, idA);
        
        session.setAttribute("currCollezione", currCollezione);

        RequestDispatcher rd = request.getRequestDispatcher("viewGestioneCollezione.jsp?idCollezione="+currCollezione.getId());
        rd.forward(request, response);
  
    }
}
