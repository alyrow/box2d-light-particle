package com.alyrow.gdx.particle.rules;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Set the life of particles
 */
public class ParticleLife implements Json.Serializable {
    public float life; //in seconds
    public boolean outer = false; //If false --> Survive `life` seconds then die

    /**
     * Life of particles
     * @param life In seconds
     */
    public ParticleLife(float life) {
        this.life = life;
    }

    /**
     * Life of particles when they are outside the screen or not
     * @param life In seconds
     * @param outer If true, particles survive `life` seconds outside the screen then die.
     */
    public ParticleLife(float life, boolean outer) {
        this.life = life;
        this.outer = outer;
    }

    /**
     * Internal use
     */
    public ParticleLife() {
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }


    @Override
    public void write(Json json) {
        boolean random = this instanceof ParticleLifeRandom;
        json.writeValue("random", random);
        json.writeValue("outer", outer);
        Array<Float> lifes = new Array<>();
        if (random) {
            lifes.add(((ParticleLifeRandom) this).life_min);
            lifes.add(((ParticleLifeRandom) this).life_max);
        } else lifes.add(life);
        json.writeValue("life", lifes);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }
}
