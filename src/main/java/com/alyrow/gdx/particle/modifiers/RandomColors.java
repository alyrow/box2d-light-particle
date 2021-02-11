package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RandomColors extends Modifier {

    Color[] cols = null;

    public RandomColors() {
    }

    public RandomColors(Color... colors) {
        cols = colors;
    }

    @Override
    public void modify() {
        if (cols == null) light.setColor(new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f));
        else light.setColor(cols[MathUtils.random(0, cols.length-1)]);
    }

    @Override
    public void write(Json json) {
        json.writeValue("cols", cols);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
