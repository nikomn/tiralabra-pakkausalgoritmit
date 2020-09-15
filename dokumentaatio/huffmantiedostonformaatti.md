# Huffman-tiedostojen formaatin kuvaus

Toteutus käyttää tässä vaiheessa Huffman algoritmilla pakattujen tiedostojen
tallentamiseen kustomoitua tiedostoformaattia, jossa data on jaettu 24
bitin kenttiin, seuraavalla kaavalla:

1. Headeri (tällä ei muuta merkitystä kuin toimia standardisoituna alkuna tiedostolle) (24 bittiä)
2. Taulun pituus (24 bittiä)
3. ns. skippibitit, ts. kuinka monta nollaa on varsinaisessa datan alussa (24 bittiä)
4. Huffman-puun juurisolmu (5x24 bittiä)
5. Taulu (taulun pituus x 5 x 24 bittiä)
6. Data

Esimerkki tiedoston rakenteesta:   

100000000000000000000000  | Headeri   

000000000000000000000101  | Taulunpituus, ts. kuinka monta solmua taulussa on   

000000000000000000000011  | Skippibitit, ts. kuinka nollabittiä datan alussa on, ennen varsinaista dataa   

Puunjuuri  

000000000000000000101101  | merkki   
000000000000000000001001  | toistuvuus   
000000000000000000101101  | vasemmalla oleva merkki   
000000000000000000101101  | oikealla oleva merkki   
000000000000000000101101  | vanhempi merkki (jos NULL -> '-', muutettava myöhemmin...)   

Taulu, ts. Huffmansolmut   

000000000000000001100001  | merkki   
000000000000000000000010  | toistuvuus   
000000000000000000101101  | vasen   
000000000000000000101101  | oikea   
000000000000000000101101  | vanhempi   

000000000000000001100010  | merkki   
000000000000000000000010  | toistuvuus   
000000000000000000101101  | jne...   
000000000000000000101101   
000000000000000000101101   

000000000000000001100011   
000000000000000000000010   
000000000000000000101101   
000000000000000000101101   
000000000000000000101101   

000000000000000001100100   
000000000000000000000010   
000000000000000000101101   
000000000000000000101101   
000000000000000000101101   

000000000000000000001010   
000000000000000000000001   
000000000000000000101101   
000000000000000000101101   
000000000000000000101101   

000110001011101001011101  | Varsinainen data...   
...   
