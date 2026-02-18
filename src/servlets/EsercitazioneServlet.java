package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EsercitazioneController;
import interfaces.IEsercitazioneController;
import model.Collezione;

public class EsercitazioneServlet extends HttpServlet {
	
	private IEsercitazioneController esercitazioneController;
	
	public void init() throws ServletException{
		esercitazioneController = EsercitazioneController.getInstance();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
    	
    	esercitazioneController.fineEsercitazione();
		
		response.sendRedirect("viewGestioneCollezione.jsp?idCollezione="+currCollezione.getId());
	}
}
