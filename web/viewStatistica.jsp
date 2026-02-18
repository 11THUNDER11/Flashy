<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>

<%@ page import="utils.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%>

<html>
   <head>
      <title>Statistica</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewQuiz.css" rel="stylesheet"></link>
   </head>
   <body>
   	
   	   	<%
   	   		//ricezione dei parametri
   	   		
   	   		if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
			Utente currUtente = (Utente) session.getAttribute("utente");
			
   	   		
			if(session.getAttribute("risultati") == null){
				response.sendRedirect("homeUtente.jsp");
			}
			Risultati risultati = (Risultati) session.getAttribute("risultati");
   	   		
   	   		
			Duration durata = risultati.getTempoImpiegato();
			String argomentoPeggiore = risultati.getNomeArgomentoPeggiore();
			int numeroPositive = risultati.getNumPositive();
			int numeroNegative = risultati.getNumNegative();
			
			String d = durata.toString().replace("PT","");
			d = d.replace("M", " minuti ");
			d = d.replace("H", " ore ");
			d = d.replace("S", " secondi ");

   		%>
   	
		<p id="benvenuto">Esito Simulazione</p>
		
		<div>
				<p>Tempo impiegato:		<%= d %></p>
				<p>Argomento peggiore:		<%= argomentoPeggiore %></p>
				<p>Carte con valutazione positiva:	<%= numeroPositive %></p>
				<p>Carte con valutazione negativa:	<%= numeroNegative %></p>
			
			<div>	
				
				<a href="homeUtente.jsp">
					<button id="concludi">TORNA ALLA HOME</button>
				</a>
				
			</div>
		</div>
		
		

   </body>
</html>