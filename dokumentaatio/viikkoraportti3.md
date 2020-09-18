# Viikkoraportti - viikko 2

## Yhteenveto tällä viikolla tehdyistä asioista:

- Tiedostojen lukemisen ja kirjoittamisen toteututtaminen

## Mitä seuraavaksi

- Huffman tiedostojen formaatiin kirjoittaminen ja lukeminen viimeistely
- Huffman algoritmin hiominen, tehostaminen ja pakattujen tiedostojen purkaminen
- Yksikkötestien kirjoittaminen
- Dokumentaation täydentäminen
- LZ algoritmin toteuttaminen
- Valmiiden tietorakenteiden korvaaminen itse tehdyillä toteutuksilla.

## Raportti

### Mitä olen tehnyt

Tällä viikolla törmäsin jatkuvasti ongelmiin Huffman algoritmilla koodattujen tiedostojen kirjoittamisen ja erityisesti lukemisen kohdalla. Kyseisestä operaatiosta on muodostunut huomattavasti työläämpi urakka, kuin mitä arvelin ja tilanne alkaa näyttää pahalta kyseisen ominaisuuden kohdalla. Ongelmaksi muodostuu ennenkaikkea se, miten saisin järkevällä tavalla tallennettua koodin purkamiseen tarvittavan taulun ja myöhemmin purettua sen ja tulkittua sen taas auki. Mikään aikaisempi kurssi ei ole antanut kauheasti eväitä tällaiseen, joten käytännössä joudun tekemään toteutusta täysin ilman mitään ennakkotietoja. Käytännössä toteutus edellyttää, että määrittelen kokonaan uuden tiedostoformaatin ja sen tulkintaan liittyvät asiat, joka tuntuu olevan enimmäkseen kurssin aiheen ulkopuolista asiaa, mutta en oikein ymmärrä, miten voisin kirjoittaa omalla algoritmillani koodatun bittitason datan tiedostoo ja lukea sen sieltä suoraan ilman, että toteutan koko tiedoston määrittelyn itse. Tämä loputon nollien ja ykkösten pyörittely edes takaisin on vienyt tällä viikolla aivan liikaa aikaa, enkä ole vieläkään ehtinyt toteuttaa dokumentaatiota ja testejä kunnolla. Alan varmaankin seuraavaksi keskittyä dokumentaatio puoleen, joka sekin tuntuu hieman hullulta, kun varsinainen ohjelma, jota olen dokumentoimassa ja testaamassa ei vielä toimi...

### Mitä opin

Opin paljonkin binäärimuotoisen datan käsittelystä, mutta en kauheasti mitään muuta...

### Mikä jäi epäselväksi

Epäselväksi jäi tällä viikolla todella iso määrä asioita. Edellyttääkö kurssin rajoitukset, että minun ehdottomasti täytyy kirjoittaa toteutus, joka kirjaa datan tiedosto-tasolle pakatussa muodossa? (Helpompaa olisi, jos voisin vain tehdä merkkijonojen tasolla tapahtuvan pakkauksen ja purkamisen, mutta en tiedä riittääkö se). Joissakin Huffman algoritmin kuvauksissa puhutaan siitä, että solmut voi tallentaa lopuksi prioriteettijonoon, mutta jäin miettimään, eikö prioriteettijono ole sellainen tietorakenne, josta näkee vain yhden tietueen kerrallaan, ts. miten etsiminen siis tulisi toteuttaa.


## Aikaa käytetty

Tällä viikolla aikaa käytety 14 tuntia
