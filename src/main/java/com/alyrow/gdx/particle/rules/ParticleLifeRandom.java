package com.alyrow.gdx.particle.rules;

/**
 * @author alyrow
 * Set random life of particles
 * @see ParticleLife
 */
public class ParticleLifeRandom extends ParticleLife {
    public float life_min; //in seconds
    public float life_max; //in seconds
    public boolean outer = false; //If false --> Survive `life` seconds then die

    public ParticleLifeRandom(float life_min, float life_max) {
        this.life_min = life_min;
        this.life_max = life_max;
    }
    public ParticleLifeRandom(float life_min, float life_max, boolean outer) {
        this.life_min = life_min;
        this.life_max = life_max;
        this.outer = outer;
    }

    public float getLife() {
        return (float) (Math.random()*(life_max-life_min)+life_min);
    }

    public void setLife(float life_min, float life_max) {
        this.life_min = life_min;
        this.life_max = life_max;
    }
}
