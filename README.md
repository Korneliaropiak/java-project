# Projekt na procesy wytwarzania oprogramowania
https://github.com/Korneliaropiak/java-project
## Zasada pojedynczej odpowiedzialności (SRP): Każda klasa powinna mieć jedną odpowiedzialność, a tym samym tylko jeden powód do zmiany.
BookController i BookRestController są dobrymi przykładami, ponieważ koncentrują się tylko na operacjach związanych z książkami, takich jak dodawanie, aktualizowanie lub usuwanie książek.
AuthController i AuthRestController obsługują tylko zadania związane z uwierzytelnianiem, takie jak logowanie, rejestracja i zarządzanie użytkownikami.

## Zasada otwartości/zamknięcia (OCP): Klasy powinny być otwarte na rozszerzenia, ale zamknięte na modyfikacje.
Dobrym przykładem jest wykorzystanie interfejsów takich jak UserService. Pozwala to na łatwe rozszerzenie klasy UserServiceImpl bez modyfikowania interfejsu.
Klasy Book i User mogą być rozszerzane o nowe funkcje bez modyfikowania istniejącego kodu.
 
## Liskov Substitution Principle (LSP): Obiekty nadklasy powinny być zastępowalne obiektami jej podklas bez wpływu na poprawność programu.
Zasada ta nie jest bezpośrednio widoczna w dostarczonym kodzie, ale ma zastosowanie w sposobie korzystania z dziedziczenia i interfejsów. Na przykład, gdyby istniały podklasy User, powinny one być użyteczne wszędzie tam, gdzie oczekuje się User.

## Interface Segregation Principle (ISP): Żaden klient nie powinien być zmuszany do polegania na metodach, których nie używa.
Interfejs UserService jest dobrym przykładem, ponieważ zapewnia konkretne metody związane z użytkownikami. Nie zmusza on klas implementacji do posiadania metod, które nie są istotne dla operacji użytkownika.

## Zasada odwracania zależności (DIP): Moduły wysokiego poziomu nie powinny zależeć od modułów niskiego poziomu. Oba powinny zależeć od abstrakcji.
Wstrzykiwanie konstruktora w klasach takich jak BookController, UserServiceImpl i CustomUserDetailsService demonstruje tę zasadę. Zależą one od abstrakcji (BookRepository, UserRepository, RoleRepository itp.), a nie od konkretnych implementacji.
