# Uudistettu Huffman pakattujen tiedostojen formaatti

Aikaisemmassa versiossa ongelma on, että puun tallentaminen vie suuren
määrän tilaa. Puun säilyttämiselle ei pitäisi olla mitään varsinaista tarvetta,
joten tässä tiiviimmässä formaatissa tallennetaan vain muunnostaulukko.

Tiedostoformaattissa, jossa data on kenttiin, seuraavalla kaavalla:

1. Headeri (tällä ei muuta merkitystä kuin toimia standardisoituna alkuna tiedostolle) (8 bittiä)
2. Taulun pituus (24 bittiä)
3. ns. skippibitit, ts. kuinka monta nollaa on varsinaisessa datan alussa (8 bittiä)
4. Taulu
5. Data

esim merkkijono "abcdabcda\n".

0000000000000101  taulun pituus (5 merkki -> 5)

00000011 skippibitit (3 -> 3 nollaa alussa, että koko tasan tavuihin jaettuna)

Taulu:
   a    |    p   | 11
01100001|00000010|11
   b    |    p   | 00
01100010|00000010|00
   c    |    p   | 01
01100011|00000010|01
   d    |    p   | 101
01100100|00000011|101
  \n    |    p   | 100
00001010|00000011|100

Data:

0010011101010011101010011
