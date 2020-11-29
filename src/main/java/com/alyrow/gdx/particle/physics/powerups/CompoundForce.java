package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.Vector2;

public class CompoundForce extends PhysicForce {

    PhysicForce[] forces;

    public CompoundForce(PhysicForce... forces) {
        this.forces = forces;
    }

    Vector2 cache = new Vector2();
    ;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(0, 0);
        for (PhysicForce force : forces) cache.add(force.getForce(particle));
        return cache;
    }

    public PhysicForce[] getPhysicForces() {
        return forces;
    }
}
