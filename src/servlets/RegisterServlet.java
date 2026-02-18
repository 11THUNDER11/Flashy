package servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.RegistraController;
import model.Utente;

import dao.*;
import interfaces.IRegistraController;

public class RegisterServlet extends HttpServlet {

	//private DAOFactory df;
    //private UtenteDAO ud;

    /*public void init() throws ServletException{
        this.df = DAOFactory.getFactory(1);
        this.ud = this.df.getUtenteDAO();
    }
    */
	
	
	private IRegistraController registraController; 

	public void init() throws ServletException{
    	this.registraController = new RegistraController();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//System.out.println("HELlo");
    	
        //Recupera parametri
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //System.out.println("Username " + username);
        //System.out.println("Password " + password);
        

        //Registrazione
        boolean registered = this.registraController.registraCredenziali(username, password);

        if(!registered) {
			//Utente gia registrato	
        	session.setAttribute("error", "Corrispondenza trovata");
			RequestDispatcher rd = request.getRequestDispatcher("registrazione.jsp");
			rd.forward(request, response);
			return;
        } 
        else {
            if(session.getAttribute("utente")==null) {
        		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                return;
        	}
        	else {
        		response.setStatus(200);
            	response.setHeader("mittente", "servlet");
            	response.setHeader("nome utente", username);
            	PrintWriter out = response.getWriter();
            	out.println(username);
            	out.close();
        	}
        }   
    }

    

}
