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
        long alku = System.nanoTime();
        String sisalto = tlukija.lueTiedosto("testi.txt");
        long loppu = System.nanoTime();
        if (sisalto.length() < 10000) {
            System.out.println("Luettiin: " + sisalto);
        }
        System.out.println("Tiedoston sisältö luettu!");
        long kesto = (loppu - alku) / 1000000;  // Millisekunneissa 1000000
        System.out.println("Tiedoston lukemisessa kesti " + kesto + " ms");

        double lahdeKoko = new File("testi.txt").length();

        // Huffman koodaus...
        System.out.println("");
        System.out.println("Suoritetaan Huffman-koodaus");
        System.out.println("Muodostetaan merkkitaulua");
        long pakkausAlku = System.nanoTime();
        alku = System.nanoTime();
        h.muodostaTaulu(sisalto);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Taulun muodostus kesti " + kesto + " ms");

        System.out.println("Muodostetaan Huffmanpuuta");
        alku = System.nanoTime();
        h.muodostaPuu();
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Puun muodostus kesti " + kesto + " ms");

        System.out.println("Koodataan data...");
        alku = System.nanoTime();
        String koodattu = h.koodaa();
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Datan koodaus kesti " + kesto + " ms");

        // Kirjoitetaan tiedostoon...
        System.out.println("Kirjoitetaan tiedostoon...");
        alku = System.nanoTime();
        tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "huffman.dat");
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Tiedoston kirjoittamisessa kesti " + kesto + " ms");

        long pakkausLoppu = System.nanoTime();
        kesto = (pakkausLoppu - pakkausAlku) / 1000000;  // Millisekunneissa
        System.out.println("\n\nKoko pakkausoperaatio kesti " + kesto + " ms\n\n");

        double kohdeKoko = new File("huffman.dat").length();
        double suhde = Math.round((kohdeKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
        System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
        System.out.println("Pakatun tiedoston koko: " + kohdeKoko + " tavua");

        System.out.println("\nPakattu tiedosto on n. " + suhde + "% alkuperäisestä.");

        long purkuAlku = System.nanoTime();
        System.out.println("Luetaan dataa koodatusta Huffman-tiedosta");
        alku = System.nanoTime();
        String[] luettu = tlukija.lueKoodattuTiedosto("huffman.dat");
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Huffman-tiedoston lukeminen kesti " + kesto + " ms");

        System.out.println("Muunnetaan huffman-koodattu data takaisin tekstiksi...");
        alku = System.nanoTime();
        h.puraKoodattuTiedosto(luettu);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Koodatun datan tulkinta kesti " + kesto + " ms");
        long purkuLoppu = System.nanoTime();
        kesto = (purkuLoppu - purkuAlku) / 1000000;  // Millisekunneissa
        System.out.println("\n\nKoko purkamisoperaatio kesti " + kesto + " ms\n\n");

        System.out.println("\n\nSuoritetaan sama operaation LZ algortimilla");
        pakkausAlku = System.nanoTime();
        LempelZivWelch lz = new LempelZivWelch(16);
        /*
        Huom. LempelZivWelch argumenttina annetaan käytettävä bittikoko.
        Perusidea on se, että normaaleihin kirjaimiin tarvittavaa 8 bitin kokoa
        voidaan laajentaa esim. 9 ja näin saadaan iso määrä yhdiste merkkejä
        käyttöön perusmerkistön lisäksi, ilman että tilaa kuitenkaan tarvitaan
        paljo enempää.
        Käytännössä homma toimii hyvin vain jos koodataan merkkejä a-zA-Z0-9
        mutta muilla merkeillä asia on paljon vaikeampaa, eikä merkit 
        normaalimuodossakaan mahdu 8 bittiin, esim. '—' (vrt. '-') on numeerisessa
        muodossa 8212, ts. 10000000010100, eli tarvitaan 14 bittiä.
        
        
         */
        alku = System.nanoTime();
        lz.pakkaa2(sisalto);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Koodaus kesti " + kesto + " ms");
        
        alku = System.nanoTime();
        lz.tallenna2();
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Pakatun datan tallennus kesti " + kesto + " ms");
        
        pakkausLoppu = System.nanoTime();
        kesto = (pakkausLoppu - pakkausAlku) / 1000000;  // Millisekunneissa
        System.out.println("\n\nKoko pakkausoperaatio kesti " + kesto + " ms\n\n");

        kohdeKoko = new File("lz.dat").length();
        suhde = Math.round((kohdeKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
        System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
        System.out.println("Pakatun tiedoston koko: " + kohdeKoko + " tavua");

        System.out.println("\nPakattu tiedosto on n. " + suhde + "% alkuperäisestä.");


        System.out.println("Luetaan tiedostosta...");
        purkuAlku = System.nanoTime();
        alku = System.nanoTime();

        String d = lz.lueTiedostosta("lz.dat");
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Pakatun datan lukeminen kesti " + kesto + " ms");

        alku = System.nanoTime();
        String purettuMerkkijono = lz.pura2(d);
        loppu = System.nanoTime();
        kesto = (loppu - alku) / 1000000;  // Millisekunneissa
        System.out.println("Purerun datan tulkinta kesti " + kesto + " ms");
        
        purkuLoppu = System.nanoTime();
        kesto = (purkuLoppu - purkuAlku) / 1000000;  // Millisekunneissa
        System.out.println("\n\nKoko purkamisoperaatio kesti " + kesto + " ms\n\n");

        if (purettuMerkkijono.equals(sisalto)) {
            System.out.println("Merkkijono on täsmälleen sama ennen ja jälkeen purkamisen");
        } else {
            System.out.println("Merkkijono ei ole sama ennen ja jälkeen purkamisen!");
        }

        if (purettuMerkkijono.length() < 10000) {
            System.out.println("Purettu:");
            System.out.println(purettuMerkkijono);
        }

    }

}
