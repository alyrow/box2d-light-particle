package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class BlackHole extends PhysicForce {
    public float x, y, force;

    public BlackHole(float x, float y, float force) {
        this.x = x;
        this.y = y;
        this.force = force;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        float dx = x - particle.x;
        float dy = y - particle.y;
        if (Math.abs(dx) > Math.abs(dy)) {
            return new Vector2((dx > 0)? force : -force, force*dy/dx);
        }
        else if (Math.abs(dx) < Math.abs(dy))return new Vector2(force*dx/dy, (dy > 0)? force : -force);
        else return new Vector2((dx > 0)? force : -force, (dy > 0)? force : -force);
    }
}
