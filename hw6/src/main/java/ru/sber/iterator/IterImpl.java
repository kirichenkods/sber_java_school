package ru.sber.iterator;

import java.util.NoSuchElementException;

public class IterImpl<E> implements Iter<E> {
    private E[] array;
    private int pointer = 0;
    @Override
    public boolean hasNext() {
        return (array.length - pointer) > 0;
    }

    @Override
    public E next() throws NoSuchElementException {
        if (pointer >= array.length) {
            throw new NoSuchElementException("Нет больше элементов для перебора");
        }
        return array[pointer++];
    }

    @Override
    public void remove() throws IllegalStateException {
        if (array.length - 1 < 0) {
            throw new IllegalStateException("Нет элементов для удаления");
        }
        @SuppressWarnings("unchecked")
        E[] nextArray = (E[]) new Object[array.length - 1];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != pointer) {
                nextArray[k++] = array[i];
            }
        }
        this.array = nextArray;
    }

    public IterImpl(E[] array) {
        this.array = array;
    }
}