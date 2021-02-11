package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PointAttract extends PhysicForce {

    private Vector2 center;
    private float strength;

    private float drs = 0.3f; // destruction radius squared

    public PointAttract(float x, float y, float strength) {
        this.center = new Vector2(x, y);
        this.strength = strength;
    }

    public PointAttract(float x, float y, float strength, float drs) {
        this.center = new Vector2(x, y);
        this.strength = strength;
        this.drs = drs;
    }

    Vector2 cache = new Vector2();

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(center).sub(particle.x, particle.y);
        if(cache.len2() < drs) particle.deleteParticle();
        return cache.nor().scl(strength);
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }

    @Override
    public void write(Json json) {
        json.writeValue("center_x", center.x);
        json.writeValue("center_y", center.y);
        json.writeValue("strength", strength);
        json.writeValue("drs", drs);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
