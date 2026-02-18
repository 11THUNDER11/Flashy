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
import model.Collezione;
import model.Utente;

import utils.*;

public class LoginServlet extends HttpServlet {
	/*
	private DAOFactory df;
    private UtenteDAO ud;

    public void init() throws ServletException{
        this.df = DAOFactory.getFactory(1);
        this.ud = this.df.getUtenteDAO();
    }
    */
	
	private ILoginController loginController;
	private ICollezioniController collezioniController;
	
	private IContenutoController contenutoController;
	
	public void init() throws ServletException{
		this.loginController = new LoginController();
		this.collezioniController = new CollezioniController();
		this.contenutoController = new ContenutoController();
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        
        //Recupero la sessione
        HttpSession session = request.getSession();

        //Verifica credenziali
        Autenticazione auth = loginController.verificaCredenziali(username, password);
        
        
        if(auth.isGranted()) {
            //Credenziali valide
        	session.setAttribute("username", username);
           
            
            //Controllo admin
            if(auth.isAdmin()) {
                //Ridirezione pagina admin
            	
            	Utente utente = new Utente(username);
            	session.setAttribute("utente", utente);
            	
                RequestDispatcher rd = request.getRequestDispatcher("homeAmministratore.jsp");
                rd.forward(request, response);
            } else {
            	
            	//Caricamento collezioni utente
            	Utente utente = new Utente(username);
            	
            	//------------------------------------------------------
            	List<Collezione> collezioniUtente = this.collezioniController.inizializzaHome(utente);
            	/*
            	for(Collezione c : collezioniUtente) {
            		this.contenutoController.scaricaContenutoUtente(utente);
            	}
            	*/
            	
            	
            	//------------------------------------------------------
            	
            	utente.setCollezioni(collezioniUtente);
            	session.setAttribute("utente", utente);
            	
            	
                //Ridirezione pagina principale
                RequestDispatcher rd = request.getRequestDispatcher("homeUtente.jsp");
                rd.forward(request, response);
            }
            
            
        } else {
            //Credenziali non valide
            session.setAttribute("error", "Credenziali non valide");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        
        
    }
    
    
    

}
