package all;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-12:16
 */
public class Singleton05 {
    private static Singleton05 INSTANCE;

    private Singleton05(){}

    /**
     * 同步代码块，但是此方法线程不安全
     * @return 单例
     */
    public static Singleton05 getInstance(){
        if (INSTANCE == null){
            //试图通过减小同步代码块的方法提高效率，然后不可行
            synchronized(Singleton05.class){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton05.getInstance().hashCode())).start();
        }
    }
}
