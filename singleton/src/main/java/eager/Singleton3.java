package eager;

/**
 * 静态代码块饿汉式(适合复杂实例化)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-13-0:21
 */
public class Singleton3 {
    /**
     *  静态代码块
     */
    public static final Singleton3 INSTANCE;
    private String info;

    static {
        try {
            INSTANCE = new Singleton3("123");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private Singleton3(String info) {
        this.info = info;
    }
}

