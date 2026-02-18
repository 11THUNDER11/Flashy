<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Aggiunta di una Carta</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/aggiungiCarta.css" rel="stylesheet"></link>
   </head>
   <body>
   
		<%
			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
		
			Utente currUtente = (Utente) session.getAttribute("utente");
			
			Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
		
		%>	
  		
  		<p id="benvenuto"><%= currUtente.getUsername() %> Stai visualizzando la collezione: <%= currCollezione.getNome() %> </p>
	
		<p id="benvenuto">Crea una nuova Carta</p>
				
			<form action="AggiugiCarta" method="post">
				<input id="domanda" type="text" placeholder="Domanda" name="domanda" required="required">
				<br/>
				<input id="risposta" type="text" placeholder="Risposta" name="risposta" required="required">
				<br/>
				 <select id="argomenti" name="argomento">
				 	<option value="" selected="selected">Nessun Argomento</option>
				 	<%
				 		for( Argomento arg : currCollezione.getArgomenti() ){
				 	%>
				 		<option value="<%= arg.getId()%>" > <%= arg.getTitolo() %></option>
				 	<% 
				 		} 
				 	%>
				 </select>
				 <br/>
				<input id="button" type="submit" value="CONFERMA">
				
			</form>
			
			<div>
			<a href="viewGestioneCollezione.jsp?idCollezione=<%= currCollezione.getId() %>">
				<button>INDIETRO</button>
			</a>
			</div>	
		
   </body>
</html>