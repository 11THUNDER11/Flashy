<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>View Preliminare</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/login.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewPreliminare.css" rel="stylesheet"></link>
		
   </head>
   <body>
   
   		<%
   			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
		
			Utente currUtente = (Utente) session.getAttribute("utente");
			
			Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
   		%>
   
		<p id="benvenuto">Inserisci i dati prima di avviare il quiz</p>
		<div>
			<form action=avviaQuiz method="post">
				<input type="number" id="lunghezza" name="lunghezza" required="true" min="1" max="<%= currCollezione.getCarte().size() %>">
				<br/>
				<select id="argomentoScelto" name="argomentoScelto">
				 	<option value="" selected="selected">Nessun Argomento</option>
				 	<%
				 		for( Argomento arg : currCollezione.getArgomenti() ){
				 	%>
				 		<option value=<%= arg.getId() %>><%= arg.getTitolo() %></option>
				 	<% 
				 		} 
				 	%>
				 </select>
				<br/>
				<input id="tipoEsercitazione" type="submit" name="action" value="AVVIA ESERCITAZIONE">
				<input id="tipoSimulazione" type="submit" name="action" value="AVVIA SIMULAZIONE">
				<br/>
			</form>
			
				<a href="viewGestioneCollezione.jsp?idCollezione=<%= currCollezione.getId() %>">
					<button>INDIETRO</button>
				</a>
		</div>
   </body>
</html>