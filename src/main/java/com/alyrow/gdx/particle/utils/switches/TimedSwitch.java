package com.alyrow.gdx.particle.utils.switches;

import com.badlogic.gdx.Gdx;

public class TimedSwitch extends SwitchAdaptor {

    private float timeMilliSec;
    private int statesCount;

    public TimedSwitch(float timeMilliSec, int statesCount) {
        this.timeMilliSec = timeMilliSec;
        this.statesCount = statesCount;
    }

    private float lastTime = 0;

    private int ind = 0;

    @Override
    public int getState() {
        lastTime += Gdx.graphics.getDeltaTime();
        if (lastTime > timeMilliSec) {
            lastTime = 0;
            ind++;
        }
        return ind % statesCount;
    }

    @Override
    public int getStatesCount() {
        return statesCount;
    }

}
