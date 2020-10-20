# Ohjelman testauksesta

Ohjelman testaus on suoritettu automatisoiduilla JUnit testeillä, sekä manuaalisesti
tehdyillä testauksella. Koodin laatua on testattu Checkstyle testeillä.

## Automaattiset JUnit testit

Ohjelman keskeiset toiminnallisuudet on testattavissa Junit-testeillä. Testit voi
ajaa esim. netbeanssissa valikosta Run -> Test project (tai Alt+F6)

Automaattinen testaus sisältää seuraavat testit:

1. TestiHuffman
   - koodauksenLopputulosOikein: Tarkistetaan, että merkkijonon koodaaminen huffman
   muotoon tuottaa oikean lopputuloksen
   - koodattuDataTulkitaanOikein: Tarkistetaan, että Huffman algoritmilla koodattu
   data tulkitaan oikein takaisin tekstiksi muunnettaessa.
   - luettujenSolmujenMuodostaminenToimiiOikein: Tarkistetaan, että tiedostosta
   luettu puu muodostetaan oikein.
   - huffmanPakatunTiedostonTallennusToimii: Tarkistetaan tiedostoon tallennuksen
   toimivuus
2. TestiHuffmanSolmu
   - huffmanSolmunMuodostusToimii: Testataan, että solmujen muodostus onnistuu
   yleisessä tapauksessa (osittain päällekkäinen testin luettujenSolmujenMuodostaminenToimiiOikein kanssa)
3. TestiLempelZivWelch
   - koodauksenLopputulosOikeinYhdeksanBitinPaloissa: Testataan, että koodaus toimii
   oikein silloin, kun palan koko on 9 bittiä (yleistapaus)
   - koodauksenLopputulosOikeinKahdeksantoistaBitinPaloissa: testataan, että koodaus
   toimii kahdeksallatoista bitillä, mitä ohjelman varsinaisessa versiossa käytetään
   - bittimuotoOikein: Tarkistetaan, että merkit muunnetaan oikeaan binääriesitysmuotoon
   - pakattuDataTulkitaanOikein: Tarkistetaan, että lzw koodattu data tulkitaan oikein
   takaisin merkkijonoksi
   - datanKirjoitusToimii: Tarkistetaan, että lzw tiedostojen kirjoitus onnistuu
4. TestiPrioriteettijono
   - prioriteettijonoOikeassaJarjestyksessa: Tarkisteaan prioriteettijonon toiminta
   yleistapauksessa
5. TestiHajautustaulu
   - hajautustauluunLisaysToimii: Testataan hajautustaulun toimintaa yleistapauksessa
   - hajautustauluTestiHuffmanSolmulla: Testaan hajautustaulun toimintaa HuffmanSolmuilla
6. TestiLista
   - listaanLisaysToimii: Testataan listan toiminta yleistapauksessa

### Testikattavuus

Testikattavuus on n. 80% tasolla:

![Jacoco-raportti 1](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/jacoco1.png)

![Jacoco-raportti 2](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/jacoco2.png)

![Jacoco-raportti 3](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/jacoco3.png)

![Jacoco-raportti 4](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/jacoco4.png)

![Jacoco-raportti 5](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/jacoco5.png)

## Checkstyle

Koodin laatu on tarkistettu checkstylellä, virheiden määrä on tällä hetkellä 0

![Checkstyle-raportti](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/jacoco-kuvat/checkstyle1.png)

## Järjestelmätestaus ja toiminnallisudet

Järjestelmätestaus on suoritettu manuaalisesti, siten, että ohjelmassa on suoritettu
erikseen kaikki käyttötarkoituksen mukaiset oleelliset toiminnallisuudet.

Pakkaus ja purkaminen on testattu mahdollisimman erilaisilla tiedostoilla.
Ohjelman sisäänrakennetun tiedostojen tarkistuksen lisäksi purkutoiminnon
toimivuus on tarkistettu vertailemalla erikseen komentorivillä alkuperäistä ja
pakatusta tiedostosta purettua tiedostoa keskenään komennolla

```console
$ diff -s alkuperäinen.txt purettu.txt
```

ja tarkistettu näin, että purettu tiedosto on identtinen alkuperäisen kanssa.
