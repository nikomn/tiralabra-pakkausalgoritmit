package tiralabra.pakkausalgoritmit.apuohjelmat;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import tiralabra.pakkausalgoritmit.HuffmanSolmu;

/**
 * Luokka sisältää tiedostojen kirjoittamiseen tarvittavat toiminnallisuudet.
 */
public class Tiedostonkirjoittaja {

    /**
     * Paluuarvoton metodi kirjoittaa tiedostoonHuffman algoritmilla koodatun
     * tekstin binäärimuotoisena.
     *
     * @param aineisto Huffman algoritmilla koodattu merkkijono
     * @param puunjuuri Huffman puun juurisolmu
     * @param pituus Huffmanpuun solmujen määrä
     * @param puu Huffmanpuun solmuja sisältävä taulukko
     * @param merkkitaulu Merkkien toistuvuudet sisältävä taulu
     * @param tiedostonnimi kohdetiedoston nimi
     *
     */
    public void kirjoitaTiedosto(String aineisto, HuffmanSolmu puunjuuri, HuffmanSolmu[] puu, Integer pituus, HashMap<Character, HuffmanSolmu> merkkitaulu, String tiedostonnimi) throws Exception {
        System.out.println("Kirjoitetaan tiedostoon...");
        System.out.println("Vaihe 1. Muodostetaan kirjoitettavaa bittijonoa...");
        int taulunPituus = pituus;
        String taulunPituusBinaariformaatissa = String.format("%24s", Integer.toBinaryString(taulunPituus)).replace(' ', '0');

        Double tasan = Math.ceil(aineisto.length() / 24.0);
        Double tavuMax = 24 * tasan - aineisto.length();
        int ylihypattavat = tavuMax.intValue();
        String skippiBitit = String.format("%24s", Integer.toBinaryString(ylihypattavat)).replace(' ', '0');
        //System.out.println("Skippibitit: " + skippiBitit);

        String puunJuuriBinaariformaatissa = puunjuuri.muunnaBinaariEsitysmuotoon();

        String taulu = "";
//        for (Character avain : merkkitaulu.keySet()) {
//            //System.out.println(avain + ": " + taulu2.get(avain).toistuvuus);
//            taulu = taulu + merkkitaulu.get(avain).muunnaBinaariEsitysmuotoon();
//        }

        for (int i = 0; i < taulunPituus; i++) {
            taulu = taulu + puu[i].muunnaBinaariEsitysmuotoon();

        }

        String kokoAineisto = taulunPituusBinaariformaatissa + skippiBitit
                + puunJuuriBinaariformaatissa + taulu + aineisto;

        //System.out.println("Koko aineisto: " + kokoAineisto);
//        for (Map.Entry<Character, HuffmanSolmu> entry : merkkitaulu.entrySet()) {
//            properties.put(entry.getKey(), entry.getValue());
//        }
        //System.out.println("Kirjoitus...");
        //System.out.println("Alkuperäinen: " + aineisto);
//        Properties properties = new Properties();
//
//        for (Map.Entry<Character, HuffmanSolmu> entry : merkkitaulu.entrySet()) {
//            properties.put(entry.getKey(), entry.getValue());
//        }
//
//        properties.store(new FileOutputStream("merkkitaulu.dat"), null);
        
        System.out.println("Käännetään aineistoa...");
        int aineistonPituus = aineisto.length() + ylihypattavat;
        int kasiteltavana = 0;
        int prosenttiKokkonaisuudesta = aineistonPituus / 100;
        String kaanteinenAineisto = "";

        for (int i = aineisto.length() - 1; i >= 0; i--) {
            kasiteltavana++;
            if (kasiteltavana % prosenttiKokkonaisuudesta == 0) {
                double prosentti = Math.round((kasiteltavana * 1.0) / (aineistonPituus * 1.0) * 100.0);
                System.out.println(prosentti + "%" + " käsitelty...");
            }
            kaanteinenAineisto = kaanteinenAineisto + aineisto.charAt(i);
        }
        //System.out.println("Ylihypättäviä: " + ylihypattavat);
        for (int i = 0; i < ylihypattavat; i++) {
            kasiteltavana++;
            if (kasiteltavana % prosenttiKokkonaisuudesta == 0) {
                double prosentti = Math.round((kasiteltavana * 1.0) / (aineistonPituus * 1.0) * 100.0);
                System.out.println(prosentti + "%" + " käsitelty...");
            }
            kaanteinenAineisto = kaanteinenAineisto + '0';

        }

        String kaanteinenTaulunPituusBinaariformaatissa = "";
        for (int i = taulunPituusBinaariformaatissa.length() - 1; i >= 0; i--) {
            kaanteinenTaulunPituusBinaariformaatissa = kaanteinenTaulunPituusBinaariformaatissa + taulunPituusBinaariformaatissa.charAt(i);
        }

        //kaanteinenAineisto = kaanteinenAineisto + kaanteinenTaulunPituusBinaariformaatissa + "000000000000000000000001";
        //System.out.println("Kaanteinen aineisto: " + kaanteinenAineisto);
        String kaanteinenSkippiBitit = "";
        for (int i = skippiBitit.length() - 1; i >= 0; i--) {
            kaanteinenSkippiBitit = kaanteinenSkippiBitit + skippiBitit.charAt(i);
        }

        String kaanteinenPuunjuuri = "";
        for (int i = puunJuuriBinaariformaatissa.length() - 1; i >= 0; i--) {
            kaanteinenPuunjuuri = kaanteinenPuunjuuri + puunJuuriBinaariformaatissa.charAt(i);
        }

        String kaanteinenTaulu = "";
        for (int i = taulu.length() - 1; i >= 0; i--) {
            kaanteinenTaulu = kaanteinenTaulu + taulu.charAt(i);
        }
        
        /*
        Koska tiedosto kirjoitetaan tavu kerrallaan, eikä kirjoitettavaa dataa
        välttämättä ole täsmälleen n tavua, data täydentyy nollilla.
        Luettaessa ei ole mahdollista tietää kuinka monta nollaa tiedostossa on, 
        ennen varsinaista dataa. Tähän ratkaisuna datan alkuun lisätty kolmen 
        tavun mittainen "100000000000000000000000" ripsu, joka vastaa toteutuksessa
        käytettyä "data-lohkon" pituutta. Tämän avulla tiedostoa luettaessa
        tiedetään, että varsinainen data alkaa kun tullaan ensimmäisen 1 kohdalle
        ja siitä voidaan skipata yksi lohko eteenpäin. Käytännössä siis tiedosto 
        alkaa aina tuolla 1:llä.
         */

        String kirjoitettavaData = kaanteinenAineisto + kaanteinenTaulu
                + kaanteinenPuunjuuri + kaanteinenSkippiBitit
                + kaanteinenTaulunPituusBinaariformaatissa + "000000000000000000000001";

//        String kirjoitettavaData = "100000000000000000000000" 
//                + taulunPituusBinaariformaatissa + skippiBitit 
//                + puunJuuriBinaariformaatissa + taulu + aineisto;
        //System.out.println("Taulu: " + taulu);
//        
//        for (int i = taulu.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + taulu.charAt(i);
//        }
//        for (int i = taulu.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + taulu.charAt(i);
//        }
//        
//        for (int i = puunJuuriBinaariformaatissa.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + puunJuuriBinaariformaatissa.charAt(i);
//        }
//        
//        
//        for (int i = taulunPituusBinaariformaatissa.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + taulunPituusBinaariformaatissa.charAt(i);
//        }
//        
        //System.out.println(kirjoitettavaData);
        //kaanteinenAineisto = taulunPituusBinaariformaatissa + skippiBitit + puunJuuriBinaariformaatissa + taulu + kaanteinenAineisto;
        //kaanteinenAineisto = kaanteinenAineisto + taulu + puunJuuriBinaariformaatissa + skippiBitit + taulunPituusBinaariformaatissa;
        int bittiMaara = kirjoitettavaData.length();
        int kasiteltavaMerkkiLkm = 0;
        prosenttiKokkonaisuudesta = bittiMaara / 100;
        BitSet bitsetti = new BitSet(kirjoitettavaData.length());
        int kpl = 0;
        for (Character c : kirjoitettavaData.toCharArray()) {
            kasiteltavaMerkkiLkm++;
            //System.out.println("Koodi on nyt " + koodattu);
            if (kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (bittiMaara * 1.0) * 100.0);
                System.out.println(prosentti + "%" + " käsitelty...");
            }
            //System.out.println("c: " + c);
            if (c.equals('1')) {
                bitsetti.set(kpl);
                //System.out.println("kpl: " + kpl);
                //System.out.println(bitsetti.get(kpl));
            }
            kpl++;
        }

