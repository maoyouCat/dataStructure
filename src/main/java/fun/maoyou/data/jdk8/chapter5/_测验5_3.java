package fun.maoyou.data.jdk8.chapter5;

import java.util.Optional;

public class _测验5_3 {
    private static void question1() {
        //流中存在多少个菜
        Optional<Integer> reduce = Dish.menu.stream().map(e -> 1).reduce(Integer::sum);
        /*

        这种连接方式被称为map-reduce模式，它很容易并行化
         */
    }

    public static void main(String[] args) {

    }
}
