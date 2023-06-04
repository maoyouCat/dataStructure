import fun.maoyou.data.concurrent.impl.BlockQueueArray;
import fun.maoyou.data.redis.impl.SDS;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
//        sdstest();

        blockArraytest();
    }

    private static void blockArraytest() {
        BlockQueueArray<Integer> objectBlockQueueArray = new BlockQueueArray<>(12);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    objectBlockQueueArray.put(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println("------");
        threadPoolExecutor.shutdown();
    }


    private static void sdstest() {
        SDS sds = new SDS();
        String str = "";
        for (int i = 0; i < 1024; i++) {
            str += i;
        }
        sds.sdsnew(str);
        System.out.println(sds);
    }
}