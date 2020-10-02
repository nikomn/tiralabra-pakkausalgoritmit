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

## Merkistökoodauksista

LZ algoritmin ongelma näyttää liittyvän utf-8 merkistökoodaukseen. Esim. iso-8859-1
merkistökoodauksella olevat tiedostot pakkautuvat ilman ongelmia. Käytännössä
LZ pakkauksen tässä toteutuksessa oletetaan, että lähdetiedoston merkistö on kiinteällä
tavumäärällä kuvattu. utf-8 tiedostojen osalta merkkien tavukoko vaihtelee ja
ilmeisesti pahimmassa tapauksessa tarvitaan esim. 4 tavua. Tässä toteutuksessa
ei kuitenkaan ole toiminnallisuutta, joka osaisi mukautua eri merkkien mukaan, joten
ainoa mahdollisuus on olettaa jokin maksimipituus ja käyttää sitä, mutta tällöin
tiedoston pakkaustiheys kärsii. Huffman algortimin nykyisen toteutuksen kohdalla
tätä ongelmaa ei ole, koska oletuksena käytetään 4 tavun kokoista tilaa merkkien
tallentamiseen.

LZ algoritmin osalta esim. 18 bitin toteutus näyttää toimivan suurimmalle osalle
tiedostoista, mutta tällöin päästään vain n. 70% pakkaukseen.

Myös iso-8859-1 tiedostoissa ei toteutuksessa päästä siihen ihanteelliseen tilaan
mitä lähdemateriaalien esimerkeissä mainosteaan, ts. että päästäisiin yhden bitin
laajennuksella hyviin lopputuloksiin, koska toteutuksessa indeksiin lisätään yhdistettyjä
merkkejä, käytännössä siis pohjimmainen idea lienee se, että esim. "aaba" voidaan
kuvata pelkkänä yhtenä 9 bittisenä merkkinä vrt. että se kuvattaisiin neljänä
8 bitin merkkinä. Totetutuksessa kuitenkin lähdetään liikkeelle siitä, että yhdistemerkkejä
lisätään indeksiin jonka arvo kasvaa koko ajan, tällöin siis 9 bitin toteutuksessa
ensimmäinen indeksi viittaa paikkaan 256 ts. 256 tarkoittaa mitä tahansa mitä kyseisessä
muistipaikassa on (esim. juuri "aaba" tms.) jne. Yhdeksän bitin toteutuksessa
rajat tulevat vastaan jo indeksin 511 jälkeen, jolloin tarvitaan jo uusi bitti käyttöön.
Eli käytännössä siis yhdenksän bitin toteutuksessa voidaan laajentaa ascii merkistöä
vain 255 uudella yhdistemerkillä, joka raja tulee hyvin pian vastaan laajemmissa
tekstimassoissa.
