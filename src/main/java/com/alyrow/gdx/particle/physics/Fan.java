package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Fan extends PhysicForce implements Json.Serializable {

    private float speed;
    private Vector2 center;

    public Fan() {
    }

    public Fan(float x, float y, float speed) {
        center = new Vector2(x, y);
        this.speed = speed / 100;
    }

    private Vector2 cache = new Vector2(0, 0);

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return cache.set(particle.y - center.y, center.x - particle.x).scl(speed);
    }

    @Override
    public void write(Json json) {

        json.writeValue("center x", center.x);
        json.writeValue("center y", center.y);

        json.writeValue("speed", speed);

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        speed = jsonData.getFloat("speed");

    }

    @Override
    public String toString() {
        return "Fan{ x: " + center.x + "  y: " + center.y + "  speed: " + speed + " }";
    }
}