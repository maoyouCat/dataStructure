package fun.maoyou.data.redis.impl;

import fun.maoyou.data.redis.SimpleDynamicString;

public class SDS implements SimpleDynamicString {
    private SDSNode sdsNode; //节点对象
    private static int MAX_LEN = 512;//buf的扩容策略

    /**
     * 给定字符串生成SDS
     *
     * @param str
     * @return
     */
    @Override
    public SDS sdsnew(String str) {
        sdsNode = new SDSNode();
        ensureCapacity(str.length());
        //存入字符
        System.arraycopy(str.toCharArray(), 0, sdsNode.buf, 0, str.length());
        sdsNode.len = str.length();//记录已使用的字符空间
        sdsNode.free = sdsNode.buf.length - sdsNode.len;//未使用的空间
        return this;
    }

    /**
     * 判断当前是否能存储下字符串
     * 是 不进行扩容
     * 否 进行扩容
     *
     * @param length
     */
    private void ensureCapacity(int length) {
        char[] newbuf;
        if (sdsNode.free < length) {
            if (sdsNode.free <= MAX_LEN) {
                //sds需要扩容，并且当前长度小于1M,扩容策略为扩容为当前需要容量的两倍
                newbuf = new char[(sdsNode.len + length) << 1];
            } else {
                //sds需要扩容，并且当前长度大于1M,扩容策略为扩容直接增加1M
                newbuf = new char[sdsNode.buf.length + length + MAX_LEN];
            }
            System.arraycopy(sdsNode.buf, 0, newbuf, 0, sdsNode.len);
            sdsNode.buf = newbuf;
        }
    }

    @Override
    public boolean sdsempty() {
        return false;
    }

    @Override
    public boolean sdsfree() {
        return false;
    }

    @Override
    public int sdslen() {
        return 0;
    }

    @Override
    public int sdsavail() {
        return 0;
    }

    @Override
    public SimpleDynamicString sdsdup() {
        return null;
    }

    @Override
    public boolean sdsclear() {
        return false;
    }

    @Override
    public SimpleDynamicString sdscatsds() {
        return null;
    }

    @Override
    public SimpleDynamicString sdscpy() {
        return null;
    }

    @Override
    public SimpleDynamicString sdsgrowzero() {
        return null;
    }

    @Override
    public SimpleDynamicString sdsrange(int start, int end) {
        return null;
    }

    @Override
    public SimpleDynamicString sdstrim(String str) {
        return null;
    }

    @Override
    public boolean sdscmp(SimpleDynamicString sds) {
        return false;
    }

    public static class SDSNode {
        // 当前未使用空间
        private int free;
        // 当前已使用空间
        private int len;
        // 存储字符串
        private char[] buf = new char[0];
    }
}
