package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.alyrow.gdx.particle.utils.switches.Switch;
import com.alyrow.gdx.particle.utils.switches.TimedSwitch;
import com.badlogic.gdx.math.Vector2;

public class SwitchableForce extends PhysicForce {

    private PhysicForce[] forces;
    private Switch swt;

    public SwitchableForce(PhysicForce... forces) {
        this.forces = forces;
        swt = new TimedSwitch(100, forces.length);
    }

    public SwitchableForce(Switch swt, PhysicForce... forces) {
        this.forces = forces;
        this.swt = swt;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return forces[swt.getState()].getForce(particle);
    }
}
