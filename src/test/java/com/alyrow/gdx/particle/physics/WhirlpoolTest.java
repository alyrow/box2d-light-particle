package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

public class WhirlpoolTest {

    public static void main(String[] args) {

        new Tester().forForce(() -> new Whirlpool(0, 0, 20, 20)).Test();

    }

}