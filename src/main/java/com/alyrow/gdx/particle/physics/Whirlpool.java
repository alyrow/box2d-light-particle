package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.CompoundForce;

public class Whirlpool extends CompoundForce {

    public Whirlpool(float x, float y, float speed, float attrtc) {
        super(new Revolution(x, y, speed), new BlackHole(x, y, attrtc));
    }

}
