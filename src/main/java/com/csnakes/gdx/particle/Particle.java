package com.csnakes.gdx.particle;

import com.badlogic.gdx.graphics.Texture;

public class Particle {
    public float life; //in seconds
    public boolean outer; //If false --> Survive `life` seconds then die
    public Texture texture;

    public Particle(float life, boolean outer, Texture texture) {}
}
