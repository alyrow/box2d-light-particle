package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.math.MathUtils;

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

}
