package com.alyrow.gdx.particle.modifiers;

public class MassProportionalLightRadius extends Modifier {
    public float factor;

    public MassProportionalLightRadius(float factor) {
        this.factor = factor;
    }

    @Override
    public void modify() {
        setMass(factor * light.getDistance());
    }
}
