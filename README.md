Single Responsibility Principle (SRP): Każda klasa powinna mieć tylko jeden powód do zmiany, co oznacza, że powinna mieć tylko jedno zadanie.

BookRestController i AuthRestController są dobrymi przykładami. Każdy kontroler jest odpowiedzialny za obsługę żądań związanych z określoną domeną: odpowiednio książkami i uwierzytelnianiem.
Zasada otwartości/zamknięcia (OCP): Klasy powinny być otwarte na rozszerzenia, ale zamknięte na modyfikacje.

Zasada ta jest widoczna w użyciu interfejsów takich jak UserService. Implementacja tych usług (UserServiceImpl) może zostać zmieniona bez modyfikowania klas, które z nich korzystają.
Liskov Substitution Principle (LSP): Obiekty nadklasy powinny być zastępowalne obiektami jej podklas bez zmiany poprawności programu.

Kod nie zawiera wyraźnego przykładu dziedziczenia, w którym ta zasada jest stosowana. Zasada ta jest jednak domyślnie przestrzegana w przypadku korzystania przez Spring z interfejsów i ich implementacji.
Interface Segregation Principle (ISP): Klient nie powinien być zmuszany do polegania na interfejsach, których nie używa.

Dobrymi przykładami są interfejsy UserService, BookRepository i UserRepository. Są one specyficzne dla swoich domen, a klienci mogą korzystać z tych interfejsów bez przeciążania ich niepotrzebnymi metodami.
Zasada odwracania zależności (DIP): Moduły wysokiego poziomu nie powinny zależeć od modułów niskiego poziomu. Oba powinny zależeć od abstrakcji.

Konstruktory w kontrolerach takich jak AuthController i BookController wykazują inwersję zależności. Zależą one od interfejsów usług (UserService, BookRepository), a nie od konkretnych implementacji.