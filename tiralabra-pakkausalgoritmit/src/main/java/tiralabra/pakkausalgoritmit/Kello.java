/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

/**
 *
 * @author nikoniem
 */
public class Kello {

    private long alku;
    private long loppu;
    private long kesto;
    private boolean kaynnissa;

    public void Kello() {
        this.alku = -1;
        this.loppu = -1;
        this.kesto = 0;
        this.kaynnissa = false;
    }

    public void kaynnista() {
        if (!this.kaynnissa) {
            this.alku = System.nanoTime();
            this.kaynnissa = true;
        } else {
            System.out.println("Ajastin on jo käynnistetty!");
        }

    }

    public long pysayta() {
        if (this.kaynnissa) {
            this.loppu = System.nanoTime();
            this.kaynnissa = false;
            this.kesto = (this.loppu - this.alku) / 1000000;
            return this.kesto;
        } else {
            System.out.println("Ajastin ei ole käynnissä!");
            return -1;
        }
    }
    
    public void nollaa() {
        this.alku = -1;
        this.loppu = -1;
        this.kesto = 0;
        this.kaynnissa = false;
    }

}
