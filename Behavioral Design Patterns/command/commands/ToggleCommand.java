package command.commands;

import command.RemoteLightBulb;

public class ToggleCommand implements Command {
    private RemoteLightBulb lightBulb;

    public ToggleCommand(RemoteLightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    @Override
    public void execute() {
        lightBulb.toggle();
    }
}