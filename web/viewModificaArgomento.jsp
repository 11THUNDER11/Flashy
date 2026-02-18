<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Modifica di Argomento</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/login.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewGestioneCollezione.css" rel="stylesheet"></link>
   </head>
   <body>
   
   		<%
   			
	   		if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
		
			Utente currUtente = (Utente) session.getAttribute("utente");
			
			Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
			
			String IDArgStr = request.getParameter("id");
			if(IDArgStr == null)
				response.sendRedirect("viewAggiungiArgomento.jsp");
			
			int idA = Integer.parseInt(IDArgStr);
			Argomento currArg = currCollezione.getArgomentoconId(idA);
			
   		%>
   		
  	
		<p id="benvenuto">Modifica l'Argomento </p>			
				
		<form action="modificaArgomento" method="post">
			<input  class="hidden" name="idArgomento" value="<%= currArg.getId() %>">
			<input id="nome" type="text"  placeholder="nome argomento" value="<%= currArg.getTitolo() %>" name="nomeArgomento">
			<br/>
			<!-- bottone non funzionante -->
			<input id="button" type="submit" value="CONFERMA">
			<a href="viewGestioneCollezione.jsp?idCollezione=<%= currCollezione.getId() %>">
				<button>INDIETRO</button>
			</a>				
		</form>
		
   </body>
</html>