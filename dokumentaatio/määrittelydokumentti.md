# Määrittelydokumentti (alustava luonnos)

## Opinto-ohjelma

Tietojenkäsittelytieteen kandidaatti (TKT)

## Projektin kieli

- Dokumentaatio, kommentit, muuttujat: Suomi
- Ohjelmointikieli: Java

## Toteutettavat algoritmit ja tietorakenteet

Tavoitteena on toteuttaa pakkausalgoritmeista Huffmanin pakkausalgoritmi ja LZ zip pakkausalgoritmi tms. Näitä varten tarvitaan ainakin prioriteettijonoa.

## Ongelma ja valitut algoritmit/tietorakenteet

Tarkoituksen on ratkaista ongelma siitä, miten data saadaan pakattua pienempään muotoon ja vertailla eri pakkausalgoritmien tehokkuutta. Tavoitteena on päästä  40-60% alkuperäisestä koosta. Valitsin algoritmeiksi Huffmanin pakkausalgoritmin ja LZ zip pakkauksen, koska ne ovat kyseisen ongelmakentän klassisimpia ratkaisumalleja. Tarkoitus on toteuttaa pakkaus ja purkutoiminnallisuudet, ts. data voidaan pakata ja pakattu data palauttaa alkuperäiseen muotoon.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Alustavan suunnitelman mukaan tarkoitus olisi antaa ohjelmalle teksti-tiedostoja pakattavaksi. Näiden sisältö pakataan algoritmia hyödyntäen tiiviimpään muotoon.

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Aikavaativuus Huffman algoritmille on O(n log n) ja tähän aikavaativuuteen myös projektissa tullaan pyrkimään.

## Lähteet (täydentyy myöhemmin...)

- https://en.wikipedia.org/wiki/Huffman_coding
