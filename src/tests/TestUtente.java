package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class TestUtente {
    Utente utente;
    @Before
    public void setUp() throws Exception {
        utente = new Utente("utente1");
    }

    @Test
    public void testGetUsername() {
        assertEquals("utente1", utente.getUsername());
    }

    @Test
    public void testSetUsername() {
        utente.setUsername("utente2");
        assertEquals("utente2", utente.getUsername());
    }

    @Test
    public void testGetCollezioni() {
        assertEquals(0, utente.getCollezioni().size());
    }

    @Test
    public void testAggiungiCollezione() {
        assertEquals(0, utente.getCollezioni().size());
        utente.aggiungiCollezione(new Collezione("collezione1", 1));
        assertEquals(1, utente.getCollezioni().size());
    }

    @Test
    public void testRimuoviCollezione() {
        Collezione collezione = new Collezione("collezione1", 1);
        utente.aggiungiCollezione(collezione);
        assertEquals(1, utente.getCollezioni().size());
        utente.rimuoviCollezione(collezione);
        assertEquals(0, utente.getCollezioni().size());
    }

    

    @Test
    public void testSetCollezioni() {
        List<Collezione> collezioni = new ArrayList<Collezione>();
        Collezione collezione = new Collezione("collezione1", 1);
        collezioni.add(collezione);
        utente.setCollezioni(collezioni);
        assertEquals(1, utente.getCollezioni().size());
        assertEquals(collezione, utente.getCollezioni().get(0));
    }
}