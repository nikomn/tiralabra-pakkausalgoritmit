/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nikoniem
 */
public class Main {

    static HuffmanSolmu puunjuuri;
    static HashMap<Character, Integer> taulu;
    static HashMap<Character, HuffmanSolmu> taulu2;

    public static void muodostaTaulu(String mj) {
        HashMap<Character, Integer> taulu = new HashMap<>();
        for (int i = 0; i < mj.length(); i++) {
            char m = mj.charAt(i);
            System.out.println("Käsitellään merkkiä: " + m);
            if (!taulu.containsKey(m)) {
                taulu.put(m, 1);
            } else {
                Integer kpl = taulu.get(m);
                taulu.put(m, kpl + 1);
            }
        }

    }

    public static void muodostaTaulu2(String mj) {
        for (int i = 0; i < mj.length(); i++) {
            char m = mj.charAt(i);
            System.out.println("Käsitellään merkkiä: " + m);
            if (!taulu2.containsKey(m)) {
                taulu2.put(m, new HuffmanSolmu(m, 1, null, null));
            } else {
                taulu2.get(m).toistuvuus++;
            }
        }

    }

    public static HuffmanSolmu etsi(Character haettavaMerkki, Integer toistuvuus) {
        HuffmanSolmu solmu = puunjuuri;
        //System.out.println("Haku alkaa...");
        //System.out.println("Etsitään merkkiä " + haettavaMerkki);
        //System.out.println("Merkki toistuu aineistossa " + toistuvuus + " kertaa");
        //String koodi = "";
        while (solmu != null && solmu.merkki != haettavaMerkki) {
            //System.out.println("Koodi on nyt: " + koodi);
            System.out.println("Käsiteltävä solmu: " + solmu.merkki + ", toistuvuus: " + solmu.toistuvuus);
            if (toistuvuus < solmu.toistuvuus) {
                //koodi = 0 + koodi;
                solmu = solmu.haeVasen();
            } else {
                solmu = solmu.haeOikea();
                //koodi = 1 + koodi;
            }
        }
        return solmu;
    }

    public static String etsiJuuri(HuffmanSolmu alku) {
        //HuffmanSolmu solmu = puunjuuri;
        //System.out.println("Haku alkaa...");
        //System.out.println("Etsitään merkkiä " + haettavaMerkki);
        //System.out.println("Merkki toistuu aineistossa " + toistuvuus + " kertaa");
        HuffmanSolmu solmu = alku;

        String koodi = "";
        while (solmu != puunjuuri) {
            //System.out.println("Käsiteltävä solmu: " + solmu.merkki + "(" + solmu.toistuvuus + ")");
            //System.out.println("Koodi on nyt: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
            HuffmanSolmu vanhempi = solmu.vanhempi;
            if (vanhempi.vasen == solmu) {
                koodi = 0 + koodi;
            } else if (vanhempi.oikea == solmu) {
                koodi = 1 + koodi;
            }

            solmu = solmu.vanhempi;
//            if (solmu == puunjuuri) {
//                System.out.println("Juuri löydetty!");
//            }
        }
        System.out.println("Koodi on: " + koodi + " (alkuperäinen: " + alku.merkki + ")");
        return koodi;
    }

