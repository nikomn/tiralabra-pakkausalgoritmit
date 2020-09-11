package tiralabra.pakkausalgoritmit.apuohjelmat;

public class BinaariMuotoilija {
    
    public String muunnaBinaariksi(String mjono) {
        String koodattu = "";
        for (int i = 0; i < mjono.length(); i++) {
            char m = mjono.charAt(i);
            koodattu = Integer.toBinaryString(m) + koodattu;
        }

        return koodattu;
    }
    
}
