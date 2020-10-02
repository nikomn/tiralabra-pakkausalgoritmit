package tiralabra.pakkausalgoritmit.tiedostot;

/**
 * Luokka tarjoaa aputoiminnallisuuden, jossa merkkijono muunnetaan
 * binäärimerkkijonoksi.
 */
public class BinaariMuotoilija {

    /**
     * Metodi muotoilee merkkijonon nollista ja ykkösistä koostuvaksi merkkijonoksi.
     *
     * @param mjono merkkijono
     *
     *
     *
     * @return nollista ja ykkösistä koostuva merkkijono
     */
    public String muunnaBinaariksi(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            koodattu = Integer.toBinaryString(m) + koodattu;
        }

        return koodattu;
    }

}
