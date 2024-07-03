import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private List<Observer> observers;
    private int temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

class TemperatureDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("TemperatureDisplay: La temperatura actual es " + temperature + " grados.");
    }
}

class WeatherWarning implements Observer {
    private static final int WARNING_THRESHOLD = 30;

    @Override
    public void update(int temperature) {
        if (temperature > WARNING_THRESHOLD) {
            System.out.println("WeatherWarning: ¡Advertencia! La temperatura ha superado los " + WARNING_THRESHOLD + " grados.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        WeatherStation weatherStation = new WeatherStation();

        TemperatureDisplay tempDisplay = new TemperatureDisplay();
        WeatherWarning weatherWarning = new WeatherWarning();

        weatherStation.addObserver(tempDisplay);
        weatherStation.addObserver(weatherWarning);

        // Cambiar la temperatura y notificar a los observadores
        System.out.println("Cambiando la temperatura a 25 grados.");
        weatherStation.setTemperature(25);

        System.out.println("Cambiando la temperatura a 35 grados.");
        weatherStation.setTemperature(35);

    }
}