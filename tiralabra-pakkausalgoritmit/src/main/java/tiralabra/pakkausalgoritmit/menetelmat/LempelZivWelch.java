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
    private Integer indeksi;
    private String tuloste;
    private Integer palojenKoko;
    private String tuloste2;

    public LempelZivWelch(Integer palojenKoko) {
        this.omaSanakirja = new Hajautustaulu<>();
        this.indeksi = 256;
        this.tuloste = "";
        if (palojenKoko < 9) {
            this.palojenKoko = 12;
        } else {
            this.palojenKoko = palojenKoko;
        }

    }

    public boolean onkoSanakirjassa(String mj) {
        if (this.omaSanakirja.sisaltaaAvaimen(mj)) {
            return true;
        } else {
            return false;
        }
    }

    public void lisaaSanakirjaan(String mj) {
        //System.out.println("Lisätään sanakirjaan '" + mj + "' paikkaan " + this.indeksi);
        this.omaSanakirja.lisaa(mj, indeksi);
        this.indeksi++;
    }

    public int haeSanakirjasta(String mj) {
        return this.omaSanakirja.hae(mj);
    }

    public int haeIndeksi() {
        return this.indeksi;
    }

    public void kirjoitaTulostetta(String bittijono) {
        this.tuloste = this.tuloste + bittijono;
    }

    public String haeTuloste() {
        return this.tuloste;
    }

    public String haeTuloste2() {
        return this.tuloste2;
    }

    public String muunnaBittijonoksi(Integer merkkiArvo, int bittimaara) {
        return String.format("%" + bittimaara + "s", Integer.toBinaryString(merkkiArvo)).replace(' ', '0');
    }

    public void tallenna() {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste, "lz.dat");
    }

    public void tallenna2(String tiedostonNimi) {
        Tiedostonkirjoittaja tk = new Tiedostonkirjoittaja();
        tk.kirjoitaTiedostoon(this.tuloste2, tiedostonNimi);
    }

    public String lueTiedostosta(String tiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        return tl.lueBinaaritiedosto(tiedosto);
    }

    public void pakkaa2(String merkkijono) {
        Hajautustaulu<String, Integer> omaSk = new Hajautustaulu<>();
        String koodi = "";
        int demoIndeksi = 256;
        int nykyinen = merkkijono.charAt(0);
        String n = merkkijono.charAt(0) + "";
        //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
        koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);

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
                koodi = koodi + muunnaBittijonoksi(koodiosa, this.palojenKoko) + muunnaBittijonoksi(l, this.palojenKoko);
            } else {

                //System.out.println("Lähetetään e" + n + "(" + nykyinen + ")");
                koodi = koodi + muunnaBittijonoksi(0, this.palojenKoko) + muunnaBittijonoksi(nykyinen, this.palojenKoko);
            }

        }

        //System.out.println("Lopputulos:");
        //System.out.println(koodi);
        this.tuloste2 = koodi;

    }

    public String pura2(String data, String purettuNimi) {
        Hajautustaulu<Integer, String> omaSk = new Hajautustaulu<>();
        int sanakirjaIndeksi = 256;
        String purettuMerkkijono = "";
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
                        purettuMerkkijono = purettuMerkkijono + m;
                        //System.out.print(m);
                        //sk.put(sanakirjaIndeksi, m + "");
                        omaSk.lisaa(sanakirjaIndeksi, m + "");
                        sanakirjaIndeksi++;
                    } else {
                        String sanakirjaViittaus = omaSk.hae(osat[0]);
                        //String sanakirjaViittaus = sk.get(osat[0]);
                        Character m = (char) osat[1];
                        //System.out.println("Vastaanottettiin merkkijono: " + sanakirjaViittaus + m);
                        purettuMerkkijono = purettuMerkkijono + sanakirjaViittaus + m;
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
        f.kirjoitaTekstiTiedostoon(purettuMerkkijono, purettuNimi);
        return purettuMerkkijono;
    }

}
