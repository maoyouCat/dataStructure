package fun.maoyou.data.jdk8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dish {
    private final String name;
    private final boolean vegetarian; //素的
    private final int calories; //卡路里
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {
        META, FISH, OTHER;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public static List<Dish> menu;


    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Type.META),
                new Dish("beef", false, 700, Type.META),
                new Dish("chicken", false, 400, Type.META),
                new Dish("french fries", true, 400, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );
    }

    public static void main(String[] args) {
        //筛选
        List<Integer> list = Arrays.asList(1, 222, 222, 3, 1, 444, 444, 5, 65, 8);
        list.stream().filter(e -> e % 2 == 0).distinct().forEach(System.out::println);
        //截断 选出calories>300的前3道菜
        System.out.println("截断 选出calories>300的前3道菜>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Dish> collect = menu.stream().filter(e -> e.getCalories() > 300).limit(3).collect(Collectors.toList());
        //跳过元素
        List<Dish> collect2 = menu.stream().filter(e -> e.getCalories() > 300).skip(3).collect(Collectors.toList());
        //对流的每一个元素应用函数
        List<String> names = menu.stream().map(Dish::getName).collect(Collectors.toList());
        //流的扁平化处理
        //错误的扁平化
        String[] arrStr = {"Boodbye", "World"};

        Stream<String> stringStream = Arrays.stream(arrStr);
        stringStream.map(e -> e.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        //flatMap的作用是  各个数组并不是分别映射成一个流，而是映射成流的内容。即将生成的单个相同类型的流合并为一个流。

/*        查找与匹配
          终端操作 : anyMatch:流中是否存在一个能匹配的谓词  返回boolean
                     allMatch:流中是否都可以匹配    返回boolean
                         noneMatch:确保流中没有任何元素匹配
            短路求值，有些操作不需要对整个元素遍历就可以取得结果，对于流而言 allMatch anyMatch  noneMatch       findFirst和FindAny都是短路操作，同时limit也是一个短路操作
                */

        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        Optional<Dish> first = menu.stream().findFirst();


        /**
         * 为何会同时存在FindAny和FindFirst？答案是并行，找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪一个，请使用findAny.
         */


        /*  ------------------归约--------------------*/
        int sum = 0;
        for (Integer variable : list) {
            sum += variable;
        }

        list.stream().reduce(sum, (a, b) -> a + b);

    }
}
