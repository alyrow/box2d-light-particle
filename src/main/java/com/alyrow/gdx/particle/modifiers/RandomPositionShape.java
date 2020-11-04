package com.alyrow.gdx.particle.modifiers;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class RandomPositionShape extends Modifier {

    private Shape shape;

    float x, y, w, h;

    public RandomPositionShape(Shape shape) {
        this.shape = shape;
        Rectangle r = shape.getBounds();
        x = r.x;
        y = r.y;
        w = r.width;
        h = r.height;


        AffineTransform t = new AffineTransform();
        t.rotate(45);

    }

    public RandomPositionShape(Shape...shapes) {

        Path2D path = new Path2D.Float();
        for (Shape s : shapes) path.append(s, false);

        this.shape = path;
        Rectangle r = shape.getBounds();
        x = r.x;
        y = r.y;
        w = r.width;
        h = r.height;

    }

    public RandomPositionShape(int[] xpoints, int[] ypoints) {
        this(new Polygon(xpoints, ypoints, Math.max(xpoints.length, ypoints.length)));
    }

    @Override
    public void modify() {
        int a, b;
        while (true) {
            a = (int) (x + w  * MathUtils.random());
            b = (int) (y + h  * MathUtils.random());
            if(shape.contains(a, b)) {
                setX(a);
                setY(b);
                return;
            }
        }
    }

}
