package com.csnakes.gdx.particle.rules;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;


public class ParticleEmissionLight {

    public final RayHandler rayHandler;
    public final int rays;
    public final Color color;
    public final float distance;

    public ParticleEmissionLight(RayHandler rayHandler, int rays, Color color, float distance) {
        this.rayHandler = rayHandler;
        this.rays = rays;
        this.color = color;
        this.distance = distance;
    }

    public PointLight getLight() {
        return new PointLight(rayHandler, rays, color, distance, 0, 0);
    }
}
