package pakkausalgoritmit;

import static org.junit.Assert.*;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.tietorakenteet.Lista;

public class TestiLista {

    @Test
    public void listaanLisaysToimii() {
        Lista<String> l = new Lista();
        String s = "testi";
        l.lisaa(s);
        assertEquals("Listan koko ei kasva lisäyksen yhteydessä", 1, l.koko());
        assertTrue("Lisätty objekti ei löydy listasta", l.sisaltaa(s));
        assertEquals("Objektin hakeminen listasta ei toimi", s, l.arvo(0));
    }
    
    
    

}
