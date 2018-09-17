## Skaleret DIS
Lad os prøve at stresse vores DIS og se hvor godt det egentligt performer.

I denne udgave af samme kode som fra 2. øvelsesgang har jeg indlagt en meget lang for-loop på den tråd som eksekverer programmet.
Dette er for at simulere en meget stort/langsom arbejdsopgave som serveren skal lave, inden den kan returnere til klienten.

#### 1. Opgave
1. Klon og åben dette projekt på din computer
2. Lav et request i din browser
3. Inspicer hvor lang tid requestet tager

#### 2. Opgave
For at teste hvad den langsomme svartid rent faktisk har på et stort DIS (dvs. meget trafik), er vi nødt til at simulere mange request på serveren.
En nem måde at gøre dette på er ved at installere en load tester.

Jeg har desværre kun kendskab til et værktøj som virker på Mac, så har du en PC  er du selvfølgelig velkommen til at lave din egen 
research på Google, ellers find en kammerat med en Mac og lave resten af denne opgave med.
Alternativt kan Mac'en køre Siege i mens PC'en kører serveren.

Hvis i ønsker at kommunikere med hinandens computere, skal i slå jeres firewall fra, sikre jer at i er på samme lokale netværk, og så find jeres lokale ip-adresse (starter med 192.168) og så indtaste denne efterfulgt af portnummeret. Ex "http://192.168.1.140:31337/"

Installer Siege:
Mac med homebrew skriver bare: "brew install siege".
Alternativt:
1. Naviger til den mappe hvor du vil have installationsfilerne. F.eks.: `cd /Downloads`
2. Eksekver følgende i din terminal
3. `curl -C - -O http://download.joedog.org/siege/siege-latest.tar.gz`
4. `tar -xvf siege-latest.tar.gz`
5. `cd siege-4.0.4/`
6. `sudo ./configure`
7. `sudo make`
8. `sudo make install`

Luk din terminal (CMD+Q) og åbn den igen. 
Skriv `siege -V`. Din terminal skulle gerne vise dig noget lignende: `SIEGE 4.0.4`  

Vi er nu klar til at battle-teste vores server med Siege!

9. Kør din Server
10. Åbn din terminal
11. Eksekver: `siege -c 10 -t 20S -b http://localhost:31337 -v`

OBS: husk at rette din port, hvis du bruger en anden. Her er hvad der foregår:

`-c` er hvor mange request der skal foretages ad gangen
`-t` er hvor lang tid (læg mærke til S'et, det markere "sekunder")
`-v` betyder "verbose", dvs. forklarende tekst under udførsel

Prøv at leg rundt med de forskellige indstillinger og se hvor godt din server performer (HINT: `CTRL+C` afslutter en proces i terminalen)

- Hvor mange transactions kan jeres dis lave på 20 sekunder?
- Hvor høj er jeres transaction rate?
    
        Lifting the server siege...
        Transactions: 5 hits
        Availability: 100.00 %
        Elapsed time: 19.87 secs
        Data transferred: 0.00 MB
        Response time: 9.98 secs
        Transaction rate: 0.25 trans/sec
        Throughput: 0.00 MB/sec
        Concurrency: 2.51
        Successful transactions: 5
        Failed transactions: 0
        Longest transaction: 16.82
        Shortest transaction: 0.00

#### 3. Opgave
1. Skift branch til `thread`
2. Udfør step 9., 10. og 11. igen
3. Sammenlign resultaterne

- Hvor mange transactions?
- Hvor høj er jeres nye transactions rate?

        Lifting the server siege...
        Transactions:		          29 hits
        Availability:		      100.00 %
        Elapsed time:		       19.97 secs
        Data transferred:	        0.00 MB
        Response time:		        6.23 secs
        Transaction rate:	        1.45 trans/sec
        Throughput:		        0.00 MB/sec
        Concurrency:		        9.05
        Successful transactions:          29
        Failed transactions:	           0
        Longest transaction:	       10.43
        Shortest transaction:	        3.83

#### 4. Opgave
- Hvad skyldes forskellen? Hvorfor performer den ene server bedre end den anden?

//Flere tråde kan håndtere flere klienter. Obs vær opmærksom på race-conditions og the lost update problem
- Er dette horisontalt eller vertikal skalering?

//Horisontal skalering er flere computere. Vertikal skalering er mere computerkraft (hence det her er vertikalt)
- Er dette er realistisk testmiljø? Hvilke faktorer kan måske give en forkert test?

//Vi kører på localhost, dvs nærmest alt er forkert i forhold til en realistisk webside
//JVM laver en gang i mellem nogle mærkelige optimeringsting, vi ikke har kontrol over. Hvis det java optimerer det "simulerede" loop, så er dette endnu mindre realistisk

#### 5. Opgave (Hvis der er tid)
I branchen `caching` er der implementeret en halvfærdig simpel caching funktion som simulere et meget langsomt kald til en remote database
Metoden `getElement` skal modificeres, så den ikke ryger ind i det lange for-loop, hvis elementet er blevet søgt på en gang.

- Hvad er tidsforskellen på den cachede version og første gang man forsøger at hente et element.
- Hvor mange iterationer drejer det sig om?
 
