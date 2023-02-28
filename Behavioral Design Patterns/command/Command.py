##################
# Commands
##################
# Abstract command. Do note that payload can also be contained inside Command
class Command:
    def __init__(self, target):
        self._target = target

    def execute(self): pass
    def unexecute(self): pass

# Concrete Commands
class TurnOnCommand(Command):
    def execute(self):
        self._target.turn_on()
    def unexecute(self):
        self._target.turn_off()

class TurnOffCommand(Command):
    def execute(self):
        self._target.turn_off()
    def unexecute(self):
        self._target.turn_on()

class FadeOnCommand(Command):
    def __init__(self, target, duration):
        super().__init__(target)
        self._duration = duration
    def execute(self):
        self._target.fade_on(self._duration)
    def unexecute(self):
        self._target.fade_off(self._duration)

class FadeOffCommand(Command):
    def __init__(self, target, duration):
        super().__init__(target)
        self._duration = duration
    def execute(self):
        self._target.fade_off(self._duration)
    def unexecute(self):
        self._target.fade_on(self._duration)


##################
# Target
##################
class CommandTarget:
    def turn_on(self): pass
    def turn_off(self): pass
    def fade_on(self, duration): pass
    def fade_off(self, duration): pass

class SmartLightBulb(CommandTarget):
    def turn_on(self):
        print("Smart Light Bulb is turned on")
    def turn_off(self):
        print("Smart Light Bulb is turned off")
    def fade_on(self, duration):
        print(f"Smart Light Bulb is gradually turning on in {duration} seconds")
    def fade_off(self, duration):
        print(f"Smart Light Bulb is gradually turning off in {duration} seconds")


###################
# Invokers
###################
# Let's say our remote has 3 buttons - A, B and C
class SmartRemote:
    def __init__(self, target):
        self._target = target
        self._history = []
        self.buttonACommand = TurnOnCommand(target)
        self.buttonBCommand = TurnOffCommand(target)
        self.buttonCCommand = FadeOnCommand(target, 3)

    def press_button_A(self):
        self.buttonACommand.execute()
        self._history.append(self.buttonACommand)

    def press_button_B(self):
        self.buttonBCommand.execute()
        self._history.append(self.buttonBCommand)

    def press_button_C(self):
        self.buttonCCommand.execute()
        self._history.append(self.buttonCCommand)

    def undo(self):
        if len(self._history):
            self._history.pop().unexecute()




if __name__ == '__main__':
    lightbulb = SmartLightBulb()
    remote = SmartRemote(lightbulb)

    remote.press_button_A()
    remote.press_button_B()
    remote.press_button_C()

    remote.undo()
    remote.undo()
    remote.undo()