package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.Vector2;

public class LinearTransitionForce extends LerpedForce {

    private float speed;

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
        if (getFactor() > 1 || getFactor() < 0) speed = -speed;
        return super.getForce(particle);
    }

}
