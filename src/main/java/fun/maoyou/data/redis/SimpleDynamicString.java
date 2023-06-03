package fun.maoyou.data.redis;

import fun.maoyou.data.redis.impl.SDS;

public interface SimpleDynamicString {
    SDS sdsnew(String str);

    boolean sdsempty();

    boolean sdsfree();

    int sdslen();

    int sdsavail();

    SimpleDynamicString sdsdup();

    boolean sdsclear();

    SimpleDynamicString sdscatsds();

    SimpleDynamicString sdscpy();

    SimpleDynamicString sdsgrowzero();

    SimpleDynamicString sdsrange(int start, int end);

    SimpleDynamicString sdstrim(String str);

    boolean sdscmp(SimpleDynamicString sds);

}
