package tiralabra.pakkausalgoritmit.menetelmat;

import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonkirjoittaja;
import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonlukija;
import tiralabra.pakkausalgoritmit.tietorakenteet.Hajautustaulu;

/**
 *
 * @author nikoniem
 */
public class LempelZivWelch {

    private Hajautustaulu<String, Integer> omaSanakirja;
    private Integer palojenKoko;
    private String tuloste2;

    public LempelZivWelch(Integer palojenKoko) {
        this.omaSanakirja = new Hajautustaulu<>();
        this.tuloste2 = "";
        if (palojenKoko < 9) {
            this.palojenKoko = 12;
        } else {
            this.palojenKoko = palojenKoko;
        }

    }


    public String haeTuloste() {
        return this.tuloste2;
    }

    public String muunnaBittijonoksi(Integer merkkiArvo, int bittimaara) {
        return String.format("%" + bittimaara + "s", Integer.toBinaryString(merkkiArvo)).replace(' ', '0');
    }

    public void tallenna(String tiedostonNimi) {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste2, tiedostonNimi);
    }

    public String lueTiedostosta(String tiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        return tl.lueBinaaritiedosto(tiedosto);
    }

    public void pakkaa(String merkkijono) {
        
        /*
        Data esim. syötteellä 'aaaaabbbc' (huom: esimerkki on kuvattu 9 bitin 
        paloja käyttäen, vaikka usein on tarpeen käyttää suurempaa palakokoa. 
        Periaate on joka tapauksessa sama ja vain bittien määrä vaihtuisi.)
        
        Koodaus             | Tulkinta purkutilanteessa           | Sanakirja
        ----------------------------------------------------------|-------
        000000000 001100001 | -a -> a ('a')                       | 256:a
        100000000 001100001 | 256a -> a+a ('aaa')                 | 257:aa
        100000001 001100010 | 257b -> aa+b ('aaaaab')             | 258:aab
        000000000 001100010 | -b -> b ('aaaaabb')                 | 259:b
        100000011 001100011 | 259c -> bc ('aaaaabbbc')            | 260:bc
        
        
        Tässä '-' tarkoittaa, että merkkiä ei ole ennen kohdattu, ts.
        '257b'  ja sitä seuraava '-b' tarkoittaa, että on luettu merkkijonoa
        seuraavasti 
        ...[a]abb... | a on sanakirjassa, luetaan eteenpäin
        ...[aa]bb... | aa on sanakirjassa, luetaan eteenpäin
        ...[aab]b... | aab ei ole sanakirjassa lisätään se sanakirjaan ja dataan
        ...aab[b]... | b ei ole sanakirjassa lisätään se sanakirjaan ja dataan
        jne.
        
        */
        
        
        Hajautustaulu<String, Integer> omaSk = new Hajautustaulu<>();
        String koodi = "";
        StringBuilder merkkijononKoostaja = new StringBuilder();
        int demoIndeksi = 256;
        int nykyinen = merkkijono.charAt(0);
        String n = merkkijono.charAt(0) + "";
        //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
        //koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);
        // Paljon nopeampaa...
        merkkijononKoostaja.append(muunnaBittijonoksi(0, this.palojenKoko));
        merkkijononKoostaja.append(muunnaBittijonoksi(nykyinen, this.palojenKoko));

        omaSk.lisaa(n, demoIndeksi);
        demoIndeksi++;
        int i = 1;
        while (i < merkkijono.length()) {
            nykyinen = merkkijono.charAt(i);
            n = merkkijono.charAt(i) + "";
            i++;
            while (omaSk.sisaltaaAvaimen(n) && i < merkkijono.length()) {
                nykyinen = merkkijono.charAt(i);
                n = n + merkkijono.charAt(i);
                i++;
            }
            omaSk.lisaa(n, demoIndeksi);
            //System.out.println("Lisätään sanakirjan indeksiin " + demoIndeksi + " " + n);
            demoIndeksi++;
            //System.out.println("n: " + n);
            if (n.length() > 1) {
                String lahetettava = n.substring(n.length() - 1, n.length());
                String sanakirjaosa = n.substring(0, n.length() - 1);
                Integer koodiosa = omaSk.hae(sanakirjaosa);
                Integer l = (int) lahetettava.charAt(0);

                //System.out.println("Lähetetään " + koodiosa + "" + lahetettava + "(" + l + ")");
                //koodi = koodi + muunnaBittijonoksi(koodiosa, this.palojenKoko) + muunnaBittijonoksi(l, this.palojenKoko);
                // Paljon nopeampaa...
                merkkijononKoostaja.append(muunnaBittijonoksi(koodiosa, this.palojenKoko));
                merkkijononKoostaja.append(muunnaBittijonoksi(l, this.palojenKoko));
            } else {

                //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
                //koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);
                // Paljon nopeampaa...
                merkkijononKoostaja.append(muunnaBittijonoksi(0, this.palojenKoko));
                merkkijononKoostaja.append(muunnaBittijonoksi(nykyinen, this.palojenKoko));
            }

        }

        //System.out.println("Lopputulos:");
        //System.out.println(koodi);
        //this.tuloste2 = koodi;
        // Isoilla tiedostoilla indeksi voi virrata yli määritellyn palakoon,
        // esim. indeksi == 549827 -> 10000110001111000011 -> 20bittiä!
        //System.out.println("Indeksin koko: " + demoIndeksi);
        this.tuloste2 = merkkijononKoostaja.toString();

    }

    public String pura(String data, String purettuNimi) {
        
        Hajautustaulu<Integer, String> omaSk = new Hajautustaulu<>();

        int sanakirjaIndeksi = 256;
        //String purettuMerkkijono = "";
        StringBuilder merkkijononKoostaja = new StringBuilder();
        boolean dataAlkanut = false;
        int osoitin = data.length() - 1;
        if (data.charAt(osoitin) == '1') {
            dataAlkanut = true;
        }
        while (!dataAlkanut) {
            osoitin--;
            if (!dataAlkanut) {
                if (data.charAt(osoitin) == '1') {
                    dataAlkanut = true;
                }
            }
        }
        osoitin--;
        String pala1 = "";
        String pala2 = "";
        int[] osat = new int[2];
        int uusiOsa = 0;
        int laskuri = 0;
        boolean viestiAlkanut = false;
        for (int i = osoitin; i >= 0; i--) {

            pala1 = pala1 + data.charAt(i);
            //System.out.println("pala: " + pala1);

            if (pala1.length() == this.palojenKoko) {
                int sv = Integer.parseInt(pala1, 2);
                osat[laskuri] = sv;

                //System.out.println("sv: " + sv);
                pala2 = pala2 + ":" + sv;
                pala1 = "";
                laskuri++;
                //System.out.println("Nyt luettu: " + pala2);
                if (laskuri == 2) {
                    //System.out.println("Luettiin: " + pala2);
                    //System.out.println(osat[0] + " & " + osat[1]);
                    pala2 = "";
                    laskuri = 0;
                    if (osat[0] == 0) {
                        Character m = (char) osat[1];
                        //System.out.println("Vastaanottettiin merkki: " + m);
                        //purettuMerkkijono = purettuMerkkijono + m;
                        // Paljon nopeampaa...
                        merkkijononKoostaja.append(m);
                        //System.out.print(m);
                        //sk.put(sanakirjaIndeksi, m + "");
                        omaSk.lisaa(sanakirjaIndeksi, m + "");
                        sanakirjaIndeksi++;
                    } else {
                        String sanakirjaViittaus = omaSk.hae(osat[0]);
                        //String sanakirjaViittaus = sk.get(osat[0]);
                        Character m = (char) osat[1];
                        //System.out.println("Vastaanottettiin merkkijono: " + sanakirjaViittaus + m);
                        //purettuMerkkijono = purettuMerkkijono + sanakirjaViittaus + m;
                        // Paljon nopeampaa...
                        merkkijononKoostaja.append(sanakirjaViittaus);
                        merkkijononKoostaja.append(m);
                        //System.out.print(sanakirjaViittaus + m);
                        //sk.put(sanakirjaIndeksi, sanakirjaViittaus + m);
                        omaSk.lisaa(sanakirjaIndeksi, sanakirjaViittaus + m);
                        sanakirjaIndeksi++;

                    }
                    if (!viestiAlkanut && osat[0] == 0) {
                        viestiAlkanut = true;
                    }
                }

            }
        }

        //System.out.println("Purettu merkkijono: " + purettuMerkkijono);
        System.out.println("");
        Tiedostonkirjoittaja f = new Tiedostonkirjoittaja();
        //f.kirjoitaTekstiTiedostoon(purettuMerkkijono, purettuNimi);
        f.kirjoitaTekstiTiedostoon(merkkijononKoostaja.toString(), purettuNimi);
        //return purettuMerkkijono;
        return merkkijononKoostaja.toString();
    }

}
