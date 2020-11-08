package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;

public class PathFollowTest {

    public static void main(String[] args) {

        new Tester()
                .forForce(() -> new PathFollow(new float[]{
                        256, 192,
                        256, 0,
                        768, 0,
                        768, 192
                }, 20))
                .Test();

    }

}