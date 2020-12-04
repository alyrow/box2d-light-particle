package com.alyrow.gdx.particle.performance;

import com.alyrow.gdx.particle.physics.BrownianForce;
import com.alyrow.gdx.particle.physics.Revolution;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;

public class plsTest {
    public static void main(String[] args) {

        new TestPerformance()
                .setEmissionNumberMode(ParticleEmissionNumber.PER_SECONDS)
                .setEmissionSecondsDelay(0.1f)
                .setPc(25)
                .forForce(() -> new BrownianForce(10, 265484598, 1))
                .forForce(() -> new Revolution(0, 0, 10))
                .Test();
    }
}
