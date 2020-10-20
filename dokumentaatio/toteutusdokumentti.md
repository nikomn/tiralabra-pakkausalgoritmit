# Toteutusdokumentti

## Yleistason esittely toteutetuista algoritmeista

[Algoritmien esittelyä](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/projektin_esittely.pdf)

## Huffman-tiedostojen formaatin kuvaus

Toteutus käyttää Huffman algoritmilla pakattujen tiedostojen
tallentamiseen kustomoitua tiedostoformaattia, jossa data on jaettu 24
bitin kenttiin, seuraavalla kaavalla:

1. Headeri (tällä ei muuta merkitystä kuin toimia standardisoituna alkuna tiedostolle) (24 bittiä)
2. Taulun pituus (24 bittiä)
3. ns. skippibitit, ts. kuinka monta nollaa on varsinaisessa datan alussa (24 bittiä)
4. Huffman-puun juurisolmu (6x24 bittiä)
5. Taulu (taulun pituus x 6 x 24 bittiä)
6. Data

Mahdolliset null-arvot on kuvattu pelkinä nollilla ("000000000000000000000000" == null)

Esimerkki tiedoston rakenteesta:   

100000000000000000000000  | Headeri   

000000000000000000000101  | Taulunpituus, ts. kuinka monta solmua taulussa on   

000000000000000000000011  | Skippibitit, ts. kuinka nollabittiä datan alussa on, ennen varsinaista dataa   

Puunjuuri  

000000010011101000110001  | tunniste  
000000000000000000101101  | merkki   
000000000000000000001001  | toistuvuus   
000000000000000000101101  | vasemmalla oleva merkki (tunniste)   
000000000000000000101101  | oikealla oleva merkki (tunniste)  
000000000000000000101101  | vanhempi merkki (tunniste)   

Taulu/Puu, ts. Huffmansolmut   

000001010101011101000100    | tunniste  
000000000000000001100001    | merkki  
000000000000000000000010    | toistuvuus  
000000000000000000000000    | vasen  
000000000000000000000000    | oikea  
000011110000011101100011    | vanhempi  

000000011101010001101101    | tunniste  
000000000000000001100010    | merkki  
000000000000000000000010    | jne...  
000000000000000000000000  
000000000000000000000000  
000011100001111100011100  

...  

000110001011101001011101    | Varsinainen data  
...

## Toteutetutu tietorakenteet

Projektissa ei saanut käyttää valmiita tietorakenteita, vaan algoritmien ja  
ohjelman käyttämät tietorakenteet piti toteuttaa itse. Projektia varten on  
toteutettu seuraavat tietorakenteet:  
- Hajautustaulu
- Lista
- Pari (hajautustaulua varten)
- Prioriteettijono

