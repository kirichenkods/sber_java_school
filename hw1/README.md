Домашнее задание №1
Цель: познакомится со статическими и нестатическими блоками, определить, как и в какой последовательности вызываются блоки и конструкторы базового класса и наследников.

Задание

Написать абстрактный класс Parent, реализовать

1. приватное поле Name
2. статический блок, выводящий на экран «Parent:static 1»
3. нестатический блок, выводящий на экран «Parent:instance 1»
4. статический блок, выводящий на экран «Parent:static 2»
5. конструктор без параметров, выводящий на экран «Parent:constructor»
6. нестатический блок, выводящий на экран «Parent:instance 2»
7. конструктор c параметром Name, выводящий на экран «Parent:name-constructor»

Написать класс Child, расширяющий класс Parent, реализовать:

1. статический блок, выводящий на экран «Child:static 1»
2. нестатический блок, выводящий на экран «Child:instance 1»
3. статический блок, выводящий на экран «Child:static 2»
4. конструктор без параметров, выводящий на экран «Child:constructor»
5. конструктор c параметром Name, выводящий на экран «Child:name-constructor»
6. нестатический блок, выводящий на экран «Child:instance 2»

Создать два объекта Child(), Child(“Ivan”)