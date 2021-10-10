package all;

/**
 * 懒汉式，此写法虽然达到了按需初始化的目的，但却带来线程不安全的问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-11:42
 */
public class Singleton03 {

    private static Singleton03 INSTANCE;

    private Singleton03(){}

    /**
     * 线程不安全
     * @return 单例
     */
    public static Singleton03 getInstance(){
        if (INSTANCE == null){
            //线程速度太快，睡一会的目的是让其它线程可以打断
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton03.getInstance().hashCode())).start();
        }
    }

}
