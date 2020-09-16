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
    private HuffmanSolmu[] solmut;
    private int indeksi;

    public void puraKoodattuTiedosto(String[] tiedostonSisalto) {
        String[] testi = new String[8];
        testi[0] = "100000000000000000000000";  // headeri
        testi[1] = "000000000000000000000001";  // taulunpituus
        testi[2] = "000000000000000000000011";  // skippibitit

        testi[3] = "000000000000000001100001";  // merkki
        testi[4] = "000000000000000000000010";  // toistuvuus
        testi[5] = "000000000000000000101101";  // vasen
        testi[5] = "000000000000000000101101";  // oikea
        testi[6] = "000000000000000000101101";  // vanhempi
        int tauluPituus = Integer.parseInt(tiedostonSisalto[1], 2);
        int skippiBitit = Integer.parseInt(tiedostonSisalto[2], 2);
        System.out.println("taulun pituus: " + tauluPituus);
        HuffmanSolmu[] solmulista = new HuffmanSolmu[tauluPituus];
        for (int i = 7; i < (tauluPituus * 6) + 7; i = i + 6) {
            System.out.println("i: " + i);
            int merkkiNumero = Integer.parseInt(tiedostonSisalto[i + 1], 2);
            Character merkki = (char) merkkiNumero;
            System.out.println("Merkki: " + merkki);
            int toistuvuus = Integer.parseInt(tiedostonSisalto[i + 2], 2);
            // Character merkki, Integer toistuvuus, HuffmanSolmu vasen, HuffmanSolmu oikea
            HuffmanSolmu hs = new HuffmanSolmu(merkki, toistuvuus, tiedostonSisalto[i], null, null);
            System.out.println(hs.muunnaBinaariEsitysmuotoon());

        }
    }

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
        //System.out.println("Taulun pituus: " + this.taulu.size());
        System.out.println("Merkkitaulu muodostettu!");

    }

    public int haeTaulunKoko() {
        return this.taulu.size();
    }

    public HuffmanSolmu haePuunjuuri() {
        return this.puunjuuri;
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
        this.solmut = new HuffmanSolmu[99999];
        this.indeksi = 0;
        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
        //System.out.println("\nTaulu:");
        for (Character avain : this.taulu.keySet()) {
            //System.out.println(avain + ": " + this.taulu.get(avain).toistuvuus);
            jono.add(this.taulu.get(avain));
            this.solmut[this.indeksi] = this.taulu.get(avain);
            this.indeksi++;
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
            this.solmut[this.indeksi] = yhdistetty;
            this.indeksi++;
        }
    }

    public HashMap<Character, HuffmanSolmu> getTaulu() {
        return this.taulu;
    }

}
