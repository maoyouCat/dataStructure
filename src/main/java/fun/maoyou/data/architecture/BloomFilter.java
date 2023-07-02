package fun.maoyou.data.architecture;

public interface BloomFilter<T> {
    boolean put(T t);

    boolean contains(T t);
}