    public static String koodaaMerkkijono(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            System.out.println("Käsitellään merkkiä: " + m);
            if (!taulu2.containsKey(m)) {
                System.out.println("Virheellinen merkkojono!");
                return "VIRHE!";
            } else {
                //HuffmanSolmu solmu = etsi(m, taulu.get(m));
                HuffmanSolmu solmu = taulu2.get(m);
                if (solmu == null) {
                    System.out.println("Tässä on nyt joku virhe...");
                }
                //System.out.println("Löydetty solmu: " + solmu.merkki);
                koodattu = etsiJuuri(solmu) + koodattu;
            }
        }
        return koodattu;
    }

    public static String muunnaBinaariksi(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            koodattu = Integer.toBinaryString(m) + koodattu;
        }

        return koodattu;
    }

    public static void main(String[] args) throws Exception {
        Scanner tlukija = new Scanner(new File("testi.txt"));
        String mjono = "";
        while (tlukija.hasNextLine()) {
            //System.out.println(tlukija.nextLine());
            String x = tlukija.nextLine();
            mjono = mjono + x + "\n";
        }
        tlukija.close();

        //System.out.println(Integer.toBinaryString('x'));
        // TODO code application logic here
        //String mjono = "Huffman";
        //String mjono = "Tämä merkkijono on koodattu Huffman algoritmilla!";
//        String mjono = "Oletus on, että mitä enemmän merkkejä tekstissä on"
//                + ", sitä pienempään tilaan se menee. Jää vielä nähtäväksi"
//                + ", onko tosiaan niin, ainakaan tässä minun tekemässäni"
//                + " toteutuksessa, vai onko siinä jokin bugi, josta johtuen"
//                + " pakkaus ei toimi kunnolla, eikä päästä tavoitteeseen"
//                + " siitä, että pakattu tiedosto olisi n. 50% alkuperäisestä...";
//        String mjono = "Ero 'tekemisen' ja ’sallimisen’ välillä on Driverin mukaan merkittävä, koska on ainakin intuition tasolla moraalisesti tuomittavampaa tehdä aktiivisesti jotain haitalliseen lopputulokseen johtavaa, kuin vain antaa haitallisen lopputuloksen tapahtua (Driver, J: Ethics, The Fundamentals, s. 124-126). Driver esittää useita erilaisia esimerkkejä, jotka kuvaavat tilanteita, joissa tämä eroavuus ilmenee. Näissä yhdistävä tekijä on se, että kaikissa tapauksissa varsinainen negatiivinen lopputulos on sama (esimerkiksi yhtä monta kuolonuhria), mutta tapahtuma ketjuissa on erona se, tekeekö tarkasteltavana oleva henkilö itse jotakin, joka aiheuttaa haitallisen lopputuloksen (esim. hukuttaa jonkun) tai vain antaa asian tapahtua, ilman, että puuttuu itse tapahtumien kulkuun (esim. antaa henkilön hukkua). Driver tuo esille asiaan liittyen, eron negatiivisten ja positiivisten oikeuksien välillä. Negatiivisissa oikeuksissa kyse on oikeuksista, jotka liittyvät siihen, mitä yksillölle ei saa tehdä. Vastaavasti positiiviset oikeudet ovat sellaisia, joita yksilö on oikeutettu saamaan toisilta. Näissä esimerkki tapauksissa yksilön positiivisiin oikeuksiin kuuluu saada apua, jos hän on hukkumassa, ja vastaavasti negatiivinen oikeus on, että yksilöä ei saa hukuttaa. Driverin mukaan negatiivisten oikeuksien painoarvo on suurempi, kuin positiivisten, joten tämän pohjalta siis se, että joku hukuttaa toisen (negatiivisen oikeuden rikkomista) on pahempaa, kuin se, että joku antaa toisen hukkua (positiivisen oikeuden rikkomista). Kummassakin siis on jotain pahaa, mutta eroa on pahuuden määrässä tai laadussa. Driver tuo esiin myös näkemyksen, jonka mukaan negatiivisiin oikeuksiin puuttumisen ”pahuus” liittyisi siihen, että  näiden rikkominen koskee yksilön itsemääräämisoikeutta. Driverin mukaan tämän eroavuuden havaitseminen on haaste seuraamuksiin painottavissa teorioissa, joissa teon hyvyys määritellään lopputulosten perusteella. Tällaisissa teorioissa annetaan painoa negatiivisille vastuille, eli yksilö on vastuussa myös niistä negatiivisista seuraamuksista, jotka hän olisi voinut estää.\n"
//                + "Driver tuo esiin myös näkemyksiä, joiden mukaan ero tällaisten esimerkki tilanteiden välillä olisi ainakin jossain määrin teennäistä, sillä niissä usein oletetaan, että aktiivisen tekemisen taustalla on jokin paha motiivi, kun taas tapahtumisen sallimisessa taas henkilö olisi motiiveiltaan täysin neutraali. Driverin mukaan intuitioomme siitä, kuinka tuomittavana pidämme tekoa, tai tapahtumisen sallimista, vaikuttaa se, jos tiedämme tällaisesta negatiivisesta motiivista. Driver mainitsee esimerkkinä tilanteen, jossa perintöä odottava henkilö hukuttaa jonkun saadakseen perinnön ja tälle vastakohdan jossa samassa tilanteessa oleva henkilö huomaa jonkun hukkuvan, jonka kuolema toisi tälle henkilölle hänen odottamansa perinnön ja antaa tämän henkilön hukkua. Näissä tilanteissa motiivi on yhtä paha (ts. halu saada henkilö kuoliaaksi ja sitä kautta perintö omaan taskuun) ja lopputulos sama (henkilön kuolema), ja Driverin mukaan näissä tilanteissa emme voi katsoa toisen teon olevan toista parempi. Tällöin siis vaikuttaisi siltä, että ero tekemisen ja sallimisen välillä on illuusio ja moraalisesti voi olla aivan yhtä tuomittavaa tehdä tai vain sallia.\n"
//                + "Driver tuo myös esiin kritiikkiä tällaista näkemystä kohtaan, jonka mukaan tällaisissa esimerkeissä ainoa selkeä asia, mitä voidaan päätellä on, että tekeminen ja salliminen voivat molemmat olla moraalisesti tuomittavia tekoja. Tämä ei kuitenkaan poista eroavuutta näiden kahden asian välillä, siis sitä, että on eroa sillä, tekeekö itse jotain pahaa, vai antaako pahan asian vain tapahtua, koska näiden välisellä eroavuudella on tarkoitus kuvata vain asioiden suhteellista pahuutta. Molemmat teot voivat siis olla pahoja. Driverin mukaan näissä esimerkeissä useimmat olisivat siitä huolimatta sitä mieltä, että vaikka jonkun hukkumisen salliminen ilman siihen puuttumista perinnön toivossa onkin todella moraalisesti tuomittavaa, on vielä tuomittavampaa aktiivisesti hukuttaa joku perinnön toivossa.\n"
//                + "Shafer-Landaun mukaan moraalin ja uskonnon välillä on yleisesti ottaen hyvin läheinen suhde (Shafer-Landau R: The Fundamentals of Ethics, s. 58 – 70). Shafer-Landaun mukaan useat ovat jopa sitä mieltä, että ilman uskontoa moraali ei ole edes mahdollista. Tällaiset ihmiset uskovat esimerkiksi siihen, että koska Jumala jakaa tuomion pahoista teoista kuoleman jälkeen, estää tämä tieto sen, että ihminen tekisi pahoja tekoja, vaikka saisi niistä jotain hyvää maanpäälisessä elämässään. Tällä on Shafer-Landaun mukaan se seuraamus, että loppujen lopuksi tällaisten ihmisten moraalisuus on melko kyseenalainen, koska jos Jumalan tuomion pelko olisi ainoa asia, miksi he pidättäytyvät pahoista teoista, ei heitä voi pitää kovinkaan luotettavina moraalisesti, koska jos he lakkaisivat uskomasta Jumalaan, tällöin he eivät myöskään enää kokisi mitään tarvetta toimia moraalisesti.\n"
//                + "Shafer-Landaun mukaan jotkut myös uskovat, että Jumala on luonut moraalin ja ilman Jumalaa, ei voi olla moraalia. Tämä on kuitenkin kyseenalaistettavissa, kun tarkastellaan kysymystä siitä, ovatko asiat moraalisesti hyviä, koska Jumala on sitä mieltä, että näin on, vai onko Jumala sitä mieltä, koska ne ovat hyviä. Jos siis Jumala olisi luonut moraalin tyhjästä, ilman mitään perusteita tai syitä, olisi moraali tällöin täysin satunnainen asia. Tällöin moraali voisi myös muuttua kaiken aikaa, sen mukaan jos Jumala sattuu muuttamaan mieltään jonkin asian suhteen. Jos taas Jumalalla on ollut jokin peruste, miksi hän pitää jotain asiaa hyvänä, silloin tämä peruste on syy sille, että asia on hyvä, eikä Jumalaa välttämättä tarvita moraalin olemassa olon perustelemiseksi. Hyvät asiat ovat hyviä niistä syistä, joiden perusteella Jumala niitä pitää hyvinä, eikä Jumalan mielipiteellä ole vaikutusta asiaan. Jos olisi jokin peruste, miksi jokin asia on hyvä ja Jumala silti päätäisi pitää sitä pahana Jumala siis ei tällöin olisi täydellinen, vaan erehtyväinen tai vähintään häilyväinen.\n"
//                + "Shafer-Landaun mukaan myös monet ovat sitä mieltä, että uskontoa tarvitaan ohjenuorana moraalisuuteen. Jos Jumala on olemassa, on Hän kaikkitietävä myös moraaliin liittyvistä asioista. Tällöin siis uskova ihminen voi tukeutua päätöksissään Jumalan sanaan - riippumatta siitä, onko Jumala luonut moraalin vai vain todennut täydellisessä viisaudessaan mitkä asiat ovat hyviä – sillä tätä kautta on löydettävissä luotettavaa tietoa moraalisten päätösten tueksi. Tässä kuitenkin Shafer-Landau tuo esiin ongelman siitä, mihin tietolähteeseen uskovan ihmisen tulisi luottaa ja miten tulkita sitä. Uskontoja on useampia, niistä pitäisi ensinnäkin valita ”oikea”. Uskontokuntien pyhiä kirjoituksia on eri ajoilta monia ja niissä on eroja ja ristiriitoja, joten myös näiden väliltä pitäisi jotenkin pystyä valitsemaan oikea tiedonlähde. Tämän lisäksi pitää myös päättää, miten jotain pyhää tekstiä tulee tulkita, esim. nykyään saatetaan jotain raamatunkohtaa tulkita kirjaimellisesti ja jotain toista kuvainnollisesti. Eli myös tämä pitää päättää jotenkin, jos haluaa löytää luotettava tukea moraalisten päätösten taustaksi uskonnosta.\n"
//                + "Shafer-Landaun mukaan vaikka uskonnon ja moraalin välinen suhde on vahva, ei uskovainen ole kuitenkaan kovinkaan helpon ja itsestään tehtävän äärellä pohtiessaan moraalisia kysymyksiä uskonsa pohjalta. Shafer-Landaun mukaan uskovainen voi perustaa moraalinsa uskontoonsa, jos Jumala on olemassa, Jumala ylipäänsä tarjoaa moraalista ohjeistusta, uskova ihminen on valinnut perustellusti oikean pyhän kirjoituksen tietolähteekseen ja pystyy perustellusti tulkitsemaan kyseistä kirjoitusta oikealla tavalla.";
//        mjono = mjono + mjono + mjono;
        String binaarimuoto = muunnaBinaariksi(mjono);

        //HashMap<Character, Integer> taulu = muodostaTaulu(mjono);
        taulu = new HashMap<>();
        muodostaTaulu(mjono);
        taulu2 = new HashMap<>();
        muodostaTaulu2(mjono);

        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
        PriorityQueue<HuffmanSolmu> jonoYhdistetyille = new PriorityQueue<>();
        ArrayList<HuffmanSolmu> merkit = new ArrayList<>();
        System.out.println("\nTaulu:");
//        for (Character avain : taulu.keySet()) {
//            System.out.println(avain + ": " + taulu.get(avain));
//            jono.add(new HuffmanSolmu(avain, taulu.get(avain), null, null));
//        }
        for (Character avain : taulu2.keySet()) {
            System.out.println(avain + ": " + taulu2.get(avain).toistuvuus);
            jono.add(taulu2.get(avain));
        }

        System.out.println("\nPrioriteetit:");

        System.out.println("\nKirjaimet: ");

        int solmunSuuruus = 0;

        while (!jono.isEmpty()) {
            // HuffmanSolmu solmu = jono.poll();
            // System.out.println(solmu);
            HuffmanSolmu eka = jono.poll();
            HuffmanSolmu toka = null;
            if (jono.isEmpty()) {
                toka = jonoYhdistetyille.poll();
            } else {
                toka = jono.poll();
            }
            //System.out.println(eka);
            //System.out.println(toka);
            HuffmanSolmu yhdistetty = new HuffmanSolmu(null, eka.toistuvuus + toka.toistuvuus, eka, toka);
            eka.vanhempi = yhdistetty;
            toka.vanhempi = yhdistetty;
            jonoYhdistetyille.add(yhdistetty);
            System.out.println("");
            System.out.println("    (" + yhdistetty.toistuvuus + ")   ");
            System.out.println("   /    \\  ");
            System.out.println("  /      \\ ");
            String v = "";
            String o = "";
            if (eka.merkki == null) {
                v = "(" + eka.toistuvuus + ")";
            } else {
                v = "<" + eka.merkki + ":" + eka.toistuvuus + ">";
            }
            if (toka.merkki == null) {
                o = "(" + toka.toistuvuus + ")";
            } else {
                o = "<" + toka.merkki + ":" + toka.toistuvuus + ">";
            }
            System.out.println(v + "    " + o);

        }

        System.out.println("\nYhdistelmälehdet: ");
        while (solmunSuuruus != mjono.length()) {
            //HuffmanSolmu solmu = jonoYhdistetyille.poll();
            //System.out.println(solmu);
            HuffmanSolmu eka = jonoYhdistetyille.poll();
            if (eka.toistuvuus == mjono.length()) {
                puunjuuri = eka;
                break;
            }
            //System.out.println(eka);
            HuffmanSolmu toka = jonoYhdistetyille.poll();
            //System.out.println(toka);
            solmunSuuruus = eka.toistuvuus + toka.toistuvuus;
            HuffmanSolmu yhdistetty = new HuffmanSolmu(null, solmunSuuruus, eka, toka);
            if (yhdistetty.toistuvuus == mjono.length()) {
                puunjuuri = yhdistetty;
            }
            eka.vanhempi = yhdistetty;
            toka.vanhempi = yhdistetty;
            jonoYhdistetyille.add(yhdistetty);
            System.out.println("");
            System.out.println("   (" + yhdistetty.toistuvuus + ")   ");
            System.out.println("  /   \\  ");
            System.out.println(" /     \\ ");
            System.out.println("(" + eka.toistuvuus + ")    (" + toka.toistuvuus + ")");

        }

        //System.out.println("\nEtsitään 'H' ...");
        //System.out.println(etsi('H', taulu.get('H')));
        //Hakupuu p = new Hakupuu();
        //p.lisaa(solmunSuuruus);
        String koodattu = koodaaMerkkijono(mjono);
        int alkuperainen = binaarimuoto.length();
        int pakattu = koodattu.length();
        double suhde = Math.round((pakattu * 1.0) / (alkuperainen * 1.0) * 100.0);
        System.out.println("\n\n\nMerkkijono: " + mjono);
        //System.out.println("Binäärimuodossa: " + binaarimuoto);
        //System.out.println("Koodattuna: " + koodattu);
        System.out.println("");
        System.out.println("Koodaamattoman merkkijonon koko: " + alkuperainen + " bittiä");
        System.out.println("Koodatun merkkijonon koko: " + pakattu + " bittiä");

        System.out.println("\nPakattu on n." + suhde + "% alkuperäisestä.");

//        jono.add(new HuffmanSolmu('A', 10, null, null));
//        jono.add(new HuffmanSolmu('E', 15, null, null));
//        jono.add(new HuffmanSolmu('I', 12, null, null));
//        jono.add(new HuffmanSolmu('S', 3, null, null));
//        jono.add(new HuffmanSolmu('T', 4, null, null));
//        jono.add(new HuffmanSolmu('P', 13, null, null));
//        jono.add(new HuffmanSolmu('\n', 1, null, null));
//        while (!jono.isEmpty()) {
//            HuffmanSolmu solmu = jono.poll();
//            System.out.println(solmu);
//        }
//        HuffmanSolmu eka = jono.poll();
//        System.out.println("eka: " + eka);
//        HuffmanSolmu toka = jono.poll();
//        System.out.println("toka: " + toka);
//        HuffmanSolmu x = new HuffmanSolmu(null, eka.toistuvuus + toka.toistuvuus, eka, toka); // 4
//        HuffmanSolmu kolmas = jono.poll();
//        System.out.println("kolmas: " + kolmas);
//        HuffmanSolmu y = new HuffmanSolmu(null, x.toistuvuus + kolmas.toistuvuus, kolmas, x); // 8
//        HuffmanSolmu neljas = jono.poll();
//        System.out.println("neljäs: " + neljas);
//        HuffmanSolmu z = new HuffmanSolmu(null, y.toistuvuus + neljas.toistuvuus, neljas, y); // 18
//
//        HuffmanSolmu viides = jono.poll();
//        HuffmanSolmu kuudes = jono.poll();
//        HuffmanSolmu w = new HuffmanSolmu(null, viides.toistuvuus + kuudes.toistuvuus, viides, kuudes); // 25
//
//        HuffmanSolmu seitsemas = jono.poll();
//        HuffmanSolmu v = new HuffmanSolmu(null, seitsemas.toistuvuus + z.toistuvuus, seitsemas, z); // 33
//
//        HuffmanSolmu u = new HuffmanSolmu(null, w.toistuvuus + v.toistuvuus, w, v); // 58
//
//        Hakupuu p = new Hakupuu();
//        p.lisaa(eka.toistuvuus);
//        p.lisaa(toka.toistuvuus);
//        p.lisaa(x.toistuvuus);
//        p.lisaa(kolmas.toistuvuus);
//        p.lisaa(y.toistuvuus);
//        p.lisaa(neljas.toistuvuus);
//        p.lisaa(z.toistuvuus);
//        p.lisaa(viides.toistuvuus);
//        p.lisaa(kuudes.toistuvuus);
//        p.lisaa(w.toistuvuus);
//        p.lisaa(seitsemas.toistuvuus);
//        p.lisaa(v.toistuvuus);
//        p.lisaa(u.toistuvuus);
//        p.tulosta(u);
//        System.out.println("Korkeus:");
//        p.korkeus(u);
    }

}
