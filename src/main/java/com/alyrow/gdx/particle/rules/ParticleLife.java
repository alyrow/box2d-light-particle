package com.alyrow.gdx.particle.rules;

/**
 * @author alyrow
 * Set the life of particles
 */
public class ParticleLife {
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
}
