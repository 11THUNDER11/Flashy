<%@page import="java.time.LocalDateTime"%>
<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<html>
   <head>
      <title>View Log</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewLog.css" rel="stylesheet"></link>
   </head>
   <body>
   				
		<!-- ONLOAD -> logController fornisce l'elenco delle LogEntries del file di log -->
		<!-- In questo caso si è fatto un mock -->
		<% 
		
			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
		
			String s1 = "2023-12-06 15:30" + " | L'utente Marco ha effettuato il Login";
			String s2 = "2023-12-06 15:30" + " | L'utente PierClaudio ha creato la collezione Matematica";
			LogEntry e1 = new LogEntry(s1);
			LogEntry e2 = new LogEntry(s2);
			LogEntry[] entries = new LogEntry[]{e1, e2};
		%>
		
		<p id="benvenuto">File di Log</p>
		
				
		<div id="lineeLog">
			<% 
				for( LogEntry e : entries ){  
			%>
				<p class="line"><%= e.toString() %></p>
			<% } %>
		</div>
		
		<div id="divButtons">
			<a href="./homeAmministratore.jsp">
				<button>Torna alla Home Amministratore</button>
			</a>
		</div>
		
   </body>
</html>