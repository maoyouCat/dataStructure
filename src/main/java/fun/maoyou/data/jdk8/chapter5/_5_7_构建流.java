package fun.maoyou.data.jdk8.chapter5;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _5_7_构建流 {
    public static void main(String[] args) {
        //由值创建流 Stream.of
        Stream.of("uuu", "rrttr", "mydwwdafdw").map(String::toUpperCase).forEach(System.out::println);
        //空流
        Stream<String> empty = Stream.empty();
        Stream<Object> empty1 = Stream.empty();

        //由数组创建流 静态方法 Arrays.stream接收一个数组
        int nums[] = {1, 2, 2};
        IntStream stream = Arrays.stream(nums);

        //由文件生成流


    }
}
