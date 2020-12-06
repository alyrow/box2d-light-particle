package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.texture.ParticleTexture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Jsonify implements Json.Serializable {

    ParticleSystem system;
    public String systemToJSON(ParticleSystem system) {
        Json json = new Json();
        this.system = system;
        return json.prettyPrint(this);
    }


    @Override
    public void write(Json json) {
        json.writeValue("system.type", system.type, ParticleType.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }
}
