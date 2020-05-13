package com.alyrow.gdx.particle.rules;

/**
 * @author alyrow
 * Set rule for the emission number with random number
 * @see ParticleEmissionNumber
 */
public class ParticleEmissionNumberRandom extends ParticleEmissionNumber {
    public int min;
    public int max;
    public int mode;

    public ParticleEmissionNumberRandom(int mode, int min, int max) {
        this.mode = mode;
        this.min = min;
        this.max = max;
    }

    public int getNumber() {
        return (int) Math.round(Math.random()*(max-min)+min);
    }
}
