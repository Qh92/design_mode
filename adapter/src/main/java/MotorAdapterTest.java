import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-03-24-0:48
 */
public class MotorAdapterTest {
    public static void main(String[] args) {
        System.out.println("适配器模式测试：");
        Motor motor=(Motor)ReadXML.getObject();
        motor.drive();
    }
}

//目标：发动机
interface Motor {
    public void drive();
}
//适配者1：电能发动机
class ElectricMotor {
    public void electricDrive() {
        System.out.println("电能发动机驱动汽车！");
    }
}
//适配者2：光能发动机
class OpticalMotor {
    public void opticalDrive() {
        System.out.println("光能发动机驱动汽车！");
    }
}
//电能适配器
class ElectricAdapter implements Motor {
    private ElectricMotor emotor;
    public ElectricAdapter() {
        emotor=new ElectricMotor();
    }
    public void drive() {
        emotor.electricDrive();
    }
}
//光能适配器
class OpticalAdapter implements Motor {
    private OpticalMotor omotor;
    public OpticalAdapter(){
        omotor=new OpticalMotor();
    }
    public void drive(){
        omotor.opticalDrive();
    }
}

class ReadXML {
    public static Object getObject() {
        try {
            DocumentBuilderFactory dFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=dFactory.newDocumentBuilder();
            Document doc;
            doc=builder.parse(new File("adapter\\config.xml"));
            NodeList nl=doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName=classNode.getNodeValue();
            Class<?> c=Class.forName(cName);
            Object obj=c.newInstance();
            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
