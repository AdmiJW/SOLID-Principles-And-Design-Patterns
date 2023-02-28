
# In Python, iterators are built in, via __iter__(), __next()__ and raising StopIteration

class InventoryIterator:
    def __init__(self, inventory):
        self._inventory = inventory
        self._index = -1

    def __next__(self):
        self._index += 1
        if self._index >= len( self._inventory ):
            raise StopIteration
        return self._inventory[ self._index ]

class Inventory:
    def __init__(self):
        self.inventory = ["Sword", "Shield", "Potion", "Bandage"]

    def __iter__(self):
        return InventoryIterator(self.inventory)


if __name__ == '__main__':
    inv = Inventory()

    # Iterators in Python can be easily used in for loop
    for item in inv:
        print(item)

    # Alternatively, we can use iter() and next()
    it = iter( Inventory() )
    print(next(it))
    print(next(it))
    print(next(it))
    print(next(it))
