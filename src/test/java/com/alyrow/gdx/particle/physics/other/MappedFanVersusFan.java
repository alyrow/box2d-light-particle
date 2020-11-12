package com.alyrow.gdx.particle.physics.other;

import com.alyrow.gdx.particle.physics.Fan;
import com.alyrow.gdx.particle.physics.MappedFan;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;

public class MappedFanVersusFan {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new Fan /**/ (Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 20))
                //.forForce(() -> new MappedFan(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0.2f))
                .Test();

    }

}
