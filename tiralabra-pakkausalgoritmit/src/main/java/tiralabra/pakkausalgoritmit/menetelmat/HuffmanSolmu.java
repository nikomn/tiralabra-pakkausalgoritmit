package tiralabra.pakkausalgoritmit.menetelmat;

/**
 * Luokka kuvaa HuffmanSolmu-olion ominaisuudet.
 */
public class HuffmanSolmu implements Comparable {

    String merkki;
    Integer toistuvuus;
    HuffmanSolmu vasen;
    HuffmanSolmu oikea;
    HuffmanSolmu vanhempi;
    String tunnisteBinaarina;
    //Long tunniste;

    /**
     * Konstruktori jota käytetään, kun merkkijona pakataan
     *
     * @param merkki, merkkijono, voi olla null
     * @param toistuvuus, kokonaisluku
     * @param vasen, HuffmanSolmu-olio, voi olla null
     * @param oikea, HuffmanSolmu-olio, voi olla null
     */
    public HuffmanSolmu(String merkki, Integer toistuvuus, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        //System.out.println("Lisätään merkkiä " + merkki);
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

    /**
     * Konstruktori jota käytetään, kun pakattua merkkijona puretaan takaisin
     * merkkijonoksi
     *
     * @param merkki, merkkijono, voi olla null
     * @param toistuvuus, kokonaisluku
     * @param tunniste, merkkijono
     * @param vasen, HuffmanSolmu-olio, voi olla null
     * @param oikea, HuffmanSolmu-olio, voi olla null
     */
    public HuffmanSolmu(String merkki, Integer toistuvuus, String tunnisteId, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        this.merkki = merkki;
        this.toistuvuus = toistuvuus;
        this.vasen = vasen;
        this.oikea = oikea;
        this.tunnisteBinaarina = tunnisteId;
        //System.out.println("Luotu uusi: " + this.tunnisteBinaarina);
    }

    /**
     * Metodi hakee HuffmanSolmun toistuvuuden
     *
     * @return toistuvuus kokonaislukuna
     */
    public Integer haeToistuvuus() {
        return this.toistuvuus;
    }

    /**
     * Metodi hakee HuffmanSolmun uniikin tunnisteen
     *
     * @return HuffmanSolmu-olion tunniste merkkijonona
     */
    public String haeTunniste() {
        return this.tunnisteBinaarina;
    }

    /**
     * Metodi hakee HuffmanSolmun vasemmalla puolella olevan HuffmanSolmun
     *
     * @return vasemmanpuoleinen HuffmanSolmu
     */
    public HuffmanSolmu haeVasen() {
        return this.vasen;
    }

    /**
     * Metodi hakee HuffmanSolmun oikealla puolella olevan HuffmanSolmun
     *
     * @return oikeanpuoleinen HuffmanSolmu
     */
    public HuffmanSolmu haeOikea() {
        return this.oikea;
    }

    /**
     * Metodi muuntaa HuffmanSolmu-olion binaarimuotoon tiedostoon tallennusta
     * varten
     *
     *
     * @return Nollista ja ykkösistä koostuva merkkijono
     */
    public String muunnaBinaariEsitysmuotoon() {
        /*
        HUOM! Koska HuffmanSolmu oliot voivat sisältää myös null arvoja
         , jolle ei ole varsinaista numeerista vastinetta, muunnetaan kaikki
        null arvot muotoon "000000000000000000000000" tiedostoon tallennuksen 
        yhteydessä.
         */

        String id = this.tunnisteBinaarina;
        //System.out.println("Muunnetaan solmua binaariksi: " + this.merkki);

        String m = "000000000000000000000000";
        if (this.merkki != null && this.merkki.length() == 1) {
            m = String.format("%24s", Integer.toBinaryString(this.merkki.charAt(0))).replace(' ', '0');
        }

        String t = String.format("%24s", Integer.toBinaryString(0)).replace(' ', '0');
        if (this.toistuvuus != null) {
            t = String.format("%24s", Integer.toBinaryString(this.toistuvuus)).replace(' ', '0');;
        }

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

        String w = "000000000000000000000000";
        if (this.vanhempi != null) {
            w = this.vanhempi.tunnisteBinaarina;
        }
        //System.out.println("V: " + V);

        String binaariMerkkijono = id + m + t + v + o + w;

        //System.out.println("binaariMerkkijono: " + binaariMerkkijono);
        return binaariMerkkijono;
    }

    /**
     * Metodi muodostaa HuffmanSolmu-olion merkkijonomuotoisen kuvauksen.
     *
     *
     * @return merkkijono, jossa HuffmanSolmu-olion merkki ja toistuvuus
     */
    @Override
    public String toString() {
        String tuloste = "";
        String m = this.merkki;
        return m + "(" + this.toistuvuus + ")";
    }

    /**
     * Metodi vertailee kahta HuffmanSolmua keskenään. Tarvitaan
     * prioriteettijonoon tallennusta varten
     *
     * @param verrattava, Objekti, käytännössä HuffmanSolmu
     *
     * @return kokonaisluku, positiivinen jos verrattava on pienempi ,
     * negatiivinen muutoin
     */
    @Override
    public int compareTo(Object verrattava) {
        try {
            HuffmanSolmu verrokki = (HuffmanSolmu) verrattava;
            return this.toistuvuus.compareTo(verrokki.toistuvuus);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Paluuarvoton metodi, joka lisää HufmanSolmulle vasemman solmun
     *
     * @param s, HuffmanSolmu
     *
     *
     */
    public void lisaaVasen(HuffmanSolmu s) {
        this.vasen = s;
    }

    /**
     * Paluuarvoton metodi, joka lisää HufmanSolmulle oikean solmun
     *
     * @param s, HuffmanSolmu
     *
     *
     */
    public void lisaaOikea(HuffmanSolmu s) {
        this.oikea = s;
    }

    /**
     * Paluuarvoton metodi, joka lisää HufmanSolmulle vanhemman solmun
     *
     * @param s, HuffmanSolmu
     *
     *
     */
    public void lisaaVanhempi(HuffmanSolmu s) {
        this.vanhempi = s;
    }

}
