package com.alyrow.gdx.particle.performance;

import com.alyrow.gdx.particle.physics.BlackHole;
import com.alyrow.gdx.particle.physics.BrownianForce;
import com.alyrow.gdx.particle.tester.CompareTwoTester;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;

public class LightAndWithoutLight {
    public static void main(String[] args) {

        new CompareTwoTester(
                new Tester().forForce(() -> new BrownianForce(15,28765, 1))
                    .setLightOn(true),
                new Tester().forForce(() -> new BrownianForce(15,28765, 1))
                    .setLightOn(false)
        );


    }
}
