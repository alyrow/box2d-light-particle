package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.TestGame;
import com.alyrow.gdx.particle.tester.Tester;
import org.junit.Test;

public class FanTest {

    public static void main(String[] args) {

        new Tester().addForce(new Fan(0,0,20)).Test();

    }

}