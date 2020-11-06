package com.alyrow.gdx.particle.utils.switches;

import com.alyrow.gdx.particle.physics.Fan;
import com.alyrow.gdx.particle.physics.powerups.SwitchableForce;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SimpleSwitchTest {

    public static void main(String[] args) {

        SimpleSwitch swt = new SimpleSwitch(2);

        new Tester()
                .forForce(() -> new SwitchableForce(
                        swt,
                        new Fan(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 20),
                        new Fan(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, -20)
                ))
                .addKey(Input.Keys.SPACE, swt::stepUp)
                .Test();


    }

}