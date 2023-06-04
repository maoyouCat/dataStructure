package fun.maoyou.data.concurrent;

public interface Queue<T> extends Collection<T> {
    boolean offer(T t);

    T poll();

    T element();

    T peek();
}
