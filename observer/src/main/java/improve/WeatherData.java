package improve;

import java.util.ArrayList;
import java.util.List;

/**
 * 类是核心
 * 1. 包含最新的天气情况信息
 * 2. 含有 观察者集合，使用ArrayList管理
 * 3. 当数据有更新时，就主动的调用 ArrayList，通知所有的观察者（接入方）就看到最新的信息
 * @author Qh
 * @version 1.0
 * @date 2021-11-13 12:46
 */
public class WeatherData implements Subject{

    private float temperature;
    private float pressure;
    private float humidity;
    /**
     * 观察者集合
     */
    private List<Observer> observers;

    /**
     * 加入新的第三方
     */
    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    /**
     * 当数据有更新时，就调用 setData
     * @param temperature
     * @param pressure
     * @param humidity
     */
    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //通知所有的观察者
        notifyObservers();
    }

    /**
     * 注册一个观察者
     * @param o 新增的观察者
     */
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    /**
     * 移除一个观察者
     * @param o 观察者
     */
    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    /**
     * 遍历所有的观察者，并通知
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(this.temperature, this.pressure, this.humidity);
        }
    }
}
