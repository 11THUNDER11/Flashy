function checkText() {
   //Se il carattere inserito non è alfabetico lo elimino

    //Prendo il testo inserito
    let text = document.getElementById("testo").value;


    //Prendo l'ultimo carattere inserito
    let lastChar = text.charAt(text.length - 1);
	console.log("Carattere inserito : ",lastChar);
    //Se il carattere inserito non è alfabetico lo elimino
    if (!isNaN(lastChar) && lastChar != " ") {
        text = text.substring(0, text.length - 1);
        document.getElementById("testo").value = text;
    }


    
    //Se il testo inserito raggiunge i 1000 caratteri faccio una richiesta AJAX
    if (text.length == 1000 || lastChar === '7') {
		console.log("Invio richiesta automatica ....");
		carica("calculate.jsp",document.getElementById("result"));
    }


}

//Funzione che effettua una richiesta AJAX
function sendRequest(ID) {
    //Prendo il testo inserito
    
    //Utilizzo JQuery per effettuare una richiesta AJAX
    console.log("Richiesta inviata...");
    $.ajax({
        url: "ActiveSessionsServlet?ID="+ID,
        type: "GET",
        dataType : 'json',
        success: function (response) {
            //Se la richiesta ha successo stampo il risultato
            console.log("Risposta ricevuta", response);
        },
        error: function (xhr, status, error) {
            //Se la richiesta fallisce stampo l'errore
            console.log("Errore", error);
        }
    });

}


