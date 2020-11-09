package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.badlogic.gdx.math.Vector2;

public class MappedFan extends MappedForce {

    public MappedFan(float x, float y, float speed) {
        super(true);
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = new Vector2(j - y, x - i).scl(speed);
    }

}
