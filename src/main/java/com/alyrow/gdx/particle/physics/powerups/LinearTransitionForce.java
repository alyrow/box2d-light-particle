package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.function.Supplier;

public class LinearTransitionForce extends LerpedForce {

    private float speed;
    private Supplier<Boolean> condition = () -> getFactor() > 1 || getFactor() < 0;

    public LinearTransitionForce(PhysicForce one, PhysicForce two, float speed) {
        super(one, two);
        this.speed = speed;
    }

    public LinearTransitionForce(PhysicForce one, PhysicForce two, float factor, float speed) {
        super(one, two, factor);
        this.speed = speed;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        addFactor(speed);
        if (condition.get()) speed = -speed;
        return super.getForce(particle);
    }

    public void setCondition(Supplier<Boolean> condition) {
        this.condition = condition;
    }

    @Override
    public void write(Json json) {
        super.write(json);
        json.writeValue("speed", speed);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
