package com.alyrow.gdx.particle.utils;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    @Test
    public void LineSegmentLineIntersect() {
        Line line1 = Line.fromTwoPoints(0, 0, 100, 0, true);
        Line line2 = Line.fromTwoPoints(1, -1, 1, 1);

        Assert.assertTrue(line1.intersects(line2));

        line1 = Line.fromTwoPoints(0, 0, 100, 0, true);
        line2 = Line.fromTwoPoints(-1, -1, -1, 1);

        Assert.assertFalse(line1.intersects(line2));
    }

    @Test
    public void LinePointIntersect() {
        Line line = Line.fromTwoPoints(0, 0, 100, 0);

        Assert.assertEquals(0, line.put(10, 0), 0.0);
        Assert.assertEquals(-1, line.put(1, 1), 0.0);
        Assert.assertEquals(1, line.put(10, -1), 0.0);
    }

    @Test
    public void LineLineIntersect() {
        Line line1 = Line.fromTwoPoints(1, 1, 100, -1);
        Line line2 = Line.fromTwoPoints(0, 0, 100, 1);

        Assert.assertTrue(line1.intersects(line2));

        line1 = Line.fromTwoPoints(1, 1, 2, 20);
        line2 = Line.fromTwoPoints(0, 0, 100, 2);

        Assert.assertTrue(line1.intersects(line2));

        line1 = Line.fromTwoPoints(2, 1, 30, 20);
        line2 = Line.fromTwoPoints(2, 3, 30, 22);

        Assert.assertFalse(line1.intersects(line2));
    }

}