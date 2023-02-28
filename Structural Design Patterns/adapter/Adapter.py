
class Infantry:
    def interpret_command(self, command):
        if command == 'walk':
            self.move_forward()
        elif command == 'fire':
            self.fire_rifle()
    def move_forward(self):
        print("Infantry moved!")
    def fire_rifle(self):
        print("Infantry fired their rifle!")

class Tank:
    def interpret_command(self, command):
        if command == 'drive':
            self.drive_forward()
        elif command == 'missile':
            self.fire_missle()
    def drive_forward(self):
        print("Tank drove forward")
    def fire_missile(self):
        print("Tank fired a missile!")


###################
# Adapters
###################
# Traditional Adapter
class TankAdapter:
    def __init__(self, tank):
        self._tank = tank
    def interpret_command(self, command):
        if command == 'walk':
            self._tank.drive_forward()
        elif command == 'fire':
            self._tank.fire_missile()
    # When we access attributes that aren't of TankAdapter's, search the attribute in tank
    def __getattr__(self, attr):
        return getattr(self._tank, attr)

# Python-y way of adapter
# A Generic Adapter class in Python, not specifically binded to this use case
class Adapter:
    def __init__(self, object, **adapted_methods):
        self._object = object
        self.__dict__.update(**adapted_methods)

    # When we access attributes that aren't in adapted methods, search the attribute in object
    def __getattr__(self, attr):
        return getattr(self._object, attr)




if __name__ == '__main__':
    # Traditional way
    tank1 = TankAdapter( Tank() )
    tank1.interpret_command('walk')
    tank1.interpret_command('fire')

    # Python way
    def adapted_interpret_command(tank, command):
        if command == 'walk':
            tank.drive_forward()
        elif command == 'fire':
            tank.fire_missile()

    tank2 = Tank()
    tank2 = Adapter( tank2, interpret_command=lambda command: adapted_interpret_command( tank2, command) )

    tank1.interpret_command('walk')
    tank1.interpret_command('fire')
