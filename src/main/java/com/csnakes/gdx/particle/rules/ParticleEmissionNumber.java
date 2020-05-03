package com.csnakes.gdx.particle.rules;

public class ParticleEmissionNumber {
    public int number;
    public int mode;

    public ParticleEmissionNumber(int mode, int number) {
        this.mode = mode;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    //MODE
    public static int INNER_SCREEN = 0;
    public static int PER_SECONDS = 1;
}
