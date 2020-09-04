package tiralabra.pakkausalgoritmit;


import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Collections;


public class Hakupuu {

    HuffmanSolmu puunjuuri;

    public HuffmanSolmu etsi(int haettuToistuvuus) {
        HuffmanSolmu solmu = puunjuuri;
        while (solmu != null && solmu.toistuvuus != haettuToistuvuus) {
            if (haettuToistuvuus < solmu.toistuvuus) {
                solmu = solmu.haeVasen();
            } else {
                solmu = solmu.haeOikea();
            }
        }
        return solmu;
    }

    public HuffmanSolmu lisaa(Integer lisattavaToistuvuus) {
        if (puunjuuri == null) {
            puunjuuri = new HuffmanSolmu();
            puunjuuri.toistuvuus = lisattavaToistuvuus;
            puunjuuri.oikea = null;
            puunjuuri.vasen = null;
            puunjuuri.vanhempi = null;
        } else {
            HuffmanSolmu solmu = puunjuuri;
            HuffmanSolmu edellinenSolmu = solmu;
            while (solmu != null && solmu.toistuvuus != lisattavaToistuvuus) {
                edellinenSolmu = solmu;
                if (lisattavaToistuvuus < solmu.toistuvuus) {
                    solmu = solmu.haeVasen();
                } else {
                    solmu = solmu.haeOikea();
                }
            }
            if (solmu == null) {
                solmu = new HuffmanSolmu();
                solmu.toistuvuus = lisattavaToistuvuus;
                solmu.vanhempi = edellinenSolmu;
                solmu.vasen = null;
                solmu.oikea = null;
                if (lisattavaToistuvuus < edellinenSolmu.toistuvuus) {
                    edellinenSolmu.vasen = solmu;
                } else {
                    edellinenSolmu.oikea = solmu;
                }
                return solmu;
            } else {
                return null;
            }
        }
        return null;
    }
    
    public void tulosta(HuffmanSolmu solmu) {
        if (solmu != null) {
            tulosta(solmu.haeVasen());
            System.out.println(solmu.toistuvuus);
            tulosta(solmu.haeOikea());
        }
    }
    
    public int korkeus(HuffmanSolmu solmu) {
        if (solmu == null) {
            return -1;
        } else {
            return 1 + max(korkeus(solmu.vasen), korkeus(solmu.oikea));
        }
    }
    
    public static void main(String[] args) {
        Hakupuu p = new Hakupuu();
        ArrayList<Integer> testisyote = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            testisyote.add(i);
        }
        Collections.shuffle(testisyote);
        for (int i = 0; i < testisyote.size(); i++) {
            p.lisaa(testisyote.get(i));
        }
        
        //p.lisaa(2);
        //p.lisaa(1);
        //System.out.println(p.puunjuuri.toistuvuus);
        //System.out.println(p.puunjuuri.vasen.vanhempi.toistuvuus);
        //p.tulosta(p.puunjuuri);
        System.out.println("Puun korkeus: " + p.korkeus(p.puunjuuri));
        
        
    }

}
