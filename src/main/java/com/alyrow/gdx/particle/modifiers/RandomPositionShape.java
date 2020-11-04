package com.alyrow.gdx.particle.modifiers;

import com.alyrow.gdx.particle.utils.T;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Path;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_ja;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class RandomPositionShape extends Modifier implements T.Transformer {

    private Shape shape;

    float x, y, w, h;

    public RandomPositionShape(Shape shape) {
        this.shape = shape;
        calculateBounds();


        AffineTransform t = new AffineTransform();
        t.rotate(45);

    }

    public void calculateBounds() {
        Rectangle r = shape.getBounds();
        x = r.x;
        y = r.y;
        w = r.width;
        h = r.height;
    }

    public RandomPositionShape(Shape... shapes) {

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

    public T<RandomPositionShape> getTransform() throws Exception {
        if (shape instanceof Path2D) {
            T<RandomPositionShape> builder = new T<>();
            builder.setHolder(this);
            return builder;

        } else throw new Exception("Provided shape is not an instance of java.awt.geom.Path2D");
    }

    @Override
    public void modify() {
        int a, b;
        while (true) {
            a = (int) (x + w * MathUtils.random());
            b = (int) (y + h * MathUtils.random());
            if (shape.contains(a, b)) {
                setX(a);
                setY(b);
                return;
            }
        }
    }

    @Override
    public void transform(AffineTransform t) {
        if(shape instanceof Path2D) ((Path2D) shape).transform(t);
        calculateBounds();
    }
}
