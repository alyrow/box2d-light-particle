package com.csnakes.gdx.particle;

import com.csnakes.gdx.particle.rules.ParticleEmissionDuration;
import com.csnakes.gdx.particle.rules.ParticleEmissionLight;
import com.csnakes.gdx.particle.rules.ParticleEmissionNumber;
import com.csnakes.gdx.particle.rules.ParticleLife;

public class ParticleRules {
    public ParticleLife life;
    public ParticleEmissionNumber number;
    public ParticleEmissionDuration duration;
    public ParticleEmissionLight light;

    public ParticleRules() {
        this.life = new ParticleLife(5f);
        this.number = new ParticleEmissionNumber(ParticleEmissionNumber.INNER_SCREEN, 15);
        this.duration = new ParticleEmissionDuration(true);
    }

    public void setLife(ParticleLife life) {
        this.life = life;
    }

    public void setNumber(ParticleEmissionNumber number) {
        this.number = number;
    }

    public void setDuration(ParticleEmissionDuration duration) {
        this.duration = duration;
    }

    public void setLight(ParticleEmissionLight light) {
        this.light = light;
    }
}
