package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.texture.ParticleTexture;

/**
 * @author alyrow
 * Define particles type...
 */
public enum ParticleType {
    HALO(new ParticleTexture("halo.png")),
    CIRCLE(new ParticleTexture("circle.png")),
    SQUARE(new ParticleTexture("square.png")),
    TRIANGLE(new ParticleTexture("triangle.png")),
    TEXTURE(new ParticleTexture()), // TODO: Minecraftian14, What is this? What's it supposed to be?
    NOTHING(new ParticleTexture());

    private ParticleTexture texture;

    ParticleType(ParticleTexture texture) {
        this.texture = texture;
    }

    public ParticleTexture getTexture() {
        return texture;
    }

    public void setTexture(ParticleTexture texture) {
        this.texture = texture;
    }

    public boolean isImage() {
        return this == HALO;
    }
}

