package all;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-10-09-12:23
 */
public class Singleton06 {
    private static volatile Singleton06 INSTANCE;

    private Singleton06(){}

    /**
     * 同步代码块，double check，<b>以前认为此单例是最完美的写法</b>
     * @return 单例
     */
    public static Singleton06 getInstance(){
        if (INSTANCE == null){
            //双重检查
            synchronized(Singleton06.class){
                if (INSTANCE == null){
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //同一个类的不同对象，其hash码是不同的
            new Thread(() -> System.out.println(Singleton06.getInstance().hashCode())).start();
        }
    }
}
