package all;

/**
 * 不仅可以解决线程同步，还可以防止反序列化(枚举类没有构造器)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-12:48
 */
public enum Singleton08 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton08.INSTANCE.hashCode())).start();
        }
    }
}
