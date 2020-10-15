package tiralabra.pakkausalgoritmit.tietorakenteet;

/**
 * Luokka sisältää pariin littyvät toiminnot. Hajautustaulun apuluokka.
 */
public class Pari<K, V> {

    private K avain;
    private V arvo;

    /**
     * Konstruktori
     *
     * @param avain, objekti
     * @param arvo, objekti
     *
     *
     */
    public Pari(K avain, V arvo) {
        this.avain = avain;
        this.arvo = arvo;
    }

    /**
     * Metodi hakee avaimen
     *
     *
     *
     * @return objekti
     */
    public K haeAvain() {
        return this.avain;
    }

    /**
     * Paluuarvoton metodi asettaa avaimen
     *
     * @param avain, objekti
     *
     *
     */
    public void asetaAvain(K avain) {
        this.avain = avain;
    }

    /**
     * Metodi hakee arvon
     *
     *
     *
     * @return objekti
     */
    public V haeArvo() {
        return this.arvo;
    }

    /**
     * Paluuarvoton metodi asettaa arvon
     *
     * @param arvo, objekti
     *
     *
     */
    public void asetaArvo(V arvo) {
        this.arvo = arvo;
    }
}
