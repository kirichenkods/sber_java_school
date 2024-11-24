package ru.sbt.generics.task1;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private final Map<T, Integer> container;

    public CountMapImpl() {
        this.container = new HashMap<>();
    }

    @Override
    public void add(T t) {
        container.put(t, container.getOrDefault(t, 0) + 1);
    }

    @Override
    public int getCount(T t) {
        return container.getOrDefault(t, 0);
    }

    @Override
    public int remove(T t) {
        return container.remove(t);
    }

    @Override
    public int size() {
        return container.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        source.toMap().forEach((k, v) -> container.merge(k, v, Integer::sum));
    }

    @Override
    public Map<T, Integer> toMap() {
        return this.container;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(this.container);
    }
}
