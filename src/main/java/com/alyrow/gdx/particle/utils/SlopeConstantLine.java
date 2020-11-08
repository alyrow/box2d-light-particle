package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * <p> Creates a line object, which programmatically exists in the form <b> y = mx + c </b> </p>
 */
public class SlopeConstantLine implements Line {

    /**
     * m = slope of the line as m = y/x.
     * c = width constant of the line.
     */
    float m, c;

    float hypot;

    /**
     * <p> Stores a bool regarding if it is a line segment. </p>
     */
    boolean isSegment;

    /**
     * <p> part of line between it's abscissa belonging from xf to xt </p>
     */
    float xf, xt;

    /**
     * m = slope of the line as y/x.
     * c = width constant of the line.
     */
    SlopeConstantLine(float m, float c) {
        this.m = m;
        this.c = c;
        hypot = (float) Math.hypot(m, 1);
    }

    @Override
    public float yint() {
        return c;
    }

    @Override
    public float xint() {
        return -c / m;
    }

    @Override
    public float distance(float x, float y) {
        return Math.abs(m * x - y + c) / hypot;
    }

    @Override
    public float distance(Line line) {
        if (isParallel(line)) return Math.abs(yint() - line.yint()) / hypot;
        return 0;
    }

    @Override
    public boolean isParallel(Line line) {
        if (line instanceof SlopeConstantLine) {
            SlopeConstantLine l = (SlopeConstantLine) line;
            return MathUtils.isEqual(m, l.m) && !MathUtils.isEqual(c, l.c);

        } else if (line instanceof InvertedSlopeConstantLine) {
            InvertedSlopeConstantLine l = (InvertedSlopeConstantLine) line;
            return MathUtils.isEqual(m, 1f / l.m) && !MathUtils.isEqual(c, -1f / l.c);

        }
        return false;
    }

    @Override
    public void normal(Vector2 vect, float x, float y) {
        vect.set(m, -1);
        vect.nor().scl(put(x, y));
    }

    @Override
    public float put(float x, float y) {
        return Math.signum(m * x + c - y);
    }

    @Override
    public boolean isBounded(float x, float y) {
        if (isSegment) return (xf < x && x < xt) && (m * xf + c < y && y < m * xt + c);
        return true;
    }

    @Override
    public boolean intersects(float x, float y) {
        return isBounded(x, y) && MathUtils.isEqual(y, m * x + c);
    }

    @Override
    public boolean intersects(Line line) {
        if (isSegment) {
            if (line.put(xf, m * xf + c) != line.put(xt, m * xt + c)) return !isParallel(line);
            return false;
        }
        return !isParallel(line);
    }

    @Override
    public void getIntersection(Vector2 vect, Line line) {
        if (intersects(line)) {
            if (line instanceof SlopeConstantLine) {
                SlopeConstantLine l = (SlopeConstantLine) line;
                vect.x = (l.c - c) / (m - l.m);
                vect.y = m * vect.x + c;

            } else if (line instanceof InvertedSlopeConstantLine) {
                InvertedSlopeConstantLine l = (InvertedSlopeConstantLine) line;
                vect.x = (l.m * c + l.c) / (1 - m * l.m);
                vect.y = m * vect.x + c;

            }

        } else vect = null;
    }

    @Override
    public boolean isSegment() {
        return isSegment;
    }

    @Override
    public void makeSegment(float x1, float x2, float y1, float y2) {
        isSegment = true;
        xf = Float.min(x1, x2);
        xt = Float.max(x1, x2);
    }

}


