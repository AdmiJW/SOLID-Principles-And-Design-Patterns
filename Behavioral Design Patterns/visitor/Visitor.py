#########################
# Goods
#########################
class Goods:
    def get_price(self): pass
    def get_price_after_tax(self, tax_visitor):
        return tax_visitor.calc( self.get_price() )

#######################
# Good Types
######################
class Alcohol(Goods): pass
class SugaryFood(Goods): pass

########################
# Concrete Goods
########################
class Wine(Alcohol):
    def get_price(self):
        return 5

class Cola(SugaryFood):
    def get_price(self):
        return 2


#########################
# Abstract Visitors
########################
class TaxVisitor:
    def calc(self, ori_price): pass


#########################
# Concrete Visitor
#########################
class AlcoholTaxVisitor(TaxVisitor):
    def calc(self, ori_price):
        return ori_price * 1.06

class SugarTaxVisitor(TaxVisitor):
    def calc(self, ori_price):
        return ori_price * 1.03




if __name__ == '__main__':
    alcohol_tax_visitor = AlcoholTaxVisitor()
    sugar_tax_visitor = SugarTaxVisitor()

    alc1 = Wine()
    cola = Cola()

    print( alc1.get_price_after_tax(alcohol_tax_visitor) )
    print( cola.get_price_after_tax(sugar_tax_visitor) )