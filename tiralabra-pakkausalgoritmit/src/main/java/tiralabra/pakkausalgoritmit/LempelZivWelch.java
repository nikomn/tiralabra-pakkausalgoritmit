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
    private HashMap<Integer, String> purkutaulu;
    private Integer indeksi;
    private String tuloste;
    private Integer palojenKoko;

    public LempelZivWelch(Integer palojenKoko) {
        this.sanakirja = new HashMap<>();
        this.indeksi = 256;
        this.tuloste = "";
        if (palojenKoko < 9) {
            this.palojenKoko = 12;
        } else {
            this.palojenKoko = palojenKoko;
        }
        
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
                //System.out.println("output: " + n + " " + nykyinen);
                this.tuloste = this.tuloste + muunnaBittijonoksi(nykyinen, this.palojenKoko);
            } else {
                int seuraava = merkkijono.charAt(i + 1);
                String s = merkkijono.charAt(i + 1) + "";
                String yhdistetty = n + s;
                if (!this.sanakirja.containsKey(yhdistetty)) {
                    if (this.sanakirja.containsKey(n)) {
                        //System.out.println("output: " + n + " " + this.sanakirja.get(n));
                        this.tuloste = this.tuloste + muunnaBittijonoksi(this.sanakirja.get(n), this.palojenKoko);
                    } else {
                        //System.out.println("output: " + n + " " + nykyinen);
                        this.tuloste = this.tuloste + muunnaBittijonoksi(nykyinen, this.palojenKoko);
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

    public String pura(String data) {
        this.purkutaulu = new HashMap<>();
        this.indeksi = 256;
        this.tuloste = "";
        String purettuMerkkijono = "";
        boolean dataAlkanut = false;
        int osoitin = data.length() - 1;
        if (data.charAt(osoitin) == '1') {
            dataAlkanut = true;
        }
        while (!dataAlkanut) {
            osoitin--;
            if (!dataAlkanut) {
                if (data.charAt(osoitin) == '1') {
                    dataAlkanut = true;
                }
            }
        }
        osoitin--;

//        int merkkienMaara = (osoitin / 9) + 1;
//        Integer[] taulukko = new Integer[merkkienMaara];
//        int tauluIndeksi = 0;
        String pala = "";
        Integer edellinen = -1;
        int nykyinen = -1;
        int seuraava = -1;
        Integer tulostettava = -1;
        Integer kierros = -1;
        boolean paritonMaara = true;
        if (osoitin % this.palojenKoko == 0) {
            paritonMaara = false;
        }
        
        //System.out.println("Merkkejä on datassa pariton määrä: " + paritonMaara);
        String vikamerkki = "";
        for (int i = osoitin; i >= 0; i--) {
            pala = pala + data.charAt(i);
            
            if (i == 0 && paritonMaara) {
                
                //System.out.println("pala lopussa: " + pala);
                int sanakirjaViitaus = Integer.parseInt(pala, 2);
                //System.out.println("VIka sanakirjaviittaus: " + sanakirjaViitaus);
                if (sanakirjaViitaus > 255) {
                            vikamerkki = this.purkutaulu.get(sanakirjaViitaus);
                        } else {
                            vikamerkki = "" + (char) sanakirjaViitaus;
                        }
                //System.out.println("Merkki lopussa: " + vikamerkki);
                
                
            }
            
            if (pala.length() == this.palojenKoko) {
                kierros++;
                //System.out.println("pala: " + pala);
                int sanakirjaViitaus = Integer.parseInt(pala, 2);
                //System.out.println(sanakirjaViitaus);

                pala = "";
                if (kierros == 0) {
                    nykyinen = sanakirjaViitaus;
                }
                if (kierros == 1) {
                    seuraava = sanakirjaViitaus;
                    //System.out.println("Nykyinen: \tSeuraava");
                    //System.out.println(nykyinen + "\t" + seuraava);
                }
                if (kierros > 1) {
                    nykyinen = seuraava;
                    seuraava = sanakirjaViitaus;
                    //System.out.println("Nykyinen: \tSeuraava");
                    //System.out.println(nykyinen + "\t" + seuraava);

                }
                if (kierros > 0) {
                    if (nykyinen < 256 && seuraava < 256) {
                        Character nykyinenMerkki = (char) nykyinen;
                        //System.out.println("nykyinenMerkki: " + nykyinenMerkki);
                        Character seuraavaMerkki = (char) seuraava;
                        //System.out.println("seuraavaMerkki: " + seuraavaMerkki);
                        String uusi = nykyinenMerkki + "" + seuraavaMerkki;
                        //System.out.println("uusi: " + uusi);
                        //System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
                        this.purkutaulu.put(this.indeksi, uusi);
                        this.indeksi++;
                        this.tuloste = this.tuloste + nykyinenMerkki;
                    } else {
                        String uusi = "";
                        if (nykyinen > 255) {
                            uusi = uusi + this.purkutaulu.get(nykyinen);
                        } else {
                            uusi = uusi + "" + (char) nykyinen;
                        }
                        this.tuloste = this.tuloste + uusi;

                        if (seuraava > 255) {
                            uusi = uusi + this.purkutaulu.get(seuraava);
                        } else {
                            uusi = uusi + "" + (char) seuraava;
                        }

                        //System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
                        this.purkutaulu.put(this.indeksi, uusi);
                        this.indeksi++;
                    }

                }

                //kierros++;
            }

        }
        
        this.tuloste = this.tuloste + vikamerkki;

        //System.out.println("Lopputulos: " + this.tuloste);

        return this.tuloste;
    }

    public static void main(String[] args) throws Exception {
        LempelZivWelch lz = new LempelZivWelch(12);
        //String sisalto = "Äät ja ööt?";     // Random bugi: "..." -> ".null"???
        Tiedostonlukija tlukija = new Tiedostonlukija();
        String sisalto = tlukija.lueTiedosto("testi.txt");

        //System.out.println("Luettiin: " + sisalto);
        System.out.println("Tiedoston sisältö luettu!");

        lz.pakkaa(sisalto);
        System.out.println(lz.haeTuloste());
        lz.tallenna();
        System.out.println("Luetaan tiedostosta...");
        //System.out.println(lz.lueTiedostosta("lz.dat"));
        String d = lz.lueTiedostosta("lz.dat");
        System.out.println(lz.pura(d));

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
