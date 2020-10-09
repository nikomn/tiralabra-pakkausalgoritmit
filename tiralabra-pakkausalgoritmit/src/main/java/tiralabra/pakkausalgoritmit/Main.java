package tiralabra.pakkausalgoritmit;

import tiralabra.pakkausalgoritmit.kayttoliittyma.Kayttoliittyma;
//import tiralabra.pakkausalgoritmit.tiedostot.Tiedostonlukija;

public class Main {

    public static void main(String[] args) throws Exception {
//        Tiedostonlukija tl = new Tiedostonlukija();
//        String testi = tl.lueTiedostoTavuina("asciitesti.png");
//        System.out.println("Tiedoston testi.txt ascii ilmiasu:");
//        System.out.println(testi);
        Kayttoliittyma k = new Kayttoliittyma();
        k.kaynnista();
    }
}