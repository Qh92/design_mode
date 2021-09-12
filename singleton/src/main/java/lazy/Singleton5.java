package lazy;

/**
 * 线程安全(使用于多线程)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-13-0:22
 */
public class Singleton5 {
    /**
     * 1、构造器私有化
     * 2、用一个静态变量保存这个唯一的实例
     * 3、提供一个静态方法，获取这个实例对象
     */
    static volatile Singleton5 instance;
    private Singleton5() {}

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
                return instance;
            }
        }
        return instance;
    }
}

