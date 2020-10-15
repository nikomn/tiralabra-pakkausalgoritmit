package tiralabra.pakkausalgoritmit.tietorakenteet;

/**
 * Luokka sisältää Hajautustauluun liittyvät toiminnallisuudet.
 */
public class Hajautustaulu<K, V> {

    private Lista<Pari<K, V>>[] arvot;
    private Lista<K> avaimet;
    private int arvoja;

    public Hajautustaulu() {
        this.arvot = new Lista[999999];
        this.avaimet = new Lista();
        this.arvoja = 0;
    }

    /**
     * Metodi hakee avaimeen liittyvän arvon.
     *
     * @param avain, Objekti, joka käytettyn avaimen muodossa
     *
     * @return Objekti, joka käytetyn arvon muodossa
     */
    public V hae(K avain) {
        int hajautusArvo = Math.abs(avain.hashCode() % this.arvot.length);
        if (this.arvot[hajautusArvo] == null) {
            return null;
        }

        Lista<Pari<K, V>> arvotIndeksissa = this.arvot[hajautusArvo];

        for (int i = 0; i < arvotIndeksissa.koko(); i++) {
            if (arvotIndeksissa.arvo(i).haeAvain().equals(avain)) {
                return arvotIndeksissa.arvo(i).haeArvo();
            }
        }

        return null;

    }

    /**
     * Metodi tarkistaa onko avain taulussa.
     *
     * @param avain, Objekti, joka käytettyn avaimen muodossa
     *
     * @return totuusarvo
     */
    public boolean sisaltaaAvaimen(K avain) {
        if (hae(avain) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi hakee kaikki avaimet.
     *
     *
     * @return Lista
     */
    public Lista<K> haeAvaimet() {
        return this.avaimet;
    }

    /**
     * Metodi hakee kaikki avaimeen liittyvän listan, käytetään apumetodina
     * muissa toiminnoissa.
     *
     * @param avain, Objekti, joka käytetyn avaimen muodossa
     *
     * @return Lista
     */
    private Lista<Pari<K, V>> haeAvaimeenLiittyvaLista(K avain) {

        int hajautusArvo = Math.abs(avain.hashCode() % arvot.length);
        if (arvot[hajautusArvo] == null) {
            arvot[hajautusArvo] = new Lista<>();
        }

        return arvot[hajautusArvo];
    }

    /**
     * Metodi hakee avaimen indeksin, käytetään apumetodina muissa toiminnoissa.
     *
     * @param avain, Objekti, joka käytetyn avaimen muodossa
     * @param lista, parit
     *
     * @return kokonaisluku
     */
    private int haeAvaimenIndeksi(Lista<Pari<K, V>> lista, K avain) {
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.arvo(i).haeAvain().equals(avain)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Paluuarvoton metodi joka lisää avaimen ja arvon tauluun
     *
     * @param avain, Objekti, joka käytetyn avaimen muodossa
     * @param arvo, Objekti, joka käytetyn arvon muodossa
     *
     */
    public void lisaa(K avain, V arvo) {
        Lista<Pari<K, V>> arvotIndeksissa = haeAvaimeenLiittyvaLista(avain);
        int indeksi = haeAvaimenIndeksi(arvotIndeksissa, avain);
        if (indeksi < 0) {
            arvotIndeksissa.lisaa(new Pari<>(avain, arvo));
            this.avaimet.lisaa(avain);
            this.arvoja++;
        } else {
            arvotIndeksissa.arvo(indeksi).asetaArvo(arvo);
        }

    }

    /**
     * Metodi joka hakee taulun koon
     *
     * @return kokonaisluku
     *
     */
    public int koko() {
        return this.arvoja;
    }

}
