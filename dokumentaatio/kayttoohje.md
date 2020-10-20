# Käyttöohje

## Ohjelman lataaminen koneelle

Uusimman version voi ladata joko zip-pakettina github repositorion Code painikkeella
ja valitsemalla vaihtoehto Download as ZIP

![ZIP paketti githubista](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/dokumentaatio/kuvat/ohjekuva1.png)

Vaihtoehtoisesti uusimmman version voi ladata komentoriviltä gitillä.

Ohjelmasta on saatavilla myös jar-pakettina oleva julkaisuversio:
[Ohjelman ensimmäinen loppupalautus-ehdokas versio](https://github.com/nikomn/tiralabra-pakkausalgoritmit/releases/tag/rc1)



## Käynnistäminen

### Jar versio

Jos kyseessä on ladattu jar-tiedostona sen voi suorittaa komennolla

```console
$ java -jar tiralabra-pakkausalgoritmit.jar
```

### Githubista ladattu versio

Gitistä ladatun version voi avata esim. netbeansissa ja käynnistää sieltä tai
vaihtoehdoisesti komentoriviltä projektin kansiossa komennolla

```console
$ gradle run --console=plain
```

### Automaattisten testien ajaminen

Automaattiset testit voi ajaa githubista ladatulle versiolle esim. netbeansissa
valikosta Run -> Run tests (tai Alt+F6)

Jacoco testiraporin saa generoitua komennolla:

```console
$ gradle jacocoTestReport
```

Jacoco-raportti löytyy kansiosta build/reports/jacoco/test/html. Raportti on  
html muodossa, joten sen voi avata esim. firefoxissa.

## Aloitusvalikko

Ensimmäisessä näkymässä voidaan valita ajetaanko demo, pakataanko vai puretaanko.  

```console
Pakkausalgoritmien vertailu
Valitse toiminto
  1. Demo: Pakataan ja puretaan tiedosto Huffman ja LZ algoritmeilla
  2. Pakkaus
  3. Purkaminen
  x. Sammuta ohjelma
```

## Demo

Demo toiminto kysyy pakattavan tiedoston nimeä (huom. projektin mukana satunnaisia  
sanoja sisältävä 1Mt tekstitiedosto 'testi.txt', jota voi halutessaan käyttää  
testaamiseen), jonka jälkeen kyseinen tiedosto  

1. Pakataan Huffman algoritmilla
2. Pakattu Huffman tiedosto puretaan uuteen tiedostoon
3. Alkuperäinen tiedosto pakataan LZ algortimilla
4. Pakattu LZ tiedosto puretaan uuteen tiedostoon

Tiedostoja vertaillaan ja aikoja lasketaan suorituksen aikana ja lopuksi tulostetaan  
yhteenveto, esim.:  

```console
### YHTEENVETO ###

Pakkaustehokkuus

tiedosto	      | koko (tavua)	| pakkaussuhde
----------------|---------------|--------------
alkuperäinen	  | 233493.0	    | 100%
huffman     	  | 117852.0	    | 50.0%
lz          	  | 171325.0	    | 73.0%

Nopeudet

operaatio		            | kesto
------------------------|---------
huffman pakkaus		      | 11159 ms.
huffman purku		        | 9014 ms.
lz pakkaus		          | 3549 ms.
lz purku		            | 12682 ms.

Tiedostojen tarkistus

Huffman pakattu tiedosto on purkamisen jälkeen täsmälleen samanlainen kuin alkuperäinen. Kaikki OK!
LZ pakattu tiedosto on purkamisen jälkeen täsmälleen samanlainen kuin alkuperäinen. Kaikki OK!

#########

Jatka painamalla Enter...
```

## Pakkaus

Pakkauksen aluksi voidaan valita kumpaa algortimia käytetään.  

Pakkauksen yhdeydessä kysytään ensin pakattavaa tiedostoa ja muodostettavan pakatun tiedoston nimeä.  
Pakatun tiedoston nimen loppuun lisätään aina automaattisesti ".huffdat" / ".lzdat"  
eli tiedoston päätettä ei tarvitse määritellä itse.  


## Purkaminen

Purkamisen aluksi määritellään kummalla algoritmilla data on pakattu.  

Purkamisen yhdeydessä kysytään ensin purettavaa tiedostoa ja muodostettavan puretun tiedoston nimeä.  

## Esimerkki ohjelman suorituksesta

```console
$ ls
build  build.gradle  gradle.properties  settings.gradle  src  testi.txt
$ gradle run --console=plain
...
Pakkausalgoritmien vertailu
Valitse toiminto
  1. Demo: Pakataan ja puretaan tiedosto Huffman ja LZ algoritmeilla
  2. Pakkaus
  3. Purkaminen
  x. Sammuta ohjelma
2
Valitse käytettävä pakkausalgoritmi
  1. Huffman
  2. LZW
  x. Peruuta
2
Mikä tiedosto pakataan:
testi.txt

Mikä nimi pakatulle tiedostolle:
lzpakattu_testi
Vaihe 1. Muodostetaan bittitaulua...
1.0% käsitelty...
2.0% käsitelty...
...
Tiedoston kirjoitus valmis!
Alkuperäisen tiedoston koko: 233493.0 tavua
Pakatun tiedoston koko: 171325.0 tavua
Pakattu tiedosto on n. 73.0% alkuperäisestä.

Valitse käytettävä pakkausalgoritmi
  1. Huffman
  2. LZW
  x. Peruuta
x
Pakkausalgoritmien vertailu
Valitse toiminto
  1. Demo: Pakataan ja puretaan tiedosto Huffman ja LZ algoritmeilla
  2. Pakkaus
  3. Purkaminen
  x. Sammuta ohjelma
3
Millä algoritmilla data on pakattu?
  1. Huffman
  2. LZW
  x. Peruuta
2
Mikä tiedosto puretaan?
lzpakattu_testi.lzdat
Mikä nimi puretulle tiedostolle?
lzpurettu_testi.txt
1.0% luettu...
2.0% luettu...
3.0% luettu...
...
100.0% luettu...

LZ tiedosto purettu
Millä algoritmilla data on pakattu?
  1. Huffman
  2. LZW
  x. Peruuta  
x
Pakkausalgoritmien vertailu
Valitse toiminto
  1. Demo: Pakataan ja puretaan tiedosto Huffman ja LZ algoritmeilla
  2. Pakkaus
  3. Purkaminen
  x. Sammuta ohjelma
x

BUILD SUCCESSFUL in 5m 26s  
$ ls
build  build.gradle  gradle.properties  lzpakattu_testi.lzdat  lzpurettu_testi.txt  settings.gradle  src  testi.txt
$ diff -s testi.txt lzpurettu_testi.txt
Files testi.txt and lzpurettu_testi.txt are identical
```
