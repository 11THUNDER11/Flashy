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
import controller.LoginController;
import dao.DAOFactory;
import dao.UtenteDAO;
import interfaces.ICollezioniController;
import interfaces.ILoginController;
import model.Collezione;
import model.Utente;

import utils.*;

public class EliminaCollezioneServlet extends HttpServlet {
	
	
	private ICollezioniController collezioniController;
	
	public void init() throws ServletException{
		this.collezioniController = new CollezioniController();
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String IDStr = request.getParameter("idCollezione");
        int ID = Integer.parseInt(IDStr);
    	
        //Recupero la sessione
        HttpSession session = request.getSession();
        
        if(session.getAttribute("utente")==null)
        	response.sendRedirect("login.jsp");
        
        Utente utente = (Utente) session.getAttribute("utente");
        
        //Ottengo la Collezione dell'utente
        Collezione collezioneDaEliminare = null;
        for(Collezione c : utente.getCollezioni()) {
        	if(c.getId() == ID) {
        		collezioneDaEliminare = c;
        		break;
        	}
        }
        
        utente.rimuoviCollezione(collezioneDaEliminare);
        this.collezioniController.rimuoviCollezione(collezioneDaEliminare);
        
        
        session.setAttribute("utente", utente);
        
        RequestDispatcher rd = request.getRequestDispatcher("homeUtente.jsp");
        rd.forward(request, response);
        
        
        
    }
    
    
    

}
