# Tema POO  - GwentStone

### Marinescu Maria-Catalina, 324CA

In realizarea temei am creat doua pachete "gametools" ce contine clasa in <br> 
care se initializeaza jocul si "cardtypes" unde se afla clasele ce reprezinta <br>
fiecare tip de carte si o clasa cu actiunile ce vor fi realizate.

#### **cardtypes** : <br>
In acest pachet am creat o clasa abstracta "Card" in care se afla <br>
caracteristicile comune pentru toate cele 3 tipuri de carti. Aceasta clasa este<br>
mostenita de alte 3 clase abstracte ce reprezinta cele 3 tipuri "Minion", <br>
"Environment" si "Hero" unde am initializat fiecare camp in cadrul <br>
constructorului, adaugand alte campuri daca acestea erau necesare. Fiecare <br>
dintre aceste 3 clase este mostenita la randul ei de clasele ce constituie <br>
cartile ce fac parte din tipul respectiv, in cadrul lor fiind implementate si <br>
atacurile fiecarei carti ce suprascriu metoda din clasa parinte.<br>
Totodata, in acest pachet se afla si clasa "Command" in care pentru fiecare<br>
actiune data de la input am apelat metodele necesare pentru indeplinirea lor<br>
si le am afisat in format Json.<br>

#### **gametools** :
In acest pachet am creat clasa "CreateGame" ce contine toate elementele<br>
ce constituie jocul.<br>
> **metoda createCards** convertest cartile primite de la input conform tipului <br>
lor in functie de numele cartii.

> **metoda createHero** este asemanatoare cu cea de createCards, doar ca <br>
aceasta converteste cartea erou primita de la input.<br>

> **constructorul CreateGame** primeste ca parametrul inputul si initializeaza <br>
fiecare camp al clasei. Cartile primite se convertesc cu ajutorul functiei<br>
createCards si se adauga in pachetul jucatorului corespunzator.<br>

> **metoda startGame** verifica atunci cand se termina o runda (o data la doua <br>
ture) si creste mana care trebuie sa fie adaugata fiecarui jucator si adauga <br>
in mana fiecarui jucator o carte din pachet.<br>
>
> **metoda placeCard** verifica al cui este randul iar pentru fiecare dintre cei<br>
doi jucatori efectueaza urmatorii pasi: verifica daca numarul cartilor din <br>
mana este mai mare decat cartea care trebuie sa fie plasata pe masa, in acest<br>
caz se verifica daca mana jucatorului respectiv este mai mica decat mana de pe<br>
carte, daca este adevarat atunci de afiseaza mesajul de eroare corespunzator. <br>
In continuare cartea se plaseaza pe masa daca la locul corespunzator<br>
(conform numelui) pe tabla daca randul nu este plin si daca mana jucatorului<br>
este mai mare sau egala decat mana cartii, totodata scazandu-se si mana <br>
jucatorului. In cazul in care randul este plin sau cartea care trebuie plasata<br>
pe masa este de tip Environment se vor afisa mesajele de eroare <br>
corespunzatoare.



