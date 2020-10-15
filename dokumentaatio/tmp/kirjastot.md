# Käytetyt kirjastot

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
