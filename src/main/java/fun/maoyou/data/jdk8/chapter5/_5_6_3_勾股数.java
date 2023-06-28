package fun.maoyou.data.jdk8.chapter5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _5_6_3_勾股数 {
    public static void main(String[] args) {
        //生成满足 勾股定理的三元数组


        //筛选 c为一个整数 使用Math.sqrt(a*a+b*b) % 1==0


        //创建三元组 map(b->new int{a,b,Math.sqrt(a*a+b*b) }

        //生成b的值
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed().flatMap(
                a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
        );

        /*目前的方法并不是最优解，让代码更紧凑的方式是先生成所有的三元数组，然后在筛选*/
        Stream<int[]> stream1 = IntStream.rangeClosed(1, 100).boxed().flatMap(
                a -> IntStream.rangeClosed(a, 100).boxed().map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}).filter(t -> t[2] % 1 == 0)
        );
    }
}
