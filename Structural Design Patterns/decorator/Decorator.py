
####################################
# Interface (Not necessary in Python)
####################################
class Beverage:
    def get_cost(self): pass
    def describe(self): pass


###############################
# Concrete Beverages
###############################
class Mocha(Beverage):
    def get_cost(self):
        return 5
    def describe(self):
        return "Mocha"

class Espresso(Beverage):
    def get_cost(self):
        return 3
    def describe(self):
        return "Espresso"

##############################
# Interface for Beverages
##############################
class BeverageDecorator(Beverage):
    def __init__(self, beverage):
        self._beverage = beverage

###############################
# Concrete Decorators
###############################
class WhippedCreamDecorator(BeverageDecorator):
    def get_cost(self):
        return 1 + self._beverage.get_cost()
    def describe(self):
        return "Whipped Cream " + self._beverage.describe()

class ChocolateDecorator(BeverageDecorator):
    def get_cost(self):
        return 1.5 + self._beverage.get_cost()
    def describe(self):
        return "Chocolate " + self._beverage.describe()



if __name__ == '__main__':
    beverage1 = ChocolateDecorator( WhippedCreamDecorator( Mocha() ) )
    print( beverage1.get_cost() )
    print( beverage1.describe() )

    beverage2 = WhippedCreamDecorator( Espresso() )
    print(beverage2.get_cost())
    print(beverage2.describe())