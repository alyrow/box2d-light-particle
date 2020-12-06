package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLight;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * This class defines the rules for the particle system and particles
 */
public class ParticleRules implements Json.Serializable{
    /**
     * Define life of particles
     * @see ParticleLife
     */
    public ParticleLife life;

    /**
     * Define the number of particles emitted
     * @see ParticleEmissionNumber
     */
    public ParticleEmissionNumber number;

    /**
     * Define the duration of emission
     * @see ParticleEmissionDuration
     */
    public ParticleEmissionDuration duration;

    /**
     * Set the light values of particles
     * @see ParticleEmissionLight
     */
    public ParticleEmissionLight light;

    /**
     * Well, that is the constructor...
     */
    public ParticleRules() {
        this.life = new ParticleLife(5f);
        this.number = new ParticleEmissionNumber(ParticleEmissionNumber.INNER_SCREEN, 15);
        this.duration = new ParticleEmissionDuration(true);
    }

    /**
     * @see ParticleLife
     * @param life Set life of particles
     */
    public void setLife(ParticleLife life) {
        this.life = life;
    }

    /**
     * @see ParticleEmissionNumber
     * @param number Set number of particles emitted
     */
    public void setNumber(ParticleEmissionNumber number) {
        this.number = number;
    }

    /**
     * @see ParticleEmissionDuration
     * @param duration Set duration of emission
     */
    public void setDuration(ParticleEmissionDuration duration) {
        this.duration = duration;
    }

    /**
     * @see ParticleEmissionLight
     * @param light Turn on the lights!
     */
    public void setLight(ParticleEmissionLight light) {
        this.light = light;
    }

    @Override
    public void write(Json json) {
        json.writeValue("ParticleEmissionDuration", duration, ParticleEmissionDuration.class);
        json.writeValue("ParticleEmissionLight", light, ParticleEmissionLight.class);
        json.writeValue("ParticleEmissionNumber", number, ParticleEmissionNumber.class);
        json.writeValue("ParticleLife", life, ParticleLife.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }
}
