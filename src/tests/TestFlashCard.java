package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Argomento;
import model.FlashCard;
public class TestFlashCard {

    FlashCard flashCard;
    @Before
    public void setUp() throws Exception {
        flashCard = new FlashCard("domanda", "risposta", 1);
    }

    @Test
    public void testGetDomanda() {
        assertEquals("domanda", flashCard.getDomanda());
    }

    @Test
    public void testSetDomanda() {
        flashCard.setDomanda("domanda2");
        assertEquals("domanda2", flashCard.getDomanda());
    }

    @Test
    public void testGetRisposta() {
        assertEquals("risposta", flashCard.getRisposta());
    }

    @Test
    public void testSetRisposta() {
        flashCard.setRisposta("risposta2");
        assertEquals("risposta2", flashCard.getRisposta());
    }

    @Test
    public void testGetID() {
        assertEquals(1, flashCard.getId());
    }

    @Test
    public void testSetID() {
        flashCard.setId(2);
        assertEquals(2, flashCard.getId());
    }

    @Test
    public void testArgomento() {
        assertNull(flashCard.getArgomento());
        flashCard.setArgomento(new Argomento("argomento1", 1));
        assertEquals("argomento1", flashCard.getArgomento().getTitolo());
    }



}