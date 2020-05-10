package com.csnakes.gdx.particle.rules;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;

public class ParticleEmissionLightRandom extends ParticleEmissionLight {
    public RayHandler rayHandler;
    public int rays;
    public Color color;
    public float distance_min;
    public float distance_max;

    public ParticleEmissionLightRandom(RayHandler rayHandler, int rays, Color color, float distance_min, float distance_max) {
        this.rayHandler = rayHandler;
        this.rays = rays;
        this.color = color;
        this.distance_min = distance_min;
        this.distance_max = distance_max;
    }

    public PointLight getLight() {
        return new PointLight(rayHandler, rays, color, (float) (Math.random()*(distance_max-distance_min)+distance_min), 0, 0);
    }
}
