### Optional简介

Optional<T>是一个容器类，代表一个值存在或不存在。

- isPresent() 将在Optional包含值的时候返回true，否则返回false.
- ifPresent(Consumer<T> block)会在值存在的时候执行给定的代码块。
- T get()会在值存在的时候返回值，否则抛出一个NoSuchElement异常
- T orElse(T other)会在值存在的时候返回值，否则返回一个默认值other.

