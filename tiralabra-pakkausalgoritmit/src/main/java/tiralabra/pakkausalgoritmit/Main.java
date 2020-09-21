package tiralabra.pakkausalgoritmit;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import tiralabra.pakkausalgoritmit.apuohjelmat.BinaariMuotoilija;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonlukija;

public class Main {

    public static void main(String[] args) throws Exception {

        // Työvälineiden alustus
        Tiedostonlukija tlukija = new Tiedostonlukija();
        BinaariMuotoilija binaarimuuntaja = new BinaariMuotoilija();
        Huffman h = new Huffman();
        Tiedostonkirjoittaja tkirjoittaja = new Tiedostonkirjoittaja();

        // Datan lukeminen
        System.out.println("Luetaan tiedostoa...");
        String sisalto = tlukija.lueTiedosto("testi.txt");
        System.out.println("Luettiin: " + sisalto);
        System.out.println("Tiedoston sisältö luettu!");
        //sisalto = "abcdabcd\n";

        // Binääriformaattiin muuntaminen, lähinnä testailua varten tässä vaiheessa...
        System.out.println("Muunnetaan vertailun vuoksi tiedoston sisältö 'binäärimuotoon'...");
        String binaarimuoto = binaarimuuntaja.muunnaBinaariksi(sisalto);
        System.out.println("Muunnos valmis!");

        // Huffman koodaus...
        h.muodostaTaulu(sisalto);
        h.muodostaPuu();
        h.tulostaKoodit();
        h.muodostaAvaintaulu();
        System.out.println("\n\n");
        System.out.println("Binääritaulu:");
        String x = h.avaintauluBinaarina();
        System.out.println("\n");
        String koodattuna = "";
        for (int i = 0; i < sisalto.length(); i++) {
            koodattuna = h.haeAvaintaulusta(sisalto.charAt(i)) + koodattuna;
        }
        System.out.println("Avaintaulusta haettuna:");
        System.out.println(koodattuna);
        System.out.println("Koodataan vanhalla tavalla...");
        String koodattu = h.koodaa();
        System.out.println("Tulostetaan taulua...");
        h.tulostaTaulut();

        // Kirjoitetaan tiedostoon...
        //tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "huffman.dat");
        tkirjoittaja.kirjoitaTiedosto2(koodattuna, h.avaintauluBinaarina(), h.haeIndeksi(), "huffman2.dat");

        // Lopputulokset...
        int alkuperainen = binaarimuoto.length();  // Ei vastaa todellista kokoa, mutta lienee riittävän lähellä?
        int pakattu = koodattu.length();
        double suhde = Math.round((pakattu * 1.0) / (alkuperainen * 1.0) * 100.0);

        // Debugausta varten...
        //System.out.println("\n\n\nMerkkijono: " + sisalto);
        //System.out.println("Binäärimuodossa: " + binaarimuoto);
        System.out.println("Koodattuna: " + koodattu);
        //System.out.println("Koodattu sisältää " + koodattu.length() + " bittiä");
        //System.out.println("Tiedostoon kirjoittaminen tapahtuu tavuissa, joten täytebittejä tulee yhteensä...");
        Double tasan = Math.ceil(koodattu.length() / 8.0);
        //System.out.println(tasan);
        Double kpl = 8 * tasan - koodattu.length();
        int lkm = kpl.intValue();
        //System.out.println(lkm);
        System.out.println("Koodaamattoman merkkijonon koko: " + alkuperainen + " bittiä");
        System.out.println("Koodatun merkkijonon koko: " + pakattu + " bittiä");

        System.out.println("\nPakattu on n. " + suhde + "% alkuperäisestä.");

        System.out.println("Tiedostosta luettuna: ");
        //System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));
        //String[] luettu = tlukija.lueKoodattuTiedosto("huffman.dat");
        //String luettuKoodattu = luettu[luettu.length - 1];
        //String subString = luettuKoodattu.substring(lkm, luettuKoodattu.length());
        //System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));
        //System.out.println(subString);
        //h.puraKoodattuTiedosto(luettu);
        String luettu = tlukija.lueKoodattuTiedosto2("huffman2.dat");
        boolean dataAlkanut = false;
        for (int i = 0; i < luettu.length(); i++) {
            if (!dataAlkanut && luettu.charAt(i) == '1') {
                dataAlkanut = true;
            }
            if (dataAlkanut) {
                System.out.println(luettu.charAt(i));
            }
        }
        System.out.println(luettu);

    }

}
