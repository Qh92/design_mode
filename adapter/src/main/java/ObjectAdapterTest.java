/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-24-0:37
 */
//对象适配器类
class ObjectAdapter implements Target {
    private Adaptee adaptee;
    public ObjectAdapter(Adaptee adaptee){
        this.adaptee=adaptee;
    }
    public void request() {
        adaptee.specificRequest();
    }
}
//客户端代码
public class ObjectAdapterTest {
    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
