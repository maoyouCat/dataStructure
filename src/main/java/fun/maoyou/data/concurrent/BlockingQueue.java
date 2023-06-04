package fun.maoyou.data.concurrent;

public interface BlockingQueue<T> extends Queue<T> {
    void put(T t) throws InterruptedException;


    T take() throws InterruptedException;
}
