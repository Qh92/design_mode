package all;

/**
 * 针对03写法，可以进行加锁使其线程安全，但是也带来了效率下降的问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-12:12
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

    private Singleton04(){}

    /**
     * 线程安全，加锁，锁的是Singleton04.class这个类
     * @return 单例
     */
    public static synchronized Singleton04 getInstance(){
        if (INSTANCE == null){
            //线程速度太快，睡一会的目的是让其它线程可以打断
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton04.getInstance().hashCode())).start();
        }
    }
}
