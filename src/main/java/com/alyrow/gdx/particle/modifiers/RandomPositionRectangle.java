package com.alyrow.gdx.particle.modifiers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RandomPositionRectangle extends Modifier {

    Polygon r;
    Rectangle b;

    public RandomPositionRectangle(float x, float y, float w, float h) {
        r = new Polygon(new float[]{
                x, y,
                x + w, y,
                x + w, y + h,
                x, y + h
        });
        b = r.getBoundingRectangle();
    }

    public RandomPositionRectangle(float w, float h) {
        this(0, 0, w, h);
    }

    @Override
    public void modify() {
        int p, q;
        while (true) {
            p = (int) (b.x + b.width * MathUtils.random());
            q = (int) (b.y + b.height * MathUtils.random());
            if (r.contains(p, q)) {
                setX(p);
                setY(q);
                return;
            }
        }
    }

    public RandomPositionRectangle rotate(float theta, float anx, float any) {
        r.setOrigin(b.x + anx, b.y + any);
//        r.translate(-anx - b.x, -any - b.y);
        r.rotate(theta);
//        r.translate(anx + b.x, any + b.y);
        b = r.getBoundingRectangle();
        return this;
    }

    @Override
    public void write(Json json) {
        json.writeValue("rectangle", b);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
