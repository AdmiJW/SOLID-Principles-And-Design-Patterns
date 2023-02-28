package state.states;

import state.Gate;

public abstract class GateState {
    protected Gate gate;

    public GateState(Gate gate) {
        this.gate = gate;
    }

    public abstract String getDisplayMessage();
    public abstract String getDisplayLight();
    public abstract void enter();
    public abstract void pay();
    public abstract void transactionComplete();
}
