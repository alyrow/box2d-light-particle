package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.tester.Tester;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrownianForceTest {

    public static void main(String[] args) {

        new Tester()
                .setParticleType(ParticleType.DISCORD)
                .forForce(() -> new BrownianForce(25, 25, 156548, 1))
                .Test();

    }

}