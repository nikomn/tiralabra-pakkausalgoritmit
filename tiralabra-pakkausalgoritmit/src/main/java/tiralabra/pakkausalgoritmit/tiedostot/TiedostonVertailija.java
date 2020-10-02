/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit.tiedostot;



/**
 *
 * @author nikoniem
 */
public class TiedostonVertailija {
    private String alkuperainen;
    
    public TiedostonVertailija(String alkuperainenTiedosto) throws Exception {
        Tiedostonlukija tl = new Tiedostonlukija();
        this.alkuperainen = tl.lueTiedosto(alkuperainenTiedosto);
    }

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
