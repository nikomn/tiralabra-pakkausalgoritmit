# Käyttöohje

Alustavassa toteutuksessa ohjelman kanssa samassa kansiossa oleva tiedosto, jonka
nimenä on "testi.txt" pakataan huffman algoritmilla ja pakattu tiedosto tallennetaan
samaan kansioon nimellä "huffman.dat".

## Huomiota

Tiedoston kirjoittamiseen liittyy tällä hetkellä optimointi-ongelma, jonka seurauksena
isompien tiedostojen pakaamisessa kestää melko pitkä aika. Ongelma liittyy
siihen, että tiedoston kirjoitus edellyttää, että data kirjoitetaan lopusta
alkuun järjestyksessä, kun se alunperin taas generoidaan päinvastaisessa järjestyksessä.
Nykyisessä toteutuksessa tämä kierretään sillä, että aineisto käännetään "oikeaan"
järjestykseen tiedoston kirjoituksen yhteydessä. Tämä kääntäminen vie suhteettoman
paljon aikaa, joka näkyy isojen tiedostojen yhteydessä selkeänä hidastuksena.

Koska pelkkä tiedostojen kirjoittaminen ja puretun datan käsittelyn toteuttaminen
vei aikaa paljon enemmän, kuin mitä oli suunniteltu ja ongelma ilmenee vain isompien
tiedostojen kohdalla, kohdistetaan resurssit tällä erää puuttuvien ominaisuuksien
toteuttamiseen ja tähän optimointi-ongelmaan palataan toivottavasti myöhemmässä vaiheessa.

Testausta varten on suositeltavaa käyttää alle 10000 riviä tekstiä sisältäviä tekstitiedostoja,
tai muussa tapauksessa joutuu odottamaan ohjelman valmistumista.
