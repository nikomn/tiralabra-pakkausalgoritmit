package tiralabra.pakkausalgoritmit;

import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static HuffmanSolmu puunjuuri;
    static HashMap<Character, HuffmanSolmu> taulu;


    public static void muodostaTaulu(String mj) {
        for (int i = 0; i < mj.length(); i++) {
            char m = mj.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!taulu.containsKey(m)) {
                taulu.put(m, new HuffmanSolmu(m, 1, null, null));
            } else {
                taulu.get(m).toistuvuus++;
            }
        }

    }


    public static String etsiJuuri(HuffmanSolmu alku) {
        //System.out.println("Haku alkaa...");
        //System.out.println("Etsitään merkkiä " + haettavaMerkki);
        //System.out.println("Merkki toistuu aineistossa " + toistuvuus + " kertaa");
        HuffmanSolmu solmu = alku;

        String koodi = "";
        while (solmu != puunjuuri) {
            //System.out.println("Käsiteltävä solmu: " + solmu.merkki + "(" + solmu.toistuvuus + ")");
            //System.out.println("Koodi on nyt: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
            HuffmanSolmu vanhempi = solmu.vanhempi;
            if (vanhempi.vasen == solmu) {
                koodi = 0 + koodi;
            } else if (vanhempi.oikea == solmu) {
                koodi = 1 + koodi;
            }

            solmu = solmu.vanhempi;
//            if (solmu == puunjuuri) {
//                System.out.println("Juuri löydetty!");
//            }
        }
        //System.out.println("Koodi on: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
        return koodi;
    }

    public static String koodaaMerkkijono(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!taulu.containsKey(m)) {
                System.out.println("Virheellinen merkkojono!");
                return "VIRHE!";
            } else {
                HuffmanSolmu solmu = taulu.get(m);
                if (solmu == null) {
                    System.out.println("Tässä on nyt joku virhe...");
                }
                //System.out.println("Löydetty solmu: " + solmu.merkki);
                koodattu = etsiJuuri(solmu) + koodattu;
            }
        }
        return koodattu;
    }

    public static String muunnaBinaariksi(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            koodattu = Integer.toBinaryString(m) + koodattu;
        }

        return koodattu;
    }

    public static void main(String[] args) throws Exception {
        Scanner tlukija = new Scanner(new File("testi.txt"));
        String mjono = "";
        while (tlukija.hasNextLine()) {
            //System.out.println(tlukija.nextLine());
            String x = tlukija.nextLine();
            mjono = mjono + x + "\n";
        }
        tlukija.close();

        //System.out.println(Integer.toBinaryString('x'));
        //String mjono = "Huffman";
        String binaarimuoto = muunnaBinaariksi(mjono);

        taulu = new HashMap<>();
        muodostaTaulu(mjono);

        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
        //System.out.println("\nTaulu:");
        for (Character avain : taulu.keySet()) {
            //System.out.println(avain + ": " + taulu2.get(avain).toistuvuus);
            jono.add(taulu.get(avain));
        }

        int solmunSuuruus = 0;
        
        while (solmunSuuruus != mjono.length()) {
            HuffmanSolmu eka = jono.poll();
            if (eka.toistuvuus == mjono.length()) {
                puunjuuri = eka;
                break;
            }
            HuffmanSolmu toka = jono.poll();
            solmunSuuruus = eka.toistuvuus + toka.toistuvuus;
            HuffmanSolmu yhdistetty = new HuffmanSolmu(null, solmunSuuruus, eka, toka);
            if (yhdistetty.toistuvuus == mjono.length()) {
                puunjuuri = yhdistetty;
            }
            eka.vanhempi = yhdistetty;
            toka.vanhempi = yhdistetty;
            jono.add(yhdistetty);
        }


        String koodattu = koodaaMerkkijono(mjono);
        int alkuperainen = binaarimuoto.length();
        int pakattu = koodattu.length();
        double suhde = Math.round((pakattu * 1.0) / (alkuperainen * 1.0) * 100.0);
        //System.out.println("\n\n\nMerkkijono: " + mjono);
        //System.out.println("Binäärimuodossa: " + binaarimuoto);
        //System.out.println("Koodattuna: " + koodattu);
        System.out.println("");
        System.out.println("Koodaamattoman merkkijonon koko: " + alkuperainen + " bittiä");
        System.out.println("Koodatun merkkijonon koko: " + pakattu + " bittiä");

        System.out.println("\nPakattu on n." + suhde + "% alkuperäisestä.");


    }

}
