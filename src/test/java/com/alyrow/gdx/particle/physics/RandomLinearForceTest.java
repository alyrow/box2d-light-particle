package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

import static org.junit.Assert.*;

public class RandomLinearForceTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new RandomLinearForce(0,10,0,10))
                .Test();

    }

}