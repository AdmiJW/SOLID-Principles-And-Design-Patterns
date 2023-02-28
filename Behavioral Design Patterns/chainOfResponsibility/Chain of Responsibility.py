##################
# Abstract Handler
##################
class CalcHandler:
    def __init__(self, next):
        self._next = next
    def calculate(self, num1, num2, operation): pass

#################
# Handlers
#################
class InvalidOperation(CalcHandler):
    def __init__(self): pass
    def calculate(self, num1, num2, operation):
        raise NotImplementedError("No such operation")

class Adder(CalcHandler):
    def calculate(self, num1, num2, operation):
        if operation == '+':
            return num1 + num2
        return self._next.calculate(num1, num2, operation)

class Subtracter(CalcHandler):
    def calculate(self, num1, num2, operation):
        if operation == '-':
            return num1 - num2
        return self._next.calculate(num1, num2, operation)

class Multiplier(CalcHandler):
    def calculate(self, num1, num2, operation):
        if operation == '*':
            return num1 * num2
        return self._next.calculate(num1, num2, operation)

class Divider(CalcHandler):
    def calculate(self, num1, num2, operation):
        if operation == '/':
            return num1 / num2
        return self._next.calculate(num1, num2, operation)


#####################
# Our Calculator
#######################
class Calculator:
    def __init__(self):
        # I can really make it look like a chain does it?
        self.calculate_chain = Adder( Subtracter( Multiplier( Divider( InvalidOperation()))))

    def calculate(self, num1, num2, operation):
        return self.calculate_chain.calculate( num1, num2, operation )



if __name__ == '__main__':
    calc = Calculator()

    print(calc.calculate(10, 5, '+'))
    print(calc.calculate(10, 5, '-'))
    print(calc.calculate(10, 5, '*'))
    print(calc.calculate(10, 5, '/'))
    print(calc.calculate(10, 5, ' '))