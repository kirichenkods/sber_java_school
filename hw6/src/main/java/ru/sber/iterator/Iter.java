package ru.sber.iterator;

import java.util.NoSuchElementException;

public interface Iter<E> {
    /**
     * Вернет true, если еще есть элементы в итерируемом объекте
     */
    boolean hasNext();

    /**
     * Возвращает следующий элемент
     * @throws NoSuchElementException если нет больше элементов
     */
    E next() throws NoSuchElementException;

    /**
     * Удаление элемента
     * @throws IllegalStateException если нет такого элемента
     */
    void remove() throws IllegalStateException;
}