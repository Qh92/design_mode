# 单例模式下为什么一定要加volatile关键字

有一道面试题，单例模式已经双重检查锁定（Double-Check-Lock）了，要不要加volatile关键字。以下是双重检查锁定来实现单例模式代码



```java
public class DoubleCheckLock {

    private static DoubleCheckLock INSTANCE;

    private DoubleCheckLock() {

    }

    public static DoubleCheckLock getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckLock.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new DoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }
}
```

先介绍下volatile关键字的特性，一个变量被volatile修饰后具备两个特性

**可见性**：此变量对所有线程可见，当一个线程修改了这个变量的值，新值对其他线程是可以立即得知的，如果不是volatile修饰的变量的值在线程间传递均需要通过主内存来完成，比如线程A修改了普通变量的值，然后向主内存进行回写，另一条线程B在线程A回写完成之后再对主内存进行读取操作，新变量值才会对线程B可见。
**禁止指令重排序优化**：普通的变量仅会保证该方法的执行过程中所有依赖赋值结果的地方都能获取到正确的结果，而不能保证变量赋值操作的顺序与程序代码中的执行顺序一致。
先试着解释下如果不加volatile会有什么问题，第一个线程过来，检查发现其他线程没有初始化然后就加上锁，上锁后对这个INSTANCE进行初始化，可是在这个过程中我们new了这个对象并且申请了内存，申请完内存里边的成员变量已经赋了初始值0，但这个时候INSTANCE就已经指向内存了，所以这个INSTATNCE已经不等于空了，这种情况下里一个线程来了，来了之后他首先执行if (INSTANCE == null),这个时候他处于半初始化，不为空的状态，第二个线程就直接使用这个初始值了，而不是用那个默认值，解决这个问题就要加上volatile。

使用jclasslib查看getInstance方法的字节码，下面是 INSTANCE = new DoubleCheckLock(); 的字节码指令

```java
new
dup
invokespecial 
putstatic
```

new指令在java堆上为对象分配内存空间，并将地址压入操作数栈顶
dup指令为复制操作数栈顶值，并将其压入栈定，这是操作数栈有连续相同的两个对象地址
invokespecial调用实例的构造函数，这时会弹出一个栈顶元素
putstatic指令将对象地址赋值给变量INSTANCE，这时也会弹出一个栈顶元素
invokespecial指令与putstatic指令发生重排序，使这个INSTANTCE进行了半初始化，才会导致出现问题

使用volatile主要是加了内存屏障，指令重排序时不能把后面的指令重排序到内存屏障之前的位置

```java
public class DoubleCheckLock {

    private volatile static DoubleCheckLock INSTANCE;

    private DoubleCheckLock() {

    }

    public static DoubleCheckLock getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckLock.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new DoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }
}
```

