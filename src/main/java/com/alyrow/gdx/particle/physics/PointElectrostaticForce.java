package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class PointElectrostaticForce extends PhysicForce {

    private float effect;
    private Vector2 center;

    public PointElectrostaticForce(float x, float y, float charge, float k) {
        center = new Vector2(x, y);
        effect = k * charge;
    }

    public PointElectrostaticForce(float x, float y, float charge) {
        this(x, y, charge, 40000);
    }

    private Vector2 cache;
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache = new Vector2(center.x - particle.x, center.y - particle.y);
        rs = cache.len2();
        return cache.nor().scl(effect * particle.charge / rs);
    }
}
