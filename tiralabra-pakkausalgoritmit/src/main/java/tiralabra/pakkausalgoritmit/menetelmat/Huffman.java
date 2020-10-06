package tiralabra.pakkausalgoritmit.menetelmat;

import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.tietorakenteet.Hajautustaulu;
import tiralabra.pakkausalgoritmit.tietorakenteet.Lista;
import tiralabra.pakkausalgoritmit.tietorakenteet.Prioriteettijono;

/**
 * Luokka sisältää Huffman algoritmiin liittyvät toiminnallisuudet.
 */
public class Huffman {

    private HuffmanSolmu puunjuuri;
    private Hajautustaulu<Character, HuffmanSolmu> omaTaulu;
    private String sisalto;
    private HuffmanSolmu[] solmut;
    private HuffmanSolmu[] solmulista;
    private int indeksi;

    /**
     * Metodi muodostaa HuffmanSolmun tiedostosta luetun datan perusteella.
     *
     * @param tunnisteString, binäärimuotoinen merkkijono, joka sisältää
     * HuffmanSolmu-olion uniikin tunnisteen binäärimuotoon muunnettuna
     * @param merkiString, binäärimuotoinen merkkijono, joka sisältää
     * merkkijonon tai null arvon binäärimuotoon muunnettuna
     * @param toistuvuusString, binäärimuotoinen merkkijono, joka sisältää
     * kokonaisluvun binäärimuotoon muunnettuna.
     *
     * @return HuffmanSolmu-olio
     */
    public HuffmanSolmu muodostaSolmu(String tunnisteString, String merkkiString, String toistuvuusString) {
        String m = null;
        if (!merkkiString.equals("000000000000000000000000")) {
            int merkkiNumero = Integer.parseInt(merkkiString, 2);
            Character merkki = (char) merkkiNumero;
            m = merkki + "";
        }
        Integer t = Integer.parseInt(toistuvuusString, 2);

        HuffmanSolmu solmu = new HuffmanSolmu(m, t, tunnisteString, null, null);
        return solmu;
    }

    /**
     * Paluuarvoton metodi joka määrittää paramatrina annettua tunnistetta
     * vastaavan HuffmanSolmu-olion.
     *
     * Käytetään pakattujen tiedostojen purkamisen yhteydessä.
     *
     * @param tunniste, merkkijono
     */
    public void asetaPuunjuuri(String tunniste) {
        for (int i = 0; i < this.solmulista.length; i++) {
            if (this.solmulista[i].tunnisteBinaarina.equals(tunniste)) {
                this.puunjuuri = this.solmulista[i];
            }
        }
    }

    /**
     * Paluuarvoton metodi joka yhdistää parametrina annettuja tunnisteita
     * vastaavat HuffmanSolmut toisiinsa.
     *
     * Käytetään pakattujen tiedostojen purkamisen yhteydessä.
     *
     * @param tunniste, merkkijono
     * @param vasen, merkkijono
     * @param oikea, merkkijono
     * @param vanhempi, merkkijono
     */
    public void muodostaYhteys(String tunniste, String vasen, String oikea, String vanhempi) {
        //System.out.println("Muodostetaan yhteyttä: ");
        //System.out.println("Tunniste: " + tunniste);
        int s = -1;
        int o = -1;
        int v = -1;
        int V = -1;
        for (int i = 0; i < this.solmulista.length; i++) {
            //System.out.println("this.solmulista[i].tunnisteBinaarina " + this.solmulista[i].tunnisteBinaarina);
            if (this.solmulista[i].tunnisteBinaarina.equals(tunniste)) {
                s = i;
            }
            if (this.solmulista[i].tunnisteBinaarina.equals(vasen)) {
                v = i;
            }
            if (this.solmulista[i].tunnisteBinaarina.equals(oikea)) {
                o = i;
            }
            if (this.solmulista[i].tunnisteBinaarina.equals(vanhempi)) {
                V = i;
            }
        }

        if (v != -1) {
            solmulista[s].vasen = solmulista[v];
        } else {
            solmulista[s].vasen = null;
        }
        if (o != -1) {
            solmulista[s].oikea = solmulista[o];
        } else {
            solmulista[s].oikea = null;
        }
        if (V != -1) {
            solmulista[s].vanhempi = solmulista[V];
        } else {
            solmulista[s].vanhempi = null;
        }

    }

