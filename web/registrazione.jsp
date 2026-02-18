<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>Registrazione</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/login.css" rel="stylesheet"></link>
   </head>
   <body>
   
		<p id="benvenuto">Crea un account per poter accedere ai fantastici servizi di Flashy</p>
		
		
		
		<div>
			<form action="register" method="post">
				<input id="username" type="text" placeholder="username" name="username">
				<br/>
				<input id="password" type="password" placeholder="password" name="password">
				<br/>
				<input id="button" type="submit" value="CONFERMA">
			</form>
			
			<%
             //In caso di errori nella registrazione : se tutto va bene non viene mostrato nulla

             String err = (String) session.getAttribute("error");
             //Se è stata effettuata una richiesta che ha generato un errore
             if(err != null){ %>
                 <p><b>Attenzione : <%= err %></b></p>
             <% }
        %>
			
			<a href="./login.jsp">
				<button id="loginButton">Hai già un account? Allora cosa aspetti?! Premi qui per accedere</button>
			</a>
		</div>
		
		
   </body>
</html>