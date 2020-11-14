package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.texture.ParticleTexture;

/**
 * @author alyrow
 * Define particles type...
 */
public enum ParticleType {
    CIRCLE("circle.png"),
    GRILL("grill.png"),
    HALO("halo.png"),
    SQUARE("square.png"),
    STAR("star.png"),
    TRIANGLE("triangle.png"),

    TEXTURE(), // TODO: Minecraftian14, What is this? What's it supposed to be?
    NOTHING();

    private ParticleTexture texture;
    private final String path;

    ParticleType(String path) {
        this.path = path;
    }

    ParticleType() {
        path = "NOT DEFINED";
    }

    public ParticleTexture getTexture() {
        if (texture == null) prepareTexture();
        return texture;
    }

    private void prepareTexture() {
        if (path.equals("NOT DEFINED"))
            texture = new ParticleTexture();
        else
            texture = new ParticleTexture(path);
    }

    public void setTexture(ParticleTexture texture) {
        this.texture = texture;
    }

    public boolean isImage() {
        return this != NOTHING && this != TEXTURE;
    }
}

