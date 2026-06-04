package WeatherForcastingStation;

import WeatherForcastingStation.Observant.WeatherData;
import WeatherForcastingStation.Observer.NewsChannelObserver;
import WeatherForcastingStation.Observer.RadioStationObserver;

public class WeatherStationDemo {
    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();
        NewsChannelObserver newsChannelObserver1 = new NewsChannelObserver(weatherData);
//        NewsChannelObserver newsChannelObserver2 = new NewsChannelObserver(weatherData);
        RadioStationObserver radioStationObserver1 = new RadioStationObserver(weatherData);
//        RadioStationObserver radioStationObserver2 = new RadioStationObserver(weatherData);

        weatherData.updateWeather(32, 10, 30);
        weatherData.updateWeather(31, 12, 30);
        weatherData.updateWeather(32.30F, 11, 32);
        weatherData.updateWeather(33.90F, 10, 30);
    }
}
