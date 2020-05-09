package com.csnakes.gdx.particle.physics;

import com.badlogic.gdx.utils.Array;

public class PhysicManager {
    public Array<PhysicForce> forces = new Array<>();

    public PhysicManager() {}

    public void addForce(PhysicForce force) {
        forces.add(force);
    }
    public void removeForce(PhysicForce force) {
        forces.removeValue(force, true);
    }

    public PhysicParticle getParticleForces(float x, float y) {
        return new PhysicParticle(x, y);
    }
}
