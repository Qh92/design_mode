package all;

/**
 * 饿汉式：类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点，不管用到与否，类装载时就完成实例化（话说你不用的，你装载它干嘛）
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-11:30
 */
public class Singleton01 {
    private static final  Singleton01 INSTANCE = new Singleton01();

    private Singleton01(){
    }

    public static Singleton01 getInstance(){
        return INSTANCE;
    }
}
