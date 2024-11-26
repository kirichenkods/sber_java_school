package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> fruit1 = CollectionUtils.newArrayList();
        fruit1.add("apple");
        fruit1.add("banana");
        List<String> fruit2 = new ArrayList<>();
        fruit2.add("orange");
        fruit2.add("fig");
        fruit2.add("grapes");

        // addAll
        System.out.println("addAll");
        System.out.println("fruit1 " + fruit1);
        System.out.println("fruit2 " + fruit2);
        CollectionUtils.addAll(fruit1, fruit2);
        System.out.println("fruit1 " + fruit1);
        System.out.println("fruit2 " + fruit2);
        System.out.println();

        // newArrayList
        System.out.println("newArrayList");
        List<String> animals =  CollectionUtils.newArrayList();
        animals.add("dog");
        System.out.println(animals);
        System.out.println();

        // indexOf
        System.out.println("indexOf");
        System.out.println(CollectionUtils.indexOf(fruit2, "peach"));
        System.out.println(CollectionUtils.indexOf(fruit2, "banana"));
        System.out.println();

        // limit
        System.out.println("limit");
        System.out.println("fruit2: " + fruit2);
        System.out.println(CollectionUtils.limit(fruit2, 3));
        System.out.println();

        // add
        System.out.println("add");
        System.out.println("fruit1: " + fruit1);
        CollectionUtils.add(fruit1, "papaya");
        System.out.println(fruit1);
        System.out.println();

        // removeAll
        System.out.println("removeAll");
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(8);
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(6);
        list1.add(4);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(8);
        list2.add(1);
        list2.add(3);
        System.out.println("list1: " + list1);
        CollectionUtils.removeAll(list1, list2);
        System.out.println("list1: " + list1);
        System.out.println();

        //containsAll
        System.out.println("containsAll");
        System.out.println(CollectionUtils.containsAll(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                Arrays.asList(3, 5, 6)));
        System.out.println(CollectionUtils.containsAll(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                Arrays.asList(3, 5, 6, 9)));
        System.out.println();

        // containsAny
        System.out.println("containsAny");
        System.out.println(CollectionUtils.containsAny(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                Arrays.asList(3, 12, 15)));
        System.out.println(CollectionUtils.containsAny(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                Arrays.asList(10, 11)));
        System.out.println();

        // range
        System.out.println("range");
        System.out.println(CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6));
        System.out.println(CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 9, 10));
        System.out.println();

        // range comparator
        System.out.println("range with comparator");
        System.out.println(CollectionUtils.range(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                3,
                6,
                Comparator.reverseOrder()));
        System.out.println(CollectionUtils.range(
                Arrays.asList(8, 1, 3, 5, 6, 4),
                9,
                10,
                Comparator.reverseOrder()));

    }
}
