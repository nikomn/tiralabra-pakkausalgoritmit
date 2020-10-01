# Algoritmien vertailua ja huomioita algortmien toiminnasta

## Suoritusaikojen vertailua

Vertailu suoritettu satunnaisista suomenkielisistä sanoista muodostetulla n. 334kb
kokoisella tiedostolla.

- Alkuperäisen tiedoston koko: 333678.0 tavua
- Alkuperäisen tiedoston lukeminen: 261 ms
- Huffman pakkaus yht. 25846 ms
   - Merkkitaulun muodostus: 47 ms
   - Huffmanpuun muodostus 2 ms
   - Datan muuntaminen koodattuun muotoon: 25231 ms
   - Pakatun tiedoston kirjoittaminen: 563 ms
   - Pakatun tiedoston koko: 167643.0 tavua
   - Pakattu tiedosto on n. 50.0% alkuperäisestä.
- Huffman datan purku yht. 21045 ms
   - Huffman-tiedoston lukeminen: 12486 ms
   - Koodatun datan tulkinta ja puretun tiedoston kirjoitus: 8559 ms
- LZ pakkaus yht. 5931 ms
   - Datan muuntaminen koodattuun muotoon: 5332 ms
   - Pakatun datan tallennus: 594 ms
   - Pakatun tiedoston koko: 205033.0 tavua
   - Pakattu tiedosto on n. 61.0% alkuperäisestä.
- LZ pakkatun datan purkaminen yht. 20612 ms
   - Pakatun datan lukeminen: 19714 ms
   - Purerun datan tulkinta ja puretun itedoston kirjoittaminen: 896 ms

Lopputuloksena oli täsmälleen samanlaiset tiedostot:

```console
$ diff -s testi.txt huffman_purettu.txt
Files testi.txt and huffman_purettu.txt are identical
$ diff -s testi.txt lz_purettu.txt
Files testi.txt and lz_purettu.txt are identical
```

## Huomioita

Huffman pakkaus on tässä toteutuksessa hieman hitaampi kuin LZ, mutta toisaalta
Huffman pakkaus toimii tehokkaammin. Molemmat algoritmit joka tapauksessa pystyvät
pakkaamaan dataa n. 50-60% tehokkuudella.

Molempaan algoritmiin on vielä jäänyt tehostamismahdollisuuksia, joten pienellä
optimoinnilla nopeutta voitaneen vielä lisätä nykyisestä.

Toteutuksessa on ongelmia isompien tiedostojen kanssa ja sellaisissa tapauksissa,
joissa tiedostot sisältävät harvinaisempia merkkejä. Tällöin lopputulos ei aina
ole oikea. Pienemmillä tiedostoilla (< 500kb) ja yleisiä merkkejä käyttäessä
algoritmit tuntuisivat toimivan kuitenkin luotettavasti.
