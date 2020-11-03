package com.alyrow.gdx.particle.utils.switches;

public class SimpleSwitch extends SwitchAdaptor {

    private int statesCount;
    private int state;

    public SimpleSwitch(int statesCount) {
        this.statesCount = statesCount;
        state = 0;
    }

    public SimpleSwitch(int statesCount, int currentState) {
        this.statesCount = statesCount;
        this.state = currentState;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public int getStatesCount() {
        return statesCount;
    }

    public void stepUp() {
        state++;
        if (state >= statesCount) state = 0;
    }

    public void stepDown() {
        state--;
        if (state < 0) state = statesCount - 1;
    }

}
