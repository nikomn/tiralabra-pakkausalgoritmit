/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkausalgoritmit;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;
import tiralabra.pakkausalgoritmit.tietorakenteet.Hajautustaulu;

/**
 *
 * @author nikoniem
 */
public class TestiHajautustaulu {

    @Test
    public void hajautustauluunLisaysToimii() {
        Hajautustaulu<String, String> ht = new Hajautustaulu<>();
        ht.lisaa("a", "b");
        assertEquals("Hajautustaulun koko ei kasva lisäyksen yhteydessä", 1, ht.koko());
        assertEquals("Lisätty objekti ei löydy hajautustaulusta avaimella", "b", ht.hae("a"));
        assertTrue("Objektin sisältymisen tarkistus hajautustaulusta toimii", ht.sisaltaaAvaimen("a"));
    }
    
    @Test
    public void hajautustauluTestiHuffmanSolmulla() {
        HuffmanSolmu a = new HuffmanSolmu("a", 1, null, null);
        Hajautustaulu<String, HuffmanSolmu> ht = new Hajautustaulu<>();
        ht.lisaa("a", a);
        assertEquals("Lisätty HuffmanSolmu ei löydy hajautustaulusta avaimella", a, ht.hae("a"));
        
    }

}
