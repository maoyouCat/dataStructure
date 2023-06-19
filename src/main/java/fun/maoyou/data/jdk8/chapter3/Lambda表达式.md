### Lambda表达式的组成

- 参数列表
- 箭头符=>
- Lambda主体

(a,b)=>a=b
(a)=>a
(a,b)=>{ int c = 0; c =(a+b)*2 return c;}

### 函数式接口

- Predicate<T> : boolean test(T t)
- Consumer<T>  : T => void
- Function<T,R> : T=>R
- Supplier<T> :()=>T
- UnaryOperator<T> : T=>T
- BinaryOperator<T>: (T,T)=>T
- BiPredicate<L,R>: (L,R)=>boolean
- BiConsumer<T,U>: (T,U)=>void
- BiFunction<T,U,R>: (T,U)=>R

函数式接口的抽象方法就是Lambda表达式的签名。只需注意这些表达式的参数和返回即可，无须注意
它们的抽象方法名称。
Lambda表达式能够适配对应的所有具有相同方法签名的函数式接口。
------Lambda表达式重形不重迹

### 类型检查

    Lambad的类型是从使用Lambda的上下文推断出来的

Comparator<T>  (T t,T t)=>int
1.首先是查看方法的定义，方法的形参上已经说明了当前的形参类型
2.查看函数式接口的抽象方法
3.抽象方法中定义了它的参数类型和返回值类型。

### 类型推断

进一步简化Lambda
Comparator<T>  ( t, t)=>int
编译器会从程序的上下文中自动的找到最匹配的类型配合我们的Lambda表达式。
*ps:有时它比加类型的lambda更易读，但有时不是*

### 局部变量

由于局部变量都存储在栈上，使用Lambda由于是将一个行为传递给方法，程序并不能判断出这个程序的调用
时机，需要扩大变量的使用范围。故而需要使用final修饰它，或者事实上使用的局部变量是一个final类型的。

### 方法引用

语法
**目标引用::方法名称**

- 指向静态方法的引用 类名::方法
- 指向任意类型实例方法的引用 类名::方法
- 指向现有对象的实例方法的引用 对象::方法

第二种和第三种可能会存在误导，实际上第二种的方法引用这个对象指的是lambda的一个参数，是方法参数
如(String s)=>s.toUpperCase(); => (s)=>String::toUpperCase;
而第三种则是引用的一个外部实例的方法，如:
()=>user.getName(); => user::getName

### 构造函数引用

对于现有构造函数，可以使用它的名称和关键字来创建一个它的引用: **ClassName::new**.

例如，通过一个weight和price创建一个Apple实例
BiFunction<Integer,BigDecimal,Apple> func=Apple::new;
Apple apple=func.apply(1,1.0);

### 复合Lambda

Java8的函数式接口都有为方便而设计的方法。
它可以做到什么：

- 将多个简单的Lambda复合成复杂的表达式

#### 比较器复合

对Apple根据weight排序
Comparator<Apple> comparator=Comparator::comparing(Apple::getWeight);
1.逆序
comparator.reversed();
2.比较器链
如果存在一样重的苹果，那么哪个苹果需要排在前面呢？那么就需要在进行一次排序。
comparator.reversed().thenComparing(Apple::getCountry);

#### 谓词复合

谓词接口包含三个方法: `negate` `and` `or`
比如不是红色的苹果并且有很重的或者是绿苹果
redApple.negate().and(a-> a.getWeight>150).or(a->a.getColor().equals("green"));

#### 函数复合

可以使用Function接口的 andThen和compose两个默认方法将Lambda复合出来。

andThen方法会返回一个Function，它先对输入应用一个给定函数，再对输出应用另一个函数。例如，假设存在一个函数f给数字加1:(x->
x+1),
存在另一个函数g将数字乘以2,你可以将它们组合为一个函数h,先加1在乘以2:
Function f=x->x+1;
Function g=x->x*2;
Function h=f.andThen(g);
int result=h.apply(1);

也可以使用compose
Function f=x->x+1;
Function g=x->x*2;
Function h=f.compose(g);
int result=h.apply(1); //输出3
区别
andThen 是g(f(x))而compose是 f(g(x))

### 特化函数式接口

避免大量的装箱和拆箱操作
IntPredicate....
