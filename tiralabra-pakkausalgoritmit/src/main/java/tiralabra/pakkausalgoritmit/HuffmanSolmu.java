/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

import java.util.Objects;

/**
 *
 * @author nikoniem
 */
public class HuffmanSolmu implements Comparable {
    String merkki;
    Integer toistuvuus;
    HuffmanSolmu vasen;
    HuffmanSolmu oikea;
    HuffmanSolmu vanhempi;
    String tunnisteBinaarina;
    //Long tunniste;
    
    public HuffmanSolmu(String merkki, Integer toistuvuus, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        this.toistuvuus = toistuvuus;
        this.vasen = vasen;
        this.oikea = oikea;
        Long tunniste = System.nanoTime() % 999999;
        //System.out.println("Tunniste: " + tunniste);
        this.tunnisteBinaarina = String.format("%24s", Long.toBinaryString(tunniste)).replace(' ', '0');
        if (merkki == null) {
            this.merkki = this.tunnisteBinaarina;
        } else {
            this.merkki = merkki;
        }
        //System.out.println("Luotu uusi: " + this.tunnisteBinaarina);
    }
    
    public HuffmanSolmu(String merkki, Integer toistuvuus, String tunnisteId, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        this.merkki = merkki;
        this.toistuvuus = toistuvuus;
        this.vasen = vasen;
        this.oikea = oikea;
        this.tunnisteBinaarina = tunnisteId;
        //System.out.println("Luotu uusi: " + this.tunnisteBinaarina);
    }
    
    public Integer haeToistuvuus() {
        return this.toistuvuus;
    }
    
    public String haeTunniste() {
        return this.tunnisteBinaarina;
    }
    
    public HuffmanSolmu haeVasen() {
        return this.vasen;
    }
    
    public HuffmanSolmu haeOikea() {
        return this.oikea;
    }
    
    public String muunnaBinaariEsitysmuotoon() {
        //System.out.println("Merkki: " + this.merkki);
        // String.format("%16s", Integer.toBinaryString(1)).replace(' ', '0')
        String id = this.tunnisteBinaarina;
        //System.out.println("id: " + id);
        
        //String m = String.format("%24s", Integer.toBinaryString('-')).replace(' ', '0'); 
        String m = "000000000000000000000000";
        if (this.merkki != null && this.merkki.length() == 1) {
            m = String.format("%24s", Integer.toBinaryString(this.merkki.charAt(0))).replace(' ', '0'); 
        }
        
        //System.out.println("m: " + m);
        String t = String.format("%24s", Integer.toBinaryString(0)).replace(' ', '0'); ; 
        if (this.toistuvuus != null) {
            t = String.format("%24s", Integer.toBinaryString(this.toistuvuus)).replace(' ', '0'); ;
        }
        
        //System.out.println("t: " + t);
        String v = "000000000000000000000000"; 
        if (this.vasen != null) {
            v = this.vasen.tunnisteBinaarina;
        }
        //System.out.println("v: " + v);
        String o = "000000000000000000000000"; 
        if (this.oikea != null) {
            o = this.oikea.tunnisteBinaarina;
        }
        //System.out.println("o: " + o);
        
        String V = "000000000000000000000000"; 
        if (this.vanhempi != null) {
            V = this.vanhempi.tunnisteBinaarina;
        }
        //System.out.println("V: " + V);
        
        String binaariMerkkijono = id + m + t + v + o + V;
        
        //System.out.println("binaariMerkkijono: " + binaariMerkkijono);
        

        return binaariMerkkijono;
    }
    
    @Override
    public String toString() {
        String tuloste = "";
        String m = this.merkki;
//        Integer t = this.toistuvuus;
//        Character v = vasen.merkki;
//        Character o = oikea.merkki;
//        Character V = vanhempi.merkki;
        return m + "(" + this.toistuvuus + ")";
        //return this.merkki + ";" + this.toistuvuus + ";" + vasen.merkki + ";" + oikea.merkki + ";" + vanhempi.merkki;
    }

    @Override
    public int compareTo(Object verrattava) {
        try {
            HuffmanSolmu verrokki = (HuffmanSolmu) verrattava;
            return this.toistuvuus.compareTo(verrokki.toistuvuus);
        } catch (Exception e) {
            return -1;
        }
    }
    

    
}
