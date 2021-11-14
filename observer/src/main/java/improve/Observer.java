package improve;

/**
 * 观察者接口，由观察者来实现
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-13 12:47
 */
public interface Observer {

    void update(float temperature, float pressure,float humidity);
}
