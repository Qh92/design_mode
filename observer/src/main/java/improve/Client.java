package improve;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-11-13 13:02
 */
public class Client {

    public static void main(String[] args) {
        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();
        //创建观察者
        CurrentConditions currentConditions = new CurrentConditions();
        BaiduSite baiduSite = new BaiduSite();
        //注册到WeatherData
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(baiduSite);
        //测试
        System.out.println("通知所有的观察者");
        weatherData.setData(10f, 100f, 30.3f);

        weatherData.remove(currentConditions);

        System.out.println("新一轮通知");
        weatherData.setData(26.6f, 120f, 40.4f);
    }
}
