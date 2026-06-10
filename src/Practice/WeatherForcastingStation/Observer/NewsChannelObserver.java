package Practice.WeatherForcastingStation.Observer;

import Practice.WeatherForcastingStation.Observant.WeatherData;

public class NewsChannelObserver implements Observer{
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public NewsChannelObserver(WeatherData weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        System.out.println("Breaking news! The temperature has risen to : " + temperature + " along with humidity crossing: " + humidity);
    }
}
