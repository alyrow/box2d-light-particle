package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class PointAttract extends PhysicForce {

    private Vector2 center;
    private float strength;

    private float drs = 0.3f; // destruction radius squared

    public PointAttract(float x, float y, float strength) {
        this.center = new Vector2(x, y);
        this.strength = strength;
    }

    Vector2 cache;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache = new Vector2(center).sub(particle.x, particle.y);
        if(cache.len2() < drs) particle.deleteParticle();
        return cache.nor().scl(strength);
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }
}
