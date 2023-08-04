## Koodnimed

Rakendus talletab ja võimaldab kuvada kontaktandmeid. Andmed on salvestatud PostgreSQL andmebaasis db_clients.sql.
Rakenduse back-end on loodud java ja spring boot tehnoloogiatega. Kasutajaliides on loodud javascripti ja vue.js kasutades.
Infosüsteemil on REST API, mis toetab json formaadis andmeid.

Rakenduse kasutajaliideses kuvatakse korraga 10 kontakti, kontakte on võimalik sorteerida nime, koodnime ja telefoninumbri järgi nii kasvavas, kui kahanevas järjekorras.
Kontakte on võimalik läbi kasutajaliidese otsingusõna järgi otsida.

### Käivitamine

Rakenduse käivitamiseks tuleb kõigepealt luua andmebaas.
Sobilik andmebaasi *dump* on olemas failis **db_clients_dump.sql**.

Vastavalt andmebaasile tuleb application.properties failis muuta järgmiste muutujate väärtused:
- spring.datasource.url (uuendada andmebaasi nime ja pordiga)
- spring.datasource.username=clients (uuendada andmebaasile juurde pääseva kasutaja kasutajanimega)
- spring.datasource.password (uuendada andmebaasile juurde pääseva kasutaja parooliga)

#### Server

Serveri otseseks käivitamiseks navigeerida projekti kustas Server\demo kusta ja jooksutada käsureal järgmine käsklus:

***./mvnw spring-boot:run***

Selle käsu jookustamisel peaks server tööle hakkama pordil 8080.


## Frontend

Kasutajaliidese käivitamiseks navigeerida projekti kaustas Client\frontend kausta ja jooksutada käsureal jäsklus:

***npm run serve***

Selle käsu jooksutamisel peaks kliendi server tööle hakkama pordil 8081.

Kui Kliendi rakendus ei tööta korrektselt, kontrollige, kas kliendi rakendus on pordil 8081. Kui ei ole, siis avage projektis fail Server\demo\src\main\java\com\example\demo\MainController.java ja muutke real 17 asuv pordi number nii, et see oleks sama port, millel töötab kliendi rakendus.