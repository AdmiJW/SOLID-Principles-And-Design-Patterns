package command.commands;

import command.RemoteLightBulb;

public class TurnOffCommand implements Command {
    private RemoteLightBulb lightBulb;

    public TurnOffCommand(RemoteLightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    @Override
    public void execute() {
        lightBulb.turnOff();
    }
}
