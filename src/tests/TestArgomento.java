//classe JUNIT per il test della classe Argomento
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Argomento;
public class TestArgomento {
    Argomento argomento;
    @Before
    public void setUp() throws Exception {
        argomento = new Argomento("argomento1",1);
    }

    @Test
    public void testGetTitolo() {
        assertEquals("argomento1", argomento.getTitolo());
    }

    @Test
    public void testSetTitolo() {
        argomento.setTitolo("argomento2");
        assertEquals("argomento2", argomento.getTitolo());
    }

    @Test
    public void testGetID() {
        assertEquals(1, argomento.getId());
    }

    @Test
    public void testSetID() {
        argomento.setId(2);
        assertEquals(2, argomento.getId());
    }

    @Test
    public void testEquals() {
        Argomento argomento2 = new Argomento("argomento1",1);
        assertTrue(argomento.equals(argomento2));
    }

    @Test
    public void testNotEquals() {
        Argomento argomento2 = new Argomento("argomento2",2);
        assertFalse(argomento.equals(argomento2));
    }

}

