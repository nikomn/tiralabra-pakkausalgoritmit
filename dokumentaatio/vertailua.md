# Algoritmien vertailua ja huomioita algortmien toiminnasta

Testiaineistona käytetty 5 utf-8 muotoista tekstitiedostoa:

1. Tiettyjä merkkejä: abcd.txt (sisältää vain merkkejä abcd, tiedosto projektin mukana)
2. Satunnaisia sanoja: testi.txt (satunnaisista suomenkielisistä sanoista koostuva aineisto, tiedosto projektin mukana)
3. Englanninkielinen "todellinen" teksti: The Adventures of Sherlock Holmes by Arthur Conan Doyle (https://www.gutenberg.org/ebooks/1661)
4. Suomenkielinen "todellinen" teksti: Juha by Juhani Aho (https://www.gutenberg.org/ebooks/10863)
5. Laajempi aineisto: Project Gutenberg Complete Works of Winston Churchill by Winston Churchill (https://www.gutenberg.org/ebooks/5400)

## Testien tulokset

### Pakkausteho

Tiedosto     | alkuperäinen (tavua) | huffman pakattu (tavua) | Huffman pakkaussuhde | lzw pakattu (tavua) | lzw pakkaussuhde
-------------|----------------------|-------------------------|----------------------|---------------------|------------------
abcd.txt     | 107743               | 34029                   | 31,58 %              | 80857               | 75,05 %
testi.txt    | 1045396              | 519912                  | 49,73 %              | 631077              | 60,37 %
pg5400.txt   | 9540229              | 5313255                 | 55,69 %              | 8475965             | 88,84 %
1661-0.txt   | 607791               | 333981                  | 54,95 %              | 447702              | 73,66 %
pg10863.txt  | 278954               | 149676                  | 53,66 %              | 225681              | 80,90 %

Testien perusteella Huffman algoritmi näyttää tuottavan kaikilla aineistoilla
paremman tuloksen kuin lzw. Huffman algoritmi pääsee n. 50% pakkaustulokseen,
kun vastaavasti lzw n. 75%. Tämä on jossain määrin yllättävä tulos, koska huffman
tiedostoissa puun tallennusta ei ole erityisen tehokkaasti optimoitu, jolloin se
vie melko paljon tilaa, mutta siitä huolimatta lopputulos on pienempi kuin lzw
tiedostoissa. Asiaan toki vaikuttaa paljon se, että lzw algoritmissa palan koko
on 18 tavua. Jos dataa käsiteltäisiin tekstin sijaan tavuina, tätä voisi tiivistää.
Toisaalta myös Huffman tiedoston muotoa voisi tällöin tiivistää, joten myös sen osalta
tiedostonkoko pienenisi.

![pakkaustehojen vertailu](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio1.png)

![abcd.txt pakkaus](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio2.png)

![testi.txt pakkaus](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio3.png)

![pg5400.txt pakkaus](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio4.png)

![pg1661-0.txt pakkaus](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio5.png)

![pg10863.txt pakkaus](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio6.png)

### Pakkausnopeus

Tiedosto	  | huffman pakaus (ms) |	lzw pakkaus (ms)
------------|---------------------|-----------------
abcd.txt	  | 340	                | 318
testi.txt	  | 1408	              | 1743
pg5400.txt	| 12615	              | 23613
1661-0.txt	| 971	                | 1228
pg10863.txt |	474	                | 585

Pakkausnopeudessa Huffman algoritmi on nopeampi kuin lzw, ero näkyy selkeiten
isojen tiedostojen kohdalla. Pienillä tiedostoilla lzw voi olla jopa hieman nopeampi.

![pakkausnopeuden vertailu tiedostoittain](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio7.png)

![pakkausnopeuden vertailu kokonaisajassa](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio9.png)


### Purkunopeus

Tiedosto     | huffman purku (ms) | lzw purku (ms)
-------------|--------------------|----------------
abcd.txt     | 293                | 224
testi.txt    | 486                | 596
pg5400.txt   | 4447               | 8486
1661-0.txt   | 315                | 386
pg10863.txt  | 145                | 194

Purkunopeudessa Huffman algoritmi on nopeampi kuin lzw, ero näkyy selkeiten
isojen tiedostojen kohdalla. Pienillä tiedostoilla lzw voi olla jopa hieman nopeampi.

![purkunopeuden vertailu tiedostoittain](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio8.png)

![purkunopeuden vertailu kokonaisajassa](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kaaviot/kaavio10.png)


## Huomiot

Huffman pakkaus on nykyisessä toteutuksessa selkeästi lzw pakkausta luotettavampi,
tehokaampi ja nopeampi.