        System.out.println("Vaihe 2. Kirjoitettaan data tiedostoon...");

        try {
            DataOutputStream tuloste = new DataOutputStream(new FileOutputStream(tiedostonnimi));
            //System.out.println(merkkitaulu.toString());
            //tuloste.writeBytes(merkkitaulu.toString());
            kasiteltavaMerkkiLkm = 0;
            for (byte b : bitsetti.toByteArray()) {
                kasiteltavaMerkkiLkm++;
                //System.out.println("Koodi on nyt " + koodattu);
                if (kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                    double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (bittiMaara * 1.0) * 100.0);
                    System.out.println(prosentti + "%" + " käsitelty...");
                }
                //System.out.println("Kirjoitetaan: " + b);

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }

        System.out.println("Huffman-tiedoston kirjoitus valmis!");

    }

    /**
     * Paluuarvoton metodi kirjoittaa tiedostoonHuffman algoritmilla koodatun
     * tekstin binäärimuotoisena.
     *
     * @param aineisto Huffman algoritmilla koodattu merkkijono
     * @param avainTaulu Huffman koodatun datan tulkintaan tarvittava taulu
     * @param pituus Huffmanpuun solmujen määrä
     * @param tiedostonnimi kohdetiedoston nimi
     *
     *
     */
    public void kirjoitaTiedosto2(String aineisto, String avainTaulu, Integer pituus, String tiedostonnimi) throws Exception {
        int taulunPituus = pituus;
        String taulunPituusBinaariformaatissa = String.format("%24s", Integer.toBinaryString(taulunPituus)).replace(' ', '0');

        String kaanteinenAineisto = "";

        for (int i = aineisto.length() - 1; i >= 0; i--) {
            kaanteinenAineisto = kaanteinenAineisto + aineisto.charAt(i);
        }

        String kaanteinenTaulunPituusBinaariformaatissa = "";
        for (int i = taulunPituusBinaariformaatissa.length() - 1; i >= 0; i--) {
            kaanteinenTaulunPituusBinaariformaatissa = kaanteinenTaulunPituusBinaariformaatissa + taulunPituusBinaariformaatissa.charAt(i);
        }

        String kaanteinenTaulu = "";
        for (int i = avainTaulu.length() - 1; i >= 0; i--) {
            kaanteinenTaulu = kaanteinenTaulu + avainTaulu.charAt(i);
        }

        /*
        Koska tiedosto kirjoitetaan tavu kerrallaan, eikä kirjoitettavaa dataa
        välttämättä ole täsmälleen n tavua, data täydentyy nollilla.
        Luettaessa ei ole mahdollista tietää kuinka monta nollaa tiedostossa on, 
        ennen varsinaista dataa. Tähän ratkaisuna datan alkuun lisätty yhden 
        tavun mittainen "10000000" ripsu. Tämän avulla tiedostoa luettaessa
        tiedetään, että varsinainen data alkaa kun tullaan ensimmäisen 1 kohdalle
        ja siitä voidaan skipata yksi tavu eteenpäin.
         */
        String kirjoitettavaData = kaanteinenAineisto + kaanteinenTaulu
                + kaanteinenTaulunPituusBinaariformaatissa + "00000001";

        System.out.println("Kirjoitetaan data:");
        System.out.println(kirjoitettavaData);

        BitSet bitsetti = new BitSet(kirjoitettavaData.length());
        int kpl = 0;
        for (Character c : kirjoitettavaData.toCharArray()) {
            //System.out.println("c: " + c);
            if (c.equals('1')) {
                bitsetti.set(kpl);
                //System.out.println("kpl: " + kpl);
                //System.out.println(bitsetti.get(kpl));
            }
            kpl++;
        }

        try {
            DataOutputStream tuloste = new DataOutputStream(new FileOutputStream(tiedostonnimi));
            //System.out.println(merkkitaulu.toString());
            //tuloste.writeBytes(merkkitaulu.toString());
            for (byte b : bitsetti.toByteArray()) {
                //System.out.println("Kirjoitetaan: " + b);

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }

    }

}
