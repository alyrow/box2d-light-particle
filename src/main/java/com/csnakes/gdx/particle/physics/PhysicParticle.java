package com.csnakes.gdx.particle.physics;

import java.util.HashMap;
import java.util.Map;

public class PhysicParticle {
    public float x, x_start, y, y_start, r;
    public Map<String, Float> data = new HashMap<String, Float>();

    public PhysicParticle(float x, float y) {
        this.x = x;
        this.y = y;
        x_start = x;
        y_start = y;
    }
}
