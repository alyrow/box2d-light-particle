package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.PhysicForces;
import com.badlogic.gdx.math.Vector2;

public class BlackHole extends PhysicForce {

    private float effect;
    private Vector2 center;

    private float drs = 0.1f; // destruction radius squared
    private float limit = 500;

    public BlackHole(float x, float y, float mass, float G) {
        center = new Vector2(x, y);
        effect = G * mass;
    }

    public BlackHole(float x, float y, float mass) {
        this(x, y, mass, 40000);
    }

    private Vector2 cache = Vector2.Zero;
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache = new Vector2(center.x - particle.x, center.y - particle.y);
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
}
