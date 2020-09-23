package tiralabra.pakkausalgoritmit.apuohjelmat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

/**
 * Luokka sisältää tiedostojen lukemiseen tarvittavat toiminnot.
 */
public class Tiedostonlukija {

    /**
     * Metodi lukee tavallisen tekstitiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     *
     * @return merkkijono
     */
    public String lueTiedosto(String tiedosto) throws Exception {
        String mjono = "";
        try {
            Scanner tlukija = new Scanner(new File(tiedosto));
            System.out.println("Luetaan tiedostoa...");
            while (tlukija.hasNextLine()) {
                //System.out.println(tlukija.nextLine());
                String x = tlukija.nextLine();
                mjono = mjono + x + "\n";
            }
            tlukija.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "Tiedostoa ei voitu lukea!";
        }

        return mjono;
    }

    /**
     * Metodi erottelee Huffman algoritmilla pakatusta tiedostosta luetut bitit
     * määrämittaisiin kenttiin.
     *
     * @param merkkijono nollista ja ykkösistä koostuva merkkijono
     *
     * @return nollista ja ykkösistä koostuvia merkkijonoja sisältävä taulukko
     */
    public String[] erotteleKentat(String merkkijono) {
        int lohkojenMaara = merkkijono.length() / 24;
        //System.out.println("Lohkot: " + lohkojenMaara);
        String[] dataLohkot = new String[lohkojenMaara];

        String x = "";
        int indeksi = 0;
        for (int i = 0; i < merkkijono.length(); i++) {
            x = x + merkkijono.charAt(i);
            if (x.length() == 24) {
                //System.out.println("x: " + x);
                dataLohkot[indeksi] = x;
                x = "";
                indeksi++;
            }
        }

        //System.out.println(Arrays.toString(dataLohkot));
        String[] kentat = new String[5];

        int tauluPituus = Integer.parseInt(dataLohkot[1], 2);
        int skippiBitit = Integer.parseInt(dataLohkot[2], 2);

//        merkkijono = merkkijono.substring(24, merkkijono.length());
//        
//        String taulunPituus = merkkijono.substring(0, 24);
//        System.out.println("Taulunpituus: " + taulunPituus);
//        int taulunpituusNumerona = Integer.parseInt(taulunPituus, 2);
//        kentat[0] = taulunPituus;
//
//        merkkijono = merkkijono.substring(24, merkkijono.length());
//        String ylihypattavat = merkkijono.substring(0, 24);
//        kentat[1] = ylihypattavat;
//
//        merkkijono = merkkijono.substring(24, merkkijono.length());
//        String puunjuuri = merkkijono.substring(0, 120);
//        kentat[2] = puunjuuri;
//
//        merkkijono = merkkijono.substring(120, merkkijono.length());
//        System.out.println("Taulunpituus: " + taulunpituusNumerona);
//        String taulu = merkkijono.substring(0, taulunpituusNumerona);
//        kentat[3] = taulu;
//
//        merkkijono = merkkijono.substring(120 + taulunpituusNumerona, merkkijono.length() - 1);
//        String data = merkkijono;
//        kentat[4] = data;
        return dataLohkot;
    }

    /**
     * Metodi lukee Huffman algoritmilla koodatun tiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     *
     * @return nollista ja ykkösistä koostuvia merkkijonoja sisältävä taulukko
     */
    public String[] lueKoodattuTiedosto(String tiedosto) throws Exception {
        String mjono = "";
        try {
            byte[] bitit = Files.readAllBytes(Paths.get(tiedosto));
            //System.out.println(Arrays.toString(bitit));
            for (int i = 0; i < bitit.length; i++) {
                //System.out.println("bitti: " + bitit[i]);
                //System.out.println(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                mjono = String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0') + mjono;
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "-1";
        }

        String[] kentat = erotteleKentat(mjono);

        return kentat;
        //return mjono;
    }

    /**
     * Metodi lukee Huffman algoritmilla koodatun tiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     *
     * @return nollista ja ykkösistä koostuva merkkijono
     */
    public String lueKoodattuTiedosto2(String tiedosto) throws Exception {
        String mjono = "";
        try {
            byte[] bitit = Files.readAllBytes(Paths.get(tiedosto));
            //System.out.println(Arrays.toString(bitit));
            for (int i = 0; i < bitit.length; i++) {
                //System.out.println("bitti: " + bitit[i]);
                //System.out.println(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                mjono = String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0') + mjono;
                

            }
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "-1";
        }

        //String[] kentat = erotteleKentat(mjono);
        return mjono;
        //return mjono;
    }

}
