<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Creazione di una Collezione</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/aggiungiCarta.css" rel="stylesheet"></link>
   </head>
   <body>
   
   		<%
	   		
   			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
   		
   			Utente currUtente = (Utente) session.getAttribute("utente");
   		%>
   		
  		<p id="benvenuto">Benvenuto, <%= currUtente.getUsername() %>!</p>
		<p id="benvenuto">Crea una nuova Collezione</p>
				
			<form action="nuovaCollezione" method="post">
				<input id="nomeCollezione" required type="text" placeholder="Nome della nuova Collezione" name="nomeCollezione">
				<br/>
				<input id="button" type="submit" value="CONFERMA">
			
			</form>
			
			<div>
				<a href="homeUtente.jsp">
					<button>INDIETRO</button>
				</a>
			</div>
		

		
   </body>
</html>