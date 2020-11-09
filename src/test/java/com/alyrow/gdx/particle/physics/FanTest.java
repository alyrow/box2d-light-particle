package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;

public class FanTest {

    public static void main(String[] args) {

        new Tester().forForce(() -> new Fan(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f,20)).Test();

    }

}