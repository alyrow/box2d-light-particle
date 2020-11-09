package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.Line;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PathFollow extends PhysicForce {

    Fan fan;

    float[] points;
    Vector2 center;
    float attraction;

    Line[] lines;

    public PathFollow(float[] points, Vector2 center, float speed, float attraction) {
        assert points.length % 2 == 0;
        this.points = points;
        this.center = center;
        this.attraction = attraction;

        fan = new Fan(center.x, center.y, speed);

        createLines();
    }

    public PathFollow(float[] points, float speed, float attraction) {
        this(points, new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f), speed, attraction);
    }

    public void createLines() {
        lines = new Line[points.length / 2];
        for (int i = 0; i < points.length; i += 2)
            lines[i / 2] = Line.fromTwoPoints(points[i], points[i + 1], points[(i + 2) % points.length], points[(i + 3) % points.length], true);
    }

    Vector2 addend;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        addend = Vector2.Zero;

        Line line = Line.fromTwoPoints(particle.x, particle.y, center.x, center.y, true);

        for (Line part : lines) {
            if (part.intersects(particle.x, particle.y))
                break;
            if (line.intersects(part)) {
                part.normal(addend, particle.x, particle.y);
                break;
            }
        }

        return new Vector2().add(addend.scl(-attraction)).add(fan.getForce(particle));
    }
}
