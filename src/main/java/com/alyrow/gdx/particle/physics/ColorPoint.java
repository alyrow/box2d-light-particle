package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ColorPoint extends PhysicForce {

    Vector2 center;
    float radius;
    Color color;

    public ColorPoint(Color color, float x, float y, float radius) {
        this.color = color;
        this.center = new Vector2(x, y);
        this.radius = radius;
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        if (Math.sqrt((center.x - particle.x)*(center.x - particle.x) + (center.y - particle.y)*(center.y - particle.y)) <= radius) {
            particle.setLightColor(new Color((particle.getLightColor().r+color.r)/2f, (particle.getLightColor().g+color.g)/2f, (particle.getLightColor().b+color.b)/2f, (particle.getLightColor().a+color.a)/2f)); //particle.getLightColor().add(color).mul(1/2f)
        }

        return new Vector2(0,0);
    }
}
