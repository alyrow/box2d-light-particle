package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

import static org.junit.Assert.*;

public class PointAttractTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new PointAttract(0,0,20))
                .Test();

    }

}