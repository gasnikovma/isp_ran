package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Collection<Integer>first  = new HashSet<>(Arrays.asList(1, 2));
        Collection<Character>second  = Arrays.asList('3', '8');
        Collection<String>third  = new HashSet<>(Arrays.asList("hse","miem"));
        List<Collection<?>> a = new ArrayList<>();
        a.add(first);
        a.add(second);
        a.add(third);
        CartesianProductIterator<?> cartesianProductIterator = new CartesianProductIterator(a);
        System.out.println(cartesianProductIterator.next());
        while (cartesianProductIterator.hasNext()) {
            System.out.println(cartesianProductIterator.next());
        }
    }
}