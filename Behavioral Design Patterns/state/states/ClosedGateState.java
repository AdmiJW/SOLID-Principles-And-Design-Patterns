package state.states;

import state.Gate;

public class ClosedGateState extends GateState {

    public ClosedGateState(Gate gate) {
        super(gate);
    }

    @Override
    public String getDisplayMessage() {
        return "CLOSED";
    }

    @Override
    public String getDisplayLight() {
        return "RED";
    }

    @Override
    public void enter() {
        System.out.println("Unauthorized entry!");
    }

    @Override
    public void pay() {
        gate.setGateState(new ProcessingGateState(gate));
    }

    @Override
    public void transactionComplete() {
        System.out.println("Unauthorized transaction!");
    }
}
