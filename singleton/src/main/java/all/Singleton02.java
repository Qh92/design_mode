package all;

/**
 * 本质和01没有区别
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-11:40
 */
public class Singleton02 {
    private static final  Singleton02 INSTANCE;
    static{
        INSTANCE = new Singleton02();
    }

    private Singleton02(){
    }

    public static Singleton02 getInstance(){
        return INSTANCE;
    }
}
