package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

public class WhiteHoleTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new WhiteHole(0, 0, 60))
                .Test();

    }

}