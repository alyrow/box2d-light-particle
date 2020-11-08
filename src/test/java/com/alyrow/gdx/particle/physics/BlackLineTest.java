package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.utils.Line;

public class BlackLineTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new BlackLine(
                        Line.fromTwoPoints(0, 0, 0.5f, 1),
                        40)
                )
                .Test();

    }

}