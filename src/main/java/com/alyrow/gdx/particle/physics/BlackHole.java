package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.PhysicForces;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class BlackHole extends PhysicForce {

    private float effect;
    private Vector2 center;

    private float drs = 0.5f; // destruction radius squared
    private float limit = 500;

    public BlackHole(float x, float y, float mass, float G) {
        center = new Vector2(x, y);
        effect = G * mass;
    }

    public BlackHole(float x, float y, float mass) {
        this(x, y, mass, 40000);
    }

    private Vector2 cache = new Vector2();
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(center.x - particle.x, center.y - particle.y);
        rs = cache.len2();
        if (rs < drs) particle.deleteParticle();

        return PhysicForces.Clamp(cache.nor().scl(effect * particle.mass / rs), limit);
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }

    public void disableDestructionRadius() {
        drs = 0;
    }

    @Override
    public void write(Json json) {
        json.writeValue("x", center.x);
        json.writeValue("y", center.y);
        json.writeValue("effect", effect);
        json.writeValue("drs", drs);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
