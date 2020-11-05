package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.math.MathUtils;

public class RandomMassModifier extends Modifier {

    private float min, ran;

    public RandomMassModifier() {
        this(1, 10);
    }

    public RandomMassModifier(float min, float max) {
        this.min = min;
        this.ran = max - min;
    }

    @Override
    public void modify() {
        setMass(min + ran * MathUtils.random());
    }

}
