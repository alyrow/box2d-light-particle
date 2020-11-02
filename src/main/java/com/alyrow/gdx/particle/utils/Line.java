package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * <p> Creates a line object, which programmatically exists in the form <b> y = mx + c </b> </p>
 */
public class Line {

    /**
     * m = slope of the line as m = y/x.
     * c = width constant of the line.
     */
    float m, c;

    /**
     * m = slope of the line as angle in theta.
     * c = width constant of the line.
     */
    public Line(float m, float c) {
        this.m = (float) Math.tan(Math.toRadians(m));
        this.c = c;
    }

    public Line(float x1, float y1, float x2, float y2) {
        m = (y2 - y1) / (x2 - x1);
        c = y1 - m * x1;
    }

    public float distance(float x, float y) {
        return Math.abs(m * x - y + c) / (float) Math.hypot(m, 1);
    }

    public Vector2 normal(float x, float y) {
        return new Vector2(m, -1).nor().scl(put(x, y));
    }

    public float put(float x, float y) {
        return Math.signum(m * x - y + c);
    }
}
