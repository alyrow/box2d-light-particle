package com.alyrow.gdx.particle.utils;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class PhysicForces {

    private static Vector2 cache = new Vector2();

    public static Vector2 Lerp(PhysicForce one, PhysicForce two, PhysicParticle particle, float factor) {
        return cache.set(one.getForce(particle)).lerp(two.getForce(particle), factor);
    }

    public static Vector2 Clamp(Vector2 vc, float limit) {
        return Clamp(vc, -limit, limit);
    }

    public static Vector2 Clamp(Vector2 vc, float ll, float ul) {
        float mag = vc.len();
        if(mag < ll) return cache.set(vc).nor().scl(ll);
        if(mag > ul) return cache.set(vc).nor().scl(ul);
        return cache.set(vc);
    }
}