    /**
     * Paluuarvoton metodi joka käsittelee ja tulkitsee tiedostosta luetun
     * binäärimuotoisen datan ja muodostaa sen pohjalta Huffmanpuun ja purkaa
     * koodatun datan merkkijonomuotoon.
     *
     *
     * @param tiedostonSisalto, merkkijonotaulu
     */
    public void puraKoodattuTiedosto(String[] tiedostonSisalto, String purettuNimi) {
        System.out.println("Puretaan koodattua dataa...");
        String m = null;
        if (!tiedostonSisalto[4].equals("000000000000000000000000")) {
            int merkkiNumero = Integer.parseInt(tiedostonSisalto[4], 2);
            Character merkki = (char) merkkiNumero;
            m = merkki + "";
        }
        Integer t = Integer.parseInt(tiedostonSisalto[5], 2);

        HuffmanSolmu juuri = new HuffmanSolmu(m, t, tiedostonSisalto[3], null, null);
        //System.out.println("Juuri: " + juuri);
//        String[] testi = new String[8];
//        testi[0] = "100000000000000000000000";  // headeri
//        testi[1] = "000000000000000000000001";  // taulunpituus
//        testi[2] = "000000000000000000000011";  // skippibitit
//
//        testi[3] = "000000000000000001100001";  // merkki
//        testi[4] = "000000000000000000000010";  // toistuvuus
//        testi[5] = "000000000000000000101101";  // vasen
//        testi[5] = "000000000000000000101101";  // oikea
//        testi[6] = "000000000000000000101101";  // vanhempi
        int tauluPituus = Integer.parseInt(tiedostonSisalto[1], 2);
        int skippiBitit = Integer.parseInt(tiedostonSisalto[2], 2);
        //System.out.println("taulun pituus: " + tauluPituus);
        this.solmulista = new HuffmanSolmu[tauluPituus];
        Integer solmulistaIndeksi = 0;
        //int edistymisIndeksi = 0;
        for (int i = 9; i < (tauluPituus * 6) + 7; i = i + 6) {
            //edistymisIndeksi++;
            //System.out.println("Käsitellään solmua " + edistymisIndeksi + "/" + tauluPituus);

            //System.out.println("i: " + i);
            int merkkiNumero = Integer.parseInt(tiedostonSisalto[i + 1], 2);
            Character merkki = (char) merkkiNumero;
            //System.out.println("Merkki: " + merkki);
            int toistuvuus = Integer.parseInt(tiedostonSisalto[i + 2], 2);
            // Character merkki, Integer toistuvuus, HuffmanSolmu vasen, HuffmanSolmu oikea
            HuffmanSolmu hs = muodostaSolmu(tiedostonSisalto[i], tiedostonSisalto[i + 1], tiedostonSisalto[i + 2]);
            //HuffmanSolmu hs = new HuffmanSolmu(merkki + "", toistuvuus, tiedostonSisalto[i], null, null);
            //System.out.println(hs.muunnaBinaariEsitysmuotoon());
            this.solmulista[solmulistaIndeksi] = hs;
            solmulistaIndeksi++;
        }

        //int edistymisIndeksi = 0;
        int tiedostoIndeksi = 9;
        for (int i = 9; i < (tauluPituus * 6) + 7; i = i + 6) {
            //edistymisIndeksi++;
            //System.out.println("Käsitellään solmua " + edistymisIndeksi + "/" + tauluPituus);
            muodostaYhteys(tiedostonSisalto[i], tiedostonSisalto[i + 3], tiedostonSisalto[i + 4], tiedostonSisalto[i + 5]);
            tiedostoIndeksi = i + 6;
        }

        StringBuilder merkkijononKoostaja = new StringBuilder();
        String koodattu = "";
        
        for (int i = tiedostoIndeksi; i < tiedostonSisalto.length; i++) {
            
            //koodattu = koodattu + tiedostonSisalto[i];
            merkkijononKoostaja.append(tiedostonSisalto[i]);
        }
        
        

        koodattu = merkkijononKoostaja.toString();

        String subString = koodattu.substring(skippiBitit, koodattu.length());
        //System.out.println("Koodatun tiedoston data: " + subString);
        

        asetaPuunjuuri(tiedostonSisalto[3]);

        String merkkijono = "";
//        System.out.println("Puu:");
//        HuffmanSolmu s = this.puunjuuri;
//        System.out.println("\t\t" + s.vasen.merkki + " | " + s.oikea.merkki);
//        s = s.vasen;
//        System.out.println(s.vasen.merkki + " | " + s.oikea.merkki);
//        s = s.oikea;
//        System.out.println(s);
//        //System.out.println(s.vasen.merkki);
        HuffmanSolmu solmu = this.puunjuuri;
        // 110001011101001011101
        // abcdabcd
        //
        // aaaa\n == 01111
        // a == 1, \n == 0?

        // Pitäisi olla 11 == a
        /*
        11: a -> 11
        00: b -> 00
        01: c -> 10
        101: d -> 101
        100: \n -> 001
         */
        // 10011101010011101010011
        //            d c b a  
        //subString = "101100011"; //abcd
        //subString = "10"; // c?
        //subString = "00"; // b?
        //subString = "11"; // a?
        //subString = "01";
//        String kaanteinensubString = "";
//
//        for (int i = subString.length() - 1; i >= 0; i--) {
//            kaanteinensubString = kaanteinensubString + subString.charAt(i);
//        }
        String kaanteinenSubString = new StringBuilder(subString).reverse().toString();

        //subString = kaanteinensubString;
        //for (int i = subString.length() - 1; i > -1; i--) {
        StringBuilder merkkijononKoostaja2 = new StringBuilder();
        for (int i = 0; i < subString.length(); i++) {
            //System.out.println("Nykyinen solmu loopin alussa " + solmu.merkki);
            //System.out.println("solmu.vasen" + solmu.vasen);

            if (subString.charAt(i) == '0') {
                //System.out.println("Vaihtuu vasempaan");
                solmu = solmu.vasen;
            } else {
                //System.out.println("Vaihtuu oikeaan");
                solmu = solmu.oikea;
            }
            //System.out.println("Käsiteltävänä: " + subString.charAt(i));
            //System.out.println("Lehti " + solmu.merkki);
            if (solmu.vasen == null && solmu.oikea == null) {
                //System.out.println("Lehti " + solmu.merkki);
                
                //merkkijono = merkkijono + solmu.merkki;
                // Paljon nopeampaa...
                merkkijononKoostaja2.append(solmu.merkki);
                solmu = this.puunjuuri;
            }

        }
        Tiedostonkirjoittaja f = new Tiedostonkirjoittaja();
        f.kirjoitaTekstiTiedostoon(merkkijononKoostaja2.toString(), purettuNimi);

        //System.out.println("Tiedostosta luettu teksti: \n" + merkkijono);
    }

