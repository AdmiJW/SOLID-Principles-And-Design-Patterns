package state;

import state.states.ClosedGateState;
import state.states.GateState;

public class Gate {
    private GateState gateState;

    public Gate() {
        gateState = new ClosedGateState(this);
    }

    public void setGateState(GateState gateState) {
        this.gateState = gateState;
    }

    public String getDisplayMessage() {
        return gateState.getDisplayMessage();
    }

    public String getDisplayLight() {
        return gateState.getDisplayLight();
    }

    public void enter() {
        gateState.enter();
    }

    public void pay() {
        gateState.pay();
    }

    public void transactionComplete() {
        gateState.transactionComplete();
    }
}
