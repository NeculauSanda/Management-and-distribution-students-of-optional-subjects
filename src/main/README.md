Neculau Sanda-Elena Seria CB Grupa 323

Pentru implementarea temei m-am folosit in pricipal de notiunile de genericitate, mostenire, exceptii si encapsulare.

Pentru inceput in programul principal in clasa Main am citit din fisier fiecare comanda si am apelat cu ajutorul
entitatii "secretariat" metoda specifica comenzii. Doar unde mai era nevoie sa prind o exceptie am mai adaugat scrierea in
fisierul de out a exceptiei.

Programul contine alte 5 clase Secretariat, Curs, Student Licenta, Student Master, cele doua extizand clasa Student.

-- Clasa Secretariat -- v-a contine doua arraylist-uri:
- unul care contine studentii din toata facultatea
- si celalat care contine toate cursurile

In cadrul acestei clase se am implementat metodele pentru rezolvarea comenzilor.

-- Clasa Curs -- utilizeaza generacitatea in functie de tipul de student. In cadrul clasei pe langa parametri initiali se afla
si un arraylist ce v-a contine toti studentii repartizati la acel curs.

-- Clasele StudentLicenta si StudentMaster -- extind clasa Student si contin doar un constructor.
-- Clasa Student -- clasa principala a tipului de Student

Comanda "adauga student" v-a fi apelata de catrea secretariat unde se verifica
mai intai daca studentul nu a mai fost deja adugat, daca a fost se arunca o exceptie pe care o prindem in clasa main. Pe 
urma cream tipul de student(master/licenta) si la final il adaugam in lista de studenti.

Comanda "adauga curs" este apelata de secretariat in functie de tipul de studenti pe care ii accepta(licenta/ master) acesta
se v-a da ca parametru metodei. In functie de tipul de studenti pe care ii primeste se generaza cursul si se adauga mai apoi in lista.

Comanda "citeste mediile" se v-a deschide in clasa main fiecare fisier in care sunt trecute mediile pana cand nu mai exita 
fisiere de deschis, atunci se v-a genera o exceptie si se v-a opri din citit. Metoda v-a cauta studentul in lista de studenti 
si cand il gaseste ii v-a atribui media.

Comanda "contestatie" la fel ca la citeste mediile v-a cauta studentul in lista si v-a rescrie noua medie.

Comanda "adauga preferinte" v-a adauga preferintele stundetilor in listele lor de cursuri. Pentru inceput extrag cursurile
preferate apoi caut studentul in baza de date. Dupa ce l-am gasit pentru fiecare curs preferat de el il caut in mai intai in lista 
cu cursuri generala si apoi il adaug la preferintele studentului.

Comanda "repartizeaza" -- prima data ordonam descrescator studentii dupa medie(am utilizat o metoda ce face swap 
intre 2 studenti cu ajutorul unui alt parametru intermediar de tip student). Apoi pentru fiecare student se i-a lista cu optiuni
si se verifica daca mai este loc la prima optiune pe care au ales-o, daca nu are loc, dar are media egala cu cea a ultimului student atunci 
se face o exceptie si il adauga si pe el in lista, daca nu are deloc loc atunci trece la urmatoarea optiune, unde se face aceeasi verificare.
Acest ciclu continua pana cand studentul a fost adaugat in lista cu studenti a cursului respectiv.

Comanda "posteaza curs" v-a deschide fisrierul si v-a scrie detaliile despre curs(nume, capacitate si lista de studenti 
de la cursul respectiv cu tot cu medie).

Comanda "posteaza medii" se v-a sorta mai intai lista cu studenti in functie de medie si
nume doar daca au mediile egale, apoi se v-a deschide fisierul si v-a scrie in ele lista cu studenti si mediile lor.

Comanda "posteaza student" v-a deschide fisierul si v-a afisa datele studentului (tipul de student, nume, medie si cursul la care a fost asignat).
