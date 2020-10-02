/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit.menetelmat;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonlukija;

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
    private String tuloste2;

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

    public String haeTuloste2() {
        return this.tuloste2;
    }

    public String muunnaBittijonoksi(Integer merkkiArvo, int bittimaara) {
        return String.format("%" + bittimaara + "s", Integer.toBinaryString(merkkiArvo)).replace(' ', '0');
    }

    public void tallenna() {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste, "lz.dat");
    }

    public void tallenna2(String tiedostonNimi) {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste2, tiedostonNimi);
    }

    public String lueTiedostosta(String tiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        return tl.lueBinaaritiedosto(tiedosto);
    }

    public void pakkaa2(String merkkijono) {
        Scanner lukija = new Scanner(System.in);    // old-school debugger...
        HashMap<String, Integer> sk = new HashMap<>();
        String koodi = "";
        int demoIndeksi = 256;
        int nykyinen = merkkijono.charAt(0);
        String n = merkkijono.charAt(0) + "";
        //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
        koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);
//        this.sanakirja.put(n, this.indeksi);
//        this.indeksi++;
        sk.put(n, demoIndeksi);
        demoIndeksi++;
        int i = 1;
        while (i < merkkijono.length()) {
            nykyinen = merkkijono.charAt(i);
            n = merkkijono.charAt(i) + "";
            i++;
            while (sk.containsKey(n) && i < merkkijono.length()) {
                nykyinen = merkkijono.charAt(i);
                n = n + merkkijono.charAt(i);
                i++;
            }
            sk.put(n, demoIndeksi);
            //System.out.println("Lisätään sanakirjan indeksiin " + demoIndeksi + " " + n);
            demoIndeksi++;
            //System.out.println("n: " + n);
            if (n.length() > 1) {
                String lahetettava = n.substring(n.length() - 1, n.length());
                String sanakirjaosa = n.substring(0, n.length() - 1);
                Integer koodiosa = sk.get(sanakirjaosa);
                Integer l = (int) lahetettava.charAt(0);
                
                //System.out.println("Lähetetään " + koodiosa + "" + lahetettava + "(" + l + ")");
//                if (l > 511) {
//                    System.out.println("Tarvitaan enemmän kuin 9 bittiä!");
//                    System.out.println("Enter jatkaa...");
//                    String tauko = lukija.nextLine();
//                }
                koodi = koodi + muunnaBittijonoksi(koodiosa, this.palojenKoko) + muunnaBittijonoksi(l, this.palojenKoko);
            } else {

                //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
//                if (nykyinen > 511) {
//                    System.out.println("Tarvitaan enemmän kuin 15 bittiä!");
//                    System.out.println("Enter jatkaa...");
//                    String tauko = lukija.nextLine();
//                }
                koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);
            }

        }

        //System.out.println("Lopputulos:");
        //System.out.println(koodi);
        this.tuloste2 = koodi;

    }

    public String pura2(String data, String purettuNimi) {
        HashMap<Integer, String> sk = new HashMap<>();
        int sanakirjaIndeksi = 256;
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
        String pala1 = "";
        String pala2 = "";
        int[] osat = new int[2];
        int uusiOsa = 0;
        int laskuri = 0;
        boolean viestiAlkanut = false;
        for (int i = osoitin; i >= 0; i--) {

            pala1 = pala1 + data.charAt(i);
            //System.out.println("pala: " + pala1);

            if (pala1.length() == this.palojenKoko) {
                int sv = Integer.parseInt(pala1, 2);
                osat[laskuri] = sv;

                //System.out.println("sv: " + sv);
                pala2 = pala2 + ":" + sv;
                pala1 = "";
                laskuri++;
                //System.out.println("Nyt luettu: " + pala2);
                if (laskuri == 2) {
                    //System.out.println("Luettiin: " + pala2);
                    //System.out.println(osat[0] + " & " + osat[1]);
                    pala2 = "";
                    laskuri = 0;
                    if (osat[0] == 0) {
                        Character m = (char) osat[1];
                        //System.out.println("Vastaanottettiin merkki: " + m);
                        purettuMerkkijono = purettuMerkkijono + m;
                        //System.out.print(m);
                        sk.put(sanakirjaIndeksi, m + "");
                        sanakirjaIndeksi++;
                    } else {
                        String sanakirjaViittaus = sk.get(osat[0]);
                        Character m = (char) osat[1];
                        //System.out.println("Vastaanottettiin merkkijono: " + sanakirjaViittaus + m);
                        purettuMerkkijono = purettuMerkkijono + sanakirjaViittaus + m;
                        //System.out.print(sanakirjaViittaus + m);
                        sk.put(sanakirjaIndeksi, sanakirjaViittaus + m);
                        sanakirjaIndeksi++;

                    }
                    if (!viestiAlkanut && osat[0] == 0) {
                        viestiAlkanut = true;
                    }
                }

            }
        }

        //System.out.println("Purettu merkkijono: " + purettuMerkkijono);
        System.out.println("");
        Tiedostonkirjoittaja f = new Tiedostonkirjoittaja();
        f.kirjoitaTekstiTiedostoon(purettuMerkkijono, purettuNimi);
        return purettuMerkkijono;
    }

    public void pakkaa(String merkkijono) {
        int nykyinen = merkkijono.charAt(0);
        String n = merkkijono.charAt(0) + "";
        System.out.println("### Pakkaus ###");
        System.out.println("Nykyinen\tSeuraava\tOutput\t\tMuistiin");
        for (int i = 0; i < merkkijono.length(); i++) {
            //String t = "";

            if (i == merkkijono.length() - 1) {
                //System.out.println("output: " + n + " " + nykyinen);
                String siivottu = nykyinen + "\t\t" + "-" + "\t\t" + n + "(" + nykyinen + ")" + "\t\t" + "-";
                siivottu = siivottu.replaceAll("\n", "/N");
                //System.out.println(nykyinen + "\t\t" + seuraava + "\t\t" + n + "\t\t" + yhdistetty);
                System.out.println(siivottu);

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
                    String siivottu = nykyinen + "\t\t" + seuraava + "\t\t" + n + "(" + nykyinen + ")" + "\t\t" + this.indeksi + ": " + yhdistetty;
                    siivottu = siivottu.replaceAll("\n", "/N");
                    //System.out.println(nykyinen + "\t\t" + seuraava + "\t\t" + n + "\t\t" + yhdistetty);
                    System.out.println(siivottu);
                    //System.out.println("Lisätään uusi " + yhdistetty + " indeksiin " + this.indeksi);
                    this.sanakirja.put(yhdistetty, this.indeksi);
//                    if (this.indeksi == 268) {
//                        System.out.println("Indeksiin 268 lisätään: " + yhdistetty);
//                    }
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
        //System.out.println("Sanakirja:");
//        this.sanakirja.entrySet().forEach(entry -> {
//            System.out.println(entry.getValue() + " " + entry.getKey());
//        });
    }

    public String pura(String data) {
        String dataStringTesti = "";
        HashMap<Integer, String> debugausTaulu = new HashMap<>();
        this.sanakirja = new HashMap<>();
        Scanner lukija = new Scanner(System.in);    // old-school debugger...
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
        int edellinen = -1;
        int nykyinen = -1;
        int seuraava = -1;
        Integer tulostettava = -1;
        Integer kierros = -1;
        String[] edelliset = new String[999999];
        boolean paritonMaara = true;
        if (osoitin % this.palojenKoko == 0) {
            paritonMaara = false;
        }

        //System.out.println("Merkkejä on datassa pariton määrä: " + paritonMaara);
        String vikamerkki = "";
        String dbm = "";
        System.out.println("### Purkaminen ###");
        System.out.println("Nykyinen\tSeuraava\tOutput\t\tMuistiin");
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
                //System.out.println("Kierros: " + kierros);

                // Debug...
                //System.out.println("Nykyinen datapala: " + pala);
                int sv = Integer.parseInt(pala, 2);
                //System.out.println("Dataviittaus: " + sv);

                if (sv > 255) {
                    dbm = this.purkutaulu.get(sv);
                } else {
                    dbm = "" + (char) sv;
                }
                //System.out.println("Datasta luettu merkki: " + dbm);
                //System.out.println("Data: " + dbm + " " + sv);
                dataStringTesti = dataStringTesti + dbm;

                //System.out.println("Enter jatkaa...");
                //String tauko = lukija.nextLine();
                // Debug end...
                kierros++;
                //System.out.println("pala: " + pala);
                int sanakirjaViitaus = Integer.parseInt(pala, 2);
                //System.out.println("nykyinen sanakirjaviittaus: " + sanakirjaViitaus);

                pala = "";
                if (kierros == 0) {
                    edellinen = -1;
                    nykyinen = sanakirjaViitaus;
                    seuraava = -1;
                }
                if (kierros == 1) {
                    edellinen = -1;
                    seuraava = sanakirjaViitaus;
                    //System.out.println("Nykyinen: \tSeuraava");
                    //System.out.println(nykyinen + "\t" + seuraava);
                }
                if (kierros > 1) {
                    edellinen = nykyinen;
                    nykyinen = seuraava;
                    seuraava = sanakirjaViitaus;
                    //System.out.println("Nykyinen: \tSeuraava");
                    //System.out.println(nykyinen + "\t" + seuraava);

                }
                if (kierros > 0) {
                    //System.out.println("Edellinen: " + edellinen);

                    String edellinenMerkki = "";
                    if (edellinen < 256 && edellinen != -1) {
                        edellinenMerkki = "" + (char) edellinen;
                    } else {
                        edellinenMerkki = this.purkutaulu.get(edellinen);
                    }

                    String nykyinenMerkki = "";
                    if (nykyinen < 256) {
                        nykyinenMerkki = "" + (char) nykyinen;
                    } else {
                        nykyinenMerkki = this.purkutaulu.get(nykyinen);
                    }

                    //System.out.println("nykyinenMerkki: " + nykyinenMerkki);
                    String seuraavaMerkki = "";
                    if (seuraava < 256) {
                        seuraavaMerkki = "" + (char) seuraava;
                    } else {
                        seuraavaMerkki = this.purkutaulu.get(seuraava);
                    }

                    //String outputti = "";
                    //System.out.println("Nykyinen: " + nykyinen);
                    //System.out.println("Seuraava: " + seuraava);
//                    if (nykyinen > 255) {
//                        outputti = debugausTaulu.get(nykyinen);
//                    } else {
//                        outputti = nykyinen + "";
//                    }
                    String op = "";
                    if (nykyinen > 255) {
                        op = this.purkutaulu.get(nykyinen);
                    } else {
                        op = (char) nykyinen + "";
                    }

                    //System.out.println(nykyinen + "(" + (char) nykyinen + ")" + "\t\t" + seuraava + "\t\t" + outputti);
                    String ts = "";
                    String w1 = "merkin " + nykyinen;
                    String w2 = "merkin " + seuraava;
                    if (nykyinen > 255) {
                        w1 = "mitä tahansa merkkejä on muistipaikassa " + nykyinen;
                        ts = this.purkutaulu.get(nykyinen);
                    } else {
                        ts = (char) nykyinen + "";
                    }
                    if (seuraava > 255) {
                        w2 = "mitä tahansa merkkejä on muistipaikassa " + seuraava;
                        ts = ts + this.purkutaulu.get(seuraava);
                    } else {
                        ts = ts + (char) seuraava;
                    }

                    if (!this.sanakirja.containsKey(ts)) {
                        this.sanakirja.put(ts, this.indeksi);
                        this.purkutaulu.put(this.indeksi, ts);
                        this.indeksi++;
                    }

                    String siivottuOp = op.replaceAll("\n", "/N");
                    String siivottuTs = ts.replaceAll("\n", "/N");
                    String q = this.indeksi - 1 + ": " + w1 + " ja " + w2 + " yhdistelmä";
                    System.out.println(nykyinen + "\t\t" + seuraava + "\t\t" + siivottuOp + "\t\t" + q + " (ts. " + siivottuTs + ")");

                    //System.out.println("seuraavaMerkki: " + seuraavaMerkki);
//                    String uusi = nykyinenMerkki + "" + seuraavaMerkki;
//                    if (seuraava > 255) {
//                        //System.out.println("Käsitellään tapaus, jossa seuraava viittaa muistiin:");
//                        uusi = nykyinenMerkki + seuraavaMerkki;
//                        //if (edellinen > 255) System.out.println("Edellinen viittaa muistiin myös...");
//
//                    }
                    //System.out.println("uusi: " + uusi);
//                    if (!this.sanakirja.containsKey(uusi)) {
////                        System.out.println("Muistiviittaukset:");
////                        if (nykyinen > 255) {
////                            System.out.println("Nykyinen viittaa muistiin: ");
////                        }
////                        if (seuraava > 255) {
////                            System.out.println("Seuraava viittaa muistiin: ");
////                        }
////                        if (edellinen > 255) {
////                            System.out.println("Edellinen viittaa muistiin: ");
////                        }
//
//                        //System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
//                        this.sanakirja.put(uusi, this.indeksi);
//                        this.purkutaulu.put(this.indeksi, uusi);
////                        String o = "";
////                        Integer numba = nykyinen;
////                        while (this.purkutaulu.containsKey(numba)) {
////                            System.out.println(this.purkutaulu.get(numba));
////                            numba = this.sanakirja.get(this.purkutaulu.get(numba));
////                        }
//                        String o = "" + nykyinen;
//                        Integer nnn = nykyinen;
////                        while (nnn > 255) {
////                            String muististaS = this.purkutaulu.get(nnn);
////                            Integer muististaN = this.sanakirja.get(muististaS);
////                            nnn = this.sanakirja.get(this.purkutaulu.get(nnn));
////                            System.out.println("nnn: " + muististaS + " " + muististaN);
////                            o = o + "" + nnn;
////                        }
//                        //System.out.println("o: " + o);
//                        debugausTaulu.put(this.indeksi, nykyinen + " " + seuraava);
////                        if (this.indeksi == 262) {
////                            System.out.println("Saatiin aikaan '" + uusi + "'");
////                            System.out.println("Piti olla 'ak'");
////                            System.out.println("Tässä jokin menee pieleen!? Mikä???");
////                            System.out.println("Nykyinen: " + nykyinen);
////                            System.out.println("Seuraava: " + seuraava);
////                            System.out.println("Edellinen: " + edellinen);
////                            System.out.println("Nykyinen merkki: " + nykyinenMerkki);
////                            System.out.println("Seuraava merkki: " + seuraavaMerkki);
////                            System.out.println("Edellinen merkki: " + edellinenMerkki);
////                            System.out.println("Uusi: " + uusi);
////                            System.out.println("Indeksi: " + this.indeksi);
////                            System.out.println("Kierros: " + kierros);
////                            String etsintaTesti = nykyinenMerkki + edellinenMerkki;
//////                            boolean loydettySanakirjasta = false;
//////                            while (!loydettySanakirjasta) {
//////                                
//////                            }
////
////                            System.out.println("Enter jatkaa...");
////                            String tauko = lukija.nextLine();
////
////                        }
//                        this.indeksi++;
//                    }
                    this.tuloste = this.tuloste + op;

//                    if (nykyinen < 256 && seuraava < 256) {
//                        Character nykyinenMerkki = (char) nykyinen;
//                        //System.out.println("nykyinenMerkki: " + nykyinenMerkki);
//                        Character seuraavaMerkki = (char) seuraava;
//                        //System.out.println("seuraavaMerkki: " + seuraavaMerkki);
//
//                        String uusi = nykyinenMerkki + "" + seuraavaMerkki;
//                        //System.out.println("uusi: " + uusi);
////                        if (this.indeksi == 268) {
////                            System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
////                        }
//                        if (!this.sanakirja.containsKey(uusi)) {
//                            System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
//                            this.sanakirja.put(uusi, this.indeksi);
//                            this.purkutaulu.put(this.indeksi, uusi);
//                            this.indeksi++;
//                        }
//
//                        this.tuloste = this.tuloste + nykyinenMerkki;
//                        //System.out.println("Output: " + nykyinenMerkki);
//
//                    }
//                    if (nykyinen > 255 && seuraava < 256) {
//                        
//                    }
//                    if (nykyinen < 256 && seuraava > 255) {
//                        String nykyinenString = "";
//                        String seuraavaString = "";
//                        String edellinenString = "";
//                        String uusi = "";
//                        if (nykyinen > 255) {
//                            nykyinenString = this.purkutaulu.get(nykyinen);
//                        } else {
//                            nykyinenString = "" + (char) nykyinen;
//                        }
//                        this.tuloste = this.tuloste + nykyinenString;
//                        //System.out.println("Jompi kumpi yli 255 kohdassa: " + uusi);
//
//                        //System.out.println("this.tuloste: " + this.tuloste);
////                        if (seuraava > 255) {
////                            seuraavaString = this.purkutaulu.get(seuraava);
////                        } else {
////                            seuraavaString = "" + (char) seuraava;
////                        }
//                        if (edellinen > 255) {
//                            edellinenString = this.purkutaulu.get(edellinen);
//                        } else {
//                            edellinenString = "" + (char) edellinen;
//                        }
//
////                        if (this.indeksi == 268) {
////                            System.out.println("Nykyinen: " + nykyinen);
////                            System.out.println("Seuraava: " + seuraava);
////                            System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
////                        }
//                        uusi = nykyinenString + edellinenString;
//                        if (!this.sanakirja.containsKey(uusi)) {
//                            //System.out.println("nykyinenString: " + nykyinenString);
//                            //System.out.println("seuraavaString: " + seuraavaString);
//                            //System.out.println("edellinenString: " + edellinenString);
//                            System.out.println("Lisätään uusi(x1): " + uusi + " indeksiin " + this.indeksi);
//                            this.sanakirja.put(uusi, this.indeksi);
//                            this.purkutaulu.put(this.indeksi, uusi);
//                            this.indeksi++;
//                        }
//
//                    } else {
//
//                        String nykyinenString = "";
//                        String seuraavaString = "";
//                        String edellinenString = "";
//                        String uusi = "";
//                        if (nykyinen > 255) {
//                            nykyinenString = this.purkutaulu.get(nykyinen);
//                        } else {
//                            nykyinenString = "" + (char) nykyinen;
//                        }
//                        this.tuloste = this.tuloste + nykyinenString;
//                        //System.out.println("Jompi kumpi yli 255 kohdassa: " + uusi);
//
//                        //System.out.println("this.tuloste: " + this.tuloste);
//                        if (seuraava > 255) {
//                            seuraavaString = this.purkutaulu.get(seuraava);
//                        } else {
//                            seuraavaString = "" + (char) seuraava;
//                        }
//                        if (edellinen > 255) {
//                            edellinenString = this.purkutaulu.get(edellinen);
//                        } else {
//                            edellinenString = "" + (char) edellinen;
//                        }
//
////                        if (this.indeksi == 268) {
////                            System.out.println("Nykyinen: " + nykyinen);
////                            System.out.println("Seuraava: " + seuraava);
////                            System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
////                        }
//                        uusi = nykyinenString + seuraavaString;
//                        if (!this.sanakirja.containsKey(uusi)) {
//                            //System.out.println("nykyinenString: " + nykyinenString);
//                            //System.out.println("seuraavaString: " + seuraavaString);
//                            //System.out.println("edellinenString: " + edellinenString);
//                            System.out.println("Lisätään uusi(x2): " + uusi + " indeksiin " + this.indeksi);
//                            this.sanakirja.put(uusi, this.indeksi);
//                            this.purkutaulu.put(this.indeksi, uusi);
//                            this.indeksi++;
//                        }
//
////                        if (this.indeksi == 268) {
////                            System.out.println("Lisätään uusi: " + uusi + " indeksiin " + this.indeksi);
////                        }
//                    }
                }

                //kierros++;
            }

        }

        this.tuloste = this.tuloste + vikamerkki;
        //System.out.println("DataStringTesti:");
        //System.out.println(dataStringTesti);

//        HashMap<String, Integer> debugtaulu = new HashMap<>();
//        for (Map.Entry<Integer, String> entry : this.purkutaulu.entrySet()) {
//            //System.out.println(entry.getKey() + " : " + entry.getValue());
//            debugtaulu.put(entry.getValue(), entry.getKey());
//        }
//        System.out.println("Purkutaulu:");
//        this.purkutaulu.entrySet().forEach(entry -> {
//            System.out.println(entry.getValue() + " " + entry.getKey());
//        });
        //System.out.println("Lopputulos: " + this.tuloste);
        return this.tuloste;
    }

    public static void main(String[] args) throws Exception {
        LempelZivWelch lz = new LempelZivWelch(18);
        //String sisalto = "Äät ja ööt?";     // Random bugi: "..." -> ".null"???
        Tiedostonlukija tlukija = new Tiedostonlukija();
        String sisalto = tlukija.lueTiedosto("testi.txt");

        //System.out.println("Luettiin: " + sisalto);
        System.out.println("Tiedoston sisältö luettu!");
        //lz.pakkaa2("nollat ja ykköset on kivoja...");
        //lz.pura2("100000100100000001100000100010000001010000100100000001010000100000000001100000100000000000100000000");
        //lz.pura2("110011100000100001100001100000000000001011100000000000000001000000000000011101100100000001000001000010000001001101100000000000101011100000000000111101100000000000010101100000000000100000000");
        lz.pakkaa2(sisalto);
        lz.tallenna2("lz.dat");

        //lz.pakkaa(sisalto);
        //System.out.println(lz.haeTuloste());
        //lz.tallenna();
        System.out.println("Luetaan tiedostosta...");
        //System.out.println(lz.lueTiedostosta("lz.dat"));
        String d = lz.lueTiedostosta("lz.dat");
//        System.out.println("Käännetään...");
//        String kaanteinend = new StringBuilder(d).reverse().toString();
//        System.out.println("Käsitellään...");
//        String vrtData = "";
//        //System.out.println(kaanteinend);
//        System.out.println("");
//        boolean ykkonenFound = false;
//        for (int i = 0; i < kaanteinend.length(); i++) {
//            if (!ykkonenFound && kaanteinend.charAt(i) == '1') {
//                ykkonenFound = true;
//            } else if (ykkonenFound) {
//                vrtData = vrtData + kaanteinend.charAt(i);
//                //System.out.print(kaanteinend.charAt(i));
//            }
//
//        }
//        if (lz.haeTuloste2().equals(vrtData)) {
//            System.out.println("Koodattu data on täsmälleen sama, kuin tiedostosta luettu.");
//        } else {
//            System.out.println("Tiedostosta luettu data ei vastaa alkuperäistä koodattua dataa!");
//        }

        //System.out.println(lz.pura(d));
        //System.out.println(lz.pura2(d));
        String purettuMerkkijono = lz.pura2(d, "lz_purettu.txt");

        if (purettuMerkkijono.equals(sisalto)) {
            System.out.println("Merkkijono on täsmälleen sama ennen ja jälkeen purkamisen");
        } else {
            System.out.println("Merkkijono ei ole sama ennen ja jälkeen purkamisen!");
        }

        if (purettuMerkkijono.length() < 10000) {
            System.out.println("Purettu:");
            System.out.println(purettuMerkkijono);
        }

        //lz.pura2(lz.haeTuloste2());
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
