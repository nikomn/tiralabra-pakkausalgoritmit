# Viikkoraportti - viikko 5

## Yhteenveto tällä viikolla tehdyistä asioista:

- Algoritmien viimeistely
- Ohjelman testaus erilaisilla tiedostoilla ja vikojen selvittely
- Käyttöliittymän toteutus
- Vertaisarviointi

## Mitä seuraavaksi

- Yksikkötestien kirjoittaminen
- Dokumentaation täydentäminen
- Valmiiden tietorakenteiden korvaaminen itse tehdyillä toteutuksilla.
- Koodin siivous

## Raportti

### Mitä olen tehnyt

Tällä viikolla sain algoritmit toimimaan riitävän tehokkaasti. Tein myös tekstipohjaisenkäyttöliittymän. Tekemistä riittää vielä muutenkin, eikä graafisen käyttöliittymän toteuttaminen ole korkean prioriteetin asia tällä hetkellä, joten siihen palataan myöhemmin jos aikaa riittää. Graafinen käyttöliittymä ei sinänsä toisi mitään varsinaista lisäarvoa ohjelman kanalta, muuta kuin näyttäisi ehkä paremmalta.

Paljon aikaa meni aluksi hukkaan, kun yritin selvittää, miksi LZ pakatun datan purkaminen ei toimi, mutta lopulta selvisi, että LZ algortimin kuvaus, johon olin perehtynyt sisälsi virheen, eikä pakkaus ja purku tulisi toimimaan sillä tavalla. Jouduin siis tekemään kokonaan uuden toteutuksen...


### Mitä opin

Opin konkrttisella tasolla, että LZ algortimissa on tiettyjä rajoitteita, ainakin siinä muodossa, johon itse tutustuin. LZ pakkaus toimii yksinkertaisella periaatteella, jos merkistö on rajattu perus ascii merkkeihin, ja jos koodattava teksti ei ole kovin pitkä. Ongelmia ja monimutkaisuutta alkaa ilmetä, jos käsitellään utf-8 merkkejä ja jos dataa on paljon. Esim. ensimmäisessä kuvauksessa, johon perehdyin hehkutettiin sitä, että tiedosojen koko rajaantuu, kun voidaan käyttää 8 bitin sijaan 9 bittiä ja ilmaista näin yhdistelmä-merkkejä. Kuitenkin, jos kyseessä on utf-8 merkkejä, niin ne jo itsessään voivat viedä enemmän kuin 8 bittiä. Ja myös totetus, jossa yhdistelmä merkit kuvataan ns. laajennuksina (esim. koodissa 97 -> "a", niinkuin normaalisti, mutta 256 -> "aa" tms.) on rajallinen, jos bittimäärää ei kasvateta enempää kuin yhdellä (ts. 8 -> 9), koska 9 bitillä voidaan ilmaista maksimissaan arvo "111111111" == 511, eli näin saadaan vain reilu 200 yhdistelmä merkkiä käyttöön. Asiaa voi korjata lisäämällä bittimäärää, mutta samalla kasvaa myös lopullisen pakatun tiedoston koko.

Eli huffman ja lz algortimeissa on molemmassa omat rajoitteensa.

### Mikä jäi epäselväksi

Ei varsinaisesti mikää erityinen tällä viikolla.


## Aikaa käytetty

Tällä viikolla aikaa käytety 25 tuntia
