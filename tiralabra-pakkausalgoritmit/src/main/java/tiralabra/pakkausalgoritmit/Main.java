package tiralabra.pakkausalgoritmit;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import tiralabra.pakkausalgoritmit.apuohjelmat.BinaariMuotoilija;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.apuohjelmat.Tiedostonlukija;

public class Main {

    public static void main(String[] args) throws Exception {
        
        HuffmanSolmu z = new HuffmanSolmu('z', 10000, null, null);
        HuffmanSolmu y = new HuffmanSolmu('y', 10000, null, null);
        HuffmanSolmu x = new HuffmanSolmu('x', 10000, null, null);
        y.vasen = x;
        y.oikea = z;
        y.vanhempi = x;
        System.out.println("y:");
        System.out.println(y.muunnaBinaariEsitysmuotoon());
        System.out.println("Binaarimuoto tuloste...");
        
        String binaariesitysmuoto = y.muunnaBinaariEsitysmuotoon();
        System.out.println("Binaari:");
        System.out.println(binaariesitysmuoto);
        System.out.println("Kentät:");
        System.out.println(binaariesitysmuoto.substring(0, 24));
        System.out.println(binaariesitysmuoto.substring(24, 48));
        System.out.println(binaariesitysmuoto.substring(48, 72));
        System.out.println(binaariesitysmuoto.substring(72, 96));
        System.out.println(binaariesitysmuoto.substring(96, 120));
        
        System.out.println("---");
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
        System.out.println("Tiedoston sisältö luettu!");

        // Binääriformaattiin muuntaminen, lähinnä testailua varten tässä vaiheessa...
        System.out.println("Muunnetaan vertailun vuoksi tiedoston sisältö 'binäärimuotoon'...");
        String binaarimuoto = binaarimuuntaja.muunnaBinaariksi(sisalto);
        System.out.println("Muunnos valmis!");

        // Huffman koodaus...
        h.muodostaTaulu(sisalto);
        h.muodostaPuu();
        String koodattu = h.koodaa();
        
        // Kirjoitetaan tiedostoon...
        tkirjoittaja.kirjoitaTiedosto(koodattu, h.getTaulu(), "huffman.dat");

        // Lopputulokset...
        int alkuperainen = binaarimuoto.length();  // Ei vastaa todellista kokoa, mutta lienee riittävän lähellä?
        int pakattu = koodattu.length();
        double suhde = Math.round((pakattu * 1.0) / (alkuperainen * 1.0) * 100.0);

        // Debugausta varten...
        //System.out.println("\n\n\nMerkkijono: " + mjono);
        //System.out.println("Binäärimuodossa: " + binaarimuoto);
        System.out.println("Koodattuna: " + koodattu);
        System.out.println("");
        System.out.println("Koodaamattoman merkkijonon koko: " + alkuperainen + " bittiä");
        System.out.println("Koodatun merkkijonon koko: " + pakattu + " bittiä");

        System.out.println("\nPakattu on n. " + suhde + "% alkuperäisestä.");
        
        System.out.println("Tiedostosta luettuna: ");
        System.out.println(tlukija.lueKoodattuTiedosto("huffman.dat"));

    }

}
