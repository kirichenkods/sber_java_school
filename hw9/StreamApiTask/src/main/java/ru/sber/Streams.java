package ru.sber;

import lombok.ToString;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ToString
public class Streams<T> {
    private final Collection<T> collection;

    public Streams(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * Статический метод, который принимает коллекцию и создает новый объект Streams
     */
    public static <T> Streams<T> of(List<T> list) {
        return new Streams<T>(list);
    }

    /**
     * Оставляет в коллекции только те элементы, которые удовлетворяют условию в лямбде
     */
    public Streams<T> filter(Predicate<? super T> predicate) {
        Collection<T> filterCollection = new ArrayList<>();
        collection.stream()
                .filter(predicate)
                .forEach(filterCollection::add);

        return new Streams<>(filterCollection);
    }

    /**
     * Преобразует элемент в другой.
     */
    public Streams<T> transform(Function<? super T, ? extends T> f) {
        Collection<T> transformCollection = collection.stream()
                .map(f)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Streams<>(transformCollection);
    }

    /**
     * - принимает 2 лямбды для создания мапы, в одной указывается,
     * что использовать в качестве ключа, в другой, что в качестве значения.
     */
    public Map<?, T> toMap(Function<? super T, ?> keyMapper, Function<? super T, ?> valueMapper) {
        Map<Object, T> map = new HashMap<>();
        for (T item : collection) {
            map.put(keyMapper.apply(item), (T) valueMapper.apply(item));
        }
        return map;
    }

}
