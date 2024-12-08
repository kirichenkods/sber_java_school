### Ответить на вопросы:
##### 1) Почему на любом объекте можем вызвать метод getClass()?
>В Java все классы - наследники класса Object, у которого определен метод getClass(), благодаря наследованию, у любого объекта можно вызвать этот метод.
##### 2) Почему на любом классе можем вызвать .class?
>В Java все классы являются объектами класса Class, оператор .class используется для получения экземпляра этого класса. Вызов .class на типе эквивалентно вызову getClass() на экземпляре типа. Отличие в том, что .class используется когда нужно получить информацию о типе, но нет экземляра этого типа.
##### 3) В чём отличие динамического прокси от статического? Приведите преимущества и недостатки.
>Динамический прокси - генерация в runtime. Возможность динамически изменять поведение объекта, но может медленнее работать в определенных сценариях, потенциально может дать больше ошибок.
>Статический прокси - генерация во время компиляции. Требуется явное создание нового класса. Из-за этого статический прокси менее гибкий, но более предсказуемый.
##### 4) Есть ли разница в инициализации класса через new и статический метод newInstance()?
>При создании объекта через new используется конструктора класса, экземпляр которого создается. newInstance используется для динамического создания объектов, когда тип класса неизвестен во время компиляции.
##### 5) Можно ли с помощью рефлексии изменить значения полей аннотации?
>да, с помощью рефлексии можно получить аннотации через getAnnotation(), и изменить значение через метод set() у Field

### Практика:
##### Задача 1: Имплементировать следующий интерфейс в классе CalculatorImpl
```java
public interface Calculator{
    /**
    * Расчет факториала числа.
    * @param number
    */
    int calc (int number);
}
```
##### Задача 2: Вывести на консоль все методы класса, включая все родительские методы (включая приватные)

##### Задача 3: Вывести все геттеры класса

##### Задача 4: Проверить что все String константы имеют значение = их имени
````java
public static final String MONDAY = "MONDAY";
````

##### Задача 5: Реализовать кэширующий прокси

##### Задача 6: Создать свою аннотацию Metric. Реализовать proxy класс PerformanceProxy, который в случае если метод аннотирован Metric будет выводить на консоль время выполнения метода.
Т.е написав
```java
public interface Calculator{
    /**
    * Расчет факториала числа.
    * @param number
    */
    @Metric
    int calc (int number);
}
```
И использовав его
```java
    Calculator calculator = new PerformanceProxy(new Calculator()));
    System.out.println(calculator.calc(3));
```
Должны увидеть:

Время работы метода: ххххх (в наносек)

##### Задача 7: Реализовать следующий класс по документации
```java
public class BeanUtils {
    /**
    * Scans object "from" for all getters. If object "to"
    * contains correspondent setter, it will invoke it
    * to set property value for "to" which equals to the property
    * of "from".
    * <p/>
    * The type in setter should be compatible to the value returned
    * by getter (if not, no invocation performed).
    * Compatible means that parameter type in setter should
    * be the same or be superclass of the return type of the getter.
    * <p/>
    * The method takes care only about public methods.
    *
    * @param to   Object which properties will be set.
    * @param from Object which properties will be used to get values.
    */
    public static void assign(Object to, Object from) {... }
}

```
