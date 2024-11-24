package ru.sbt.generics.task2;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {
    /**
     * добавляет все элементы из списка source в список destination
     */
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * возвращает индекс объекта t в списке source
     */
    public static <T> int indexOf(List<T> source, T t) {
        return source.indexOf(t);
    }

    /**
     * урезает список source до размера size
     */
    public static <T> List<T> limit(List<T> source, int size) {
        return source.stream().limit(size).collect(Collectors.toList());
    }

    public static <T> void add(List<? super T> source, T t) {
        source.add(t);
    }

    /**
     * удаляет все элементы списка c2 из removeFrom
     */
    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    /**
     * true если первый лист содержит все элементы второго
     */
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return new HashSet<>(c1).containsAll(c2);
    }

    /**
     * true если первый лист содержит хотя бы 1 элемент второго
     */
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    /**
     * Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
     */
    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        return range(list, min, max, Comparator.naturalOrder());
    }

    /**
     * Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
     * На вход принимает Comparator, задающий порядок сравнения элементов
     */
    public static <T> List<T> range(List<T> list, T min, T max, Comparator<? super T> comparator) {
        // если список не содержит искомых значений, вернуть пустой ArrayList
        if (!containsAll(list, Arrays.asList(min, max))) {
            return new ArrayList<>();
        }
        list.sort(comparator);
        int minInd = Math.min(list.indexOf(min), list.indexOf(max));
        int maxInd = Math.max(list.indexOf(min), list.indexOf(max));
        return list.subList(minInd, maxInd + 1);
    }
}