# Viikkoraportti - viikko 2

## Yhteenveto tällä viikolla tehdyistä asioista:

- Aiheeseen perehtyminen
- Huffman algoritmin toteuttaminen
- Tiedostojen lukemisen ja kirjoittamisen alustava toteutus
- Testausten aloitus

## Mitä seuraavaksi

- Huffman tiedostojen formaatiin kirjoittaminen ja lukeminen
- Huffman algoritmin hiominen, tehostaminen ja pakattujen tiedostojen purkaminen
- Yksikkötestien kirjoittaminen
- Dokumentaation täydentäminen
- LZ algoritmin toteuttaminen
- Valmiiden tietorakenteiden korvaaminen itse tehdyillä toteutuksilla.

## Raportti

### Mitä olen tehnyt

Tällä viikolla aloin toteuttaa Huffman algoritmia. Tein jokseenkin toimivalta vaikuttavan toteutuksen algoritmista. Käytin melko paljon aikaa sen selvittelyyn, miten saisin tiedostoja käsiteltyä järkevällä tavalla. Sain toteutettua tiedostojen lukemisen ja Huffman koodin luomisen tiedoston sisältämän tekstin perusteella, mutta vielä on hieman epäselvää, miten saisin toteutettua järkevällä tavalla pakattujen tiedostojen tallentamisen. Aiheeseen ei tunnu löytyvän mitään erityisen selkeitä esimerkkejä ja oletettavasti Java on kielenä sellainen, että on harvinaisempaa, että Javalla tehtäisiin toteutuksia, joissa kirjoitetaan tiedostoja bittitasolla. Kurssin esitietovaatimukset tai muutenkaan perusopintoihin kuuluvat kurssit eivät myöskään anna mitenkään erityisen vahvaa pohjaa tällaisen operaation toteuttamiseen, joten hieman improvisoiden täytyy edetä. Myöskään mitään valmista Javan toteutusta ei kielestä suoraan löydy, koska olen kuitenkin toteuttamassa kustomoitua tiedostoformaattia. Varsinaisen algoritmin toteutus ja erityisesti tiedostojen kanssa tappelu (nollien ja ykkösten pyörittely) vei tällä viikolla hieman turhankin paljon aikaa, enkä ehtinyt toteuttaa testejä kunnolla, mikä oli suunnitelmissa.

### Mitä opin

Opin ymmärtämään tarkemmin Huffman pakkauksen toimintaa. Ymmärsin myös konkreettisesti sen, että Huffmanin algorimissa on "heikkoutena" se, että merkkitaulu, joka kertoo, miten bitit pitää tulkita, pitää tallentaa tiedostoon mukaan, että se voidaan purkaa. Tällöin ei siis mitenkään voi olla, että lyhyen merkkijonon sisältävä tiedosto voisi olla lyhyempi pakattuna kuin ilman pakkausta, eli pakkauksen hyödyty tulevat näkyviin vasta isommilla tiedostoilla.

### Mikä jäi epäselväksi

Epäselväksi jäi tällä viikolla monikin asia tiedostojen kirjoittamisesta, joka tuntuu hieman turhauttavalta asialta pähkäiltäväksi, kun se tuntuu tavalllaan triviaalilta itse pakkausalgoritmin toteuttamista ajatellen. Epäselväksi jäi myös se, missä määrin Huffmannin algoritmin pitäisi pakata data ja mistä pakkaussuhde pitäisi laskea. Erilaisella datalla sain hyvin vaihtelevia tuloksia omalla algoritmillani ja näyttäisi, että pakaussuhde on tällä hetkellä n. 3000 rivillä tekstiä n. 60% paikkeilla, mikä pitäisi olla ihan ok, mutta toki siihen sitten pitäisi vielä lisätä merkkitaulu, jos kirjoitetaan tiedostoon.

Testien osalta on tässä vaiheessa hieman epäselvää, mitä pitäisi testata. Siis esim. pakkauksen lopputuloksen testaus tuntuu epäselvältä siltä osin, että jos pakkaan merkkijonon, niin minullahan ei ole tietoa siitä, mikä lopputulos pitäisi olla jne. Mutta uskoisin, että nämä epäselvyydet lähtevät selkiytymään, kunhan ehdin toteuttaa testejä kunnolla käytännössä. Nykyisissä alustavissa testeissä törmäsin omituiseen tilanteeseen, jossa ohjelma tuottaa eri koodin samalla syötteellä riippuen siitä, ajetaanko ohjelmaa main luokan kautta, vai testeissä. Ei mitään käsitystä tässä vaiheessa miksi näin tapahtuu!?

## Aikaa käytetty

Tällä viikolla aikaa käytety 10 tuntia
