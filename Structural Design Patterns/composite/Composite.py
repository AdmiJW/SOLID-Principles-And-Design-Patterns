
# The interface for Menus
class Menu:
    def __init__(self, name):
        self._name = name

    def print_menu(self):
        print(self._name)


# Child class, no children
class MenuItem(Menu):
    def __init__(self, name):
        super().__init__(name)

# Composite class
class MenuGroup(Menu):
    def __init__(self, name):
        super().__init__(name)
        self._children = []

    def print_menu(self):
        super().print_menu()

        for child in self._children:
            child.print_menu()

    def add_child(self, *children):
        self._children.extend(children)

    def remove_child(self, child):
        self._children.remove(child)




if __name__ == '__main__':
    top = MenuGroup('Top')
    sub1 = MenuGroup('SubMenu1')
    sub2 = MenuItem('SubMenu2')
    sub11 = MenuItem('SubMenu11')
    sub12 = MenuItem('SubMenu12')

    top.add_child(sub1, sub2)
    sub1.add_child(sub11, sub12)

    top.print_menu()