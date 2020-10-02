/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit.tietorakenteet;

/**
 *
 * @author nikoniem
 */
public class Pari<K, V> {
    private K avain;
    private V arvo;
    
    public Pari(K avain, V arvo) {
        this.avain = avain;
        this.arvo = arvo;
    }
    
    public K haeAvain() {
        return this.avain;
    }
    
    public void asetaAvain(K avain) {
        this.avain = avain;
    }
    
    public V haeArvo() {
        return this.arvo;
    }
    
    public void asetaArvo(V arvo) {
        this.arvo = arvo;
    }
}
