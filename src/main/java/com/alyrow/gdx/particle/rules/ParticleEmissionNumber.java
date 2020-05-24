package com.alyrow.gdx.particle.rules;

/**
 * @author alyrow
 * Set rule for the emission number
 */
public class ParticleEmissionNumber {
    /**
     * Number of particles emitted
     */
    public int number;

    /**
     * Set emission mode
     * @see #INNER_SCREEN
     * @see #PER_SECONDS
     */
    public int mode;

    /**
     * If mode is set to PER_SECONDS, this is the delay in seconds between each emission.
     */
    public int seconds = 1;

    /**
     * Set the mode of emission and number of particles
     * @param mode Emission mode
     *             {@link #INNER_SCREEN}
     *             {@link #PER_SECONDS}
     * @param number Number of particles emitted
     */
    public ParticleEmissionNumber(int mode, int number) {
        this.mode = mode;
        this.number = number;
    }

    /**
     * Set the delay between each emission if mode set to PER_SECONDS
     * @see #PER_SECONDS
     * @param seconds in second
     */
    public void setDelay(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Internal use only
     */
    public ParticleEmissionNumber() {
    }

    public int getNumber() {
        return number;
    }

    /**
     * Modes of emission
     */
    //MODE

    /**
     * Number of particles visible in the screen
     */
    public static int INNER_SCREEN = 0;

    /**
     * Number of particles emitted per second
     */
    public static int PER_SECONDS = 1;
}
