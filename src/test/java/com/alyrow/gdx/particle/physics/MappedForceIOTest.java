package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.utils.InformationHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

public class MappedForceIOTest {

    public static void main(String[] args) {

        // Final run test
        new Tester().forForce(MappedForceIOTest::TestInit).Test();

    }

    private static PhysicForce TestInit() {

        // Writing
        TestWrite();

        // Reading
        MappedForce force = TestRead();

        return force;
    }

    private static void TestWrite() {

        MappedForce force = new MappedForce(new Fan(0, 0, 20), true, null);
        PixmapIO.writePNG(Gdx.files.absolute("D://i.png"), MappedForce.toPixmap(MappedForce.pack(force)));

    }

    private static MappedForce TestRead() {

        InformationHolder holder = MappedForce.unpack(MappedForce.toData(new Pixmap(Gdx.files.absolute("D://i.png"))));
        return new MappedForce(new Fan(), holder);

    }

}