package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * <p> Creates a line object, which programmatically exists in the form <b> x = my + c </b> </p>
 */
public class InvertedSlopeConstantLine implements Line {

    /**
     * m = slope of the line as m = x/y.
     * c = width constant of the line.
     */
    float m, c;

    float hypot;

    /**
     * <p> Stores a bool regarding if it is a line segment. </p>
     */
    boolean isSegment;

    /**
     * <p> part of line between it's abscissa belonging from yf to yt </p>
     */
    float yf, yt;

    /**
     * degrees = slope of the line as x/y.
     * c = width constant of the line.
     */
    InvertedSlopeConstantLine(float m, float c) {
        this.m = m;
        this.c = c;
        hypot = (float) Math.hypot(m, 1);
    }

    @Override
    public float yint() {
        return -c / m;
    }

    @Override
    public float xint() {
        return c;
    }

    @Override
    public float distance(float x, float y) {
        return Math.abs(m * y - x + c) / hypot;
    }

    @Override
    public float distance(Line line) {
        if (isParallel(line)) return Math.abs(xint() - line.xint()) / hypot;
        return 0;
    }

    @Override
    public boolean isParallel(Line line) {
        if (line instanceof InvertedSlopeConstantLine) {
            InvertedSlopeConstantLine l = (InvertedSlopeConstantLine) line;
            return MathUtils.isEqual(m, l.m) && !MathUtils.isEqual(c, l.c);

        } else if (line instanceof SlopeConstantLine) {
            SlopeConstantLine l = (SlopeConstantLine) line;
            return MathUtils.isEqual(m, 1f / l.m) && !MathUtils.isEqual(c, -1f / l.c);

        }
        return false;
    }

    @Override
    public void normal(Vector2 vect, float x, float y) {
        vect.set(1, -m);
        vect.nor().scl(put(x, y));
    }

    @Override
    public float put(float x, float y) {
        return Math.signum(m * y + c - x);
    }

    @Override
    public boolean isBounded(float x, float y) {
        if (isSegment) return (m * yf + c < x && x < m * yt + c) && (yf < y && y < yt);
        return true;
    }

    @Override
    public boolean intersects(float x, float y) {
        return isBounded(x, y) && MathUtils.isEqual(x, m * y + c);
    }

    @Override
    public boolean intersects(Line line) {
        if (isSegment) {
            if (line.put(m * yf + c, yf) != line.put(m * yt + c, yt)) return !isParallel(line);
            return false;
        }
        return !isParallel(line);
    }

    @Override
    public void getIntersection(Vector2 vect, Line line) {
        if (intersects(line)) {
            if (line instanceof InvertedSlopeConstantLine) {
                InvertedSlopeConstantLine l = (InvertedSlopeConstantLine) line;
                vect.y = (l.c - c) / (m - l.m);
                vect.x = m * vect.y + c;

            } else if (line instanceof SlopeConstantLine) {
                SlopeConstantLine l = (SlopeConstantLine) line;
                vect.y = (l.m * c + l.c) / (1 - m * l.m);
                vect.x = m * vect.y + c;

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
        yf = Float.min(y1, y2);
        yt = Float.max(y1, y2);
    }

}


