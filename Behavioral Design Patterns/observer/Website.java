package observer;

public class Website implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(Observable o) {
        if (o instanceof WeatherStation) {
            WeatherStation ws = (WeatherStation) o;
            temperature = ws.getTemperature();
            humidity = ws.getHumidity();
            pressure = ws.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Website: " + temperature + " " + humidity + " " + pressure);
    }
}
