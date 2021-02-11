package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Physic manager
 */
public class PhysicManager implements Json.Serializable {
    public Array<PhysicForce> forces = new Array<>();

    public PhysicManager() {}

    /**
     * Add force to particles
     * @param force Force that extend {@link PhysicForce}
     */
    public void addForce(PhysicForce force) {
        forces.add(force);
    }

    /**
     * Remove force
     * @param force Existing forc
     */
    public void removeForce(PhysicForce force) {
        forces.removeValue(force, true);
    }

    /**
     * Internal use only!
     */
    public PhysicParticle getParticleForces(float x, float y, World world, Camera camera) {
        return new PhysicParticle(x, y, world, camera);
    }

    @Override
    public void write(Json json) {
        json.writeValue("forces", forces, PhysicForce.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
