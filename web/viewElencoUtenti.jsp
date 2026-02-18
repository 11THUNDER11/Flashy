<%@ page errorPage="./errors/failure.jsp"%>
<%@ page session="true"%>

<%@ page import="model.*"%>
<%@ page import="controller.AmministrazioneController"%>
<%@ page import="java.util.*"%>
<html>
   <head>
      <title>Elenco Utenti</title>
      	<script src="scripts/jquery-3.7.1.min.js"></script>
        <link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<link type="text/css" href="styles/viewElencoUtenti.css" rel="stylesheet"></link>
		<script type="text/javascript">
        	document.addEventListener("DOMContentLoaded", function() {
            	var xhr = new XMLHttpRequest();
            	xhr.open("GET", "AmministrazioneUtenti", true);
            	xhr.onreadystatechange = function() {
                	if (xhr.readyState == 4 && xhr.status == 200) {
                    	var data = JSON.parse(xhr.responseText);
                    	var table = document.getElementById('usersTable');
                    	table.innerHTML = '';
                    	data.forEach(function(utente) {
                        	var row = document.createElement('tr');
                        	row.className = 'row';

                        	var usernameCell = document.createElement('td');
                        	usernameCell.name = utente.username;
                        	usernameCell.textContent = utente.username;
                        	row.appendChild(usernameCell);

                        	var accessCell = document.createElement('td');
                        	accessCell.textContent = utente.ultimoAccesso;
                        	row.appendChild(accessCell);

                        	var actionCell = document.createElement('td');
                        	var button = document.createElement('button');
                        	button.className = 'eliminaButton';
                        	button.textContent = 'ELIMINA';
                        	button.onclick = function() {
                            	eliminaUtente(utente.username);
                        	};
                        	actionCell.appendChild(button);
                        	row.appendChild(actionCell);

                        	table.appendChild(row);
                    	});
                	}
            	};
            	xhr.send();
        	});

        function eliminaUtente(username) {
        	$.ajax({
                url: "AmministrazioneUtenti",
                type: "POST",
                //Lato server i parametri dovranno essere letti con request.getParameter("username") e request.getParameter("password")
                data: {
                    username: username
                },
                /*
                data: {
                    data : JSON.stringify(request)
                },
                //Lato server in questo caso occorre usare request.getParameter("data") per ottenere il JSON
                */
                success: function (response) {
                    console.log("Risposta ricevuta", response);
                    window.location.reload();
                },
                error: function (xhr, status, error) {
                    //Se la richiesta fallisce stampo l'errore
                    console.log("Errore", error);
                }
            });
        }
    </script>
		
   </head>
   <body>
   
   		<%
   		
	   		if(session.getAttribute("utente")==null){
				response.sendRedirect("login.jsp");
			}
   		
   		%>
		
		<div id="divLogout"><a id="logoutLink" href="logout">Effettua Logout</a></div>
		<p id="benvenuto">Elenco utenti</p>

				
		<div id="elenco">
		
			<table>
				<tr id="titoli">
					<th id="nomeUtente">Nome Utente</th>
					<th id="ultimoAccesso">Ultimo Accesso</th>
					<th></th>					
				</tr>
				<tbody id="usersTable">
                	<!-- generati da function js -->
            	</tbody>
			</table>
		

		</div>
		
		<div id="divButtons">
			<a href="./homeAmministratore.jsp">
				<button>Torna alla Home Amministratore</button>
			</a>
		</div>
		
   </body>
</html>