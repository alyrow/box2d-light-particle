package com.alyrow.gdx.particle.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * <p> Creates a line object, which programmatically exists in the form <b> y = mx + c </b> </p>
 */
public interface Line {

    static Line fromTwoPoints(float x1, float y1, float x2, float y2, boolean isSegment) {
        float cm = (y1 - y2) / (x1 - x2);
        float im = (x1 - x2) / (y1 - y2);

        Line line;

        if (Math.abs(cm) < Math.abs(im)) line = new SlopeConstantLine /*   */(cm, y1 - cm * x1);
        else /*                       */ line = new InvertedSlopeConstantLine(im, x1 - im * y1);

        if (isSegment) line.makeSegment(x1, x2, y1, y2);

        return line;
    }

    static Line fromTwoPoints(float x1, float y1, float x2, float y2) {
        return fromTwoPoints(x1, y1, x2, y2, false);
    }

    static Line boundedInRectangle(float x, float y, float w, float h, boolean isSegment) {
        return fromTwoPoints(x, y, x + w, y + h, isSegment);
    }

    static Line boundedInRectangle(float x, float y, float w, float h) {
        return boundedInRectangle(x, y, w, h, true);
    }

    float distance(float x, float y);

    float distance(Line line);

    void normal(Vector2 vect, float x, float y);

    float put(float x, float y);

    float yint();

    float xint();

    boolean isBounded(float x, float y);

    boolean intersects(float x, float y);

    boolean isParallel(Line line);

    boolean intersects(Line line);

    boolean boundsOnSameSide(Line line);

    void getIntersection(Vector2 vect, Line line);

    boolean isSegment();

    void makeSegment(float x1, float x2, float y1, float y2);


}
