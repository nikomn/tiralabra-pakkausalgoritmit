/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

/**
 *
 * @author nikoniem
 */
public class HuffmanSolmu implements Comparable {
    Character merkki;
    Integer toistuvuus;
    HuffmanSolmu vasen;
    HuffmanSolmu oikea;
    HuffmanSolmu vanhempi;
    
    public HuffmanSolmu() {
        
    }
    
    public HuffmanSolmu(Character merkki, Integer toistuvuus, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        this.merkki = merkki;
        this.toistuvuus = toistuvuus;
        this.vasen = vasen;
        this.oikea = oikea;
    }
    
    public Integer haeToistuvuus() {
        return this.toistuvuus;
    }
    
    public HuffmanSolmu haeVasen() {
        return this.vasen;
    }
    
    public HuffmanSolmu haeOikea() {
        return this.oikea;
    }
    
    @Override
    public String toString() {
        return this.merkki + ": " + this.toistuvuus;
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
