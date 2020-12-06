package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.texture.ParticleTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * @author alyrow
 * Define particles type...
 */
public enum ParticleType implements Json.Serializable {
    CIRCLE("circle.png"),
    DISCORD("discord.png"),
    GRILL("grill.png"),
    HALO("halo.png"),
    SQUARE("square.png"),
    STAR("star.png"),
    TRIANGLE("triangle.png"),

    TEXTURE(),
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

    public void setTexture(String path) {
        this.texture = new ParticleTexture(path);
    }

    public boolean isHalo() {
        return this != NOTHING && this != TEXTURE;
    }

    @Override
    public void write(Json json) {

        json.writeValue("particle_type_halo", isHalo());
        //json.writeObjectStart("texture");
        json.writeValue("texture", texture, ParticleTexture.class);
        //json.writeValue("random", texture.random);
        //json.writeValue("animated", texture.animated);
        //json.writeValue("fps", texture.fps);
        //json.writeValue("texture", texture.paths2d);
        //json.writeObjectEnd();


    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }


}

