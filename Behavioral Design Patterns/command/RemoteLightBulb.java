package command;

public class RemoteLightBulb {
    private boolean isOn = false;

    public void turnOn() {
        isOn = true;
        System.out.println("Light bulb is on");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Light bulb is off");
    }

    public void toggle() {
        isOn = !isOn;
        System.out.println("Light bulb is toggled " + (isOn ? "on" : "off"));
    }
}
