package example;

import org.example.CartesianProductIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CartesianProductIteratorTest {

    @ParameterizedTest
    @MethodSource("list_provider")
    void CartesianProductIterator_shouldReturnEquals(Collection<Integer> a, Collection<Integer> b, Collection<Integer> c, List<List<Integer>> answer) {
        List<Collection<Integer>> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        res.add(c);
        CartesianProductIterator<Integer> cartesianProductIterator = new CartesianProductIterator<>(res);
        int index = 0;
        while (cartesianProductIterator.hasNext()) {
            Collection<Integer> get = cartesianProductIterator.next();
            List<Integer> expected = answer.get(index);
            assertThat(get.equals(expected)).isEqualTo(true);
            index += 1;

        }
    }

    @Test
    @DisplayName("исключение")
    void CartesianProductIterator_shouldThrowException() {
        List<Collection<Integer>> res = new ArrayList<>();
        CartesianProductIterator<Integer> cartesianProductIterator = new CartesianProductIterator<>(res);
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, cartesianProductIterator::next);
        Assertions.assertEquals("collection is empty", thrown.getMessage());

    }

    @ParameterizedTest
    @MethodSource("set_provider")
    void CartesianProductIterator_shouldReturnEqualsForSet(Collection<Character> a, Collection<Character> b, Collection<Character> c, List<Set<Character>> answer) {
        List<Collection<Character>> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        res.add(c);
        CartesianProductIterator<Character> cartesianProductIterator = new CartesianProductIterator<>(res);
        int index = 0;
        while (cartesianProductIterator.hasNext()) {
            List<Character> get = cartesianProductIterator.next();
            Collections.sort(get);
            List<Character> set = new ArrayList<>(answer.get(index));
            assertThat(get.equals(set)).isEqualTo(true);
            index += 1;

        }
    }

    @ParameterizedTest
    @MethodSource("multi_provider")
    void CartesianProductIterator_shouldReturnEqualsForMix(Collection<Integer> a, Collection<Integer> b, Collection<Integer> c, List<List<Integer>> answer) {
        List<Collection<Integer>> res = new ArrayList<>();
        res.add(a);
        res.add(b);
        res.add(c);
        CartesianProductIterator<Integer> cartesianProductIterator = new CartesianProductIterator<>(res);
        int index = 0;
        while (cartesianProductIterator.hasNext()) {
            List<Integer> get = cartesianProductIterator.next();
            List<Integer> set = new ArrayList<>(answer.get(index));
            assertThat(get.equals(set)).isEqualTo(true);
            index += 1;

        }
    }

    private static Stream<Arguments> multi_provider() {
        return Stream.of(
                Arguments.of(new HashSet<>(Arrays.asList(1, 2)), Arrays.asList(4, 7), new HashSet<>(Arrays.asList(31, 0)), Arrays.asList(Arrays.asList(1, 4, 0), Arrays.asList(1, 4, 31), Arrays.asList(1, 7, 0), Arrays.asList(1, 7, 31), Arrays.asList(2, 4, 0), Arrays.asList(2, 4, 31), Arrays.asList(2, 7, 0), Arrays.asList(2, 7, 31)))

        );
    }


    private static Stream<Arguments> list_provider() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2), Arrays.asList(4, 7), Arrays.asList(3, 8), Arrays.asList(Arrays.asList(1, 4, 3), Arrays.asList(1, 4, 8), Arrays.asList(1, 7, 3), Arrays.asList(1, 7, 8), Arrays.asList(2, 4, 3), Arrays.asList(2, 4, 8), Arrays.asList(2, 7, 3), Arrays.asList(2, 7, 8)))

        );
    }

    private static Stream<Arguments> set_provider() {
        return Stream.of(
                Arguments.of(new HashSet<>(Arrays.asList('1', '2')), new HashSet<>(Arrays.asList('4', '7')), new HashSet<>(Arrays.asList('3', '8')), Arrays.asList(new HashSet<>(Arrays.asList('1', '4', '3')), new HashSet<>(Arrays.asList('1', '4', '8')), new HashSet<>(Arrays.asList('1', '7', '3')), new HashSet<>(Arrays.asList('1', '7', '8')), new HashSet<>(Arrays.asList('2', '4', '3')), new HashSet<>(Arrays.asList('2', '4', '8')), new HashSet<>(Arrays.asList('2', '7', '3')), new HashSet<>(Arrays.asList('2', '7', '8'))))

        );
    }


}