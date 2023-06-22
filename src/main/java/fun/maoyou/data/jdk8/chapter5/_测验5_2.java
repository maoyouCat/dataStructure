package fun.maoyou.data.jdk8.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _测验5_2 {
    private static void question1() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] array = Arrays.stream(numbers).map(e -> e * e).toArray();
    }

    private static void question2() {
        Integer[] n1 = {1, 2, 3};
        Integer[] n2 = {3, 4};
        Stream<Integer> n1Stream = Arrays.stream(n1);
        Stream<Integer> n2Stream = Arrays.stream(n2);

        n1Stream.map(e -> Arrays.stream(n2).map(e2 -> new int[]{e, e2})).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        question2();
    }
}
