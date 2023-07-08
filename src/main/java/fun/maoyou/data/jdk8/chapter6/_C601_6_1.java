package fun.maoyou.data.jdk8.chapter6;

import fun.maoyou.data.jdk8.chapter5.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _C601_6_1 {
    public static void main(String[] args) {
        Map<Dish.Type, Map<String, List<Dish>>> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
            if (dish.getCalories() > 400) {
                return "高";
            } else if (dish.getCalories() > 300) {
                return "中";
            } else {
                return "低";
            }
        })));
        System.out.println(collect);
    }
}
