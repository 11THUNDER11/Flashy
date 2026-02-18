<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Esercitazione</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewQuiz.css" rel="stylesheet"></link>
   </head>
   <body>
   
   		<%
   			if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
		
			Utente currUtente = (Utente) session.getAttribute("utente");
			
			Collezione currCollezione = (Collezione) session.getAttribute("currCollezione");
			List<FlashCard> carteEstratte = (List<FlashCard>) session.getAttribute("carteEstratte");
			
			int currInd = 0;
			
			if(request.getParameter("currInd") != null){
				String strCurrInd = request.getParameter("currInd");
				currInd = Integer.parseInt(strCurrInd);
			}
			
			FlashCard currCarta = carteEstratte.get(currInd);
			
			System.out.println("Carta corrente : " + currCarta.getDomanda() + " " + currCarta.getId());	
   			
   		%>
   	
   	   	<%	
   			//l'utente ha premuto il mostra risposta -> presumibilmente da cambiare
   			int mostra = 0;
   			if(request.getParameter("mostra") != null){
   				String strMostra = request.getParameter("mostra");
   				mostra = Integer.parseInt(strMostra);
   			}
   		%>
   	
		<p id="benvenuto">Esercitazione</p>
		
		<div>
			<form>
				<textarea id="domanda" name="domanda" rows="4" cols="50" readonly><%= currCarta.getDomanda() %></textarea>
				<p id="num">Stai visualizzando la carta <%= currInd + 1 %> di <%= carteEstratte.size() %></p>
				<br/>
				<%
					if (mostra == 1){
				%>
					<textarea id="risposta" name="risposta" rows="4" cols="50" readonly><%= currCarta.getRisposta() %></textarea>
				<%		
					}	
				%>
			</form>
			
			<div id="divButtons">
				<a href="?mostra=1&currInd=<%= currInd %>">
					<button id="mostra">MOSTRA RISPOSTA</button>
				</a>
			</div>
			
			<div id="divButtons">
				
				<!-- funzionante -->
				<a href="?currInd=<%= currInd-1  %>">
				
					<% if((currInd) == 0){ %>
						<button disabled id="precedenteButton">CARTA PRECEDENTE</button>
					<% }else{ %>
						<button id="precedenteButton">CARTA PRECEDENTE</button>
					<% } %>
				</a>
				
				<!-- funzionante -->
				<a href="?currInd=<%= currInd+1  %>">		
					<% if((currInd+1) == carteEstratte.size()){ %>
						<button disabled id="prossimoButton">CARTA SUCCESSIVA</button>
					<% }else{ %>
						<button id="prossimoButton">CARTA SUCCESSIVA</button>
					<% } %>	
				</a>
			</div>
		</div>
		
		<div>
			<form action="fineEsercitazione" method="get">	
				<input class="button" type="submit" value="Fine Esercitazione">
			</form>
		</div>
		
		

   </body>
</html>