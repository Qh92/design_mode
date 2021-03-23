
/**
 * 适配器模式（Adapter）包含以下主要角色。
 *
 * 1. 目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口。
 * 2. 适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口。
 * 3. 适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
 *
 * @author Qh
 * @version 1.0
 * @date 2021-03-24-0:33
 */
//客户端代码
public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}
//目标接口
interface Target {
    public void request();
}
//适配者接口
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}
//类适配器类
class ClassAdapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}
