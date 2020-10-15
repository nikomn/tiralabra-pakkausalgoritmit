/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkausalgoritmit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tiralabra.pakkausalgoritmit.menetelmat.HuffmanSolmu;
import tiralabra.pakkausalgoritmit.tietorakenteet.Prioriteettijono;

/**
 *
 * @author nikoniem
 */
public class TestiPrioriteettijono {
    
    
    @Test
    public void prioriteettijonoOikeassaJarjestyksessa() {
        HuffmanSolmu e = new HuffmanSolmu("e", 50, null, null);
        HuffmanSolmu f = new HuffmanSolmu("f", 60, null, null);
        HuffmanSolmu g = new HuffmanSolmu("g", 70, null, null);
        
        g.lisaaOikea(f);
        g.lisaaVasen(e);
        f.lisaaVanhempi(g);
        e.lisaaVanhempi(g);
        
        Prioriteettijono p = new Prioriteettijono();
        p.lisaa(g);
        p.lisaa(f);
        p.lisaa(e);
        
        assertEquals("Prioriteettijono ei ole suuruusjärjestyksessä", e, p.nouda());
        assertEquals("Prioriteettijono ei ole suuruusjärjestyksessä", f, p.nouda());
        assertEquals("Prioriteettijono ei ole suuruusjärjestyksessä", g, p.nouda());
    }
    
}
