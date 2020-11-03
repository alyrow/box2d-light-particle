package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.HashMap;
import java.util.Map;

public class PhysicParticle {
    public Body body;
    public float x, x_start, y, y_start, r, width, height, mass = 1, charge = 1;

    @Deprecated
    public Map<String, Float> data = new HashMap<String, Float>();

    public Map<String, Float> data_float = new HashMap<String, Float>();
    public Map<String, Double> data_double = new HashMap<String, Double>();
    public Map<String, Integer> data_int = new HashMap<String, Integer>();
    public Map<String, Long> data_long = new HashMap<String, Long>();
    public Map<String, String> data_string = new HashMap<String, String>();

    CircleShape shapeCircle;
    public World world;
    private Camera camera;

    public PhysicParticle(float x, float y, World world, Camera camera) {
        this.x = x;
        this.y = y;
        x_start = x;
        y_start = y;
        this.camera = camera;

        this.world = world;

        if (world != null) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(x/camera.viewportWidth, y/camera.viewportHeight);
            body = world.createBody(bodyDef);
            body.setTransform(new Vector2(x, y), 0);
        }
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        if (world != null) {
            shapeCircle = new CircleShape();
            shapeCircle.setRadius(width / 4f);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.density = 0.1f;
            fixtureDef.shape = shapeCircle;
            body.createFixture(shapeCircle, 0.1f);
            shapeCircle.dispose();
        }
    }
}
