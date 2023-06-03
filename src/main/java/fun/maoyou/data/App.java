package fun.maoyou.data;

import fun.maoyou.data.redis.impl.SDS;

public class App {
    public static void main(String[] args) {
        SDS sds = new SDS();
        String str = "";
        for (int i = 0; i < 1024; i++) {
            str += i;
        }
        sds.sdsnew(str);
        System.out.println(sds);
    }
}
