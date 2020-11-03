package com.alyrow.gdx.particle.utils.switches;

import com.badlogic.gdx.Gdx;

public class TimeMatcherSwitch extends SwitchAdaptor {

    private float[] timeDurs;
    private int statesCount;

    public TimeMatcherSwitch(float... timeDurs) {
        this.timeDurs = timeDurs;
        statesCount = timeDurs.length;
    }

    private float lastTime = 0;
    private int state = 0;

    @Override
    public int getState() {
        lastTime += Gdx.graphics.getDeltaTime();
        if (lastTime > timeDurs[state]) {
            lastTime = 0;
            state++;
            if (state >= statesCount) state = 0;
        }
        System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+lastTime);
        return state;
    }

    @Override
    public int getStatesCount() {
        return statesCount;
    }
}
