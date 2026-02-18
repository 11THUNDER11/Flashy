<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Modifica di una Carta</title>
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
			String idf = request.getParameter("id");
			int idfi = Integer.parseInt(idf);
			
			FlashCard currCarta = currCollezione.getCartaconId(idfi);
			
			int idArg;
			String argTitolo;
			if(currCarta.getArgomento() == null){
				idArg = -1;
				argTitolo = "Nessun argomento";
				
			}
			else{
				idArg = currCarta.getId();
				argTitolo = currCarta.getArgomento().getTitolo();
			}
				
			
		%>
  
		<p id="benvenuto">Modifica la Carta</p>
				
			<form action="modificaCarta" method="post">
				<input id="domanda" type="text" value="<%= currCarta.getDomanda() %>" name="domanda">
				<br/>
				<input id="risposta" type="text" value="<%= currCarta.getRisposta() %>" name="risposta">
				<br/>
				<input id="idf" type="hidden" name="idf" value="<%= currCarta.getId() %>">
				 <select id="argomenti" name="argomento">
				 	
				 	<%
				 		if(idArg != -1){
				 	%>
				 			<option value="-1" >Nessun Argomento</option>
				 			
				 	<% 		
				 		}
				 	%>
				 	
				 	<option value="<%= idArg %>" selected="selected"><%= argTitolo %></option>
				 	<%
				 		for( Argomento arg : currCollezione.getArgomenti() ){
				 			if(arg.getId() != idArg){
				 	%>
				 				<option value=<%= arg.getId() %>><%= arg.getTitolo() %></option>
				 	<% 
				 			} 
				 		}
				 	%>
				 	
				 	
				 </select>
				 <br/>
				<input id="button" type="submit" value="CONFERMA">

				<a href="viewGestioneCollezione.jsp?idCollezione=<%= currCollezione.getId() %>">
					<button>INDIETRO</button>
				</a>	
			</form>
		
   </body>
</html>