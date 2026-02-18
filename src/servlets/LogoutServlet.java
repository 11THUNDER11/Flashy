package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LogoutServlet extends HttpServlet {

    //Ricevuta una richiesta di logout, la sessione viene invalidata e l'utente viene reindirizzato alla pagina di login
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recupero la sessione
        HttpSession session = request.getSession();
        //Invalido la sessione
        session.invalidate();
        //Reindirizzo alla pagina di login
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

}