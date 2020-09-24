/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

import java.util.HashMap;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonlukija;

/**
 *
 * @author nikoniem
 */
public class LempelZivWelch {

    private HashMap<String, Integer> sanakirja;
    private Integer indeksi;
    private String tuloste;

    public LempelZivWelch() {
        this.sanakirja = new HashMap<>();
        this.indeksi = 256;
        this.tuloste = "";
    }

    public boolean onkoSanakirjassa(String mj) {
        if (this.sanakirja.containsKey(mj)) {
            return true;
        } else {
            return false;
        }
    }

    public void lisaaSanakirjaan(String mj) {
        //System.out.println("Lisätään sanakirjaan '" + mj + "' paikkaan " + this.indeksi);
        this.sanakirja.put(mj, this.indeksi);
        this.indeksi++;
    }

    public int haeSanakirjasta(String mj) {
        return this.sanakirja.get(mj);
    }

    public int haeIndeksi() {
        return this.indeksi;
    }

    public void kirjoitaTulostetta(String bittijono) {
        this.tuloste = this.tuloste + bittijono;
    }

    public String haeTuloste() {
        return this.tuloste;
    }
    
    public String muunnaBittijonoksi(Integer merkkiArvo, int bittimaara) {
        return String.format("%" + bittimaara + "s", Integer.toBinaryString(merkkiArvo)).replace(' ', '0');
    }
    
    public void tallenna() {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste, "lz.dat");
    }
    
    public String lueTiedostosta(String tiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        return tl.lueBinaaritiedosto(tiedosto);
    }
    
    public void pakkaa(String merkkijono) {
        int nykyinen = merkkijono.charAt(0);
        String n = merkkijono.charAt(0) + "";
        for (int i = 0; i < merkkijono.length(); i++) {
            //String t = "";
            if (i == merkkijono.length() - 1) {
                System.out.println("output: " + n + " " + nykyinen);
                this.tuloste = this.tuloste + muunnaBittijonoksi(nykyinen, 9);
            } else {
                int seuraava = merkkijono.charAt(i + 1);
                String s = merkkijono.charAt(i + 1) + "";
                String yhdistetty = n + s;
                if (!this.sanakirja.containsKey(yhdistetty)) {
                    if (this.sanakirja.containsKey(n)) {
                        System.out.println("output: " + n + " " + this.sanakirja.get(n));
                        this.tuloste = this.tuloste + muunnaBittijonoksi(this.sanakirja.get(n), 9);
                    } else {
                        System.out.println("output: " + n + " " + nykyinen);
                        this.tuloste = this.tuloste + muunnaBittijonoksi(nykyinen, 9);
                    }
                    this.sanakirja.put(yhdistetty, this.indeksi);
                    this.indeksi++;
                    //t = String.format("%8s", Integer.toBinaryString(nykyinen)).replace(' ', '0');

                    n = s;
                    nykyinen = seuraava;
                } else {
                    n = yhdistetty;
                    //t = String.format("%9s", Integer.toBinaryString(this.indeksi)).replace(' ', '0');
                    nykyinen = this.indeksi;
                }

            }

        }
    }

    public static void main(String[] args) throws Exception {
        LempelZivWelch lz = new LempelZivWelch();
        String merkkijono = "thisisthe";
        
        lz.pakkaa(merkkijono);
        System.out.println(lz.haeTuloste());
        lz.tallenna();
        System.out.println("Luetaan tiedostosta...");
        System.out.println(lz.lueTiedostosta("lz.dat"));
        
//        int nykyinen = merkkijono.charAt(0);
//        String n = merkkijono.charAt(0) + "";
//        for (int i = 0; i < merkkijono.length(); i++) {
//            String t = "";
//            if (i == merkkijono.length() - 1) {
//                System.out.println("output: " + n + " " + nykyinen);
//                lz.kirjoitaTulostetta(lz.muunnaBittijonoksi(nykyinen, 9) + " ");
//                //lz.kirjoitaTulostetta(nykyinen + ";");
//            } else {
//                int seuraava = merkkijono.charAt(i + 1);
//                String s = merkkijono.charAt(i + 1) + "";
//                String tuloste = "" + merkkijono.charAt(i);
//                String yhdistetty = n + s;
//                if (!lz.onkoSanakirjassa(yhdistetty)) {
//                    if (lz.onkoSanakirjassa(n)) {
//                        System.out.println("output: " + n + " " + lz.haeSanakirjasta(n));
//                        lz.kirjoitaTulostetta(lz.muunnaBittijonoksi(lz.haeSanakirjasta(n), 9) + " ");
//                        //lz.kirjoitaTulostetta(lz.haeSanakirjasta(n) + ";");
//                    } else {
//                        System.out.println("output: " + n + " " + nykyinen);
//                        lz.kirjoitaTulostetta(lz.muunnaBittijonoksi(nykyinen, 9) + " ");
//                        //lz.kirjoitaTulostetta(nykyinen + ";");
//                    }
//                    lz.lisaaSanakirjaan(yhdistetty);
//                    t = String.format("%8s", Integer.toBinaryString(nykyinen)).replace(' ', '0');
//                    //System.out.println("n == " + n);
//                    //System.out.println("nykyinen == " + nykyinen);
//
//                    n = s;
//                    nykyinen = seuraava;
//                } else {
//                    n = yhdistetty;
//                    t = String.format("%9s", Integer.toBinaryString(lz.haeIndeksi())).replace(' ', '0');
//                    nykyinen = lz.haeIndeksi();
//                }
//
//            }
//
//            //lz.kirjoitaTulostetta(t);
//        }
//
//        System.out.println(lz.haeTuloste());

    }

}
