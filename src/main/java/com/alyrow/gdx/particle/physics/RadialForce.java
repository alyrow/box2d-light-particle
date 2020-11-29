package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * @author alyrow
 * Radial force
 */
public class RadialForce extends PhysicForce {
    public float strength;

    /**
     * Create radial force
     *
     * @param strength Strength of the force
     */
    public RadialForce(float strength) {
        this.strength = strength;
    }

    private Vector2 cache = new Vector2();

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(particle.x, particle.y).sub(particle.x_start, particle.y_start);
        float distance = cache.len();

        float angle;
        if (distance == 0) particle.data_float.put("RadialForce:Angle", angle = MathUtils.random() * 2 * MathUtils.PI);
        else angle = particle.data_float.get("RadialForce:Angle");

        //Gdx.app.log("angle", String.valueOf(Math.toDegrees(angle)));
        return cache.set(strength, 0).setAngleRad(angle);
    }
}
