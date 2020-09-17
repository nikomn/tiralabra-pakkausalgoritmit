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

//        HuffmanSolmu z = new HuffmanSolmu('z', 10000, null, null);
//        HuffmanSolmu y = new HuffmanSolmu('y', 10000, null, null);
//        HuffmanSolmu x = new HuffmanSolmu('x', 10000, null, null);
//
//        y.vasen = x;
//        y.oikea = z;
//        y.vanhempi = x;
//        System.out.println("y:");
//        System.out.println(y.muunnaBinaariEsitysmuotoon());
//        System.out.println("Binaarimuoto tuloste...");
//
//        String binaariesitysmuoto = y.muunnaBinaariEsitysmuotoon();
//        System.out.println("Binaari:");
//        System.out.println(binaariesitysmuoto);
//        System.out.println("Kentät:");
//        System.out.println(binaariesitysmuoto.substring(0, 24));
//        System.out.println(binaariesitysmuoto.substring(24, 48));
//        System.out.println(binaariesitysmuoto.substring(48, 72));
//        System.out.println(binaariesitysmuoto.substring(72, 96));
//        System.out.println(binaariesitysmuoto.substring(96, 120));
//        System.out.println(binaariesitysmuoto.substring(120, 144));
//        System.out.println("---");
//        System.out.println("3 solmua...");
//        List<HuffmanSolmu> tmp = new ArrayList<>();
//        tmp.add(z);
//        tmp.add(x);
//        tmp.add(y);
//        for (HuffmanSolmu s : tmp) {
//            String bf = s.muunnaBinaariEsitysmuotoon();
//            System.out.println("Binaari:");
//            System.out.println(bf);
//            System.out.println("Kentät:");
//            System.out.println(bf.substring(0, 24));
//            System.out.println(bf.substring(24, 48));
//            System.out.println(bf.substring(48, 72));
//            System.out.println(bf.substring(72, 96));
//            System.out.println(bf.substring(96, 120));
//            System.out.println(bf.substring(120, 144));
//            System.out.println("---");
//
//        }
        //System.out.println(Integer.toBinaryString('x'));
        //System.out.println(Integer.toBinaryString(10000000));

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
        String koodattu = h.koodaa();
        System.out.println("Tulostetaan taulua...");
        h.tulostaTaulut();

        // Kirjoitetaan tiedostoon...
        tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "huffman.dat");

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
        String[] luettu = tlukija.lueKoodattuTiedosto("huffman.dat");
        String luettuKoodattu = luettu[luettu.length - 1];
        String subString = luettuKoodattu.substring(lkm, luettuKoodattu.length());
        //System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));
        System.out.println(subString);
        h.puraKoodattuTiedosto(luettu);

    }

}
