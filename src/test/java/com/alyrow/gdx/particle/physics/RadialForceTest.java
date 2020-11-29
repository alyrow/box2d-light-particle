package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

import static org.junit.Assert.*;

public class RadialForceTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new RadialForce(20))
                .Test();

    }

}