<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Aggiunta di Argomento</title>
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
		
		%>	
   		
  	
		<p id="benvenuto">Attualmente, nella collezione <%=currCollezione.getNome() %> ci sono i seguenti argomenti.</p>
		
		<p id="spiegazione">Premi su uno di essi per modificarlo</p>
				
				
		<div id="elenco">
		<table class="borderTable">
			<% 
				for( Argomento arg : currCollezione.getArgomenti() ){  
			%>
			<tr class="row">
				<th>
					<a class="underlined" href="./viewModificaArgomento.jsp?id=<%= arg.getId() %>">
						<%= arg.getTitolo() %>
					</a>
				</th>
				<!-- attualmente, la pressione dei bottoni non fa nulla. Dovrebbe eliminare la carta -->
				<th>
				
				<form action="eliminaArgomento" method="post">
					<input  class="hidden" name="idArgomento" value="<%= arg.getId() %>">
					<input type="submit" class="eliminaButton" value="ELIMINA">
				</form>
				
				</th>
			</tr>
			<% } %>
		</table>				

		<p id="benvenuto">Per aggiungiere un nuovo Argomento:</p>				
				
			<form action="aggiungiArgomento" method="post">
				<input id="nome" required type="text" placeholder="Nome del nuovo Argomento" name="nomeArgomento">
				<br/>
				<!-- bottone non funzionante -->
				<input id="button" type="submit" value="CONFERMA">
				
			</form>
			
			<div>
				<a href="viewGestioneCollezione.jsp?idCollezione=<%= currCollezione.getId() %>">
					<button>INDIETRO</button>
				</a>
			</div>
		
   </body>
</html>