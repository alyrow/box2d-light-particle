package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RandomChargeModifier extends Modifier {

    private float min, ran;

    public RandomChargeModifier() {
        this(-10, 10);
    }

    public RandomChargeModifier(float min, float max) {
        this.min = min;
        this.ran = max - min;
    }

    @Override
    public void modify() {
        setCharge(min + ran * MathUtils.random());
    }

    @Override
    public void write(Json json) {
        json.writeValue("min", min);
        json.writeValue("ran", ran);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
