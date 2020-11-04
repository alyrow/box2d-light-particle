package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.math.MathUtils;

public class RandomPositionRectangle extends Modifier {

    float x, y, w, h;

    public RandomPositionRectangle(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public RandomPositionRectangle(float w, float h) {
        this(0, 0, w, h);
    }

    @Override
    public void modify() {
        setX(x + w  * MathUtils.random());
        setY(y + h  * MathUtils.random());
    }
}
