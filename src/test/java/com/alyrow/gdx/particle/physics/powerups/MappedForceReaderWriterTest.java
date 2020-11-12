package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.*;
import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.utils.Line;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.util.zip.Deflater;

public class MappedForceReaderWriterTest {

    public static void main(String[] args) {

        new Tester().forForce(MappedForceReaderWriterTest::get).Test();

    }

    private static PhysicForce get() {
        MappedForce force = new MappedForce(
                new CompoundForce(
                        new Fan(0, 0, 20f),
                        new Whirlpool(0, 0, 20, 20) {{
                            setDestructionRadius(0);
                        }},
                        new BrownianForce(25, 25, 156548, 1),
                        new BlackLine(Line.fromTwoPoints(0, 0, 1, 1), 40) {{
                            setDestructionRadius(0);
                        }}
                )
                , true, null
        );

        test(force);

        return force;
    }

    private static void test(MappedForce force) {

        int[][] org = MappedForce.packNicely("Hello There!".getBytes(), force.map);

        PixmapIO.writePNG(Gdx.files.absolute("D://i.png"), MappedForce.toPixmap(org), Deflater.NO_COMPRESSION, false);

        int[][] wrt = MappedForce.packNicely("Hello There!".getBytes(), MappedForce.unpackNicelyGetMap(MappedForce.toData(new Pixmap(Gdx.files.absolute("D://i.png")))));

        PixmapIO.writePNG(Gdx.files.absolute("D://j.png"), MappedForce.toPixmap(wrt), Deflater.NO_COMPRESSION, false);

        long b = 0; //2142464,             wth 2079488 with BEST_COMPRESSION

        for (int i = 0; i < org.length; i++)
            for (int j = 0; j < org[0].length; j++)
                b += Math.abs(org[i][j] - wrt[i][j]);

        System.out.println(b);

    }

}