package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

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

}
