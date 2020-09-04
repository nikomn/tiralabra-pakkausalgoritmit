/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.pakkausalgoritmit;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author nikoniem
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<Character, Integer> taulu = new HashMap<>();
        taulu.put('A', 10);
        taulu.put('E', 15);
        taulu.put('I', 12);
        taulu.put('S', 3);
        taulu.put('T', 4);
        taulu.put('P', 13);
        taulu.put('\n', 1);
        
        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
        jono.add(new HuffmanSolmu('A', 10, null, null));
        jono.add(new HuffmanSolmu('E', 15, null, null));
        jono.add(new HuffmanSolmu('I', 12, null, null));
        jono.add(new HuffmanSolmu('S', 3, null, null));
        jono.add(new HuffmanSolmu('T', 4, null, null));
        jono.add(new HuffmanSolmu('P', 13, null, null));
        jono.add(new HuffmanSolmu('\n', 1, null, null));
        
//        while (!jono.isEmpty()) {
//            HuffmanSolmu solmu = jono.poll();
//            System.out.println(solmu);
//        }
        
        
        HuffmanSolmu eka = jono.poll();
        HuffmanSolmu toka = jono.poll();
        HuffmanSolmu x = new HuffmanSolmu(null, eka.toistuvuus + toka.toistuvuus, eka, toka); // 4
        HuffmanSolmu kolmas = jono.poll();
        HuffmanSolmu y = new HuffmanSolmu(null, x.toistuvuus + kolmas.toistuvuus, kolmas, x); // 8
        HuffmanSolmu neljas = jono.poll();
        HuffmanSolmu z = new HuffmanSolmu(null, y.toistuvuus + neljas.toistuvuus, neljas, y); // 18
        
        HuffmanSolmu viides = jono.poll();
        HuffmanSolmu kuudes = jono.poll();
        HuffmanSolmu w = new HuffmanSolmu(null, viides.toistuvuus + kuudes.toistuvuus, viides, kuudes); // 25
        
        HuffmanSolmu seitsemas = jono.poll();
        HuffmanSolmu v = new HuffmanSolmu(null, seitsemas.toistuvuus + z.toistuvuus, seitsemas, z); // 33
        
        HuffmanSolmu u = new HuffmanSolmu(null, w.toistuvuus + v.toistuvuus, w, v); // 58
        
        Hakupuu p = new Hakupuu();
        p.lisaa(eka.toistuvuus);
        p.lisaa(toka.toistuvuus);
        p.lisaa(x.toistuvuus);
        p.lisaa(kolmas.toistuvuus);
        p.lisaa(y.toistuvuus);
        p.lisaa(neljas.toistuvuus);
        p.lisaa(z.toistuvuus);
        p.lisaa(viides.toistuvuus);
        p.lisaa(kuudes.toistuvuus);
        p.lisaa(w.toistuvuus);
        p.lisaa(seitsemas.toistuvuus);
        p.lisaa(v.toistuvuus);
        p.lisaa(u.toistuvuus); 
        p.tulosta(u);
        System.out.println("Korkeus:");
        p.korkeus(u);
    }
    
}
