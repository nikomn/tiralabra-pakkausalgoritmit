# Määrittelydokumentti (alustava luonnos)

## Opinto-ohjelma

Tietojenkäsittelytieteen kandidaatti (TKT)

## Projektin kieli

- Dokumentaatio, kommentit, muuttujat: Suomi
- Ohjelmointikieli: Java

## Toteutettavat algoritmit ja tietorakenteet

Tavoitteena on toteuttaa pakkausalgoritmeista Huffmanin pakkausalgoritmi ja LZ zip pakkausalgoritmi tms. Näitä varten tarvitaan ainakin prioriteettijonoa. Molemman algoritmin toteutuksessa käytetään myös hajautustaulua.

## Ongelma ja valitut algoritmit/tietorakenteet

Tarkoituksen on ratkaista ongelma siitä, miten data saadaan pakattua pienempään muotoon ja vertailla eri pakkausalgoritmien tehokkuutta. Tavoitteena on päästä  40-60% alkuperäisestä koosta. Valitsin algoritmeiksi Huffmanin pakkausalgoritmin ja LZ zip pakkauksen, koska ne ovat kyseisen ongelmakentän klassisimpia ratkaisumalleja. Tarkoitus on toteuttaa pakkaus ja purkutoiminnallisuudet, ts. data voidaan pakata ja pakattu data palauttaa alkuperäiseen muotoon.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Alustavan suunnitelman mukaan tarkoitus olisi antaa ohjelmalle teksti-tiedostoja pakattavaksi. Näiden sisältö pakataan algoritmia hyödyntäen tiiviimpään muotoon.

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Tilavaativuus on molemmassa riippuvainen alkuperäisen tiedoston koosta, eikä mitään
kiinteää tilavaativuutta voi määrittää.

## Huffman

Algoritmissa käytetään prioriteettijonoa (O(n log n)), hajautustaulua (O(n)) ja käsitellään linkitettyjä
listoja (O(n log n)). Kokonaisaikavaativuus on siis luokkaa O(n log n).

## LZW

Algoritmissa käsitellään sanakirjoja, jotka ovat hajautustauluna, joihin liittyvät
operaatiot vievät aikaa O(n). Aikavaativuuden kannalta kuitenkin joudutaan tekemään
myös hakuja taulukosta, jolloin lopullinen aikavaativuus on O(n log n) luokkaa.

## Algoritmien vertailua

## Lähteet

### Huffman algoritmi

- https://www.youtube.com/watch?v=dM6us854Jk0
- https://en.wikipedia.org/wiki/Huffman_coding

### LZW algoritmi

- https://www.youtube.com/watch?v=RV5aUr8sZD0
- https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch
