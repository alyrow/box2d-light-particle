package com.alyrow.gdx.particle.utils;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.Vector2;

public class PhysicForces {

    public static Vector2 Lerp(PhysicForce one, PhysicForce two, PhysicParticle particle, float factor) {
        return new Vector2(one.getForce(particle)).lerp(two.getForce(particle), factor);
    }

}
