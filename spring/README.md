`Zadanie 1 - REST-SERVICE`

Napisać aplikację, która stworzy restowy controller i wystawi metodę get pod adresem
względnym /greeting z obsługą parametru name z domyślną wartościa 'World. Metoda powinna zwracać
tekst "Hello " + name. Obowiązkowo testy integracyjne.

Zależności:
* spring-boot-starter-web
* spring-boot-starter-test
* com.jayway.jsonpath

`Zadanie 2 - SPRING BOOT`

Napisać HelloController, 
który wyświetli w konsoli powitanie oraz 
wszystkie definicje stworzonych beanów + 
testy integracyjne.

Zależności:

* spring-boot-starter-web
* spring-boot-starter-actuator
* spring-boot-starter-test
* spring-test

`Zadanie 3 - JDBC-TEMPLATE`

wykorzystać JDBCTemplate do:
* zdropowania tabeli jeżeli istnieje
* stworzenia tabeli
* uzupełnienia tabeli 
* wykonania selecta
* wykonania batchowego inserta
* przepisania danych z tabeli do modelu javowego

Zależności:
* spring-boot-starter-jdbc

`Zadanie 4 - JPA`

Zapisać encję z użyciem repozytorium ze spring data i stworzenie warstwy dao z managerem.

Zależności:

spring-boot-starter-data-jpa

spring-data

com.h2database

`Zadanie 5 - SCHEDULER`

Stworzenie konfigurowalnego przez properties schedulera z wykorzystaniem crona:
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html

Zależności:

spring-context

`Zadanie 6 - TRANSACTIONS`

Prosta aplikacja z wykorzystaniem JDBC template oraz adnotacji @Transactional

Zależności:
* com.h2database

`Zadanie 7 - ASYNC`

Aplikacja mająca na celu uruchomienie w puli wątków asynchronicznych metod, 
oraz sprawdzenie, czy wszystkie się wykonały. 
Wykorzystanie restTemplate do wywołania zdalnie dowolnego serwisu restowego.

Zależności:
* spring-web

`Zadanie 8 - BATCH`
Wzorując się na:
http://malsolo.com/blog4java/?p=260
będziemy chcieli napisać aplikację, 
która batchowo zaczyta nam dane z csv i zapisze w bazie
z wykorzystaniem jobów

Wykorzystanie:
* JdbcBatchItemWriter
* ItemProcessor
* FlatFileItemReader
* jdbcTemplate
* JobExecutionListenerSupport

Zależności:
* spring-boot-starter-batch

`Zadanie 9 - CACHE`

Napisać prosty mechanizm pobierania danych z wykorzystaniem @Cacheable

Zależności:
* spring-boot-starter-cache

`Zadanie 10 - VALIDATOR`

Naisać walidator formularza rozszerzając klasę WebMvcConfigurerAdapter z testami integracyjnymi
i adnotacjami javax.validation.constraints

Zależności:
* spring-boot-starter-thymeleaf
* hibernate-validator 

`Zadanie 11 - MongoDB`
Connector do MongoDB

`Zadanie 12 - JMS`

Napisać aplikacje, która będzie wrzucać wiadomośc dowolnego typu na kolejkę jmsową oraz wyświetlać jej zawartość
z wykorzystaniem JmsListenera

Zależności:
* spring-boot-starter-activemq
* activemq-broker
* jackson-databind

`Zadanie 13 - FILE-UPLOADER`

Napisać aplikację, która będzie potrafiła 
* pobrać plik wskazany przez użytkownika poprzez formularz
* wylistować pobrane pliki
* ściągnąć plik wskazany przez użytkownika
* usunać wszystkie wgrane pliki

Zależności:
* spring-boot-starter-thymeleaf

`Zadanie 14 - NEO4J`
Do zrobienia
* zamodelować encję dla neo4j
* wykorzystać repozytorium neo4j do pobierania i wyświetlania danych
https://neo4j.com/online_training/graphdatabases/

Zależności:
* spring-boot-starter-data-neo4j

