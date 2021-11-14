package improve;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-13 13:05
 */
public class BaiduSite implements Observer {
    /**
     * 温度，气压，湿度
     */
    private float temperature;
    private float pressure;
    private float humidity;

    /**
     * 更新 天气情况，是由 WeatherData 来调用，我使用推送模式
     * @param temperature
     * @param pressure
     * @param humidity
     */
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    /**
     * 显示
     */
    public void display() {
        System.out.println("***Today 百度 天气: " + temperature + "***");
        System.out.println("***Today 百度 气压: " + pressure + "***");
        System.out.println("***Today 百度 湿度: " + humidity + "***");
    }
}
