package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class MassProportionalLightRadius extends Modifier {
    public float factor;

    public MassProportionalLightRadius(float factor) {
        this.factor = factor;
    }

    @Override
    public void modify() {
        setMass(factor * light.getDistance());
    }

    @Override
    public void write(Json json) {
        json.writeValue("factor", factor);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
