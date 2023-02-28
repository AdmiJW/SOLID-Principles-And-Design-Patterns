package observer;

public class Main {
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation(2);
        Website website = new Website();
        Phone phone = new Phone();
        ws.addObserver(website);
        ws.addObserver(phone);
        ws.setMeasurements(1, 2, 3);
        ws.setMeasurements(4, 5, 6);
        ws.removeObserver(phone);
        ws.setMeasurements(7, 8, 9);
    }
}
