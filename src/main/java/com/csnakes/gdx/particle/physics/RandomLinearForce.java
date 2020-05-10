package com.csnakes.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class RandomLinearForce extends PhysicForce {

    private final float vx_min;
    private final float vx_max;
    private final float vy_max;
    private final float vy_min;

    public RandomLinearForce(float vx_min, float vx_max, float vy_min, float vy_max) {
        this.vx_min = vx_min;
        this.vx_max = vx_max;
        this.vy_min = vy_min;
        this.vy_max = vy_max;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return new Vector2((float) Math.random()*(vx_max-vx_min)+vx_min, (float) Math.random()*(vy_max-vy_min)+vy_min);
    }
}
