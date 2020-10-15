# Nopeuden optimoiminen

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
