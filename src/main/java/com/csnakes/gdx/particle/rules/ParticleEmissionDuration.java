package com.csnakes.gdx.particle.rules;

public class ParticleEmissionDuration {
    public float duration; //Seconds
    public boolean infinite = true;

    public ParticleEmissionDuration(float duration) {
        this.duration = duration;
        this.infinite = false;
    }
    public ParticleEmissionDuration(boolean infinite) {
        this.infinite = infinite;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }
}
