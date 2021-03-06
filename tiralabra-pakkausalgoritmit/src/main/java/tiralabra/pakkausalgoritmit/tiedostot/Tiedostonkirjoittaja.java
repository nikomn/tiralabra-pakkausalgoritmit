package tiralabra.pakkausalgoritmit.tiedostot;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.BitSet;

import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;
import tiralabra.pakkausalgoritmit.tietorakenteet.Hajautustaulu;

/**
 * Luokka sisältää tiedostojen kirjoittamiseen tarvittavat toiminnallisuudet.
 */
public class Tiedostonkirjoittaja {

    /**
     * Metodi kirjoittaa tekstimuotoisen datan tiedostoon.
     *
     * @param data, merkkijono
     * @param tiedostonNimi, merkkijono
     *
     * @return totuusarvo
     */
    public boolean kirjoitaTekstiTiedostoon(String data, String tiedostonNimi) {
        try {
            FileWriter tk = new FileWriter(tiedostonNimi);
            tk.write(data);
            tk.close();
        } catch (Exception e) {
            System.out.println("Odottamaton virhe " + e);
            return false;

        }
        return true;
    }

    /**
     * Metodi kirjoittaa binäärimuotoisen datan tiedostoon (LZW algoritmin
     * käytössä).
     *
     * @param data, merkkijono
     * @param tiedostonNimi, merkkijono
     *
     * @return totuusarvo
     */
    public boolean kirjoitaTiedostoon(String data, String tiedostonNimi) {

        /*
        Lisätään tiedoston alkuun ns. tasausbittejä, että tiedetään, mistä
        varsinainen data alkaa. 
        
        Syynä tälle siis se, että Huffman ja LZ pakkauksella
        dataa ei kirjata tavuina, eli 8 bitin sarjoissa. Käytännössä siis
        ei voida tietää kuinka suuri osa tiedostosta on varsinaista dataa, eikä
        sitä, mistä kohdasta varsinainen data alkaa.
        
        Tästä seuraa käytännössä, että kirjoitettavaa data voi olla 
        esim. 001. Tiedostot kirjoitetaan kuitenkin
        muistiin tavun kokoisissa paloissa, jolloin em. muuttuu muotoon
        00000001 kun se kirjoitetaan tiedostoon. Tiedostosta luettaessa ei 
        kuitenkaan voida mistään päätellä onko varsinainen data 00000001 vai
        0000001 vai 000001 vai 00001 ... jne.
        
        Tätä varten tässä toteutuksessa ehkä hieman epäintuitiivisesti data
        tasataan täyteen tavumäärään kirjoittamalla alkuun tarvittava määrä
        nollia ja yksi ykkönen. Tämän seurauksena tiedetään aina kun luetaan
        tiedostoa, että varsinainen data alkaa heti ensimmäisen ykkösbitin jälkeen.
        ts. data 001 kirjautuu tiedostoon muodossa 00001001 ja tästä siis tiedetään
        että tiedostoa luettaessa varsinainen data on 001.
        
        001 on vain toki vain esimerkki, varsinainen data voi olla mitä tahansa,
        mutta sama ongelma on joka tapauksessa käsillä: jos datassa on esim. 7165
        bittiä tarvitaan 895 + 1 tavua kun kirjoitetaan tiedostoon. Tällöin siis
        varsinainen data voisi sisältää 001..ja 7162 muuta bittiä, mutta tiedoston
        alkuun silti päätyy tiedostossa kaksi ylimääräistä nollabittiä, kun 
        tiedoston pituus tasaantuu täysiin tavuihin. Tällöin siis tiedoston alku
        on 000001 ja 7162 muuta bittiä ja taas on mahdotonta tietää luetusta
        tiedostosta onko varsinaista dataa 000001 ja 7162 muuta bittiä vai 
        00001 ja 7162 muuta bittiä vai 0001 ja 7162 muuta bittiä...
         */
        Double tasan = Math.ceil(data.length() / 8.0);
        Double tasausBitit = 8 * tasan - data.length();
        String tasaaja = "00000001";
        for (int i = 0; i < tasausBitit; i++) {
            tasaaja = "0" + tasaaja;
        }
        //String kirjoitettavaData = "10000000" + data;
        //String kirjoitettavaData = data;
        String kirjoitettavaData = tasaaja + data;
        int bittiMaara = kirjoitettavaData.length();
        int kasiteltavaMerkkiLkm = 0;
        double prosenttiKokkonaisuudesta = bittiMaara / 100;
        BitSet bitsetti = new BitSet(kirjoitettavaData.length());
        int kpl = 0;
        System.out.println("Vaihe 1. Muodostetaan bittitaulua...");
        for (Character c : kirjoitettavaData.toCharArray()) {
            kasiteltavaMerkkiLkm++;
            if (prosenttiKokkonaisuudesta > 0 && kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (bittiMaara * 1.0) * 100.0);
                System.out.println(prosentti + "%" + " käsitelty...");
            }
            if (c.equals('1')) {
                bitsetti.set(kpl);
            }
            kpl++;
        }

        System.out.println("Vaihe 2. Kirjoitetaan tiedostoon...");

        try {
            DataOutputStream tuloste = new DataOutputStream(new FileOutputStream(tiedostonNimi));

            for (byte b : bitsetti.toByteArray()) {

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
            return false;
        }

        System.out.println("Tiedoston kirjoitus valmis!");
        return true;
    }

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
    public void kirjoitaTiedosto(String aineisto, HuffmanSolmu puunjuuri, HuffmanSolmu[] puu, Integer pituus, Hajautustaulu<Character, HuffmanSolmu> merkkitaulu, String tiedostonnimi) throws Exception {
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

        for (int i = 0; i < taulunPituus; i++) {
            taulu = taulu + puu[i].muunnaBinaariEsitysmuotoon();

        }

        String kokoAineisto = taulunPituusBinaariformaatissa + skippiBitit
                + puunJuuriBinaariformaatissa + taulu + aineisto;

        System.out.println("Käännetään aineistoa...");
        int aineistonPituus = aineisto.length() + ylihypattavat;
        int kasiteltavana = 0;
        int prosenttiKokkonaisuudesta = aineistonPituus / 100;
        String kaanteinenAineisto = new StringBuilder(aineisto).reverse().toString();

        for (int i = 0; i < ylihypattavat; i++) {
            kasiteltavana++;
            if (prosenttiKokkonaisuudesta > 0 && kasiteltavana % prosenttiKokkonaisuudesta == 0) {
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

        BitSet bitsetti = new BitSet(kirjoitettavaData.length());
        int kpl = 0;
        for (Character c : kirjoitettavaData.toCharArray()) {

            if (c.equals('1')) {
                bitsetti.set(kpl);

            }
            kpl++;
        }

        System.out.println("Vaihe 2. Kirjoitettaan data tiedostoon...");

        try {
            DataOutputStream tuloste = new DataOutputStream(new FileOutputStream(tiedostonnimi));

            for (byte b : bitsetti.toByteArray()) {

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }

        System.out.println("Huffman-tiedoston kirjoitus valmis!");

    }

}
