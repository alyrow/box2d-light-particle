package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.Line;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BlackLine extends PhysicForce {

    private float effect;
    private Line line;

    private float drs = 0.1f; // destruction radius squared

    public BlackLine(Line line, float mass, float G) {
        this.line = line;
        effect = G * mass;
    }

    public BlackLine(Line line, float mass) {
        this(line, mass, 10000);
    }

    private Vector2 cache = Vector2.Zero;
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache = line.normal(particle.x, particle.y);
        rs = (float) Math.pow(line.distance(particle.x, particle.y), 2);
        if(rs < drs) particle.deleteParticle();
        return cache.scl(-effect * particle.mass / rs);
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }

}
