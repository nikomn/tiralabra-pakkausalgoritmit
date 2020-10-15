package tiralabra.pakkausalgoritmit.tiedostot;

/**
 * Luokka sisältää tiedostojen sisällön vertailuun tarvittavia apuvälineitä.
 */
public class TiedostonVertailija {

    private String alkuperainen;

    /**
     * Konstruktori
     *
     * @param alkuperainenTiedosto, merkkijono
     *
     *
     */
    public TiedostonVertailija(String alkuperainenTiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        this.alkuperainen = tl.lueTiedosto(alkuperainenTiedosto);
    }

    /**
     * Metodi vertailee tiedoston sisältöä alkuperäiseen.
     *
     * @param verrattavaTiedosto, merkkijono
     *
     * @return totuusarvo
     */
    public boolean vertaa(String verrattavaTiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        String sisalto = tl.lueTiedosto(verrattavaTiedosto);
        if (this.alkuperainen.equals(sisalto)) {
            return true;
        } else {
            return false;
        }

    }

}
