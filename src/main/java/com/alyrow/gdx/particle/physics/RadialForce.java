package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * @author alyrow
 * Radial force
 */
public class RadialForce extends PhysicForce {
    float strength;

    /**
     * Create radial force
     * @param strength Strength of the force
     */
    public RadialForce(float strength) {
        this.strength = strength;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        float distance = (float) Math.sqrt((particle.x-particle.x_start)*(particle.x-particle.x_start) + (particle.y-particle.y_start)*(particle.y-particle.y_start));
        double angle;
        if (distance == 0) {
            angle = (Math.random() * 2 * Math.PI);
            if (angle > Math.PI) particle.data.put("radian", -1f);
            else particle.data.put("radian", 1f);
        } else {
            angle = Math.acos((particle.x - particle.x_start) / distance);
            if (particle.data.get("radian") == -1f) angle = -angle;
        }
        //Gdx.app.log("angle", String.valueOf(Math.toDegrees(angle)));
        return new Vector2((float) Math.cos(angle)*strength, (float) Math.sin(angle)*strength);
    }
}
