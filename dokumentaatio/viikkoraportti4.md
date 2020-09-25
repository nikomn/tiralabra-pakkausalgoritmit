# Viikkoraportti - viikko 4

## Yhteenveto tällä viikolla tehdyistä asioista:

- Tiedostojen lukemisen ja kirjoittamisen viimeistely
- LZ algoritmin pakkaus ja purkutoiminnallisuuden toteutus

## Mitä seuraavaksi

- Yksikkötestien kirjoittaminen
- Dokumentaation täydentäminen
- Valmiiden tietorakenteiden korvaaminen itse tehdyillä toteutuksilla.
- Koodin siivous
- Algoritmien tehostaminen ja hiominen
- Käyttöliittymän toteuttaminen

## Raportti

### Mitä olen tehnyt

Tällä viikolla sain vihdoin Huffman tiedostojen pakkauksen ja purkamisen valmiiksi. Algoritmissa on edelleen pientä hiottavaa tehokkuusnäkökulmasta, mutta varsinainen pakkaus ja purku toimii kuitenkin oikein. Vaikka toteutuksessa tallennettava tulkinta-taulu onkin aika paljon tilaa vievä, on lopullinen toteutus kuitenkin siinä määrin toimiva, että pakatut tiedostot ovat n. 50% alkuperäisistä.

Tein tällä viikolla myös LZ algoritmin, joka tällä hetkellä periaatteessa toimii, mutta pakatun tiedoston purkaminen jälkeen isommissa tiedostoissa kaikki merkit eivät näy oikein. Täytyy vielä selvitellä, onko ongelma pakkauksessa vai purkamisessa. Mutta varsinainen algoritmi periaatteessa toimii ja pakatut tiedostot ovat n. 50% alkuperäsestä.

### Mitä opin

Opin paljonkin LZ algoritmien totettamisesta. Opin myös konkretian tasolla sen, että tietyllä tapaa Huffman algoritmi on aika kömpelö tapa pakata dataa verrattuna esim. LZ algoritmiin. Taulun tallentaminen tiedoston mukaan on tavallaan "turhaa" ja lisää tiedostojen hallintaan ja purkamiseen ylimääräisen kerroksen monimutkaisuutta, mikä taas LZ algoritmissa ei ole mukana. LZ algortmin osalta ilmeisin ongelma on siinä, kuinka paljon merkistöä "laajennetaan" ja sen hallinta. Esim. tuntuu hieman kömpelöltä kirjoittaa kyseinen asia tiedostoon mukaan, mutta toisaalta sitten taas pakkaus lienee mahdollista tehdä tiiviimmässä muodossa, jos laajennusmäärä muuttuu luettavan datan mukaan. Toisaalta ei ole itselleni ollenkaan selvää tässä vaiheessa, millä periaatteella se pitäisi tietää, miten paljon merkistöä pitäisi laajentaa.

### Mikä jäi epäselväksi

Varsinaisesti epäselväksi jäi muutamat algoritmien toimintaan ja tehokkuuteen liittyvät ongelmat, mitä täytyy vielä hioa.


## Aikaa käytetty

Tällä viikolla aikaa käytety 14 tuntia
