package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.CompoundForce;

public class Drain extends CompoundForce {
    public Drain(float x, float y, float strength, float speed) {
        super(new PointAttract(x, y, strength), new Revolution(x, y, speed));
    }
}
