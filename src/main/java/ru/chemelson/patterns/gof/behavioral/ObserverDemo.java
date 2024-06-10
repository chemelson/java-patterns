package ru.chemelson.patterns.gof.behavioral;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherStation);
        weatherStation.setMeasurements(30.0f, 65f, 1013.1f);
    }

    interface Observer {
        void update(float temperature, float humidity, float pressure);
    }

    interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    static class WeatherStation implements Subject {
        private final List<Observer> observers = new ArrayList<>();
        private float temperature, humidity, pressure;

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (var observer : observers) {
                observer.update(temperature, humidity, pressure);
            }
        }

        public void measurementsChanged() {
            notifyObservers();
        }

        public void setMeasurements(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            measurementsChanged();
        }
    }

    static class CurrentConditionsDisplay implements Observer {
        private Subject weatherStation;

        public CurrentConditionsDisplay(Subject weatherStation) {
            this.weatherStation = weatherStation;
            weatherStation.registerObserver(this);
        }

        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("Current conditions: " + temperature + "F degrees and "
                    + humidity + "% humidity");
        }
    }

}
