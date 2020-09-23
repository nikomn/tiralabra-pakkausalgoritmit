package tiralabra.pakkausalgoritmit;

import java.io.DataOutputStream;
import java.io.File;
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
        //System.out.println("Luetaan tiedostoa...");
        long pakkausAlku = System.nanoTime();
        long alku = System.nanoTime();
        String sisalto = tlukija.lueTiedosto("testi.txt");
        long loppu = System.nanoTime();
        //System.out.println("Luettiin: " + sisalto);
        System.out.println("Tiedoston sisältö luettu!");
        long kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Tiedoston lukemisessa kesti " + kesto + " ms" );
        //sisalto = "abcdabcd\n";
        double lahdeKoko = new File("testi.txt").length();

        // Binääriformaattiin muuntaminen, lähinnä testailua varten tässä vaiheessa...
        //System.out.println("Muunnetaan vertailun vuoksi tiedoston sisältö 'binäärimuotoon'...");
        //String binaarimuoto = binaarimuuntaja.muunnaBinaariksi(sisalto);
        //System.out.println("Muunnos valmis!");

        // Huffman koodaus...
        System.out.println("Muodostetaan merkkitaulua");
        alku = System.nanoTime();
        h.muodostaTaulu(sisalto);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Taulun muodostus kesti " + kesto + " ms" );
        
        System.out.println("Muodostetaan Huffmanpuuta");
        alku = System.nanoTime();
        h.muodostaPuu();
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Puun muodostus kesti " + kesto + " ms" );
        //h.tulostaKoodit();
        //System.out.println("AvainTaulu");
        //h.muodostaAvaintaulu();
        //System.out.println("\n\n");
        //System.out.println("Binääritaulu:");
        //String x = h.avaintauluBinaarina();
        //System.out.println("\n");
//        String koodattuna = "";
//        for (int i = 0; i < sisalto.length(); i++) {
//            koodattuna = h.haeAvaintaulusta(sisalto.charAt(i)) + koodattuna;
//        }
//        System.out.println("Avaintaulusta haettuna:");
//        System.out.println(koodattuna);
        System.out.println("Koodataan data...");
        alku = System.nanoTime();
        String koodattu = h.koodaa();
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Datan koodaus kesti " + kesto + " ms" );
        
        
        //System.out.println("Tulostetaan taulua...");
        //h.tulostaTaulut();

//        String puunmuotoString = h.puunMuoto(h.haePuunjuuri());
//        HuffmanSolmu sss = h.luePuuTiedostosta(puunmuotoString);
//        System.out.println("sss: " + sss);
//        HuffmanSolmu[] y = h.haeTiedostostaLuettuPuu();
//        for (int i = 0; i < y.length; i++) {
//            HuffmanSolmu u = y[i];
//            if (u == null) {
//                break;
//            } else {
//                System.out.println(u);
//            }
//            
//        }

        // Kirjoitetaan tiedostoon...
        System.out.println("Kirjoitetaan tiedostoon...");
        alku = System.nanoTime();
        tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "huffman.dat");
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Tiedoston kirjoittamisessa kesti " + kesto + " ms" );

        long pakkausLoppu = System.nanoTime();
        kesto = (pakkausLoppu - pakkausAlku) / 10000;  // Millisekunneissa
        System.out.println("\n\nKoko pakkausoperaatio kesti " + kesto + " ms\n\n" );
        
        double kohdeKoko = new File("huffman.dat").length();
        double suhde = Math.round((kohdeKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
        System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
        System.out.println("Pakatun tiedoston koko: " + kohdeKoko + " tavua");

        System.out.println("\nPakattu tiedosto on n. " + suhde + "% alkuperäisestä.");
        //tkirjoittaja.kirjoitaTiedosto2(koodattuna, h.avaintauluBinaarina(), h.haeTaulunKoko(), "huffman2.dat");

        // Lopputulokset...
        //int alkuperainen = binaarimuoto.length();  // Ei vastaa todellista kokoa, mutta lienee riittävän lähellä?
        //int pakattu = koodattu.length();
        //double suhde = Math.round((pakattu * 1.0) / (alkuperainen * 1.0) * 100.0);

        // Debugausta varten...
        //System.out.println("\n\n\nMerkkijono: " + sisalto);
        //System.out.println("Binäärimuodossa: " + binaarimuoto);
        //System.out.println("Koodattuna: " + koodattu);
        //System.out.println("Koodattu sisältää " + koodattu.length() + " bittiä");
        //System.out.println("Tiedostoon kirjoittaminen tapahtuu tavuissa, joten täytebittejä tulee yhteensä...");
        //Double tasan = Math.ceil(koodattu.length() / 8.0);
        //System.out.println(tasan);
        //Double kpl = 8 * tasan - koodattu.length();
        //int lkm = kpl.intValue();
        //System.out.println(lkm);
        //System.out.println("Koodaamattoman merkkijonon koko: " + alkuperainen + " bittiä");
        //System.out.println("Koodatun merkkijonon koko: " + pakattu + " bittiä");

        //System.out.println("\nPakattu on n. " + suhde + "% alkuperäisestä.");

        //System.out.println("Tiedostosta luettuna: ");
        //System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));
        long purkuAlku = System.nanoTime();
        System.out.println("Luetaan dataa koodatusta Huffman-tiedosta");
        alku = System.nanoTime();
        String[] luettu = tlukija.lueKoodattuTiedosto("huffman.dat");
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Huffman-tiedoston lukeminen kesti " + kesto + " ms" );
        //String luettuKoodattu = luettu[luettu.length - 1];
        //String subString = luettuKoodattu.substring(lkm, luettuKoodattu.length());
        //System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));
        //System.out.println(subString);
        System.out.println("Muunnetaan huffman-koodattu data takaisin tekstiksi...");
        alku = System.nanoTime();
        h.puraKoodattuTiedosto(luettu);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 10000;  // Millisekunneissa
        System.out.println("Koodatun datan tulkinta kesti " + kesto + " ms" );
        long purkuLoppu = System.nanoTime();
        kesto = (purkuLoppu - purkuAlku) / 10000;  // Millisekunneissa
        System.out.println("\n\nKoko purkamisoperaatio kesti " + kesto + " ms\n\n" );
        //String luettu = tlukija.lueKoodattuTiedosto2("huffman2.dat");
//        boolean dataAlkanut = false;
//        for (int i = 0; i < luettu.length(); i++) {
//            if (!dataAlkanut && luettu.charAt(i) == '1') {
//                dataAlkanut = true;
//            }
//            if (dataAlkanut) {
//                System.out.println(luettu.charAt(i));
//            }
//        }
//        System.out.println(luettu);
//        h.puraKoodattuTiedosto2(luettu);

    }

}
