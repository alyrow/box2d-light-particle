package com.alyrow.gdx.particle.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author alyrow
 * This class load (TEXTURE or HALO mode) or create a Texture (NOTHING mode)
 */
public class ParticleTexture {

    /**
     * Libgdx texture
     * @see Texture
     */
    private Texture texture;

    /**
     * Create particule texture
     * @param path Path of the texture
     */
    public ParticleTexture(String path) {
        texture = new Texture(Gdx.files.internal(path));
    }

    /**
     * Create particule texture
     * @param texture The texture
     */
    public ParticleTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * You will never use that but create a 2x2 transparent texture
     */
    public ParticleTexture() {
        Pixmap t = new Pixmap(2, 2, Pixmap.Format.RGBA8888);
        texture = new Texture(t);
    }

    /**
     * Intern usage only
     * @return Return the texture
     */
    public Texture getTexture() {
        if (texture instanceof AnimatedTexture) return new AnimatedTexture(((AnimatedTexture) texture).textures, ((AnimatedTexture) texture).fps);
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }
}
