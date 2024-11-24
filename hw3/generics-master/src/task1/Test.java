package ru.sbt.generics.task1;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        System.out.println(map.toMap());

        System.out.println("getCount(5): " + map.getCount(5));

        System.out.println("map before remove 6: " + map.toMap());
        map.remove(6);
        System.out.println("map after remove 6: " + map.toMap());

        System.out.println("size: " + map.size());
        System.out.println("map " + map.toMap());

        Map<Integer, Integer> mapDestination = new HashMap<>();
        map.toMap(mapDestination);
        System.out.println("mapDestination " + mapDestination);
    }
}
