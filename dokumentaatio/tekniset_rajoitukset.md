# Ohjelman tekniset rajoitukset

Lopulliseen toteutukseen jäi muutamia teknisiä rajoituksia, joihin voisi palata jos  
ohjelmaa haluaisi jatkokehittää myöhemmin:  

## Rajoite 1. Ohjelma kykenee pakkaamaan vain tekstitiedostoja.

Ohjelman alkuperäisen tavoiteasettelun mukaisesti ohjelma kykenee pakkaamaan vain  
tekstitiedostoja (mikä siis oli tavoite, jonka projektille asetin). Ohjelma olisi  
kuitenkin nykyisetä monikäyttöisempi ja yleensä hyödyllisempi, jos sillä  
voisi pakata mitä tahansa dataa. Muutos edellyttäisi käsiteltävän datatyypin  
vaihtamista merkki/merkkijono muodosta tavuiksi. Muutoin kaikki toiminnot toimisivat  
pohjimmiltaan samalla logiikalla kuin nykyisin, mutta koska koko ohjelma on rakennettu  
merkki/merkkijono datan käsittelyä varten, jolloin ohjelman perimmäinen luonne  
edellyttäisi käytännössä koko ohjelman keskeisten toimintojen uudelleen kirjoittamista,  
että ne saisi toteutettu järkevimmällä mahdollisella tavalla koodissa (esimerkki  
ongelmasta ks. [tavumoitoisen datan käsittelyyn vaihtamiskokeilu](https://github.com/nikomn/tiralabra-pakkausalgoritmit/blob/master/tiralabra-pakkausalgoritmit/src/main/java/tiralabra/pakkausalgoritmit/tiedostot/Tiedostonlukija.java#L57-L101)). Tämä edellyttäisi  
niin paljon ohjelman uudelleen kirjoittamista, että siihen ei ole enää mielekästä  
tässä vaiheessa lähteä, koska se vaarantaisi ohjelman valmistumisen deadlineen mennessä.  
Ohjelma kuitenkin nykymuodossaan toimii ja tekee sen, mitä varten se on suunniteltu  
alunperin.  

## 2. Ohjelma pakkaa onnistuneesti vain riittävän suuria tiedostoja.

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

## 3. Ohjelma ei aina tuota oikeaa lopputulosta erittäin isoilla tiedostoilla.

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

## 4. Ohjelma ei aina tuota oikeaa lopputulosta, jos tiedoston merkistökoodaus on utf-8 (tai muu ascii merkistöä laajempi merkistö)

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
