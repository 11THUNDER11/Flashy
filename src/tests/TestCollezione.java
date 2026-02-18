package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class TestCollezione {
    Collezione collezione;
    Argomento argomento;
    FlashCard flashCard;
    @Before
    public void setUp() throws Exception {
        collezione = new Collezione("collezione1", 1);
        argomento = new Argomento("argomento1", 1);
        flashCard = new FlashCard("domanda", "risposta", 1);
    }

    @Test
    public void testGetNome() {
        assertEquals("collezione1", collezione.getNome());
    }

    @Test
    public void testSetNome() {
        collezione.setNome("collezione2");
        assertEquals("collezione2", collezione.getNome());
    }

    @Test
    public void testGetID() {
        assertEquals(1, collezione.getId());
    }

    @Test
    public void testSetID() {
        collezione.setId(2);
        assertEquals(2, collezione.getId());
    }

    @Test
    public void testAggiungiFlashCard() {
        assertEquals(0, collezione.getLunghezza());
        collezione.aggiungiFlashCard(flashCard);
        assertEquals(1, collezione.getLunghezza());
    }

    @Test
    public void testRimuoviFlashCard() {
        collezione.aggiungiFlashCard(flashCard);
        assertEquals(1, collezione.getLunghezza());
        collezione.rimuoviFlashCard(flashCard);
        assertEquals(0, collezione.getLunghezza());
    }

    @Test
    public void testAggiungiArgomento() {
        assertEquals(0, collezione.getArgomenti().size());
        collezione.aggiungiArgomento(argomento);
        assertEquals(1, collezione.getArgomenti().size());
    }

    @Test
    public void testRimuoviArgomento() {
        collezione.aggiungiArgomento(argomento);
        assertEquals(1, collezione.getArgomenti().size());
        collezione.rimuoviArgomento(argomento);
        assertEquals(0, collezione.getArgomenti().size());
    }

    @Test
    public void testGetCarteConArgomento() {
        FlashCard flashCard2 = new FlashCard("domanda2", "risposta2", 2);
        FlashCard flashCard3 = new FlashCard("domanda3", "risposta3", 3);

        flashCard.setArgomento(argomento);
        flashCard2.setArgomento(argomento);
        flashCard3.setArgomento(new Argomento("argomento2", 2));

        collezione.aggiungiFlashCard(flashCard);
        collezione.aggiungiFlashCard(flashCard2);
        collezione.aggiungiFlashCard(flashCard3);

        List<FlashCard> res = collezione.getCarteConArgomento(argomento);

        assertEquals(2, res.size());

        assertTrue(res.contains(flashCard));
        assertTrue(res.contains(flashCard2));
        assertFalse(res.contains(flashCard3));

    }
}