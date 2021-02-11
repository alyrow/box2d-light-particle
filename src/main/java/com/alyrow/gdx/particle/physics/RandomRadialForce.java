package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Random radial force
 */
public class RandomRadialForce extends PhysicForce {
    public final float strength_min;
    public final float strength_max;

    /**
     * @param strength_min Min strength of the force
     * @param strength_max Max strength of the force
     */
    public RandomRadialForce(float strength_min, float strength_max) {
        this.strength_min = strength_min;
        this.strength_max = strength_max;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        float distance = (float) Math.sqrt((particle.x-particle.x_start)*(particle.x-particle.x_start) + (particle.y-particle.y_start)*(particle.y-particle.y_start));
        float strength = (float) (Math.random()*(strength_max-strength_min)+strength_min);
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

    @Override
    public void write(Json json) {
        json.writeValue("strength_min", strength_min);
        json.writeValue("strength_max", strength_max);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
