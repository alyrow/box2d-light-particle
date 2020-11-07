package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.Particle;
import com.alyrow.gdx.particle.modifiers.RandomPositionRectangle;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;


public class ImageColourTest {

    public static void main(String[] args) {



        Particle.ratio = 1/64f;

        new Tester()
                .forModifier(() -> new RandomPositionRectangle(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()))
                .forForce(() -> new ImageColour(/*c*/Gdx.files.internal("lisa.jpg")))
                .forForce(() -> new Fan(Gdx.graphics.getWidth()/2f, Gdx.graphics.getWidth()/2f, 1))
                .forForce(() -> new BrownianForce(25,25, 156548, 1))
                .setPc(1).Test();

    }

}