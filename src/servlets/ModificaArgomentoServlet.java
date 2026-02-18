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
import model.Utente;

import utils.*;

public class ModificaArgomentoServlet extends HttpServlet {
	
	
	private IContenutoController contenutoController;
	
	public void init() throws ServletException{
		this.contenutoController = new ContenutoController();
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//Recupero la sessione
        HttpSession session = request.getSession();
        
    	if(session.getAttribute("utente")==null)
        	response.sendRedirect("login.jsp");
        
    	
    	String IDStr = request.getParameter("idArgomento");
    	String nuovoTitolo = request.getParameter("nomeArgomento");
    	int idArgomento = Integer.parseInt(IDStr);
        
    	Utente utente = (Utente) session.getAttribute("utente");
        Collezione collezione = (Collezione) session.getAttribute("currCollezione");
        
        
        //Rimuovo l'argomento
        Argomento argomentoDaModificare = collezione.getArgomentoconId(idArgomento);
        this.contenutoController.modificaArgomento(argomentoDaModificare, nuovoTitolo);
        
        session.setAttribute("currCollezione", collezione);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("viewAggiungiArgomento.jsp");
        rd.forward(request, response);
        
        
        
    }
    
    
    

}
