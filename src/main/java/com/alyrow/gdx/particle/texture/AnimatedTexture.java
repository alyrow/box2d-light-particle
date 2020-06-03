package com.alyrow.gdx.particle.texture;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;

import java.util.Date;

public class AnimatedTexture extends Texture {
    public Texture[] textures;
    public float fps;
    public int frame = 0;
    private float time = 0;

    public AnimatedTexture(Texture[] textures, float fps) {
        super(textures[0].getTextureData());
        this.textures = textures;
        this.fps = 1/fps;
    }

    public void render(float delta) {
        time += delta;
        if (time >= fps) {
            frame++;
            time -= fps;
            if (frame >= textures.length) frame = 0;
            //this.load(textures[frame].getTextureData());
        }
    }

    @Override
    public String toString() {
        return "-_-";
    }
}
