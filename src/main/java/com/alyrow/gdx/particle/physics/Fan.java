package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class Fan extends PhysicForce {

    // Black hole specific
    private float speed;
    private Vector2 center;

    public Fan(float x, float y, float speed) {
        center = new Vector2(x, y);
        this.speed = speed/100;
    }

    private Vector2 cache = Vector2.Zero;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.x = particle.y - center.y;
        cache.y = center.x - particle.x;

        return cache.scl(speed);
    }

}