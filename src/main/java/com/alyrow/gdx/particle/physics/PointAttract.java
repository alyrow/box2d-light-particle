package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class PointAttract extends PhysicForce {

    private Vector2 center;
    private float strength;

    public PointAttract(float x, float y, float strength) {
        this.center = new Vector2(x, y);
        this.strength = strength;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return new Vector2(center).sub(particle.x, particle.y).nor().scl(strength);
    }
}
