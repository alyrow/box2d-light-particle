package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.modifiers.ModifierManager;
import com.alyrow.gdx.particle.physics.PhysicManager;
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
        json.writeValue("rules", system.getRules(), ParticleRules.class);
        json.writeValue("physics", system.getPhysicManager(), PhysicManager.class);
        json.writeValue("modifiers", system.getModifierManager(), ModifierManager.class);
        json.writeObjectStart("position");
        json.writeValue("x", system.x);
        json.writeValue("y", system.y);
        json.writeObjectEnd();
        json.writeValue("version", system.version);
        json.writeValue("version_code", system.version_code);
        json.writeValue("min_version_code", system.min_version_code);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

        //center = new Vector2(jsonData.getFloat("center x"), jsonData.getFloat("center y"));
        //speed = jsonData.getFloat("speed");

    }
}
