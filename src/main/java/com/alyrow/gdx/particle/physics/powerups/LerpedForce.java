package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.alyrow.gdx.particle.utils.PhysicForces;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class LerpedForce extends PhysicForce {

    private PhysicForce one;
    private PhysicForce two;
    private float factor = 0.5f;
    private boolean clamp = true;


    public LerpedForce(PhysicForce one, PhysicForce two) {
        this.one = one;
        this.two = two;
    }

    public LerpedForce(PhysicForce one, PhysicForce two, float factor) {
        this.one = one;
        this.two = two;
        this.factor = factor;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return PhysicForces.Lerp(one, two, particle, factor);
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        if (clamp) this.factor = MathUtils.clamp(factor, 0, 1);
        else /* */ this.factor = factor;
    }

    public void addFactor(float factor) {
        if (clamp) this.factor = MathUtils.clamp(this.factor + factor, 0, 1);
        else /* */ this.factor += factor;
    }


    public boolean isClamp() {
        return clamp;
    }

    public void setClamp(boolean clamp) {
        this.clamp = clamp;
    }
}
