package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.Line;

public class WhiteLine extends BlackLine {

    public WhiteLine(Line line, float mass, float G) {
        super(line, -mass, G);
    }

    public WhiteLine(Line line, float mass) {
        super(line, -mass);
    }

}
