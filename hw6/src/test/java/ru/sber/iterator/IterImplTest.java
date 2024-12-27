package ru.sber.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IterImplTest {

    private IterImpl<String> iter;
    private IterImpl<String> emptyIter;

    @BeforeEach
    void setUp() {
        String[] elements = {"hasNext", "next", "remove"};
        iter = new IterImpl<>(elements);

        String[] emptyArray = {};
        emptyIter = new IterImpl<>(emptyArray);
    }

    @Test
    void givenIterWithThreeElements_whenCallingHasNext_thenReturnTrue() {
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
    }

    @Test
    void givenEmptyIter_whenCallingHasNext_thenReturnFalse() {
        assertFalse(emptyIter.hasNext());
    }

    @Test
    void givenIterWithThreeElements_whenCallingNext_thenReturnCorrectValue() {
        Assertions.assertEquals("hasNext", iter.next());
        Assertions.assertEquals("next", iter.next());
        Assertions.assertSame("remove", iter.next());
    }

    @Test
    void givenEmptyIter_whenCallingHasNext_thenThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, emptyIter::next);
        assertThrows(NoSuchElementException.class, emptyIter::next, "Нет больше элементов для перебора");
    }

    @Test
    void givenIteratorWithThreeElements_whenCallingRemove_thenReturnCorrectValue() {
        iter.next();
        iter.remove();
        assertEquals("remove", iter.next());
    }

    @Test
    void givenEmptyIter_whenCallingRemove_thenThrowsIllegalStateException() {
        Assertions.assertThrows(IllegalStateException.class, emptyIter::remove);
        Assertions.assertThrows(IllegalStateException.class, emptyIter::remove, "Нет элементов для удаления");
    }
}
