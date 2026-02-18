/*
 * Funzione che genera una lista XHTML 
 * con gli item presi dal testo RSS (linguaggio basato su xml)
 * ricevuto come argomento xml
 */

function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};

function parsificaXml( xml ) {
   
   console.log("xml",xml);
   
	// variabili di funzione
	var

		// Otteniamo la lista degli item dall'RSS 2.0 di edit
		items = xml.getElementsByTagName("data");
		
		// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
		itemNodes = new Array(),

		// la variabile di ritorno, in questo esempio, e' testuale
		risultato = "";

	
	
	
	// ciclo di lettura degli elementi
	for (    var a = 0, b = items.length;    a < b;   a++   ) {
		// [length al posto di push serve per evitare errori con vecchi browser]
		itemNodes[a] = new Object();
		itemNodes[a].length = leggiContenuto(items[a],"length");
		itemNodes[a].text = leggiContenuto(items[a],"text");
		itemNodes[a].firstChar = leggiContenuto(items[a],"firstChar");
		itemNodes[a].secondChar = leggiContenuto(items[a],"secondChar");
	}// for ( items )

	console.log(itemNodes);

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	risultato = "";

	for( var c = 0; c < itemNodes.length; c++ ) {
		
		risultato += '<p>' + "Lunghezza : " + itemNodes[c].length +'</p><br/>';
		risultato += '<p>' + "Testo : "+ itemNodes[c].text +'</p><br/>';
		
	};

	// chiudiamo la lista creata
	

     // restituzione dell'html da aggiungere alla pagina
     return risultato;

}// parsificaXml()

function parsificaJson( jsonText ) {
   
	// variabili di funzione
	var

		// Otteniamo la lista degli item dall'RSS 2.0 di edit
		items = JSON.parse(jsonText);
		console.log(items);
		console.log(jsonText);
		
		if(items == null) return "nullo";

		// Predisponiamo una struttura dati in cui memorrizzare le informazioni di interesse
		itemNodes = new Array();

		// la variabile di ritorno, in questo esempio, e' testuale
		risultato = "";

	// ciclo di lettura degli elementi
	for (    var a = 0; a < items.length;   a++   ) {
		// [length al posto di push serve per evitare errori con vecchi browser]
		itemNodes[a] = new Object();
		itemNodes[a].id = items[a].id;
		itemNodes[a].description = items[a].description;
		itemNodes[a].price = items[a].price;
		itemNodes[a].quantity = items[a].quantity;
	}// for ( items )

	// non resta che popolare la variabile di ritorno
	// con una lista non ordinata di informazioni

	// apertura e chiusura della lista sono esterne al ciclo for 
	// in modo che eseguano anche in assenza di items
	risultato = "<ul>";

	for( var c = 0; c < itemNodes.length; c++ ) {
		risultato += '<li class="item"><strong>ID: </strong> ' + itemNodes[c].id;
		risultato += '<strong> DES: </strong>' + itemNodes[c].description;
		risultato += '<strong> AV. QUAN: </strong>' + itemNodes[c].quantity;
		risultato += '<strong> PRICE: </strong>' + itemNodes[c].price + '<br/></li>';
	};

	// chiudiamo la lista creata
	risultato += "</ul>";

     // restituzione dell'html da aggiungere alla pagina
     return risultato;

}// parsificaXml()



/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {

	// designiamo la formattazione della zona dell'output
	
	if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			//ATTENZIONE
			//.responseText se si usa JSON
			//.responseXML se si usa XML
			
			console.log("response XML : ",theXhr.responseXML);
			console.log("response  text: ",theXhr.responseText);
			
			if(theXhr.responseXML){ //Oppure theXhr.responseXML
				//theElement.innerHTML = parsificaJson(theXhr.responseText);
				theElement.innerHTML = parsificaXml(theXhr.responseXML);

			}
			else{
				theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			}
				
			
		}// if 200

		else {
			// errore di caricamento
			theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function caricaCatalogoIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaCatalogoAJAX(theUri, theElement, theXhr) {
    
	// impostazione controllo e stato della richiesta
	
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	
	theUri+="?value="+document.getElementById("testo").value;
	
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// invio richiesta
	theXhr.send(null);

} // caricaFeedAJAX()



function carica(uri,e) { 

	console.log(document.getElementById("testo").value)
        
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();
		
	if ( xhr ) 
		caricaCatalogoAJAX(uri,e,xhr); 
	else 
		caricaCatalogoIframe(uri,e); 
}