package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.physics.powerups.MappedForce;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;

public class Fan extends PhysicForce {

    // Black hole specific
    private float speed;
    private Vector2 center;

    public Fan(float x, float y, float speed) {
        center = new Vector2(x, y);
        this.speed = speed/100;

    }

    private Vector2 cache = new Vector2(0, 0);

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        return cache.set(particle.y - center.y, center.x - particle.x).scl(speed);
    }

}