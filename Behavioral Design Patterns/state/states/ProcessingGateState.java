package state.states;

import state.Gate;

public class ProcessingGateState extends GateState {

    public ProcessingGateState(Gate gate) {
        super(gate);
    }

    @Override
    public String getDisplayMessage() {
        return "PROCESSING";
    }

    @Override
    public String getDisplayLight() {
        return "YELLOW";
    }

    @Override
    public void enter() {
        System.out.println("Unauthorized entry!");
    }

    @Override
    public void pay() {
        System.out.println("Already processing payment!");
    }

    @Override
    public void transactionComplete() {
        gate.setGateState(new OpenGateState(gate));
    }
}
