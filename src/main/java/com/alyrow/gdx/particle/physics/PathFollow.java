package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.Line;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PathFollow extends PhysicForce {

    Fan fan;

    float[] points;
    Vector2 center;

    Line[] lines;

    public PathFollow(float[] points, Vector2 center, float speed) {
        assert points.length % 2 == 0;
        this.points = points;
        this.center = center;

        fan = new Fan(center.x, center.y, speed);

        createLines();
    }

    public PathFollow(float[] points, float speed) {
        this(points, new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f), speed);
    }

    public void createLines() {
        lines = new Line[points.length / 2];
        for (int i = 0; i < points.length; i += 2)
            lines[i / 2] = Line.fromTwoPoints(points[i], points[i + 1], points[(i + 2) % points.length], points[(i + 3) % points.length], true);
    }

    Vector2 addend = Vector2.Zero;

    @Override
    public Vector2 getForce(PhysicParticle particle) {

        Line line = Line.fromTwoPoints(particle.x, particle.y, center.x, center.y, true);

        for (int i = 0; i < lines.length; i++) {
            Line part = lines[i];
            if (part.intersects(particle.x, particle.y))
                break;
            if (part.intersects(line)) {
                part.normal(addend, particle.x, particle.y);
                break;
            }
        }
        addend.scl(-10);
//        return addend;
        Vector2 v = new Vector2().add(addend).add(fan.getForce(particle));
//        return new Vector2(fan.getForce(particle).scl(0.1f).x + addend.y, fan.getForce(particle).scl(0.1f).y + addend.y);
        return v;
    }
}
