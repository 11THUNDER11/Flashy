//classe che rappresenta il database fittizio
package mockDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDB {
    private static MockDB instance = null;
    
    private Map<String, AccountDB> accounts;
    private Map<Integer, FlashCardDB> flashCards;
    private Map<Integer, CollezioneDB> collezioni;
    private Map<Integer, ArgomentoDB> argomenti;

    private int counterFlashCard = 0;
    private int counterCollezione = 0;
    private int counterArgomento = 0;



    private MockDB() {
        accounts = new HashMap<>();
        flashCards = new HashMap<>();
        collezioni = new HashMap<>();
        argomenti = new HashMap<>();
        
        this.init();
    }

    public static MockDB getInstance() {
        if (instance == null) {
            instance = new MockDB();
        }
        return instance;
    }

    
    // -------------------- Metodi che aggiungono/rimuovono dati nel database --------------------
    
    public boolean addAdmin(String username, String salt, String hashpassword) {

        //Controllo se l'account e' gia' presente nel database
        if (accounts.containsKey(username)) {
            return false;
        }

        AccountDB account = new AccountDB(username, salt, hashpassword, new Date(), true);
        accounts.put(username, account);
        return true;

    }

    public boolean addAccount(String username, String salt, String hashpassword) {
        
        //Controllo se l'account e' gia' presente nel database
        if (accounts.containsKey(username)) {
            return false;
        }

        AccountDB account = new AccountDB(username, salt, hashpassword, new Date(), false);
        accounts.put(username, account);
        return true;
    
    }

    public int addFlashCard(String domanda, String risposta, int fkArgomento, int fkCollezione) {
    	
        FlashCardDB flashCard = new FlashCardDB(counterFlashCard, domanda, risposta, fkArgomento, fkCollezione);
        flashCards.put(counterFlashCard, flashCard);
        int IDFlashCard = counterFlashCard;
        counterFlashCard++;
        
        return IDFlashCard;
    }

    public int addCollezione(String nomeCollezione, String account) {

        CollezioneDB collezioneDB = new CollezioneDB(counterCollezione, nomeCollezione, account);
        collezioni.put(counterCollezione, collezioneDB);
        int IDCollezione = counterCollezione;
        counterCollezione++;
        
        return IDCollezione;
        
    }

    public int addArgomento(String nomeArgomento, int fkCollezione) {

        ArgomentoDB argomentoDB = new ArgomentoDB(counterArgomento, nomeArgomento, fkCollezione);
        argomenti.put(counterArgomento, argomentoDB);
        int IDArgomento = counterArgomento;
        counterArgomento++;
        
        return IDArgomento;

        
    }

    public void removeFlashCard(int id) {
        flashCards.remove(id);
    }

    public void removeCollezione(int id) {

        //Rimuovo tutte le FlashCard della Collezione
        List<FlashCardDB> flashCardsToRemove = getFlashCardCollezione(id);
        for (FlashCardDB f : flashCardsToRemove) {
            removeFlashCard(f.getId());
        }

        //Rimuovo tutti gli Argomenti della Collezione
        List<ArgomentoDB> argomentiToRemove = getArgomentiCollezione(id);
        for (ArgomentoDB a : argomentiToRemove) {
            removeArgomento(a.getId());
        }

        collezioni.remove(id);
    }

    public ArgomentoDB removeArgomento(int id) {
        ArgomentoDB res = argomenti.get(id);
    	argomenti.remove(id);
    	
    	return res;
    }

    public void removeAccount(String username) {
    	
    	List<CollezioneDB> collezioniUtente = this.getCollezioniAccount(username);
    	for(CollezioneDB c : collezioniUtente)
    		this.removeCollezione(c.getId());
    	
        accounts.remove(username);
        
    }
    
    // -------------------- Metodi che modificano FlashCard e Argomenti ------------------------------
    public void modificaFlashCard(int id, String domanda, String risposta, int fkArgomento) {
    	FlashCardDB f = flashCards.get(id);
    	f.setDomanda(domanda);
    	f.setRisposta(risposta);
    	f.setFkArgomento(fkArgomento);
    }
    
    public void modificaArgomento(int id, String nome) {
    	ArgomentoDB f = argomenti.get(id);
    	f.setNome(nome);
    }

    // -------------------- Metodi che ritornano un oggetto presente nel database --------------------

    public AccountDB getAccount(String username) {
        AccountDB account = accounts.get(username);
        if (account != null ) {
            return account;
        }
        return null;
    }
    
    public List<AccountDB> getAllAccounts() {
    	return new ArrayList<>(accounts.values());
    }

    //Metodo che ritorna le Collezioni di un Account
    public List<CollezioneDB> getCollezioniAccount(String username) {
        List<CollezioneDB> result = new ArrayList<>();
        for (CollezioneDB c : collezioni.values()) {
            if (c.getAccount().equals(username)) {
                result.add(c);  
            }
        }
        return result;
    }

    //Metodo che ritorna gli Argomenti di una Collezione
    public List<ArgomentoDB> getArgomentiCollezione(int idcollezione) {
        List<ArgomentoDB> result = new ArrayList<>();
        for (ArgomentoDB a : argomenti.values()) {
            if (a.getCollezione() == idcollezione) {
                result.add(a);
            }
        }
        return result;
    }

    //Metodo che ritora le FlashCard di una Collezione
    public List<FlashCardDB> getFlashCardCollezione(int idcollezione) {
        List<FlashCardDB> result = new ArrayList<>();
        for (FlashCardDB f : flashCards.values()) {
            if (f.getFkCollezione() == idcollezione) {
                result.add(f);
            }
        }
        return result;
    }

    //Metodo che aggiora l'ultima data di login di un Account
    public void updateLastLogin(String username) {
        AccountDB account = accounts.get(username);
        account.setLastLogin(new Date());
    }
    
    //Metodo che effettua l'update dell'Argomento
    public void updateArgomento(int idA,String username) {
       this.argomenti.get(idA).setNome(username);;
        
    }
    
    
  //Aggiunta di dati fittizi
    public void init(){
    	this.addArgomento("Nessun Argomento" , -1);
    	
        this.addAccount("mario", "yT4IXLXKwH5X7R8kJnU3dQ==", "SYTiwVkopBBGh8TuGvkuaVlcd7HB2NPGzA5OXeIKH9g=");
        //Password: 1234

        int IDC = this.addCollezione("collezione1", "mario");
        
        int IDArg = this.addArgomento("argomento1", IDC);

        this.addFlashCard("domanda1", "risposta1", IDArg, IDC);
        this.addFlashCard("domanda2", "risposta2", IDArg, IDC);

        
        this.addCollezione("collezione2", "mario");

        
        //pass : I7sfXzeC5gXEOo1MOvK3gP9xXIH04GUhey0LzlaAQ+w=
        //salt : 9OTc/RLUpUOf3MXlRhFYXw==
        
        this.addAccount("marco", "9OTc/RLUpUOf3MXlRhFYXw==", "I7sfXzeC5gXEOo1MOvK3gP9xXIH04GUhey0LzlaAQ+w=");
        IDC = this.addCollezione("Storia", "marco");
        IDArg = this.addArgomento("Rivoluzione francese", IDC);
        
        this.addFlashCard("Quando cadde la bastiglia", "1789", IDArg, IDC);
        this.addFlashCard("Quando muore Maria Antognetta", "16 Ottobre 1813", IDArg, IDC);
        
        this.addFlashCard("Quando scoppio la seconda guerra mondiale", "1939", -1, IDC);
        
        IDC = this.addCollezione("Geografia", "marco");
        int idCapitali = this.addArgomento("Capitali", IDC);
        int idOceani = this.addArgomento("Oceani", IDC);
        
        this.addFlashCard("Capitale della Spagna?", "Madrid", idCapitali, IDC);
        this.addFlashCard("Capitale della Germania?", "Berlino", idCapitali, IDC);
        
        this.addFlashCard("Come si chiama il mare a Nord della Turchia", "Mar Morto", idOceani, IDC);
        this.addFlashCard("Quale oceano separa l'America dall'Europa", "Oceano Atlantico", idOceani, IDC);
        
        this.addFlashCard("Quante isole ha l'Italia", "Una dozzina", -1, IDC);
    
        System.out.println("DB Inizializzato");
        
        //Aggiunta admin
        //Salt : dxI236VjCyiAJsijmXDV+g==
        //Password : Password criptata : yYdoXtNqjYOAa8fYgE824Kc79j5F7p30MGjUXa+iaCc=
        this.addAdmin("admin", "dxI236VjCyiAJsijmXDV+g==", "yYdoXtNqjYOAa8fYgE824Kc79j5F7p30MGjUXa+iaCc=");
        
        
    }

    
    
}