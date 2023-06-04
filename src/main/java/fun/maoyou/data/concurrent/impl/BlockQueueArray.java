package fun.maoyou.data.concurrent.impl;

import fun.maoyou.data.concurrent.BlockingQueue;
import fun.maoyou.data.concurrent.Iterable;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列 array实现
 *
 * @param <T>
 */

public class BlockQueueArray<T> implements BlockingQueue<T> {
    final Object[] items;

    //阻塞队列，take时，如果队列已空，进入该队列阻塞
    private final Condition notEmpty;

    //阻塞队列，put时，如果该队列已满，进入该队列阻塞
    private final Condition notFull;
    //记录当前元素items的长度,这里的count不加入volatile的原因是操作count的
    //的操作都在锁代码块内，线程都是从主存中获取元素
    private int count;

    private int putIndex;
    private int takeIndex;

    /**
     * 底层基于AQS乐观锁实现，不存在用户态和内核态的切换，默认构造器是不公平锁
     */
    final ReentrantLock lock;

    /**
     * 有界队列必须给定一个初始容量
     *
     * @param InitialCapacity
     */
    public BlockQueueArray(int InitialCapacity) {
        this(InitialCapacity, false);
    }

    public BlockQueueArray(int initialCapacity, boolean b) {
        this.items = new Object[initialCapacity];
        lock = new ReentrantLock(b);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }


    @Override
    public void put(T t) throws InterruptedException {
        //首先检查元素是否为null
        checkNotNull(t);
        final ReentrantLock lock = this.lock;
        //加锁，可以中断锁
        lock.lockInterruptibly();
        try {
            //判断当前队列是否已满，如果已经满了,进入阻塞,
            // 注意需要使用while进行循环判断,以防止虚假唤醒的情况
            while (count == items.length) {
                notFull.await();
            }
            //进入到这里，就说明队列存在空间可以存储元素
            enqueue(t);//进入入队的操作
        } finally {
            lock.unlock();
        }

    }

    private void enqueue(T t) {
        //入队操作，由于putIndex会记录当前入队时需要加入的索引故而直接在
        //当前putIndex索引处加入元素即可
        final Object[] items = this.items;
        items[putIndex] = t;
        if (++putIndex == items.length) { //  维护队列的头结点
            putIndex = 0;
        }
        count++;
        notEmpty.signal();//唤醒其他在等待获取元素的线程
    }

    private void checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public T take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    private T dequeue() {
        final Object[] items = this.items;
        T item = (T) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return item;
    }

    @Override
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count == 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Iterable<T> iterator() {
        return null;
    }


    @Override
    public boolean add(T t) {
        if (offer(t)) {
            return true;
        } else {
            throw new IllegalStateException("Queue full");
        }
    }

    @Override
    public boolean remove(T t) {
        return false;
    }

    @Override
    public void clear() {

    }

    //offer添加元素不进入阻塞队列，未添加成功返回false
    @Override
    public boolean offer(T t) {
        checkNotNull(t);
        final ReentrantLock lock = this.lock;
        lock.lock();//不可中断的锁
        try {
            if (count == items.length) {
                return false;
            } else {
                enqueue(t);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count == 0 ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T element() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return (T) items[takeIndex];
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T peek() {
        return element();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
