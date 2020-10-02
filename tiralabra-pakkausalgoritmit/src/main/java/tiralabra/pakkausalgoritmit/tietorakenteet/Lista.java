package tiralabra.pakkausalgoritmit.tietorakenteet;

public class Lista<T> {

    private T[] arvot;
    private int arvoja;

    public Lista() {
        this.arvot = (T[]) new Object[10];
        this.arvoja = 0;
    }

    public void lisaa(T arvo) {
        if (this.arvoja == this.arvot.length) {
            kasvata();
        }
        this.arvot[arvoja] = arvo;
        this.arvoja++;
    }

    private void kasvata() {
        T[] uusi = (T[]) new Object[this.arvot.length * 3 / 2 + 1];
        for (int i = 0; i < this.arvot.length; i++) {
            uusi[i] = this.arvot[i];
        }
        this.arvot = uusi;
    }

    public boolean sisaltaa(T arvo) {
        for (int i = 0; i < this.arvoja; i++) {
            if (this.arvot[i].equals(arvo)) {
                return true;
            }

        }

        return false;
    }

    public int koko() {
        return this.arvoja;
    }

    public T arvo(int indeksi) {

        if (indeksi < 0 || indeksi >= this.arvoja) {
            throw new ArrayIndexOutOfBoundsException("Indeksi " + indeksi + "alueen[0, " + this.arvoja + "[ ulkopuolella.");
        }
        return this.arvot[indeksi];

    }

}
