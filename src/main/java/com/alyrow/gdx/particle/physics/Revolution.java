package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class Revolution extends PhysicForce {

    // Black hole specific
    private float speed;
    private Vector2 center;

    public Revolution(float x, float y, float speed) {
        center = new Vector2(x, y);
        this.speed = speed;
    }

    private Vector2 cache = new Vector2();

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return cache.set(particle.y - center.y, center.x - particle.x).nor().scl(speed);
    }

}
