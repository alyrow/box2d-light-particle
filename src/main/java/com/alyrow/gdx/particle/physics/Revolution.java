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

    private Vector2 cache = Vector2.Zero;
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.x = particle.y - center.y;
        cache.y = center.x - particle.x;

        return cache.nor().scl(speed);
    }

}
