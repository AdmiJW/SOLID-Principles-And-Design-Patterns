
class SecurityChecker:
    def __init__(self):
        # Default values
        self._passwords = {
            1: "123456"
        }

    def verify(self, id, password):
        return self._passwords[id] == password


class CardReader:
    def __init__(self):
        # Default values
        self._id = {
            "1111-1111-1111-1111": 1
        }

    def get_id(self, card_number):
        return self._id[card_number]


class AccountBalanceChecker:
    def __init__(self):
        # Default values
        self._balance = {
            1: 100
        }

    def get_balance(self, id):
        return self._balance[id]

    def update_balance(self, id, delta):
        self._balance[id] += delta
        return self._balance[id]


###################
# Facade: ATM
###################
class ATM:
    def __init__(self):
        self._security_system = SecurityChecker()
        self._card_reader = CardReader()
        self._balance_system = AccountBalanceChecker()

    def deposit(self, card_no, delta):
        id = self._card_reader.get_id(card_no)
        new_bal = self._balance_system.update_balance(id, delta)
        print(f"Deposit successful. New balance: {new_bal}")

    def withdraw(self, card_no, password, delta):
        id = self._card_reader.get_id(card_no)
        if not self._security_system.verify(id, password):
            return print("Wrong password. Rejecting transaction")

        new_bal = self._balance_system.update_balance(id, -delta)
        print(f"Withdrawal successful. New balance: {new_bal}")



if __name__ == '__main__':
    atm = ATM()
    card_no = "1111-1111-1111-1111"
    atm.deposit(card_no, 300)
    atm.withdraw(card_no, "123456", 200)


