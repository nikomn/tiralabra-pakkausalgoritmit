package tiralabra.pakkausalgoritmit.apuohjelmat;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import tiralabra.pakkausalgoritmit.HuffmanSolmu;

public class Tiedostonkirjoittaja {

    public void kirjoitaTiedosto(String aineisto, HashMap<Character, HuffmanSolmu> merkkitaulu, String tiedostonnimi) throws Exception {
        // Formaatti, ehkä...?
        //
        // taulunpituus(tavuina)|ylihypättävät(bitti lkm)|taulu|data
        //        
        // esim.
        // 
        
        
        
        //System.out.println("Kirjoitus...");
        //System.out.println("Alkuperäinen: " + aineisto);
//        Properties properties = new Properties();
//
//        for (Map.Entry<Character, HuffmanSolmu> entry : merkkitaulu.entrySet()) {
//            properties.put(entry.getKey(), entry.getValue());
//        }
//
//        properties.store(new FileOutputStream("merkkitaulu.dat"), null);

        String kaanteinenAineisto = "";

        for (int i = aineisto.length() - 1; i >= 0; i--) {
            kaanteinenAineisto = kaanteinenAineisto + aineisto.charAt(i);
        }

        //System.out.println(kaanteinenAineisto);
        BitSet bitsetti = new BitSet(kaanteinenAineisto.length());
        int kpl = 0;
        for (Character c : kaanteinenAineisto.toCharArray()) {
            //System.out.println("c: " + c);
            if (c.equals('1')) {
                bitsetti.set(kpl);
                //System.out.println("kpl: " + kpl);
                //System.out.println(bitsetti.get(kpl));
            }
            kpl++;
        }

        try {
            DataOutputStream tuloste = new DataOutputStream(new FileOutputStream(tiedostonnimi));
            //System.out.println(merkkitaulu.toString());
            //tuloste.writeBytes(merkkitaulu.toString());
            for (byte b : bitsetti.toByteArray()) {
                System.out.println("Kirjoitetaan: " + b);

                tuloste.writeByte(b);

            }
            tuloste.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }

    }

}
