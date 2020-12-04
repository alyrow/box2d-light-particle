package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.PhysicForces;
import com.badlogic.gdx.math.Vector2;

public class PointElectrostaticForce extends PhysicForce {

    private float effect;
    private Vector2 center;

    private float drs = 0.1f; // destruction radius squared
    private float limit = 10000;

    public PointElectrostaticForce(float x, float y, float charge, float k) {
        center = new Vector2(x, y);
        effect = k * charge;
    }

    public PointElectrostaticForce(float x, float y, float charge) {
        this(x, y, charge, 40000);
    }

    private Vector2 cache = new Vector2();
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(center.x - particle.x, center.y - particle.y);
        rs = cache.len2();
        if (rs < drs) particle.deleteParticle();

        return PhysicForces.Clamp(cache.nor().scl(effect * particle.charge / rs), limit);
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }
}
