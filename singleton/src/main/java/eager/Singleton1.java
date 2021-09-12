package eager;

/**
 * 直接实例化饿汉式(简洁直观)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-13-0:20
 */
public class Singleton1 {
    /**
     * 1、构造器私有化
     * 2、自行创建，并且用静态变量保存
     * 3、向外提供实例
     * 4、强调这是一个单例，我们可以用final修改
     */
    private Singleton1() {

    }
    public static final Singleton1 INSTANCE = new Singleton1();

}

