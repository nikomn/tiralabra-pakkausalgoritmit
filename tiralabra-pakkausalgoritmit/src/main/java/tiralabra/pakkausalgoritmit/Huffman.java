package tiralabra.pakkausalgoritmit;

import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;


public class Huffman {
    private HuffmanSolmu puunjuuri;
    private HashMap<Character, HuffmanSolmu> taulu;
    private String sisalto;
    
    
    public void muodostaTaulu(String mj) {
        this.taulu = new HashMap<>();
        this.sisalto = mj;
        System.out.println("Muodostetaan merkkitaulua merkkijonolle " + this.sisalto + "...");
        for (int i = 0; i < this.sisalto.length(); i++) {
            char m = this.sisalto.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!this.taulu.containsKey(m)) {
                this.taulu.put(m, new HuffmanSolmu(m, 1, null, null));
            } else {
                this.taulu.get(m).toistuvuus++;
            }
        }
        System.out.println("Merkkitaulu muodostettu!");

    }
    
    public String etsiJuuri(HuffmanSolmu alku) {
        //System.out.println("Haku alkaa...");
        //System.out.println("Etsitään merkkiä " + haettavaMerkki);
        //System.out.println("Merkki toistuu aineistossa " + toistuvuus + " kertaa");
        HuffmanSolmu solmu = alku;

        String koodi = "";
        while (solmu != this.puunjuuri) {
            //System.out.println("Käsiteltävä solmu: " + solmu.merkki + "(" + solmu.toistuvuus + ")");
            //System.out.println("Koodi on nyt: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
            HuffmanSolmu vanhempi = solmu.vanhempi;
            if (vanhempi.vasen == solmu) {
                koodi = 0 + koodi;
            } else if (vanhempi.oikea == solmu) {
                koodi = 1 + koodi;
            }

            solmu = solmu.vanhempi;
//            if (solmu == this.puunjuuri) {
//                System.out.println("Juuri löydetty!");
//            }
        }
        //System.out.println("Koodi on: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
        return koodi;
    }
    
    public String koodaa() {
        System.out.println("Koodataan tekstiä...");
        String koodattu = "";
        for (int i = 0; i < this.sisalto.length(); i++) {
            //System.out.println("Koodi on nyt " + koodattu);
            char m = this.sisalto.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!this.taulu.containsKey(m)) {
                System.out.println("Virheellinen merkkojono!");
                return "VIRHE!";
            } else {
                HuffmanSolmu solmu = this.taulu.get(m);
                if (solmu == null) {
                    System.out.println("Tässä on nyt joku virhe...");
                }
                //System.out.println("Löydetty solmu: " + solmu.merkki);
                koodattu = etsiJuuri(solmu) + koodattu;
            }
        }
        System.out.println("Koodaus valmis!");
        //System.out.println("Koodi on nyt " + koodattu);
        return koodattu;
    }
    
    public void muodostaPuu() {
        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
        //System.out.println("\nTaulu:");
        for (Character avain : this.taulu.keySet()) {
            //System.out.println(avain + ": " + taulu2.get(avain).toistuvuus);
            jono.add(this.taulu.get(avain));
        }

        int solmunSuuruus = 0;
        
        while (solmunSuuruus != this.sisalto.length()) {
            HuffmanSolmu eka = jono.poll();
            if (eka.toistuvuus == this.sisalto.length()) {
                this.puunjuuri = eka;
                break;
            }
            HuffmanSolmu toka = jono.poll();
            solmunSuuruus = eka.toistuvuus + toka.toistuvuus;
            HuffmanSolmu yhdistetty = new HuffmanSolmu(null, solmunSuuruus, eka, toka);
            if (yhdistetty.toistuvuus == this.sisalto.length()) {
                this.puunjuuri = yhdistetty;
            }
            eka.vanhempi = yhdistetty;
            toka.vanhempi = yhdistetty;
            jono.add(yhdistetty);
        }
    }
    
    public HashMap<Character, HuffmanSolmu> getTaulu() {
        return this.taulu;
    }
    
}
