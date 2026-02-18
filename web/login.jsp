<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>Login</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/login.css" rel="stylesheet"></link>
   </head>
   <body>
   
		<p id="benvenuto">Benvenuto utente! Prima di poter proseguire, devi autenticarti</p>
		<div>
			<form action="login" method="post">
				<input id="username" type="text" placeholder="username" name="username">
				<br/>
				<input id="password" type="password" placeholder="password" name="password">
				<br/>
				<input id="button" type="submit" value="CONFERMA">
			</form>
			
			<a href="./registrazione.jsp">
				<button id="registrazioneButton">Non hai un account? Registrati qui</button>
			</a>
		</div>
   </body>
</html>