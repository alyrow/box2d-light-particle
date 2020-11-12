package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class MappedFanTest {

    public static void main(String[] args) {

        //new Tester().forForce(() -> new MappedFan(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f, 0.2f)).Test();

        new Tester().forForce(() -> new MappedForce(new Fan(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f, 20f), true, new Camera() {
            float viewportWidth = Gdx.graphics.getWidth()/2f;
            float viewportHeight = Gdx.graphics.getHeight()/2f;
            Vector3 position = new Vector3(this.viewportWidth / 2.0f, this.viewportHeight / 2.0f, 1.0f);

            @Override
            public void update() {

            }

            @Override
            public void update(boolean updateFrustum) {

            }
        })).Test();

    }

}