[Tietorakenteiden lähdekoodit](https://github.com/nikomn/tiralabra-pakkausalgoritmit/tree/master/tiralabra-pakkausalgoritmit/src/main/java/tiralabra/pakkausalgoritmit/tietorakenteet)

Omat tietorakenteet on toteutettu aikasempien ohjelmointikurssien tietojen  
pohjalta (erityisesti ohjelmoinnin jatkokurssi ja tietorakenteet ja algoritmit kurssi),  
ja toteutukset ovat jokseenkin yksinkertaisia, eikä niiden tehokkuus ole samalla  
tasolla kuin Javan varsinaiset vastaavat, joissa on erilaisia kehittyneempiä optimointeja.  
Tästä huolimatta omien toteutusten aikavaativuus pitäisi olla suurinpiirtein samaa luokkaa  
kuin Javan valmiissa toteutuksissa, eikä tällä siis pitäisi olla vaikutusta  
koko ohjelman aikavaativuuteen.  

## Käytetyt kirjastot

Harjoitustyön tarkoituksena oli toteuttaa algortimit ja niiden tarvitsemat   
tietorakenteet itse. Poikkeuksen muodostaa tiedostojen lukemiseen / kirjoittamiseen  
tarvittavat toiminnallisuudet ja mahdollisesti käyttöliittymässä tarvittavat  
kirjastot.  

Projektissa on ohjeiden mukaisesti "piilotettu" tiedostojen käsittelyyn tarvittavat  
toiminnot omiin luokkiinsa ja vain näissä on tällaisten kirjastojen importteja.  

Kirjastot, joita näissä luokissa on käytetty:  

Tiedostojen kirjoittaminen:  
- java.io.DataOutputStream
- java.io.FileOutputStream
- java.io.FileWriter
- java.util.BitSet

Tiedoston lukeminen:  
- java.io.File
- java.nio.file.Files
- java.nio.file.Paths
- java.util.Scanner

Lisäksi käyttöliittymässä on käytetty seuraavia:  
- java.io.File (tiedostojen kokojen laskeminen tilastojen tulostamista varten)
- java.util.Scanner (käyttäjän syötteiden lukeminen)


## Nopeuden optimoiminen

Nykyisessä toteutuksessa nopeuden suhteen pullonkaulaksi muodostuu datan käsittely  
merkkijono-muodossa. Melko triviaalit operaatiot, jossa dataa koostetaan merkkijonomuotoon  
vähä vähältä, esim.  

1. Poimitaan listalta solmu
2. Muunnetaan solmu bitti merkkijonoksi
3. Lisätään bittimerkkijono lopulliseen tulostettavaan / tallennettavaan merkkijonoon
4. Käsitellään seuraava solmu
5. ...

... vie lopulta joka kierroksella enemmän ja enemmän aikaa. Ohjelman suoritusaika  
on kohtuullinen suht. pienillä tiedostoilla, mutta isommilla tiedostoilla suoritus vie  
minuutteja (jopa tunteja!) tästä johtuen.  

Totetustavasta johtuen tilanteen parantaminen vaihtamalla merkkijono-muotoisen datan  
käsittely joksikin muuksi (esim. bittitason tai suoraan tiedostoon kirjoittamiseen tms.)  
edellyttäisi todella perustavanlaatuisia ja työläitä muutoksia ohjelman peruslogiikkaan,  
joten toteutusta tehostettu käyttämällä tällaisten melko triviaalien toimenpiteiden  
suorittamiseen javan StringBuilderia, joka tekee samat operaatiot huomattavasti nopeammin kuin  
alkeellisella for silmukkalla tehtynä.  

Nopeusero on selkeästi merkittävä, esim. 860kB tekstitiedoston käsittely ilman optimointeja:  

operaatio               | kesto  
------------------------|---------  
huffman pakkaus	        | 206559 ms.  
huffman purku	          | 192763 ms.  
lz pakkaus              | 63341 ms.  
lz purku                | 266708 ms.  


Saman tiedoston käsittely optimointien jälkeen:  

operaatio               | kesto  
------------------------|---------  
huffman pakkaus         | 1510 ms.  
huffman purku           | 569 ms.  
lz pakkaus              | 1828 ms.  
lz purku                | 889 ms.  

Käytännössä siis optimoinnin jälkeen suoritusajat voi mitata sekunneissa, kun ilman niitä  
ne pitää mitata minuuteissa.  

## Ohjelman tekniset rajoitukset

Lopulliseen toteutukseen jäi muutamia teknisiä rajoituksia, joihin voisi palata jos  
ohjelmaa haluaisi jatkokehittää myöhemmin:  

### Rajoite 1. Ohjelma kykenee pakkaamaan vain tekstitiedostoja.

Ohjelman alkuperäisen tavoiteasettelun mukaisesti ohjelma kykenee pakkaamaan vain  
tekstitiedostoja (mikä siis oli tavoite, jonka projektille asetin). Ohjelma olisi  
kuitenkin nykyisetä monikäyttöisempi ja yleensä hyödyllisempi, jos sillä  
voisi pakata mitä tahansa dataa. Muutos edellyttäisi käsiteltävän datatyypin  
vaihtamista merkki/merkkijono muodosta tavuiksi. Muutoin kaikki toiminnot toimisivat  
pohjimmiltaan samalla logiikalla kuin nykyisin, mutta koska koko ohjelma on rakennettu  
merkki/merkkijono datan käsittelyä varten, jolloin ohjelman perimmäinen luonne  
edellyttäisi käytännössä koko ohjelman keskeisten toimintojen uudelleen kirjoittamista,  
että ne saisi toteutettu järkevimmällä mahdollisella tavalla koodissa (esimerkki  
ongelmasta ks. [tavumoitoisen datan käsittelyyn vaihtamiskokeilu](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/a3040b8fc1354d53208d0a0e9d3cc60fd8b7a2dd/tiralabra-pakkausalgoritmit/src/main/java/tiralabra/pakkausalgoritmit/tiedostot/Tiedostonlukija.java#L57-L101)). Tämä edellyttäisi  
niin paljon ohjelman uudelleen kirjoittamista, että siihen ei ole enää mielekästä  
tässä vaiheessa lähteä, koska se vaarantaisi ohjelman valmistumisen deadlineen mennessä.  
Ohjelma kuitenkin nykymuodossaan toimii ja tekee sen, mitä varten se on suunniteltu  
alunperin.  

### 2. Ohjelma pakkaa onnistuneesti vain riittävän suuria tiedostoja.

Pienillä tekstitiedostoilla pakatut tiedostot ovat usein jopa suurempia kuin alkuperäinen  
tiedosto, joka ei tietysti ole toivottava piirre. Ohjelmaa ei kuitenkaan ole suunniteltu  
pakkaamaan pieniä tiedostoja vielä hieman pienemmiksi, vaan tavoite olikin pakata  
jotain hieman suurempaa massaa merkittävästi pienempään kokoon ja tästä syystä esim.  
tiedostoon tallennettavien huffman puiden kokoa ei ole pyritty optimoimaan erityisen  
tiiviiseen muotoon. Ongelma liittyy myös valintaan siitä, että pyritään pakkaamaan  
nimenomaan tekstiä (ts. merkki/merkkijono-muotoista dataa), jolloin tämä asettaa  
tiettyjä rajoitteita sille, miten tiiviiseen ilmaisuun voidaan päästä. Mitään  
absoluuttista minimikokoa tiedostoille ei voi määritellä, koska asiaan vaikuttaa  
todella paljon se, minkälaista tekstiä tiedosto sisältää, mutta suunnittelussa  
lähdettiin liikkeelle siitä, että tekstiä olisi satoja rivejä tiedostossa. Sitä  
vähäisemmällä tekstimäärällä lopputulos ei välttämättä ole toivottu.  

### 3. Ohjelma ei aina tuota oikeaa lopputulosta erittäin isoilla tiedostoilla.

Tällekkään asialle ei voi antaa mitään ehdotonta rajaa, mutta ongelmia ilmaantui  
testeissä yli 6mt kokoisilla tiedostoilla. Tähän toki vaikuttaa todella paljon se  
minkälaista tekstiä tiedosto sisältää. Ongelma koskee lzw algoritmin toteutustapaa  
jossa aina jossain vaiheessa sanakirja-indeksi ylittää määritellyn data-palan koon.  
Data-palan kokoa voi toki suurentaa koodissa, mutta tällä on negatiivinen vaikutus  
pakkaustiheyden kannalta. On myös enimmäkseen mahdotonta tietää etukäteen miten  
suurta palakokoa kannattaisi käyttää ja jos käytettäisiin vaihtelevan kokoisia  
paloja eri tiedostoissa, pitäisi aina erikseen tietää purkuvaiheessa, mikä on  
palankoko, mitä ei otettu suunnitteluvaiheessa huomioon, jolloin käytännössä  
yksinkertaisinta on käyttää jotakin tiettyä kovakoodattua palakokoa, joka sopii  
useimmille tiedostoille.  

### 4. Ohjelma ei aina tuota oikeaa lopputulosta, jos tiedoston merkistökoodaus on utf-8 (tai muu ascii merkistöä laajempi merkistö)

Tässä asiassa erityisesti tulee ilmi, miksi olisi ollut lopulta "helpompaa" toteuttaa  
pakkaus siten, että käytetään data-tyyppinä tavuja, eikä oteta mitään kantaa siihen,  
mitä dataa niillä pyritään ilmaisemaan. Data-palan kokojen vaihtelu aiheutti projektin  
alkupuolella todella suuria ongelmia, kun tein toteutuksen alustavaa versiota siitä  
näkökulmasta, että data on tavun paloissa ilmaistavista ascii merkeistä koostuvaa  
dataa ja testausvaiheessa totesin, että utf-8 merkit eivät tietenkään sovi tähän  
kaavaan, koska kaikki merkit eivät mahdu 8 bittiin, esim. '—' (vrt. '-') on  
numeerisessa muodossa 8212, ts. 10000000010100, eli tarvitaan 14 bittiä. Tämän  
asian korjaaminen aiheutti todella paljon työtä jotta sain aikaa edes nykyisen  
lähes kattavan utf-8 tuen toteutettua. Jos taas olisin alunperin vain lähtenyt  
liikkeelle merkki/merkkijono muotoisen datan pakkaamisen sijaan tavu-muotoisen  
datan pakkaamisesta ei tätä ongelmaa olisi ilmaantunut, koska sillä ei olisi  
lopulta ollut merkitystä vaatiiko alkuperäinen merkki 14 vai 8 vai mitä tahansa  
määrän bittejä, että se saadaan ilmaistua. Esim. tiedosto, joka sisältää pelkästään  
merkin '—' olisi silti tallennettu tavun kokoisiin paloihin ja olisi siis ollut  
tiedostossa muodossa "0010000000010100", jolloin sen olisi vain voinut käsitellä  
kahtena palana "00100000" & "00010100".  

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
