package com.csnakes.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class LinearForce extends PhysicForce {

    public LinearForce(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;

        force = new Vector2(vx, vy);
    }
}
