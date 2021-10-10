package all;

/**
 * 静态内部类方式，JVM保证单例，加载外部类时不会加载内部类，这样可以实现懒加载
 * 比01写法要完美一些
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-12:42
 */
public class Singleton07 {

    private Singleton07(){}

    /**
     * JVM保证只加载一次该内部类
     */
    private static class SingletonHolder{
        private final static Singleton07 INSTANCE = new Singleton07();
    }

    public static Singleton07 getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton07.getInstance().hashCode())).start();
        }
    }
}
