package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.modifiers.RandomPositionRectangle;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;


public class ImageColourTest {

    public static void main(String[] args) {

        new Tester()
                .forModifier(() -> new RandomPositionRectangle(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()))
                .forForce(() -> new ImageColour(/*c*/Gdx.files.internal("libgdx.png")))
                .setPc(10000).Test();

    }

}