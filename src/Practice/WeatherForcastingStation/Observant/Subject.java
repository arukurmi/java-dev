package Practice.WeatherForcastingStation.Observant;

import Practice.WeatherForcastingStation.Observer.Observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
