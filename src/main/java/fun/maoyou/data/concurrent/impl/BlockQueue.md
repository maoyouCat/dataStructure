### ArrayBlockQueue的实现

    它的底层无疑是队列，通过不公平锁ReentrantLock来进行实现。
    put操作时，首先进行加锁，判断当前items是否已满，如果已满则在notFull中阻塞等待。
    类似的take操作，判断items是否未empty,如果为empty则在notEmpty阻塞等待。
    add方法借助于offer实现，区别是如果add失败则抛出异常，而offer只是返回false.
    remove方法参考源码得知，它的删除会判断队列的每一个元素是否符合当前要删除的元素，直到
        遍历判断元素的索引已经到达putIndex.

#### Array阻塞队列的性能

    array阻塞队列通过全局独占锁实现了每次仅有一个线程进行出队和入队的操作，这个锁的粒度很大，类似

于在方法上直接加入synchronized。它的size操作是精确的，因为每次读取count的数量都进行了
加锁解锁的操作。