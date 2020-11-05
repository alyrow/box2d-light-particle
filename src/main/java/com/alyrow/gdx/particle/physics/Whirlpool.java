package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.CompoundForce;

public class Whirlpool extends CompoundForce {

    BlackHole bh;

    public Whirlpool(float x, float y, float speed, float attrtc) {
        super(new Revolution(x, y, speed), new BlackHole(x, y, attrtc));
        bh = (BlackHole) getPhysicForces()[1];
    }

    public void setDestructionRadius(float destructionRadius) {
        bh.setDestructionRadius(destructionRadius);
    }

}
