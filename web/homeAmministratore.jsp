<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>Home Amministratore</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/homeAmministratore.css" rel="stylesheet"></link>
   </head>
   <body>
   	
   		<%
   			
	   		if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
   			Utente utenteCorrente = (Utente) session.getAttribute("utente");
        
   		
   		
   		%>
   
   
   		<div id="divLogout"><a id="logoutLink" href="logout">Effettua Logout</a></div>
		<p id="benvenuto">Benvenuto Amministratore!</p>
		
		<div id="divButtons">
				
					<a href="viewElencoUtenti.jsp">
						<button id="elencoButton">Visualizza Elenco Utenti</button>
					</a>
					
					<a href="./viewLog.jsp">
						<button id="logButton">Visualizza Log</button>
					</a>
					
					
		</div>
   </body>
</html>