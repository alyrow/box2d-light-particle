package com.alyrow.gdx.particle.physics;

public class WhiteHole extends BlackHole {

    public WhiteHole(float x, float y, float mass, float G) {
        super(x, y, -mass, G);
    }

    public WhiteHole(float x, float y, float mass) {
        super(x, y, -mass);
    }

}