    /**
     * Paluuarvoton metodi joka muodostaa merkkien toistuvuudet sisältävän
     * taulun.
     *
     * Käytetään pakattujen tiedostojen pakkaamisen yhteydessä.
     *
     * @param mj, merkkijono
     */
    public void muodostaTaulu(String mj) {
        this.omaTaulu = new Hajautustaulu<>();
        this.sisalto = mj;
        System.out.println("Muodostetaan merkkitaulua...");
        //System.out.println("Muodostetaan merkkitaulua merkkijonolle " + this.sisalto + "...");
        for (int i = 0; i < this.sisalto.length(); i++) {
            char m = this.sisalto.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!this.omaTaulu.sisaltaaAvaimen(m)) {
                this.omaTaulu.lisaa(m, new HuffmanSolmu(m + "", 1, null, null));
            } else {
                this.omaTaulu.hae(m).toistuvuus++;
            }
        }
        //System.out.println("Taulun pituus: " + this.taulu.size());
        System.out.println("Merkkitaulu muodostettu!");

    }

    /**
     * Metodi hakee HuffmanSolmujen toistuvuudet sisältävän taulukon koon.
     *
     * @return taulunkoko kokonaislukuna
     */
    public int haeTaulunKoko() {
        return this.omaTaulu.koko();
    }

    /**
     * Metodi hakee Huffmanpuun juureksi määritellyn HuffmanSolmun.
     *
     * @return HuffmanSolmu
     */
    public HuffmanSolmu haePuunjuuri() {
        return this.puunjuuri;
    }

    /**
     * Metodi hakee taulukon, joka sisältää kaikki HuffmanSolmut.
     *
     * @return HuffmanSolmuja sisältävä taulukko
     */
    public HuffmanSolmu[] haePuu() {
        return this.solmut;
    }

    /**
     * Metodi hakee HuffmanSolmuja sisältävän taulukon nykyisen indeksin.
     *
     * @return kokonaisluku
     */
    public Integer haeIndeksi() {
        return this.indeksi;
    }

    /**
     * Metodi hakee parametrina määritellystä HuffmanSolmusta lähtien puun
     * juureksi määritellyn Huffman solmun ja palauttaa niiden välillä kuljetun
     * matkan merkkijonomuodossa.
     *
     * @param alku, HuffmanSolmu
     *
     * @return merkkijono
     */
    public String etsiJuuri(HuffmanSolmu alku) {
        //System.out.println("Haku alkaa...");
        //System.out.println("Etsitään merkkiä " + haettavaMerkki);
        //System.out.println("Merkki toistuu aineistossa " + toistuvuus + " kertaa");
        HuffmanSolmu solmu = alku;

        String koodi = "";
        while (solmu != this.puunjuuri) {
            //System.out.println("Käsiteltävä solmu: " + solmu.merkki + "(" + solmu.toistuvuus + ")");
            //System.out.println("Koodi on nyt: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
            HuffmanSolmu vanhempi = solmu.vanhempi;
            if (vanhempi.vasen == solmu) {
                koodi = 0 + koodi;
            } else if (vanhempi.oikea == solmu) {
                koodi = 1 + koodi;
            }

            solmu = solmu.vanhempi;
//            if (solmu == this.puunjuuri) {
//                System.out.println("Juuri löydetty!");
//            }
        }
        //System.out.println("Koodi on: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
        return koodi;
    }

    /**
     * Metodi koodaa merkkijonon Huffman formaattiin.
     *
     *
     * @return merkkijono
     */
    public String koodaa() {
        StringBuilder merkkijononKoostaja = new StringBuilder();
        System.out.println("Koodataan tekstiä...");
        int merkkiMaara = this.sisalto.length();
        int kasiteltavaMerkkiLkm = 0;
        int prosenttiKokkonaisuudesta = merkkiMaara / 100;
        String koodattu = "";
        for (int i = 0; i < this.sisalto.length(); i++) {
            kasiteltavaMerkkiLkm++;
            //System.out.println("Koodi on nyt " + koodattu);
            if (prosenttiKokkonaisuudesta > 0 && kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (merkkiMaara * 1.0) * 100.0);
                System.out.println(prosentti + "%" + " käsitelty...");
            }
            char m = this.sisalto.charAt(i);
            //System.out.println("Käsitellään merkkiä: " + m);
            if (!this.omaTaulu.sisaltaaAvaimen(m)) {
                System.out.println("Virheellinen merkkojono!");
                return "VIRHE!";
            } else {
                HuffmanSolmu solmu = this.omaTaulu.hae(m);
                if (solmu == null) {
                    System.out.println("Tässä on nyt joku virhe...");
                } else {
                    String koodiMuoto = etsiJuuri(solmu);
                    merkkijononKoostaja.append(koodiMuoto);
                    //koodattu = koodattu + koodiMuoto;
                }

                //System.out.println("Löydetty solmu: " + solmu.merkki);
            }
        }
        System.out.println("Koodaus valmis!");
        //System.out.println("Koodi on nyt " + koodattu);
        //return koodattu;
        return merkkijononKoostaja.toString();
    }

    /**
     * Paluuarvoton metodi joka muodostaa Huffman puun.
     *
     * Käytetään pakkaamisessa.
     *
     */
    public void muodostaPuu() {
        this.solmut = new HuffmanSolmu[99999];
        this.indeksi = 0;
        Prioriteettijono omaJono = new Prioriteettijono();
        //System.out.println("\nTaulu:");
        Lista<Character> avainLista = this.omaTaulu.haeAvaimet();
        for (int i = 0; i < avainLista.koko(); i++) {
            //System.out.println("Luetaan omasta taulusta: " + this.omaTaulu.hae(avainLista.arvo(i)));
            omaJono.lisaa(this.omaTaulu.hae(avainLista.arvo(i)));
            this.solmut[this.indeksi] = this.omaTaulu.hae(avainLista.arvo(i));
            this.indeksi++;
        }
//        for (Character avain : this.taulu.keySet()) {
//            //System.out.println(avain + ": " + this.taulu.get(avain).toistuvuus);
//            jono.add(this.taulu.get(avain));
//            this.solmut[this.indeksi] = this.taulu.get(avain);
//            this.indeksi++;
//        }

        int solmunSuuruus = 0;

        while (solmunSuuruus != this.sisalto.length()) {
            HuffmanSolmu eka = omaJono.nouda();
            if (eka.toistuvuus == this.sisalto.length()) {
                this.puunjuuri = eka;
                break;
            }
            HuffmanSolmu toka = omaJono.nouda();
            solmunSuuruus = eka.toistuvuus + toka.toistuvuus;
            HuffmanSolmu yhdistetty = new HuffmanSolmu(null, solmunSuuruus, eka, toka);
            if (yhdistetty.toistuvuus == this.sisalto.length()) {
                this.puunjuuri = yhdistetty;
            }
            eka.vanhempi = yhdistetty;
            toka.vanhempi = yhdistetty;
            omaJono.lisaa(yhdistetty);
            this.solmut[this.indeksi] = yhdistetty;
            this.indeksi++;
        }
    }

    /**
     * Metodi hakee HuffmanSolmujen toistuvuuksista kirjaa pitävän taulun.
     *
     * @return Hajautustaulu
     */
    public Hajautustaulu<Character, HuffmanSolmu> getTaulu() {
        return this.omaTaulu;
    }

    /**
     * Paluuarvotn metodi joka tulostaa puuhun kuuluvat HuffmanSolmut.
     *
     * Enimmäkseen kehitysaikaisia debug tarkoituksia varten.
     *
     */
    public void tulostaTaulut() {
        //System.out.println(Arrays.toString(this.solmut));
        for (int i = 0; i < this.indeksi; i++) {
            System.out.println(this.solmut[i]);
        }
    }

}
