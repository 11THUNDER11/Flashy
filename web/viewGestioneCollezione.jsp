<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
	<head>
		<title>Gestione Collezione</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewGestioneCollezione.css" rel="stylesheet"></link>
	</head>
<body>
		<%
	   		
   			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
   		
   			Utente currUtente = (Utente) session.getAttribute("utente");
   			String IDStr = request.getParameter("idCollezione");
   			
   			if(IDStr==null){
				response.sendRedirect("homeUtente.jsp");
			}
   				
   			int IDc = Integer.parseInt(IDStr);
   			Collezione currCollezione = null;
   			for(Collezione c : currUtente.getCollezioni()){
   				if(c.getId() == IDc) {
   					currCollezione = c;
   					break;
   				}
   			}
   			
   			if(currCollezione == null)
   				response.sendRedirect("homeUtente.jsp");
   			
   			session.setAttribute("currCollezione", currCollezione);
   			
   		%>
  		 
		

	<p id="benvenuto"><%= currUtente.getUsername() %> Stai visualizzando la collezione: <%= currCollezione.getNome() %> </p>
	
	<p id="spiegazione">Per modificare una carta, premi sulla domanda dalla tabella qui sotto</p>
	

	<div id="elenco">
		<table class="borderTable">
			<% 
				for( FlashCard fc : currCollezione.getCarte() ){  
			%>
			<tr class="row">
				<th>
					<a class="underlined" href="./viewModificaCarta.jsp?id=<%= fc.getId() %>">
						<%= fc.getDomanda() %>
					</a>
				</th>
				<th>
				<form action="eliminaCarta" method="post">
					<input  class="hidden" name="idCarta" value="<%= fc.getId() %>">
					<input type="submit" class="eliminaButton" value="ELIMINA">
				</form>
				
				</th>
			</tr>
			<% } %>
		</table>
		
		<br></br> 
		<br></br>
		
		<table id="buttonsTable">
			<tr class="noBorder">
				<th class="noBorder">
					<a href="./viewAggiungiCarta.jsp">
						<button id="aggiungiCartaButton">Nuova FlashCard</button>
					</a>
				</th>
				<th class="noBorder">
					<a href="./viewAggiungiArgomento.jsp">
						<button id="aggiungiArgomentoButton">Gestione Argomenti</button>
					</a>
				</th>
				
				<% 
					if(currCollezione.getCarte().size() != 0) {
				%>
						<th class="noBorder">
							<a href="./viewPreliminare.jsp">
								<button id="preliminareButton">Effettua Quiz</button>
							</a>
						</th>
				<%
					}
				%>
			</tr>
			
			
			<a href="homeUtente.jsp">
				<button>INDIETRO</button>
			</a>			
			
		</table> 
		

	</div>

</body>
</html>