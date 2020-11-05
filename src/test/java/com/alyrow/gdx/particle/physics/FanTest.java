package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

public class FanTest {

    public static void main(String[] args) {

        new Tester().forForce(() -> new Fan(0,0,20)).Test();

    }

}