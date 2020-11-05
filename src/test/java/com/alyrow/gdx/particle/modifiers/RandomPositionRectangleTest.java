package com.alyrow.gdx.particle.modifiers;


import com.alyrow.gdx.particle.tester.Tester;

public class RandomPositionRectangleTest {

    public static void main(String[] args) {

        new Tester().forModifier(() -> new RandomPositionRectangle(100, 100, 300, 300).rotate(45, 150, 150)).Test();

    }

}