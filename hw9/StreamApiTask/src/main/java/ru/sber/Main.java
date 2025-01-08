package ru.sber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person(33, "Ivan"));
        someCollection.add(new Person(18, "Alexandra"));
        someCollection.add(new Person(62, "Fedor"));
        someCollection.add(new Person(24, "Victoria"));
        someCollection.add(new Person(40, "Konstantin"));

        Map<?, Person> m = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getAge() + 30, p.getName()))
                .toMap(Person::getName, p -> p);
        System.out.println(m);
    }
}
