package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Well base class of all forces.
 * If you want to create a force, extend your class with it and take a look at {@link PhysicParticle}
 */
public class PhysicForce implements Json.Serializable {
    public float vx, vy;
    Vector2 force;

    public Vector2 getForce(PhysicParticle particle) {
        return force;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
