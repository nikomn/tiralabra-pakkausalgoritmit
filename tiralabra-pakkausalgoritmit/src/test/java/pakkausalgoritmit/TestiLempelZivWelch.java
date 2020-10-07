package pakkausalgoritmit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.menetelmat.LempelZivWelch;

public class TestiLempelZivWelch {

    @Test
    public void koodauksenLopputulosOikeinYhdeksanBitinPaloissa() {
        String syote = "aaaaabbbc";
        LempelZivWelch lz = new LempelZivWelch(9);
        lz.pakkaa(syote);
        String tuloste = lz.haeTuloste();
        //System.out.println("Tuloste: " + tuloste);

        String oletettuLopputulos = "000000000001100001100000000001100001100000001001100010000000000001100010100000011001100011";
        assertEquals("LZW pakkaus tuottaa väärän lopputuloksen syötteellä 'aaaaabbbc', kun palankoko on 9", oletettuLopputulos, tuloste);
    }

    @Test
    public void koodauksenLopputulosOikeinKahdeksantoistaBitinPaloissa() {
        String syote = "aaaaabbbc";
        LempelZivWelch lz = new LempelZivWelch(18);
        lz.pakkaa(syote);
        String tuloste = lz.haeTuloste();
        //System.out.println("Tuloste: " + tuloste);

        String oletettuLopputulos = "000000000000000000000000000001100001000000000100000000000000000001100001000000000100000001000000000001100010000000000000000000000000000001100010000000000100000011000000000001100011";
        assertEquals("LZW pakkaus tuottaa väärän lopputuloksen syötteellä 'aaaaabbbc', kun palankoko on 18", oletettuLopputulos, tuloste);
    }

    @Test
    public void bittimuotoOikein() {
        LempelZivWelch lz = new LempelZivWelch(18);
        String tulos = lz.muunnaBittijonoksi(97, 18);
        assertEquals("LZW muuntaa merkit virheelliseen bittimuotoon", "000000000001100001", tulos);

    }
    
    @Test
    public void PurettuDataTulkitaanOikein() {
        String syote = "1100011001100000010100011000000000000100011001000000011000011000000000011000011000000000001";
        LempelZivWelch lz = new LempelZivWelch(9);
        String tuloste = lz.pura(syote, "testidata/lz_pakkaus.txt");
        //System.out.println("Tuloste: " + tuloste);

        String oletettuLopputulos = "aaaaabbbc";
        assertEquals("LZW koodattu data tulkitaan väärin, kun palankoko on 9", oletettuLopputulos, tuloste);
    }
    
    @Test
    public void DatanKirjoitusToimii() {
        String syote = "aaaaabbbc\n";
        LempelZivWelch lz = new LempelZivWelch(18);
        lz.pakkaa(syote);
        lz.tallenna("testidata/lzpakkaus.lzdat");
        String luettu = "";
        try {
            luettu = lz.lueTiedostosta("testidata/lzpakkaus.lzdat");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen ei onnistunut!");
        }
        
        String purettuMerkkijono = lz.pura(luettu, "testidata/lz_purettu.txt");
        
        
        assertEquals("LZW koodattujen tiedostojen luominen onnistuu", syote, purettuMerkkijono);
        
        
        
    }
    

}
