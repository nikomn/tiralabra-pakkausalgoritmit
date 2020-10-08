/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit.kayttoliittyma;

import java.io.File;
import java.util.Scanner;
import tiralabra.pakkausalgoritmit.Kello;
import tiralabra.pakkausalgoritmit.menetelmat.Huffman;
import tiralabra.pakkausalgoritmit.menetelmat.LempelZivWelch;
import tiralabra.pakkausalgoritmit.tiedostot.TiedostonVertailija;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonlukija;

/**
 *
 * @author nikoniem
 */
public class Kayttoliittyma {

    public void demo(Scanner lukija) throws Exception {
        // Työvälineiden alustus
        Tiedostonlukija tlukija = new Tiedostonlukija();
        Huffman h = new Huffman();
        Tiedostonkirjoittaja tkirjoittaja = new Tiedostonkirjoittaja();
        Kello tyovaiheAjastin = new Kello();
        Kello huffmanAjastin = new Kello();
        Kello lzAjastin = new Kello();

        // Tilastodataa...
        long[] huffmanPakkausAjat = new long[5];
        long[] huffmanPurkuAjat = new long[3];
        long[] lzPakkausAjat = new long[3];
        long[] lzPurkuAjat = new long[3];
        double lahdeKoko = 0.0;
        double huffmanKoko = 0.0;
        double huffmanSuhde = 0.0;
        double lzKoko = 0.0;
        double lzSuhde = 0.0;

        // Alkuperäisen datan lukeminen
        System.out.println("Mikä tiedosto pakataan?: ");
        String tiedosto = lukija.nextLine();
        if (!tiedosto.endsWith(".txt")) {
            System.out.println("Väärä tiedostomuoto! Ohjelma on suunniteltu ainoastaan "
                    + "tekstimuotoisen datan (ts. tekstitiedostojen) käsittelyyn! "
                    + "Muussa muodossa olevaa dataa ei pystytä pakkaamaan.");
        } else {
            tyovaiheAjastin.kaynnista();
            String sisalto = tlukija.lueTiedosto(tiedosto);
            long kesto = tyovaiheAjastin.pysayta();

            if (sisalto.length() < 10000) {     // Turha tulostaa, jos pitkä merkkijono...
                System.out.println("Luettiin: " + sisalto);
            }
            System.out.println("Tiedoston sisältö luettu!");
            System.out.println("Tiedoston lukemisessa kesti " + kesto + " ms");

            lahdeKoko = new File(tiedosto).length();

            // Huffman koodaus...
            System.out.println("");
            System.out.println("Suoritetaan Huffman-koodaus");

            System.out.println("Muodostetaan merkkitaulua "
                    + "(ts. lasketaan merkkien esiintymismäärät)");
            huffmanAjastin.kaynnista();

            tyovaiheAjastin.kaynnista();
            h.muodostaTaulu(sisalto);
            kesto = tyovaiheAjastin.pysayta();
            huffmanPakkausAjat[1] = kesto;     // 1: taulun muodostaminen
            System.out.println("Taulun muodostus kesti " + kesto + " ms");

            System.out.println("Muodostetaan Huffmanpuuta");
            tyovaiheAjastin.kaynnista();
            h.muodostaPuu();
            kesto = tyovaiheAjastin.pysayta();
            huffmanPakkausAjat[2] = kesto;     // 2: puun muodostaminen
            System.out.println("Puun muodostus kesti " + kesto + " ms");

            System.out.println("Koodataan data...");
//        tyovaiheAjastin.kaynnista();
//        String muunnettu = h.muunna();
//        kesto = tyovaiheAjastin.pysayta();
//        System.out.println("Datan koodaus uudella tavalla kesti " + kesto + " ms");

            tyovaiheAjastin.kaynnista();
            String koodattu = h.koodaa();
            kesto = tyovaiheAjastin.pysayta();
            huffmanPakkausAjat[3] = kesto;     // 3: Datan koodaus
            System.out.println("Datan koodaus kesti " + kesto + " ms");

            // Kirjoitetaan tiedostoon...
            System.out.println("Kirjoitetaan tiedostoon...");
            tyovaiheAjastin.kaynnista();
            tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), "huffman.dat");
            kesto = tyovaiheAjastin.pysayta();
            huffmanPakkausAjat[4] = kesto;     // 4: tiedostoon kirjoittaminen
            System.out.println("Tiedoston kirjoittamisessa kesti " + kesto + " ms");

