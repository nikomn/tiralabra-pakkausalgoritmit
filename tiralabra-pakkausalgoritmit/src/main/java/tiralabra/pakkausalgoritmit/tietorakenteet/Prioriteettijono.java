package tiralabra.pakkausalgoritmit.tietorakenteet;

import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;

public class Prioriteettijono {

    private HuffmanSolmu[] jono;
    private int viimeinen;

    public Prioriteettijono() {
        this.jono = new HuffmanSolmu[9999999];
        this.viimeinen = 0;
    }


    public void lisaa(HuffmanSolmu x) {
        // jono.add(); vastaava toiminnallisuus jotenkin toteutettuna...
        this.viimeinen++;
        int p = this.viimeinen;
        while (p > 1 && x.haeToistuvuus() < this.jono[vanhempi(p)].haeToistuvuus() ) {
            this.jono[p] = this.jono[vanhempi(p)];
            p = vanhempi(p);
        }
        this.jono[p] = x;
        
    }
    
    
    public void lisaa2(HuffmanSolmu x) {
        // jono.add(); vastaava toiminnallisuus jotenkin toteutettuna...
        this.viimeinen++;
        this.jono[this.viimeinen] = x;
        int p = this.viimeinen;
        while (p > 1 && this.jono[p].haeToistuvuus() < this.jono[vanhempi(p)].haeToistuvuus()) {
            HuffmanSolmu tmp1 = this.jono[p];
            HuffmanSolmu tmp2 = this.jono[vanhempi(p)];
            this.jono[p] = tmp2;
            this.jono[vanhempi(p)] = tmp1;
        }
        
    }

    public int vasen(int p) {
        if (2 * p > this.viimeinen) {
            return 0;
        } else {
            return 2 * p;
        }
    }

    public int oikea(int p) {
        if (2 * p + 1 > this.viimeinen) {
            return 0;
        } else {
            return 2 * p + 1;
        }
    }
    
    public int vanhempi(int p) {
        return p / 2;
    }
    
    public HuffmanSolmu nouda() {
        //jono.poll(); vastaava toiminnallisuus...
        HuffmanSolmu a = this.jono[1];
        this.jono[1] = this.jono[this.viimeinen];
        this.viimeinen--;
        painaAlas(1);
        return a;
    }
    
    public void painaAlas(int solmu) {
        int pienempiLapsi = -1;
        if (vasen(solmu) == 0) {
            //System.out.println("ei lapsia!");
            return;
        } else if (vasen(solmu) == this.viimeinen) {
            pienempiLapsi = vasen(solmu);
        } else {
            if (this.jono[vasen(solmu)].haeToistuvuus() < this.jono[oikea(solmu)].haeToistuvuus()) {
                pienempiLapsi = vasen(solmu);
            } else {
                pienempiLapsi = oikea(solmu);
            }
        }
        if (this.jono[solmu].haeToistuvuus() > this.jono[pienempiLapsi].haeToistuvuus()) {
            HuffmanSolmu tmp1 = this.jono[solmu];
            HuffmanSolmu tmp2 = this.jono[pienempiLapsi];
            this.jono[solmu] = tmp2;
            this.jono[pienempiLapsi] = tmp1;
            painaAlas(pienempiLapsi);
        }
    }
    
//    public static void main(String[] args) {
//        HuffmanSolmu a = new HuffmanSolmu("a", 10, null, null);
//        HuffmanSolmu b = new HuffmanSolmu("b", 20, null, null);
//        HuffmanSolmu c = new HuffmanSolmu("c", 30, null, null);
//        HuffmanSolmu d = new HuffmanSolmu("d", 40, null, null);
//        HuffmanSolmu e = new HuffmanSolmu("e", 50, null, null);
//        HuffmanSolmu f = new HuffmanSolmu("f", 60, null, null);
//        HuffmanSolmu g = new HuffmanSolmu("g", 70, null, null);
//        
//        g.lisaaOikea(f);
//        g.lisaaVasen(e);
//        f.lisaaVanhempi(g);
//        e.lisaaVanhempi(g);
//        
//        Prioriteettijono p = new Prioriteettijono();
//        p.lisaa(g);
//        p.lisaa(f);
//        p.lisaa(e);
//        
//        System.out.println(p.nouda());
//        System.out.println(p.nouda());
//        System.out.println(p.nouda());
//        
//        PriorityQueue<HuffmanSolmu> jono = new PriorityQueue<>();
//        jono.add(g);
//        jono.add(f);
//        jono.add(e);
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        
//        // Näyttää toimivan :)
//        
//        
//    }
    
    
    

}
