package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.modifiers.ModifierManager;
import com.alyrow.gdx.particle.physics.Fan;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.texture.ParticleTexture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Jsonify implements Json.Serializable {

    ParticleSystem system;
    public String systemToJSON(ParticleSystem system) {
        Json json = new Json();
        this.system = system;
        return json.prettyPrint(this);
    }

    public ParticleSystem JSONtoSystem(String str) {
        Json json = new Json();
        Jsonify jsonify = json.fromJson(Jsonify.class, str);
        return jsonify.system;
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
        //ParticleType type = json.fromJson(ParticleType.class, jsonData.getChild("system.type").asString());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < jsonData.get("system.type").size; i++) {
            stringBuilder.append(jsonData.get("system.type").get(i));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");

        //System.out.println(stringBuilder.toString()); //json.prettyPrint(jsonData.getChild("system.type"))
        ParticleType type = json.fromJson(ParticleType.class, stringBuilder.toString());

        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 1.0f);
        camera.update();

        system = new ParticleSystem(type, null, camera);
    }
}
