package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MappedForce extends PhysicForce {

    protected Vector2[][] map;
    protected int pad = 0;

//    public MappedForce(float[][][] fls) {
//        map = new Vector2[fls.length][fls[0].length];
//        for (int i = 0; i < fls.length; i++)
//            for (int j = 0; j < fls[0].length; j++)
//                map[i][j] = new Vector2(fls[i][j][0], fls[i][j][1]);
//    }

    public MappedForce(Vector2[][] map) {
        this.map = map;
    }

    public MappedForce(boolean enablePad) {
        if (enablePad) pad = 50;
        map = new Vector2[Gdx.graphics.getWidth() + 2 * pad][Gdx.graphics.getHeight() + 2 * pad];
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        try {
            return map[(int) particle.x+pad][(int) particle.y+pad];
        } catch (IndexOutOfBoundsException ignored) {
            return Vector2.Zero;
        }
    }
}
