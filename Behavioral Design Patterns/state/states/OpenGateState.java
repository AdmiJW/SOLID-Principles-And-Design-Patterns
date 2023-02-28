package state.states;

import state.Gate;

public class OpenGateState extends GateState {

    public OpenGateState(Gate gate) {
        super(gate);
    }

    @Override
    public String getDisplayMessage() {
        return "OPEN";
    }

    @Override
    public String getDisplayLight() {
        return "GREEN";
    }

    @Override
    public void enter() {
        System.out.println("Gate entered!");
        gate.setGateState( new ClosedGateState(gate) );
    }

    @Override
    public void pay() {
        System.out.println("Already paid!");
    }

    @Override
    public void transactionComplete() {
        System.out.println("Already paid!");
    }
    
}
