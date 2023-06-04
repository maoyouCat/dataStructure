package fun.maoyou.data.concurrent;

public interface Collection<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    Iterable<T> iterator();

    boolean add(T t);

    boolean remove(T t);

    void clear();
}
