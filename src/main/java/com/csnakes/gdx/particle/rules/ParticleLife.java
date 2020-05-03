package com.csnakes.gdx.particle.rules;

public class ParticleLife {
    public float life; //in seconds
    public boolean outer = false; //If false --> Survive `life` seconds then die

    public ParticleLife(float life) {
        this.life = life;
    }
    public ParticleLife(float life, boolean outer) {
        this.life = life;
        this.outer = outer;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
}
