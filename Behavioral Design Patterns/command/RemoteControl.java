package command;

import command.commands.Command;

public class RemoteControl {
    private Command button1;
    private Command button2;
    private Command button3;

    public RemoteControl(Command button1, Command button2, Command button3) {
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
    }

    public void pressButton1() {
        button1.execute();
    }

    public void pressButton2() {
        button2.execute();
    }

    public void pressButton3() {
        button3.execute();
    }

    public void setButton1(Command button1) {
        this.button1 = button1;
    }

    public void setButton2(Command button2) {
        this.button2 = button2;
    }

    public void setButton3(Command button3) {
        this.button3 = button3;
    }
}
