package com.alyrow.gdx.particle.tester;

import com.badlogic.gdx.Gdx;

public class CompareTwoTester {

    /*
    TODO Make particles multithread then create a Tester class where particles are put in two viewport
     */

    public CompareTwoTester(Tester tester1, Tester tester2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tester1.Test2(tester2);
                tester2.Test();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //tester2.Test();
            }
        }).start();
    }
}
