/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkausalgoritmit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;

/**
 * Huffman solmujen ominaisuuksia testaava luokka 
 */
public class TestiHuffmanSolmu {
    @Test
    public void huffmanSolmunMuodostusToimii() {
        HuffmanSolmu a = new HuffmanSolmu("a", 0, null, null);
        int solmunToistuvuus = a.haeToistuvuus();
        assertEquals("Solmua ei voi muodostaa", 0, solmunToistuvuus);
    }
    
}
