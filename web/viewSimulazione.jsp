<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>

<html>
   <head>
      <title>Simulazione</title>
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
				currInd = (Integer) Integer.parseInt(strCurrInd);
			}
			
			FlashCard currCarta = carteEstratte.get(currInd);
			Map<Integer,Integer> mappaValutazioni = (Map<Integer,Integer>) session.getAttribute("mappaValutazioni");
			
			System.out.println("Carta corrente : " + currCarta.getDomanda() + " " + currCarta.getId());
			for(Integer i : mappaValutazioni.keySet()) {
				System.out.println("Carta Id : " + i);
				System.out.println("Valutazione : " + mappaValutazioni.get(i));	
	    	}
			
			Integer currentValutazione = mappaValutazioni.get(currCarta.getId());
			System.out.println("Valutazione Corrente: " + mappaValutazioni.get(currCarta.getId()));	

   			
			String msgValutazione = "";
			if(currentValutazione.intValue() == 0){
				msgValutazione = "Nessuna";
			}
			else{
				if(currentValutazione.intValue() > 0)
					msgValutazione = "POSITIVA";
				else
					msgValutazione = "NEGATIVA";
			}
			System.out.println("Messaggio corrente : " + msgValutazione);
			System.out.println("--------------------------");
   		%>
   	
   	
		<p id="benvenuto">Simulazione</p>
		
		<div>
			<form action="valutaCarta" method="post">
				<textarea id="domanda" name="domanda" rows="4" cols="50" readonly><%= currCarta.getDomanda() %></textarea>
				<p id="num">Stai visualizzando la carta <%= currInd + 1 %> di <%= carteEstratte.size() %></p>
				<br/>
				<p id="num">Valutazione corrente: <%= msgValutazione %></p>
				<br/>
				
				<input id="positivo" name="val" class="button" type="submit" value="SI">
				<input id="negativo" name="val" class="button" type="submit" value="NO">
				<input type="hidden" name="currInd" value="<%= currInd %>">
				<input type="hidden" name="idF" value="<%= currCarta.getId() %>">
				
			</form>
			
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
		
		<% 
			boolean completa = true;
			for(Integer i : mappaValutazioni.keySet())
				if(mappaValutazioni.get(i) == 0)
					completa = false;
			
			if(completa) {
		%>
				<div>
					<form action="valutaCarta" method="get">	
						<input class="button" type="submit" value="Fine Simulazione">
					</form>
				</div>
		
		<%
			}
		%>
		

   </body>
</html>