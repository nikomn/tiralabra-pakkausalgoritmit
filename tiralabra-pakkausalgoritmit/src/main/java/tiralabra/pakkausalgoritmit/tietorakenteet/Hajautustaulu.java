package tiralabra.pakkausalgoritmit.tietorakenteet;

public class Hajautustaulu<K, V> {

    private Lista<Pari<K, V>>[] arvot;
    private Lista<K> avaimet;
    private int arvoja;

    public Hajautustaulu() {
        this.arvot = new Lista[999999];
        this.avaimet = new Lista();
        this.arvoja = 0;
    }

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
    
    public boolean sisaltaaAvaimen(K avain) {
        if (hae(avain) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Lista<K> haeAvaimet() {
        return this.avaimet;
    }

    private Lista<Pari<K, V>> haeAvaimeenLiittyvaLista(K avain) {

        int hajautusArvo = Math.abs(avain.hashCode() % arvot.length);
        if (arvot[hajautusArvo] == null) {
            arvot[hajautusArvo] = new Lista<>();
        }

        return arvot[hajautusArvo];
    }

    private int haeAvaimenIndeksi(Lista<Pari<K, V>> lista, K avain) {
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.arvo(i).haeAvain().equals(avain)) {
                return i;
            }
        }
        return -1;
    }

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
    
    public int koko() {
        return this.arvoja;
    }
    
    public void kayLapi() {
        
    }
    
    public static void main(String[] args) {
        Hajautustaulu<String, String> ht = new Hajautustaulu<>();
        ht.lisaa("Aapeli", "Muttinen");
        System.out.println(ht.hae("Aapeli"));
        System.out.println(ht.sisaltaaAvaimen("Beepeli"));
        Lista<String> l = ht.haeAvaimet();
        for (int i = 0; i < l.koko(); i++) {
            System.out.println(l.arvo(i));
        }
    }
}