            kesto = huffmanAjastin.pysayta();
            huffmanPakkausAjat[0] = kesto;     // 0: koko pakkausoperaatio yht.
            System.out.println("\n\nKoko pakkausoperaatio kesti " + kesto + " ms\n\n");
            huffmanAjastin.nollaa();

            huffmanKoko = new File("huffman.dat").length();
            huffmanSuhde = Math.round((huffmanKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
            System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
            System.out.println("Pakatun tiedoston koko: " + huffmanKoko + " tavua");
            System.out.println("\nPakattu tiedosto on n. " + huffmanSuhde + "% alkuperäisestä.");

            // Huffman tiedoston purkaminen...
            huffmanAjastin.kaynnista();
            System.out.println("Luetaan dataa koodatusta Huffman-tiedosta");
            tyovaiheAjastin.kaynnista();
            String[] luettu = tlukija.lueKoodattuTiedosto("huffman.dat");
            kesto = tyovaiheAjastin.pysayta();
            huffmanPurkuAjat[1] = kesto;     // 1: huffman datan lukeminen
            System.out.println("Huffman-tiedoston lukeminen kesti " + kesto + " ms");

            System.out.println("Muunnetaan huffman-koodattu data takaisin tekstiksi...");
            tyovaiheAjastin.kaynnista();
            h.puraKoodattuTiedosto(luettu, "huffman_purettu.txt");
            kesto = tyovaiheAjastin.pysayta();
            huffmanPurkuAjat[2] = kesto;     // 2: huffman koodatun datan tulkitseminen
            System.out.println("Koodatun datan tulkinta kesti " + kesto + " ms");
            kesto = huffmanAjastin.pysayta();
            huffmanPurkuAjat[0] = kesto;     // 0: pakatun datan purkaminen yht.
            System.out.println("\n\nKoko purkamisoperaatio kesti " + kesto + " ms\n\n");

            System.out.println("\n\nSuoritetaan pakkaus ja purkaminen LZ algortimilla");
            lzAjastin.kaynnista();

            // Jos tosi iso tiedosto?
            // Demoa ajatellen on ehkä ihan hyvä, jos pystytään muuttamaan dynaamisemmin...
            // Muodostuu ongelmaksi, jos pakataan iso tiedosto demon yhteydessä ja yritetään
            // Purkaa se myöhemmin erikseen erillisellä toiminnolla...
            int palankoko = 18;
            if (lahdeKoko > 2000000) {
                palankoko = 20;
            }

            LempelZivWelch lz = new LempelZivWelch(palankoko);
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

            Perus ascii merkeillä arvoksi riittää 8-12, mutta jos käytössä on
            utf-8 merkistö, niin 17-20 bittiä tuottaa oikean tuloksen. Tällä tietty
            hintana tiedoston pakkaustiheyden heikkeneminen.
             */
            tyovaiheAjastin.kaynnista();
            lz.pakkaa(sisalto);
            kesto = tyovaiheAjastin.pysayta();
            lzPakkausAjat[1] = kesto;     // 1: pakkaus
            System.out.println("Koodaus kesti " + kesto + " ms");

            tyovaiheAjastin.kaynnista();
            lz.tallenna("lz.dat");
            kesto = tyovaiheAjastin.pysayta();
            lzPakkausAjat[2] = kesto;     // 2: tiedostoon tallennus
            System.out.println("Pakatun datan tallennus kesti " + kesto + " ms");

            kesto = lzAjastin.pysayta();
            lzPakkausAjat[0] = kesto;     // 0: koko lz pakkauksen kesto yht.
            System.out.println("\n\nKoko pakkausoperaatio kesti " + kesto + " ms\n\n");

            lzKoko = new File("lz.dat").length();
            lzSuhde = Math.round((lzKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
            System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
            System.out.println("Pakatun tiedoston koko: " + lzKoko + " tavua");
            System.out.println("\nPakattu tiedosto on n. " + lzSuhde + "% alkuperäisestä.");

            lzAjastin.nollaa();
            lzAjastin.kaynnista();
            System.out.println("Puretaan lz pakattu tiedosto...");

            System.out.println("Luetaan tiedostosta...");
            tyovaiheAjastin.kaynnista();

            String d = lz.lueTiedostosta("lz.dat");
            kesto = tyovaiheAjastin.pysayta();
            lzPurkuAjat[1] = kesto;     // 1: pakatun tiedoston lukeminen
            System.out.println("Pakatun datan lukeminen kesti " + kesto + " ms");

            tyovaiheAjastin.kaynnista();
            String purettuMerkkijono = lz.pura(d, "lz_purettu.txt");
            kesto = tyovaiheAjastin.pysayta();
            lzPurkuAjat[2] = kesto;     // 2: pakatun datan tulkinta
            System.out.println("Purerun datan tulkinta kesti " + kesto + " ms");

            kesto = lzAjastin.pysayta();
            lzPurkuAjat[0] = kesto;     // 0: lz pakatun datan purkaminen yht.
            System.out.println("\n\nKoko purkamisoperaatio kesti " + kesto + " ms\n\n");

            System.out.println("\n ### YHTEENVETO ###\n");
            System.out.println("\nPakkaustehokkuus\n");
            System.out.println("tiedosto\t| koko (tavua)\t| pakkaussuhde");
            System.out.println("----------------|---------------|--------------");
            System.out.println("alkuperäinen\t| " + lahdeKoko + "\t| 100%");
            System.out.println("huffman     \t| " + huffmanKoko + "\t| " + huffmanSuhde + "%");
            System.out.println("lz          \t| " + lzKoko + "\t| " + lzSuhde + "%");

            System.out.println("\nNopeudet\n");
            System.out.println("operaatio\t\t| kesto");
            System.out.println("------------------------|---------");
            System.out.println("huffman pakkaus\t\t| " + huffmanPakkausAjat[0] + " ms.");
            System.out.println("huffman purku\t\t| " + huffmanPurkuAjat[0] + " ms.");
            System.out.println("lz pakkaus\t\t| " + lzPakkausAjat[0] + " ms.");
            System.out.println("lz purku\t\t| " + lzPurkuAjat[0] + " ms.");

            System.out.println("\nTiedostojen tarkistus\n");
            TiedostonVertailija tv = new TiedostonVertailija(tiedosto);
            boolean huffmanOK = tv.vertaa("huffman_purettu.txt");
            boolean lzOK = tv.vertaa("lz_purettu.txt");
            if (!huffmanOK) {
                System.out.println("!VIRHE! Huffman algoritmilla pakatun datan käsittely epäonnistui");
                System.out.println("Purettu tiedosto ei ole täsmälleen samanlainen kuin alkuperäinen!");
            } else {
                System.out.println("Huffman pakattu tiedosto on purkamisen jälkeen "
                        + "täsmälleen samanlainen kuin alkuperäinen. Kaikki OK!");
            }
            if (!lzOK) {
                System.out.println("!VIRHE! LZ algoritmilla pakatun datan käsittely epäonnistui");
                System.out.println("Purettu tiedosto ei ole täsmälleen samanlainen kuin alkuperäinen!");
            } else {
                System.out.println("LZ pakattu tiedosto on purkamisen jälkeen "
                        + "täsmälleen samanlainen kuin alkuperäinen. Kaikki OK!");
            }
            System.out.println("\n #########\n");
        }

        System.out.println("Jatka painamalla Enter...");
        String jatka = lukija.nextLine();

    }

    public void pakkaa(Scanner lukija) throws Exception {
        while (true) {

            System.out.println("Valitse käytettävä pakkausalgoritmi"
                    + "\n  1. Huffman"
                    + "\n  2. LZW"
                    + "\n  x. Peruuta");
            String valinta = lukija.nextLine();
            if (valinta.equals("1")) {
                System.out.println("Mikä tiedosto pakataan?");
                String tiedosto = lukija.nextLine();
                if (!tiedosto.endsWith(".txt")) {
                    System.out.println("Väärä tiedostomuoto! Ohjelma on suunniteltu ainoastaan "
                            + "tekstimuotoisen datan (ts. tekstitiedostojen) käsittelyyn! "
                            + "Muussa muodossa olevaa dataa ei pystytä pakkaamaan.");
                    System.out.println("Jatka painamalla Enter...");
                    String jatka = lukija.nextLine();
                } else {
                    System.out.println("");
                    System.out.println("Mikä nimi pakatulle tiedostolle?");
                    String pakattuNimi = lukija.nextLine();
                    pakattuNimi = pakattuNimi + ".huffdat";

                    Tiedostonlukija tlukija = new Tiedostonlukija();
                    String sisalto = tlukija.lueTiedosto(tiedosto);

                    Huffman h = new Huffman();
                    h.muodostaTaulu(sisalto);
                    h.muodostaPuu();
                    String koodattu = h.koodaa();

                    Tiedostonkirjoittaja tkirjoittaja = new Tiedostonkirjoittaja();
                    tkirjoittaja.kirjoitaTiedosto(koodattu, h.haePuunjuuri(), h.haePuu(), h.haeIndeksi(), h.getTaulu(), pakattuNimi);
                    double lahdeKoko = new File(tiedosto).length();
                    double huffmanKoko = new File(pakattuNimi).length();
                    double huffmanSuhde = Math.round((huffmanKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
                    System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
                    System.out.println("Pakatun tiedoston koko: " + huffmanKoko + " tavua");
                    System.out.println("Pakattu tiedosto on n. " + huffmanSuhde + "% alkuperäisestä.");
                    System.out.println("");
                }

            }
            if (valinta.equals("2")) {
                System.out.println("Mikä tiedosto pakataan?");
                String tiedosto = lukija.nextLine();
                if (!tiedosto.endsWith(".txt")) {
                    System.out.println("Väärä tiedostomuoto! Ohjelma on suunniteltu ainoastaan "
                            + "tekstimuotoisen datan (ts. tekstitiedostojen) käsittelyyn! "
                            + "Muussa muodossa olevaa dataa ei pystytä pakkaamaan.");
                    System.out.println("Jatka painamalla Enter...");
                    String jatka = lukija.nextLine();
                } else {
                    System.out.println("");
                    System.out.println("Mikä nimi pakatulle tiedostolle: ");
                    String pakattuNimi = lukija.nextLine();
                    pakattuNimi = pakattuNimi + ".lzdat";

                    Tiedostonlukija tlukija = new Tiedostonlukija();
                    String sisalto = tlukija.lueTiedosto(tiedosto);

                    LempelZivWelch lz = new LempelZivWelch(18);
                    lz.pakkaa(sisalto);
                    lz.tallenna(pakattuNimi);

                    double lahdeKoko = new File(tiedosto).length();
                    double lzKoko = new File(pakattuNimi).length();
                    double lzSuhde = Math.round((lzKoko * 1.0) / (lahdeKoko * 1.0) * 100.0);
                    System.out.println("Alkuperäisen tiedoston koko: " + lahdeKoko + " tavua");
                    System.out.println("Pakatun tiedoston koko: " + lzKoko + " tavua");
                    System.out.println("Pakattu tiedosto on n. " + lzSuhde + "% alkuperäisestä.");
                    System.out.println("");
                }

            }

            if (valinta.equals("x")) {
                break;
            }

        }

    }

    public void pura(Scanner lukija) throws Exception {
        while (true) {
            System.out.println("Millä algoritmilla data on pakattu?"
                    + "\n  1. Huffman"
                    + "\n  2. LZW"
                    + "\n  x. Peruuta");
            String valinta = lukija.nextLine();
            if (valinta.equals("1")) {
                System.out.println("Mikä tiedosto puretaan?");
                String tiedosto = lukija.nextLine();
                System.out.println("Mikä nimi puretulle tiedostolle?");
                String purettuNimi = lukija.nextLine();

                Tiedostonlukija tlukija = new Tiedostonlukija();
                String[] luettu = tlukija.lueKoodattuTiedosto(tiedosto);

                Huffman h = new Huffman();
                h.puraKoodattuTiedosto(luettu, purettuNimi);

                System.out.println("HUffman tiedosto purettu");

            }
            if (valinta.equals("2")) {
                LempelZivWelch lz = new LempelZivWelch(18);
                System.out.println("Mikä tiedosto puretaan?");
                String tiedosto = lukija.nextLine();
                System.out.println("Mikä nimi puretulle tiedostolle?");
                String purettuNimi = lukija.nextLine();

                Tiedostonlukija tlukija = new Tiedostonlukija();
                String d = lz.lueTiedostosta(tiedosto);
                String purettuMerkkijono = lz.pura(d, purettuNimi);
                System.out.println("LZ tiedosto purettu");
            }

            if (valinta.equals("x")) {
                break;
            }

        }

    }

    public void kaynnista() throws Exception {
        Scanner lukija = new Scanner(System.in);
        Kayttoliittyma kl = new Kayttoliittyma();
        while (true) {
            System.out.println("Tekstitiedostojen pakkaus- ja pakkausalgoritmien "
                    + "tehokkuuden vertailuohjelma.");
            System.out.println("Valitse toiminto"
                    + "\n  1. Demo: Pakataan ja puretaan tiedosto Huffman ja LZ algoritmeilla"
                    + "\n  2. Pakkaus"
                    + "\n  3. Purkaminen"
                    + "\n  x. Sammuta ohjelma");
            String valinta = lukija.nextLine();
            if (valinta.equals("1")) {
                kl.demo(lukija);
            }
            if (valinta.equals("2")) {
                kl.pakkaa(lukija);
            }
            if (valinta.equals("3")) {
                kl.pura(lukija);
            }
            if (valinta.equals("x")) {
                break;
            }

        }

    }

}
