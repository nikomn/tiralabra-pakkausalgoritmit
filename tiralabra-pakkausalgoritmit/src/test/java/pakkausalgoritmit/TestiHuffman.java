package pakkausalgoritmit;

import static org.junit.Assert.*;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.Huffman;

/**
 *
 * @author nikoniem
 */
public class TestiHuffman {

//    @Test
//    public void testNothing() {
//        assertTrue(true);
//    }

    @Test
    public void koodauksenLopputulosOikein() {
        String syote = "abcdabcd\n";
        Huffman h = new Huffman();
        h.muodostaTaulu(syote);
        h.muodostaPuu();
        String tuloste = h.koodaa();
        System.out.println("Tuloste: " + tuloste);
        String oletettuLopputulos = "110001011101001011101";
        assertEquals(tuloste + " != " + oletettuLopputulos, oletettuLopputulos, tuloste);
    }

}
