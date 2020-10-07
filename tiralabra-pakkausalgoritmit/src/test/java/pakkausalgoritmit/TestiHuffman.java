package pakkausalgoritmit;

import java.util.Arrays;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.menetelmat.Huffman;
import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonlukija;

/**
 * Huffman algoritmin keskeisten ominaisuuksien testaukset
 */
public class TestiHuffman {

//    @Test
//    public void testNothing() {
//        assertTrue(true);
//    }
    @Test
    public void koodauksenLopputulosOikein() {
        String syote = "aaaaabbbc";
        Huffman h = new Huffman();
        h.muodostaTaulu(syote);
        h.muodostaPuu();
        String tuloste = h.koodaa();
        //System.out.println("Tuloste: " + tuloste);
        // 1 1 1 1 1 01 01 01 00
        // a a a a a b  b  b  c
        String oletettuLopputulos = "1111101010100";

        assertEquals("Huffman koodaus tuottaa väärän lopputuloksen syötteellä 'aaaaabbc'!", oletettuLopputulos, tuloste);
    }

    @Test
    public void koodattuDataTulkitaanOikein() {
        String[] s = {"100000000000000000000000",
            "000000000000000000000101",
            "000000000000000000001011",
            "000011001001001111110001",
            "000000000000000000000000",
            "000000000000000000001001",
            "000010110001111010100101",
            "000011100011111001011001",
            "000000000000000000000000",
            "000011100011111001011001",
            "000000000000000001100001",
            "000000000000000000000101",
            "000000000000000000000000",
            "000000000000000000000000",
            "000011001001001111110001",
            "000011101110111110110011",
            "000000000000000001100010",
            "000000000000000000000011",
            "000000000000000000000000",
            "000000000000000000000000",
            "000010110001111010100101",
            "000000001100100011110001",
            "000000000000000000001010",
            "000000000000000000000001",
            "000000000000000000000000",
            "000000000000000000000000",
            "000010110001111010100101",
            "000010110001111010100101",
            "000000000000000000000000",
            "000000000000000000000100",
            "000000001100100011110001",
            "000011101110111110110011",
            "000011001001001111110001",
            "000011001001001111110001",
            "000000000000000000000000",
            "000000000000000000001001",
            "000010110001111010100101",
            "000011100011111001011001",
            "000000000000000000000000",
            "000000000001111101010100"};

        Huffman h = new Huffman();
        h.puraKoodattuTiedosto(s, "testidata/huffman_purku.txt");
        Tiedostonlukija tl = new Tiedostonlukija();
        String luettu = "";
        try {
            luettu = tl.lueTiedosto("testidata/huffman_purku.txt");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen ei onnistunut!");
        }

        assertEquals("Huffman koodatun datan tulkinta ei toimi", "aaaaabbb\n", luettu);
    }

    @Test
    public void luettujenSolmujenMuodostaminenToimiiOikein() {

        Huffman h = new Huffman();
        HuffmanSolmu s = h.muodostaSolmu("000000010011101000110001", "000000000000000001100001", "000000000000000000001001");
        HuffmanSolmu v = new HuffmanSolmu("a", 9, "000000010011101000110001", null, null);
        String sTeksti = s.toString();
        String vTeksti = v.toString();

        assertEquals("Tiedostosta luettujen HuffmanSolmujen muodostus toimii väärin", sTeksti, vTeksti);
    }

    @Test
    public void huffmanPakatunTiedostonTallennusToimii() {

        String syote = "aaaaabbb\n";
        Huffman h = new Huffman();
        h.muodostaTaulu(syote);
        h.muodostaPuu();
        String tuloste = h.koodaa();
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        Tiedostonlukija tl = new Tiedostonlukija();
        String[] tiedostostaHaettu = null;
        String luettu = "";
        try {
            tk.kirjoitaTiedosto(tuloste, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "testidata/huffman.dat");
            tiedostostaHaettu = tl.lueKoodattuTiedosto("testidata/huffman.dat");
            Huffman h2 = new Huffman();
            h2.puraKoodattuTiedosto(tiedostostaHaettu, "testidata/purettuhuffman.txt");
            luettu = tl.lueTiedosto("testidata/purettuhuffman.txt");
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen/lukeminen ei onnistunut!");
        }

        

        assertEquals("Huffman tiedostojen kirjoitus ei toimi", syote, luettu);
    }

}
