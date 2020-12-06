package com.alyrow.gdx.particle.rules;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Define the duration of emission
 */
public class ParticleEmissionDuration implements Json.Serializable {
    public float duration; //Seconds
    public boolean infinite;

    /**
     * Set a duration
     * @param duration Duration in seconds
     */
    public ParticleEmissionDuration(float duration) {
        this.duration = duration;
        this.infinite = false;
    }

    /**
     * Set infinite duration... Yes DON'T PASS FALSE AS ARGUMENT!
     * @param infinite Always put true
     */
    public ParticleEmissionDuration(boolean infinite) {
        this.infinite = infinite;
    }

    /**
     * Set the duration of emission
     * @param duration In seconds
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * Set if emission is infinite or not
     * @param infinite Infinite?
     */
    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }


    @Override
    public void write(Json json) {
        json.writeValue("infinite", infinite);
        json.writeValue("duration", duration);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }
}
