# Viikkoraportti - viikko 6

## Yhteenveto tällä viikolla tehdyistä asioista:

- Omien tietorakenteiden käyttöönotto koodissa.
- Koodin siivous
- Nopeusongelmien selvittely ja ratkaiseminen
- Kattavien testien kirjoittaminen
- Dokumentaation täydentäminen
- Vertaisarviointi

## Mitä seuraavaksi

- Testien täydentämistä
- Dokumentaation viimeistelyä
- Koodin siivoamista

## Raportti

### Mitä olen tehnyt

Tällä viikolla sain algoritmit valmiiksi ja ohjelman toimimaan kunnolla. Sain myös ohjelman hitauteen liittyvät ongelmat selvitettyä ja ohjelma toimii nyt myös isommilla tiedostoilla riittävän nopeasti (aikaisemmin isompien tiedostojen käsittely hidasti ohjelman toimintaa siten, että kesti jopa tunteja käsitellä jotain todella isoja tiedostoja - nyt niidenkin osalta kestää maksimissaan muutaman minuutin!). Täydensin myös dokumentaatiota, lisäsin testejä ja siivosin koodia. Työ alkaa olla aikalailla valmis, lähinnä vielä joitakin testien tekemistä ja pientä dokumentaation päivttämistä vielä pitää tehdä yms. viimeistelyhommia.

### Mitä opin

Opin tällä viikolla sen, että olen tajunnut koko pakkausalgoritmien toiminnan hieman väärin ja siitä syystä olen joutunut tekemään paljon ylimääräistä työtä. Toteutuksessani olen koko ajan tehnyt pakkausohjelmaa tekstitiedostoille ja tähän on kuulunut siis alkuperäisen tekstin lukeminen ja tulkinta, tekstin pakkaaminen ja pakatun tekstin tulkinta takaisin tekstiksi. Tässä merkittäviä ongelmia aiheutti utf-8 merkistön käsittely, johon olen työllä ja tuskalla saanut toteutuksen toimimaan siten, että se osaa käsitellä suurimman osan utf-8 merkeistä. Nyt olen tajunnut, että tämä kaikki työ oli aivan turhaa! Paljon helpompaa olisi ollut käsitellä dataa tavuina, joka olisi tarkoittanut sitä, että minkä tahansa tiedoston olisi voinut lukea tavuina ja käsitellä dataa täsmälleen samalla periaatteella siinä muodossa. Käytännössä tällöin kaikki data olisi aina 8 bitin paloissa, kun nyt jouduin varautumaan aina siihen, että käsiteltävä data voi olla mitä tahansa n. 8-20 bitin väliltä jne. Samalla pakkauksen olisi saanut toimimaan millä tahansa tiedostoilla, ei ainoastaan tekstitiedostoilla. Testailin saisinko muutettua koodin jotenkin suht. pienellä vaivalla siten, että dataa käsitellään tavu-muodossa, mutta koska olen tehnyt toteutuksen ihan toisesta lähtökohdasta, niin vaikka olisi helppoa lukea data tavuina, niin tässä vaiheessa asia aiheuttaisi lumipalloilmiön, jossa yhteen paikkaan tehdyt muutokset aiheuttavat tarpeen tehdä lisää muutoksia johonkin muualle ja niihin tehdyt muutokset taas johonkin muualle jne. Asian korjaaminen olisi helpointa tehdä kirjoittamalla koko ohjelma uudelleen alusta alkaen, mutta siihen ei enää tässä vaiheessa ole aikaa. Käytännössä kuitenkin tekemäni ohjelma toimii ja tekee sen, mihin se on suunniteltu ja periaatteessa olen ohjelmassa ratkaissut paljon vaikeamman ongelman kuin mitä olisi oikeastaan tarvinnut, eli en näkisi, että tästä on ollut mitään haittaa oppimisen kannalta, vaan päinvastoin.

### Mikä jäi epäselväksi

Ei varsinaisesti mikää erityinen tällä viikolla.


## Aikaa käytetty

20 tuntia
