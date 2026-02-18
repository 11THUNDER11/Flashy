<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Home Utente</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewElencoUtenti.css" rel="stylesheet"></link>
   </head>
   <body>
  
   		<%
	   		
   			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
   		
   			Utente currUtente = (Utente) session.getAttribute("utente");
   		%>
  		 
		<p id="benvenuto">Benvenuto, <%= currUtente.getUsername() %>!</p>
		<div id="divLogout"><a id="logoutLink" href="./login.jsp">Effettua Logout</a></div>
		
		<div id="elenco">
			<table>
				<% 
				for( Collezione c : currUtente.getCollezioni() ){  
				%>
					<tr class="row">
						<th>
							<a href="./viewGestioneCollezione.jsp?idCollezione=<%= c.getId() %>">
								<button class="collezioneButton"><%= c.getNome() %></button>
							</a>
						</th>
						<!-- attualmente, la pressione dei bottoni non fa nulla. Dovrebbe eliminare la carta -->
						<th>
						<form action="eliminaCollezione" method="post">
							<input  class="nomeCollezione" name="idCollezione" value="<%= c.getId() %>">
							<input type="submit" class="eliminaButton" value="ELIMINA">
						</form>
						</th>
					</tr>
				<% } %>
			</table>
			<br></br>
			<br></br>
			<a href="./viewCreaCollezione.jsp">
				<button id="nuovaCollezioneButton">Crea una nuova nuova Collezione</button>
			</a>
		</div>
		

		
   </body>
</html>