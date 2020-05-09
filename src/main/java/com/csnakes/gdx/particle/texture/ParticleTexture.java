package com.csnakes.gdx.particle.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class ParticleTexture {
    private Texture texture;

    public ParticleTexture(String path) {
        texture = new Texture(Gdx.files.internal(path));
    }

    public ParticleTexture() {
        Pixmap t = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
        texture = new Texture(t);
    }

    public Texture getTexture() {
        return texture;
    }
}
