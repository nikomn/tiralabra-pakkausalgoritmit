package tiralabra.pakkausalgoritmit.tietorakenteet;

/**
 * Luokka sisältää Huffman algoritmiin liittyvät toiminnallisuudet.
 */
public class Lista<T> {

    private T[] arvot;
    private int arvoja;

    public Lista() {
        this.arvot = (T[]) new Object[10];
        this.arvoja = 0;
    }

    /**
     * Paluuarvoton metodi lisää objektin listaan
     *
     * @param arvo, Objekti
     */
    public void lisaa(T arvo) {
        if (this.arvoja == this.arvot.length) {
            kasvata();
        }
        this.arvot[arvoja] = arvo;
        this.arvoja++;
    }

    /**
     * Paluuarvoton metodi kasvattaa listan kokoa tarvittaessa
     *
     * @return void
     */
    private void kasvata() {
        T[] uusi = (T[]) new Object[this.arvot.length * 3 / 2 + 1];
        for (int i = 0; i < this.arvot.length; i++) {
            uusi[i] = this.arvot[i];
        }
        this.arvot = uusi;
    }

    /**
     * Metodi tarkistaa onko arvo listassa
     *
     * @param arvo, Objekti
     * @return totuusarvo
     */
    public boolean sisaltaa(T arvo) {
        for (int i = 0; i < this.arvoja; i++) {
            if (this.arvot[i].equals(arvo)) {
                return true;
            }

        }

        return false;
    }

    /**
     * Metodi hakee listan koon
     *
     * 
     * @return kokonaisluku
     */
    public int koko() {
        return this.arvoja;
    }

    
    /**
     * Metodi indeksissä olevan objektin
     *
     * @param indeksi, kokonaisluku
     * 
     * @return objekti
     */
    public T arvo(int indeksi) {

        if (indeksi < 0 || indeksi >= this.arvoja) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + indeksi + "alueen[0, " + this.arvoja + "[ ulkopuolella.");
        }
        return this.arvot[indeksi];

    }

}
