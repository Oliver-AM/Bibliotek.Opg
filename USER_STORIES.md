# Bibliotekssystem – User stories og acceptkriterier

## Formål
Bibliotekssystemet skal kunne håndtere bøger, lydbøger, film og videospil samt registrere lånere, udlån og afleveringer.

## 1. Registrere en ny låner
**User story:** Som bibliotekar vil jeg kunne registrere nye lånere, så de kan låne bibliotekets materialer.

**Acceptkriterier:**
- Når jeg registrerer en låner med navn og et nyt medlemsnummer, skal låneren kunne findes ved søgning på medlemsnummeret.
- Når jeg prøver at registrere en anden låner med et medlemsnummer, der allerede findes, skal registreringen afvises, og programmet skal vise en forklaring.

## 2. Søge efter materialer
**User story:** Som bibliotekar vil jeg kunne søge efter materialer i kataloget, så jeg hurtigt kan finde det ønskede materiale.

**Acceptkriterier:**
- Når jeg søger på hele eller dele af en titel, skal alle registrerede materialer med matchende titel vises, uanset materialetype og store/små bogstaver.
- Når søgningen ikke matcher nogen materialer, skal programmet vise beskeden "Ingen materialer fundet."

## 3. Låne et materiale
**User story:** Som bibliotekar vil jeg kunne låne et materiale ud til en registreret låner, så låneren kan bruge materialet.

**Acceptkriterier:**
- Når en registreret låner låner et registreret og ledigt materiale, skal programmet bekræfte lånet, og materialet skal fremgå af lånerens aktive lån.
- Når materialet allerede er udlånt, skal et nyt låneforsøg afvises med en forklaring.
- Når låneren eller materialet ikke findes, skal forsøget afvises med en forklaring på, hvad der mangler.

## 4. Aflevere et materiale
**User story:** Som bibliotekar vil jeg kunne registrere afleveringer, så materialerne bliver tilgængelige for andre lånere.

**Acceptkriterier:**
- Når jeg afleverer et udlånt materiale, skal programmet bekræfte afleveringen, og materialet må ikke længere vises blandt lånerens aktive lån.
- Når jeg prøver at aflevere et materiale uden et aktivt lån, skal programmet oplyse, at der ikke er et aktivt lån.

## 5. Se en låners aktive lån
**User story:** Som bibliotekar vil jeg kunne se en låners aktive lån, så jeg kan hjælpe låneren med at få overblik.

**Acceptkriterier:**
- Når jeg indtaster medlemsnummeret for en registreret låner med aktive lån, skal programmet vise de lånte materialers titler og afleveringsfrister.
- Når den registrerede låner ikke har aktive lån, skal programmet vise en besked om dette.

## 6. Se overskredne lån
**User story:** Som bibliotekar vil jeg kunne se materialer med overskredet afleveringsfrist, så biblioteket kan hjemkalde dem.

**Acceptkriterier:**
- Når jeg viser overskredne lån, skal alle aktive lån med afleveringsfrist før dags dato vises sammen med lånerens navn og fristen.
- Afleverede lån og aktive lån, hvis frist er i dag eller senere, må ikke fremgå af listen.

## Prioritering
Vi implementerer **user story 1 – Registrere en ny låner** først, fordi en låner skal være registreret, før vedkommende kan låne et materiale. Derefter prioriterer vi søgning, udlån, aflevering, visning af aktive lån og overskredne lån.

## Teknisk løsning
- `Resource` er superklassen med `identifier`, `title` og `creator`.
- `Book`, `AudioBook`, `Movie` og `VideoGame` arver fra `Resource`.
- `Library` gemmer materialer samlet i en `ArrayList<Resource>`.
- `Loan` forbinder et medlem med et materiale og en afleveringsfrist.
- `loan()` returnerer `LoanResult` (`SUCCESS`, `MEMBER_NOT_FOUND`, `RESOURCE_NOT_FOUND` eller `ALREADY_LOANED`), så brugeren får en forklaring på resultatet.

## Manuel afprøvning
Funktionerne er afprøvet via konsolmenuen. Overskredne lån er også afprøvet med et midlertidigt testlån med en ældre lånedato. Testkoden er efterfølgende fjernet.
