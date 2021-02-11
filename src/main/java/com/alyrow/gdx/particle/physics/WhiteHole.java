package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class WhiteHole extends PhysicForce {

    private float effect;
    private Vector2 center;

    public WhiteHole(float x, float y, float mass, float G) {
        center = new Vector2(x, y);
        effect = -G * mass;
    }

    public WhiteHole(float x, float y, float mass) {
        this(x, y, mass, 40000);
    }

    private Vector2 cache = new Vector2();
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(center.x - particle.x, center.y - particle.y);
        rs = cache.len2();
        return cache.nor().scl(effect * particle.mass / rs);
    }

    @Override
    public void write(Json json) {
        json.writeValue("center_x", center.x);
        json.writeValue("center_y", center.y);
        json.writeValue("effect", effect);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
