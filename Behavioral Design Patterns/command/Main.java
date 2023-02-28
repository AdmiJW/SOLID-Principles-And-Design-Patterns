package command;

import command.commands.Command;
import command.commands.ToggleCommand;
import command.commands.TurnOffCommand;
import command.commands.TurnOnCommand;

public class Main {
    public static void main(String[] args) {
        RemoteLightBulb lightBulb = new RemoteLightBulb();
        
        Command toggleCommand = new ToggleCommand(lightBulb);
        Command turnOnCommand = new TurnOnCommand(lightBulb);
        Command turnOffCommand = new TurnOffCommand(lightBulb);
        RemoteControl remoteControl = new RemoteControl(toggleCommand, turnOnCommand, turnOffCommand);
        
        remoteControl.pressButton1();
        remoteControl.pressButton2();
        remoteControl.pressButton3();

        // Swap the commands on run time
        remoteControl.setButton1(turnOnCommand);
        remoteControl.setButton2(turnOffCommand);
        remoteControl.setButton3(toggleCommand);

        remoteControl.pressButton1();
        remoteControl.pressButton2();
        remoteControl.pressButton3();
    }
}
