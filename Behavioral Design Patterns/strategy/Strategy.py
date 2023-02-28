
########################
# Behavior / Strategies
########################
class NormalQuackingBehavior:
    def __init__(self, duck):
        self._duck = duck

    def quack(self):
        print(f"{self._duck.name} quacked!")

class NormalFlyBehavior:
    def __init__(self, animal):
        self._animal = animal

    def fly(self):
        print(f"{self._animal.name} flies!")

class CannotFlyBehavior:
    def __init__(self, animal):
        self._animal = animal

    def fly(self):
        print(f"{self._animal.name} tries to fly, but is unable to!")



##################################
# Classes that implement Strategy
##################################
# Duck class. Abstract.
# Instead of having quack() and fly(), assign them to variables
class Duck:
    def __init__(self, name, quackingBehavior, flyingBehavior):
        self.name = name
        self._quackingBehavior = quackingBehavior
        self._flyingBehavior = flyingBehavior

    def quack(self):
        self._quackingBehavior.quack()

    def fly(self):
        self._flyingBehavior.fly()

# Duck class. Concrete.

# Let's say CityDuck cannot fly
class CityDuck(Duck):
    def __init__(self, name):
        super().__init__(name, NormalQuackingBehavior(self), CannotFlyBehavior(self) )

# But a Wild Duck can fly
class WildDuck(Duck):
    def __init__(self, name):
        super().__init__(name, NormalQuackingBehavior(self), NormalFlyBehavior(self) )





if __name__ == '__main__':
    duck1 = CityDuck('Adrian')
    duck2 = WildDuck('Tom')

    duck1.quack()
    duck1.fly()

    duck2.quack()
    duck2.fly()