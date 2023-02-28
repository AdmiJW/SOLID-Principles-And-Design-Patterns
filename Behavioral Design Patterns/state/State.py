
#########################
# State Abstract - Gate
#########################
class GateState:
    def get_sensor_light(self): pass
    def get_message(self): pass

##########################
# Concrete States
##########################
class GateOpenState(GateState):
    def get_sensor_light(self):
        return "GREEN"
    def get_message(self):
        return "You may pass the gate now"

class GateCloseState(GateState):
    def get_sensor_light(self):
        return "RED"
    def get_message(self):
        return "Please scan your ID / Ticket on the sensor"

class GateProcessState(GateState):
    def get_sensor_light(self):
        return "YELLOW"
    def get_message(self):
        return "Processing... Please wait"





###############################################
# State Machine Abstract
# The state machine will handle the transition
###############################################
class StateMachine:
    def transition(self, input): pass
    def __getattr__(self, item): pass

#########################
# Concrete State Machine
#########################
class GateStateMachine(StateMachine):
    def __init__(self):
        self._open_state = GateOpenState()
        self._close_state = GateCloseState()
        self._process_state = GateProcessState()
        self._current_state = self._close_state

        # Maps current state -> input -> next state
        # Available inputs: scan, cross_gate, transaction_success, transaction_fail
        self._transition_map = {
            self._open_state: {
                "scan": self._open_state,
                "cross_gate": self._close_state,
                "transaction_success": self._open_state,
                "transaction_fail": self._open_state
            },
            self._close_state: {
                "scan": self._process_state,
                "cross_gate": self._close_state,
                "transaction_success": self._close_state,
                "transaction_fail": self._close_state
            },
            self._process_state: {
                "scan": self._process_state,
                "cross_gate": self._process_state,
                "transaction_success": self._open_state,
                "transaction_fail": self._close_state
            }
        }

    def transition(self, input):
        self._current_state = self._transition_map[ self._current_state ][ input ]

    # Use to access attributes of the State
    def __getattr__(self, attr):
        return getattr( self._current_state, attr )




##############################
# The Gate has a StateMachine
##############################
class Gate:
    def __init__(self):
        self._state_machine = GateStateMachine()

    def display(self):
        print(f"Light: {self._state_machine.get_sensor_light()}, Display: {self._state_machine.get_message()}")

    def start(self):
        while True:
            self.display()

            try:
                action = input("Enter action (scan/cross_gate/transaction_success/transaction_fail: ")
                self._state_machine.transition(action)
            except KeyError:
                print("Invalid Action")



if __name__ == '__main__':
    gate = Gate()
    gate.start()