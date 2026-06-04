package WeatherForcastingStation.Observer;

import WeatherForcastingStation.Observant.WeatherData;

public class RadioStationObserver implements Observer{
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public RadioStationObserver(WeatherData weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        System.out.println("Tuning in to weather updates right now! The temperature has risen to : " + temperature + " along with humidity crossing: " + humidity);
    }
}
