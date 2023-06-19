### 什么是流

简短的定义就是“从支持数据处理操作的源生成的元素序列”

- 元素序列--就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序值。
- 源--流会使用一个提供数据的源，如集合，数组或输入/输出资源。**注意:从有序集合生成的流会保留
  原有的顺序.**

流的中间操作:

- Stream<T>  fliter(Predicate<T> ) ->(T)=>boolean
- Stream<R>  map(Function<T,R>) ->(T)=>R
- Stream<T>  limit(int )
- Stream<T>  sorted(Comparator<T,T> ) -> (T,T)=>int
- Stream<T>  distinct()

流的终结操作：

- forEach
- count
- collect
