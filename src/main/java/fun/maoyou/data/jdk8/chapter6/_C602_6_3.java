package fun.maoyou.data.jdk8.chapter6;

import fun.maoyou.data.jdk8.chapter5.Dish;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class _C602_6_3 {
    public static void main(String[] args) {
        Map<Dish.Type, Optional<Dish>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect);
    }
}
