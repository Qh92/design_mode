package improve;

/**
 * 模拟气象站
 *
 * @author Qh
 * @version 1.0
 * @date 2021-11-13 12:45
 */
public interface Subject {

    void registerObserver(Observer o);
    void remove(Observer o);
    void notifyObservers();
}
