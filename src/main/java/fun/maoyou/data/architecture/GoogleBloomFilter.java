package fun.maoyou.data.architecture;

/**
 * 布隆过滤器可以自己定义误判率，根据数据规模
 * <p>
 * 其具有的成员有：
 * 记录bit位的个数
 * 记录hash函数的个数
 *
 * @param <T>
 */
public class GoogleBloomFilter<T> implements BloomFilter<T> {

    private long bitSize;

    private int hashSize;

    private long[] bits;


    private final int LONG_SIZE = 64;

    public GoogleBloomFilter(long dataSize, double failureRate) {
        double ln2 = Math.log(2);
        bitSize = (long) -(dataSize * Math.log(failureRate) / (Math.pow(ln2, 2)));
        hashSize = (int) ((bitSize / dataSize) * ln2);
        bits = new long[(int) ((bitSize + LONG_SIZE - 1) / LONG_SIZE)];
    }

    /**
     * 生成哈希函数，通过哈希函数生成的值求出索引
     * 将索引处的bit位置为1
     *
     * @param t
     * @return
     */
    @Override
    public boolean put(T t) {
        int hash1 = t.hashCode();
        int hash2 = hash1 >>> 16;
        boolean res = false;
        for (int i = 0; i < hashSize; i++) {
            int hashValue = hash1 + (i * hash2);
            if (hashValue < 0) {
                hashValue = ~hashValue;
            }
            int index = (int) (hashValue % bitSize);
            res = set(index);
            if (!res) return res;
        }
        return res;
    }

    private boolean set(int index) {
        int position = index / LONG_SIZE;
        int move = index - (LONG_SIZE * position);
        bits[position] |= (1 << move);
        return true;
    }

    @Override
    public boolean contains(T t) {
        int hash1 = t.hashCode();
        int hash2 = hash1 >>> 16;
        boolean res = false;
        for (int i = 0; i < hashSize; i++) {
            int hashValue = hash1 + (hash2 * i);
            if (hashValue < 0) {
                hashValue = ~hashValue;
            }
            int index = (int) (hashValue % bitSize);
            res = get(index);
            if (!res) return res;
        }
        return res;
    }

    private boolean get(int index) {
        int position = index / LONG_SIZE;
        int move = index - (LONG_SIZE * position);
        long res = bits[position] & (1 << move);
        return res != 0;
    }

    public static void main(String[] args) {
        BloomFilter<Integer> integerBloomFilter = new GoogleBloomFilter<>(10000, 0.01);
        int size = 0;
        for (int i = 0; i < 1000000; i++) {
            integerBloomFilter.put(i);
            if (!integerBloomFilter.contains(i)) {
                System.out.println("错误");
            }
        }
        System.out.println((size == 1000) + "---" + size);
    }
}
