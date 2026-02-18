package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.AmministrazioneController;
import controller.RegistraController;
import interfaces.IAmministrazioneController;
import model.Utente;

public class AmministrazioneServlet extends HttpServlet {
	
	private IAmministrazioneController ammc;
	
	public void init() throws ServletException {
    	this.ammc = new AmministrazioneController();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Utente> list = ammc.elencoUtenti();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        String json = gson.toJson(list);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recupero la sessione
        HttpSession session = request.getSession();
        Utente utenteCorrente = (Utente) session.getAttribute("utente");
        if(utenteCorrente == null) {
        	//Reindirizzo alla pagina di login
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
        
		String username = request.getParameter("username");
		System.out.println("Tento di eliminare l'utente " + username);
		
		ammc.eliminaUtente(new Utente(username));
		
		System.out.println("Utente eliminato");
		PrintWriter out = response.getWriter();
        out.print("Utente eliminato");
        out.flush();
	}
}
