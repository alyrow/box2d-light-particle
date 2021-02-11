package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

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

    @Override
    public void write(Json json) {
        json.writeValue("vx", vx);
        json.writeValue("vy", vy);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
