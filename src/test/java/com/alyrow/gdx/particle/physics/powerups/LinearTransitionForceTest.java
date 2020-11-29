package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.BlackHole;
import com.alyrow.gdx.particle.physics.WhiteHole;
import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.utils.switches.ImpulseSwitch;

public class LinearTransitionForceTest {

    public static void main(String[] args) {

        BlackHole b = new BlackHole(0, 0, 100);
        WhiteHole w = new WhiteHole(0, 0, 100);
        LinearTransitionForce force = new LinearTransitionForce(b, w, 0.0001f);

        ImpulseSwitch swt = new ImpulseSwitch();
        force.setCondition(() -> swt.getState() == 1);

        new Tester()
                .forForce(() -> force)
                .addKey(swt::activate)
                .Test();


    }

}