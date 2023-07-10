# ResourceManagerApp

Należy napisać aplikację, dzięki której użytkownicy będą mogli zarządzać swoimi zasobami.
(zasóbem może być np. mapa, badanie, plik - generalnie jakiś twór zawierajacy dane nt. zasobu
konkretnego typu).<br />
Technologie/narzedzia:<br />
● Java 8+<br />
● SpringBoot<br />
● Postgresql<br />
● Lombok (wedle uznania - jesli chcesz przecwiczyc gettery/settery konstruktory to pozniej
mozesz przerobic calosc na lomboka)<br />
Wymagania:<br />
Aplikacja powinna zawierac reprezentacje uzytkownika i zasobu. Zarówno uzytkownika i zasób trzeba
zapisywac w bazie danych postgres. Jeden uzytkownik moze posiadac kilka zasobow.
Przydatne informacja szukać pod haslem: spring data configuration, Spring Data JPA,
@OneToMany, Spring Data Id Generation<br />
Uzytkownik powinien zawierac informacje:<br />
● id<br />
● pseudonim<br />
● imie<br />
● nazwisko<br />
● data utworzenia<br />
● data modyfikacji<br />
● typ uzytkownika: DEFAULT, SUPER_USER<br />
Zasob powinien zawierac informacje:<br />
● id<br />
● nazwa<br />
● data utworzenia<br />
● data modyfikacji<br />
● id wlasciciela (uzytkownika)<br />
● typ: MAP, RESEARCH, FILE<br />
● metadane zapisane w postaci JSON<br />
Należy udostępnić api REST-owe pozwalajace na wykonywania nastepujacych operacji:<br />
● dodawawnie uzytkownika<br />
● usuwanie uzytkownika<br />
● edytowanie pseudonimu uzytkownika<br />
● dodawanie zasobu (musi byc przypisany do konkretnego uzytkownika)<br />
● usuwanie zasobu<br />
● edycja nazwy zasobu i metadanych (JSON)<br />
Calosc nalezy umiescic na GitHub i regularnie commitowac zmiany (projekt musi sie budowac)