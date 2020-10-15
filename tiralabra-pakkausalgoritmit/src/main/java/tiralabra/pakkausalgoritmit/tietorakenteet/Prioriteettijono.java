package tiralabra.pakkausalgoritmit.tietorakenteet;

import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;

/**
 * Luokka sisältää prioriteettijonoon liittyvät toiminnallisuudet.
 */
public class Prioriteettijono {

    private HuffmanSolmu[] jono;
    private int viimeinen;

    public Prioriteettijono() {
        this.jono = new HuffmanSolmu[9999999];
        this.viimeinen = 0;
    }

    /**
     * Paluuarvoton metodi lisää solmun listaan
     *
     * @param x, HuffmanSOlmu
     *
     *
     */
    public void lisaa(HuffmanSolmu x) {
        // jono.add(); vastaava toiminnallisuus jotenkin toteutettuna...
        this.viimeinen++;
        int p = this.viimeinen;
        while (p > 1 && x.haeToistuvuus() < this.jono[vanhempi(p)].haeToistuvuus()) {
            this.jono[p] = this.jono[vanhempi(p)];
            p = vanhempi(p);
        }
        this.jono[p] = x;

    }

    /**
     * Metodi hakee taulusta vasemman puoleisen objektin indeksin
     *
     * @param p, kokonaisluku
     *
     * @return kokonaisluku
     */
    public int vasen(int p) {
        if (2 * p > this.viimeinen) {
            return 0;
        } else {
            return 2 * p;
        }
    }

    /**
     * Metodi hakee taulusta oikean puoleisen objektin indeksin
     *
     * @param p, kokonaisluku
     *
     * @return kokonaisluku
     */
    public int oikea(int p) {
        if (2 * p + 1 > this.viimeinen) {
            return 0;
        } else {
            return 2 * p + 1;
        }
    }

    /**
     * Metodi hakee taulusta objektin vanhemman indeksin
     *
     * @param p, kokonaisluku
     *
     * @return kokonaisluku
     */
    public int vanhempi(int p) {
        return p / 2;
    }

    /**
     * Metodi hakee jonon ensimmäisen HuffmanSolmun
     *
     *
     * @return HuffmanSolmu
     */
    public HuffmanSolmu nouda() {
        //jono.poll(); vastaava toiminnallisuus...
        HuffmanSolmu a = this.jono[1];
        this.jono[1] = this.jono[this.viimeinen];
        this.viimeinen--;
        painaAlas(1);
        return a;
    }

    /**
     * Paluuarvoton metodi korjaa jonon järjestyksen
     *
     * @param solmu, kokonaisluku, taulunindeksinumero
     *
     *
     */
    public void painaAlas(int solmu) {
        int pienempiLapsi = -1;
        if (vasen(solmu) == 0) {
            //System.out.println("ei lapsia!");
            return;
        } else if (vasen(solmu) == this.viimeinen) {
            pienempiLapsi = vasen(solmu);
        } else {
            if (this.jono[vasen(solmu)].haeToistuvuus() < this.jono[oikea(solmu)].haeToistuvuus()) {
                pienempiLapsi = vasen(solmu);
            } else {
                pienempiLapsi = oikea(solmu);
            }
        }
        if (this.jono[solmu].haeToistuvuus() > this.jono[pienempiLapsi].haeToistuvuus()) {
            HuffmanSolmu tmp1 = this.jono[solmu];
            HuffmanSolmu tmp2 = this.jono[pienempiLapsi];
            this.jono[solmu] = tmp2;
            this.jono[pienempiLapsi] = tmp1;
            painaAlas(pienempiLapsi);
        }
    }

}
