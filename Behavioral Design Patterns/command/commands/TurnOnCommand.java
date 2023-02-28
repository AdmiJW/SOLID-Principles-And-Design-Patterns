package command.commands;

import command.RemoteLightBulb;

public class TurnOnCommand implements Command {
    private RemoteLightBulb lightBulb;

    public TurnOnCommand(RemoteLightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    @Override
    public void execute() {
        lightBulb.turnOn();
    }
}
