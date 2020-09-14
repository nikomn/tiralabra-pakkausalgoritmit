package tiralabra.pakkausalgoritmit.apuohjelmat;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import tiralabra.pakkausalgoritmit.HuffmanSolmu;

public class Tiedostonkirjoittaja {

    public void kirjoitaTiedosto(String aineisto, HuffmanSolmu puunjuuri, HashMap<Character, HuffmanSolmu> merkkitaulu, String tiedostonnimi) throws Exception {
        // Formaatti, ehkä...?
        //
        // taulunpituus(tavuina)|ylihypättävät(bitti lkm)|taulu|data
        //        
        // esim. 'ab'
        // 
        //
        int taulunPituus = merkkitaulu.size();
        String taulunPituusBinaariformaatissa = String.format("%24s", Integer.toBinaryString(taulunPituus)).replace(' ', '0') ;
        
        Double tasan = Math.ceil(aineisto.length() / 8.0);
        Double tavuMax = 8 * tasan - aineisto.length();
        int ylihypattavat = tavuMax.intValue();
        String skippiBitit = String.format("%24s", Integer.toBinaryString(ylihypattavat)).replace(' ', '0');
        //System.out.println("Skippibitit: " + skippiBitit);
        
        String puunJuuriBinaariformaatissa = puunjuuri.muunnaBinaariEsitysmuotoon();
       
        String taulu = "";
        for (Character avain : merkkitaulu.keySet()) {
            //System.out.println(avain + ": " + taulu2.get(avain).toistuvuus);
            taulu = merkkitaulu.get(avain).muunnaBinaariEsitysmuotoon();
        }
        
        String kokoAineisto = taulunPituusBinaariformaatissa + skippiBitit 
                + puunJuuriBinaariformaatissa + taulu + aineisto;
        
        System.out.println("Koko aineisto: " + kokoAineisto);
        
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

        String kaanteinenAineisto = "";

        for (int i = aineisto.length() - 1; i >= 0; i--) {
            kaanteinenAineisto = kaanteinenAineisto + aineisto.charAt(i);
        }
        
//        kaanteinenAineisto = taulunPituusBinaariformaatissa + skippiBitit 
//                + puunJuuriBinaariformaatissa + taulu + kaanteinenAineisto;
        
        kaanteinenAineisto = kaanteinenAineisto + taulu 
                + puunJuuriBinaariformaatissa + skippiBitit + taulunPituusBinaariformaatissa;
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
//        for (int i = skippiBitit.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + skippiBitit.charAt(i);
//        }
//        
//        for (int i = taulunPituusBinaariformaatissa.length() - 1; i >= 0; i--) {
//            kaanteinenAineisto = kaanteinenAineisto + taulunPituusBinaariformaatissa.charAt(i);
//        }
//        
        
        
        

        //System.out.println(kaanteinenAineisto);
        //kaanteinenAineisto = taulunPituusBinaariformaatissa + skippiBitit + puunJuuriBinaariformaatissa + taulu + kaanteinenAineisto;
        //kaanteinenAineisto = kaanteinenAineisto + taulu + puunJuuriBinaariformaatissa + skippiBitit + taulunPituusBinaariformaatissa;
        BitSet bitsetti = new BitSet(kaanteinenAineisto.length());
        int kpl = 0;
        for (Character c : kaanteinenAineisto.toCharArray()) {
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
                System.out.println("Kirjoitetaan: " + b);

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }

    }

}
