package com.alyrow.gdx.particle.rules;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

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
     * Use this if you doesn't want lights
     */
    public ParticleEmissionLight() {
        this(new Color(1, 1, 1, 1));
    }

    /**
     * Use this if you doesn't want lights but tint HALO
     */
    public ParticleEmissionLight(Color color) {
        this.color = color;
        this.rays = 5;
        this.distance = 1;
        this.rayHandler = new RayHandler(new World(new Vector2(0,0), true));
    }

    public PointLight getLight() {
        return new PointLight(rayHandler, rays, color, distance, 0, 0);
    }
}
