package tiralabra.pakkausalgoritmit.tiedostot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Luokka sisältää tiedostojen lukemiseen tarvittavat toiminnot.
 */
public class Tiedostonlukija {

    /**
     * Metodi lukee tavallisen tekstitiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi (ja polku)
     * @throws Exception
     *
     * @return merkkijono
     */
    public String lueTiedosto(String tiedosto) throws Exception {
        String mjono = "";
        StringBuilder merkkijononKoostaja = new StringBuilder();
        try {
            Scanner tlukija = new Scanner(new File(tiedosto));
            //System.out.println("Luetaan tiedostoa...");
            while (tlukija.hasNextLine()) {
                //System.out.println(tlukija.nextLine());
                String x = tlukija.nextLine();
                //mjono = mjono + x + "\n";
                merkkijononKoostaja.append(x + "\n");
            }
            tlukija.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            //mjono = "Tiedostoa ei voitu lukea!";
            //merkkijononKoostaja.append("")
            return "Tiedostoa ei voitu lukea!";
        }

        //return mjono;
        return merkkijononKoostaja.toString();
    }

    /**
     * @deprecated Metodi lukee minkä tahansa tiedoston sisällön tavuina ja
     * muuntaa ne merkeiksi. Ei käytössä, koska ei toimi sellaisenaan muiden
     * ohjelmanosien kanssa. Mahdollisesti hyödyllinen jatkokehitystä ajatellen.
     *
     *
     * @param tiedosto tiedoston nimi (ja polku)
     * @throws Exception
     *
     * @return merkkijono
     */
    @Deprecated
    public String lueTiedostoTavuina(String tiedosto) throws Exception {
        StringBuilder asciiIlmiasu = new StringBuilder();
        try {
            byte[] tavut = Files.readAllBytes(Paths.get(tiedosto));
            for (int i = 0; i < tavut.length; i++) {
                System.out.println("Luettu bitteinä: " + Integer.toBinaryString(tavut[i] & 0xFF));
                System.out.println("Luettu numerona: " + Integer.parseInt(Integer.toBinaryString(tavut[i] & 0xFF), 2));
                System.out.println("Luettu merkkinä: " + ((char) Integer.parseInt(Integer.toBinaryString(tavut[i] & 0xFF), 2)));
                String asciiMerkkiString = String.format("%8s", Integer.toBinaryString(tavut[i] & 0xFF)).replace(' ', '0');
                System.out.println("String muoto: " + asciiMerkkiString);
                int asciiMerkkiNumero = Integer.parseInt(asciiMerkkiString, 2);
                Character asciiMerkki = (char) asciiMerkkiNumero;
                asciiIlmiasu.append(asciiMerkki);

                /*
                0 tekee hankalaksi toteuttaa pelkästään tällaisella yksinkertaisella
                muokkauksella... 0 merkkinä on "", eli ei mitään, eli se katoaa
                datasta jos käsitellään merkki muodossa...
                
                Toisaalta sekään ei tässä auta, jos sen muuttaisi joksikin muuksi,
                koska silloin pitäisi kaikkialla missä dataa kirjoitetaan takaisin 
                tavumuotoon muuntaa se takaisin muotoon 0, jolloin taas ohjelma
                ei enää toimisi alkuperäisellä syötteellä (merkkijonot) oikein...
                
                Tällainen muokkaus siis aiheuttaisi ikävän muokkausten "haitariliikkeen"
                kaikkialle koodiin, koska molemmat pakkausalgoritmit aloittavat
                pakattavan datan hakemisella...
                 */
                if (asciiMerkkiNumero == 0) {
                    // Purkka ja jeesari viritelmä... ugh...
                    asciiMerkkiNumero = Integer.parseInt("100000000", 2);
                    //asciiMerkkiNumero = Integer.parseInt(asciiMerkkiString, 2);
                    asciiMerkki = (char) asciiMerkkiNumero;
                    asciiIlmiasu.append(asciiMerkki);
                    //System.out.println("Merkkijono on nyt: " + asciiIlmiasu.toString());
                }

            }
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen ei onnistunut!");
        }

        return asciiIlmiasu.toString();

    }

