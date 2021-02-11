package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

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

    @Override
    public void write(Json json) {
        json.writeValue("forces", forces);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
