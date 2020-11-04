package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

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
}
