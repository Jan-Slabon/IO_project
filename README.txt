Projekt jest w fazie testowej, nie posiada zdalnej bazy danych.
Stąd do działanie wymaga bazy danych mysql o nazwie appdb
z danymi logowania zapisanymi w pliku konfiguracyjnym połączenia
z bazą danych.

W projekcie zostały wykorzystane 2 wzorce:
Adapter (wrapper) w postaci frameworka Hibernate umożliwia współprace JDBC z klasą database
odpowiedzialną za implementację potzrebnych metod
Fasada Interfejs Database dostarcza najbardziej 
potrzebnych funkcjonalności w komunikacji systemu z bazą danych.