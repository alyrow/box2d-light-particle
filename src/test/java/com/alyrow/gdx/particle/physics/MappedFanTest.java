package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.utils.InformationHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector3;

public class MappedFanTest {

    public static void main(String[] args) {

        new Tester().forForce(game ->
                new MappedForce(new Fan(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 20), true, game.getCamera())
        ).Test();

//        new Tester().forForce(a -> new MappedForce(new Fan(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f, 20f), true, null)).Test();

    }

}