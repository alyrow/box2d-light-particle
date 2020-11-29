package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.modifiers.RandomMassModifier;
import com.alyrow.gdx.particle.tester.Tester;

import static org.junit.Assert.*;

public class PointElectrostaticForceTest {

    public static void main(String[] args) {

        new Tester()
                .forModifier(() -> new RandomMassModifier(10, 100))
                .forForce(() -> new PointElectrostaticForce(0,0,10000))
                .Test();

    }

}