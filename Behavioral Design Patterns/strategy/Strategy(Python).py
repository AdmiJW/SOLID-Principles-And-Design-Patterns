# In Python, we can use types.MethodType to achieve changing strategy
import types

########################
# Behavior / Strategies
########################
def normal_quack(self):
    print(f"{self.name} quacked!")

def normal_fly(self):
    print(f"{self.name} flies!")

def cannot_fly(self):
    print(f"{self.name} tries to fly, but is unable to!")


##################################
# Classes that implement Strategy
##################################
# Duck class. Abstract.
# Instead of having quack() and fly(), assign them to variables
class Duck:
    def __init__(self, name):
        self.name = name

# Duck class. Concrete.
# Let's say CityDuck cannot fly
class CityDuck(Duck):
    def __init__(self, name):
        super().__init__(name)
        self.fly = types.MethodType(normal_fly, self)
        self.quack = types.MethodType(normal_quack, self)

# But a Wild Duck can fly
class WildDuck(Duck):
    def __init__(self, name):
        super().__init__(name)
        self.fly = types.MethodType(cannot_fly, self)
        self.quack = types.MethodType(normal_quack, self)





if __name__ == '__main__':
    duck1 = CityDuck('Adrian')
    duck2 = WildDuck('Tom')

    duck1.quack()
    duck1.fly()

    duck2.quack()
    duck2.fly()