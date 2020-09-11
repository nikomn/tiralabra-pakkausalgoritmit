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
    
    public String muunnaBinaariEsitysmuotoon() {
        // String.format("%16s", Integer.toBinaryString(1)).replace(' ', '0')
        String m = String.format("%24s", Integer.toBinaryString(this.merkki)).replace(' ', '0');
        //System.out.println("m: " + m);
        String t = String.format("%24s", Integer.toBinaryString(this.toistuvuus)).replace(' ', '0') ;
        //System.out.println("t: " + t);
        String v = String.format("%24s", Integer.toBinaryString(this.vasen.merkki)).replace(' ', '0');
        //System.out.println("v: " + v);
        String o = String.format("%24s", Integer.toBinaryString(this.oikea.merkki)).replace(' ', '0');
        //System.out.println("o: " + o);
        String V = String.format("%24s", Integer.toBinaryString(this.vanhempi.merkki)).replace(' ', '0');
        //System.out.println("V: " + V);
        
        String binaariMerkkijono = m + t + v + o + V;
        
//        String binaariMerkkijono = String.format("%24s", Integer.toBinaryString(this.merkki)).replace(' ', '0')
//                + String.format("%24s", Integer.toBinaryString(this.toistuvuus)).replace(' ', '0') 
//                + String.format("%24s", Integer.toBinaryString(this.vasen.merkki)).replace(' ', '0') 
//                + String.format("%24s", Integer.toBinaryString(this.oikea.merkki)).replace(' ', '0') 
//                + String.format("%24s", Integer.toBinaryString(this.vanhempi.merkki)).replace(' ', '0');
        
        //System.out.println(Integer.toBinaryString('x'));
        //System.out.println(Integer.toBinaryString(10000000));
        return binaariMerkkijono;
    }
    
    @Override
    public String toString() {
        return this.merkki + ";" + this.toistuvuus + ";" + vasen.merkki + ";" + oikea.merkki + ";" + vanhempi.merkki;
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
