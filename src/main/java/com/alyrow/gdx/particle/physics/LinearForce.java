package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * @author alyrow
 * Linear force
 */
public class LinearForce extends PhysicForce {

    /**
     * Linear force
     * @param vx x axis intensity
     * @param vy y axis intensity
     */
    public LinearForce(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;

        force = new Vector2(vx, vy);
    }
}