    /**
     * Metodi lukee binäärimuotoisen LZW pakatun tiedoston.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     * @throws Exception
     *
     * @return nollista ja ykkösistä koostuva merkkijono
     */
    public String lueBinaaritiedosto(String tiedosto) throws Exception {
        StringBuilder merkkijononKoostaja = new StringBuilder();

        String mjono = "";
        try {
            byte[] bitit = Files.readAllBytes(Paths.get(tiedosto));
            int bittiMaara = bitit.length;
            int kasiteltavaMerkkiLkm = 0;
            double prosenttiKokkonaisuudesta = bittiMaara / 100;
            for (int i = 0; i < bitit.length; i++) {
                kasiteltavaMerkkiLkm++;
                if (prosenttiKokkonaisuudesta > 0 && kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                    double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (bittiMaara * 1.0) * 100.0);
                    System.out.println(prosentti + "%" + " luettu...");
                }
                //mjono = String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0') + mjono;
                //merkkijononKoostaja.insert(0, String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));

                // nopeampaa...
                //merkkijononKoostaja.append(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                String uusi = new StringBuilder(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0')).reverse().toString();

                merkkijononKoostaja.append(uusi);
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "-1";
            return "-1";
        }

        //return mjono;
        //return merkkijononKoostaja.toString();
        // Paljon nopeampaa...
        return new StringBuilder(merkkijononKoostaja.toString()).reverse().toString();

    }

    /**
     * Metodi erottelee Huffman algoritmilla pakatusta tiedostosta luetut bitit
     * määrämittaisiin kenttiin.
     *
     * @param merkkijono nollista ja ykkösistä koostuva merkkijono
     * 
     *
     * @return nollista ja ykkösistä koostuvia merkkijonoja sisältävä taulukko
     */
    public String[] erotteleKentat(String merkkijono) {
        int lohkojenMaara = merkkijono.length() / 24;
        String[] dataLohkot = new String[lohkojenMaara];

        String x = "";
        int indeksi = 0;
        for (int i = 0; i < merkkijono.length(); i++) {
            x = x + merkkijono.charAt(i);
            if (x.length() == 24) {
                //System.out.println("x: " + x);
                dataLohkot[indeksi] = x;
                x = "";
                indeksi++;
            }
        }

        return dataLohkot;
    }

    /**
     * Metodi lukee Huffman algoritmilla koodatun tiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     * @throws Exception
     *
     * @return nollista ja ykkösistä koostuvia merkkijonoja sisältävä taulukko
     */
    public String[] lueKoodattuTiedosto(String tiedosto) throws Exception {
        StringBuilder merkkijononKoostaja = new StringBuilder();
        String mjono = "";
        try {
            byte[] bitit = Files.readAllBytes(Paths.get(tiedosto));
            //System.out.println(Arrays.toString(bitit));

            int bittiMaara = bitit.length;
            int kasiteltavaMerkkiLkm = 0;
            double prosenttiKokkonaisuudesta = bittiMaara / 100;
            for (int i = 0; i < bitit.length; i++) {
                kasiteltavaMerkkiLkm++;
                if (prosenttiKokkonaisuudesta > 0 && kasiteltavaMerkkiLkm % prosenttiKokkonaisuudesta == 0) {
                    double prosentti = Math.round((kasiteltavaMerkkiLkm * 1.0) / (bittiMaara * 1.0) * 100.0);
                    System.out.println(prosentti + "%" + " luettu...");
                }
                //System.out.println("bitti: " + bitit[i]);
                //System.out.println(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                //mjono = String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0') + mjono;

                //merkkijononKoostaja.insert(0, String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                // Paljon nopeampaa!
                String uusi = new StringBuilder(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0')).reverse().toString();

                merkkijononKoostaja.append(uusi);
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "-1";
        }

        //String[] kentat = erotteleKentat(mjono);
        System.out.println("Erotellaan datakenttiä...");
        //String kaanteinenAineisto = new StringBuilder(merkkijononKoostaja.toString()).reverse().toString();
        //String[] kentat = erotteleKentat(merkkijononKoostaja.toString());
        // paljon nopeampaa...
        String[] kentat = erotteleKentat(new StringBuilder(merkkijononKoostaja.toString()).reverse().toString());

        return kentat;
        //return mjono;
    }

    /**
     * Metodi lukee Huffman algoritmilla koodatun tiedoston sisällön.
     *
     * @param tiedosto tiedoston nimi ja sijainti
     * @throws Exception
     *
     * @return nollista ja ykkösistä koostuva merkkijono
     */
    public String lueKoodattuTiedosto2(String tiedosto) throws Exception {
        String mjono = "";
        StringBuilder merkkijononKoostaja = new StringBuilder();
        try {
            byte[] bitit = Files.readAllBytes(Paths.get(tiedosto));
            //System.out.println(Arrays.toString(bitit));
            for (int i = 0; i < bitit.length; i++) {
                //System.out.println("bitti: " + bitit[i]);
                //System.out.println(String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));
                //mjono = String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0') + mjono;
                merkkijononKoostaja.insert(0, String.format("%8s", Integer.toBinaryString(bitit[i] & 0xFF)).replace(' ', '0'));

            }
        } catch (Exception e) {
            System.out.println("Virhe tiedoston lukemisessa!");
            mjono = "-1";
        }

        //String[] kentat = erotteleKentat(mjono);
        return merkkijononKoostaja.toString();
    }

}
