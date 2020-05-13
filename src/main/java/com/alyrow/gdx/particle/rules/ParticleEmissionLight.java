package com.alyrow.gdx.particle.rules;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;

/**
 * @author alyrow
 * Set lights settings
 */
public class ParticleEmissionLight {

    public RayHandler rayHandler;
    public int rays;
    public Color color;
    public float distance;

    /**
     * Set particles emission light
     * @param rayHandler Lights manager
     *                   {@link RayHandler}
     * @param rays Number of rays. More is realistic
     * @param color Color of the light
     * @param distance Distance of emission
     */
    public ParticleEmissionLight(RayHandler rayHandler, int rays, Color color, float distance) {
        this.rayHandler = rayHandler;
        this.rays = rays;
        this.color = color;
        this.distance = distance;
    }

    /**
     * /!\ INTERNAL USE ONLY
     */
    public ParticleEmissionLight() {
    }

    public PointLight getLight() {
        return new PointLight(rayHandler, rays, color, distance, 0, 0);
    }
}
