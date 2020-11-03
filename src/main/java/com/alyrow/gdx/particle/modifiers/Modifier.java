package com.alyrow.gdx.particle.modifiers;

import box2dLight.PointLight;
import com.alyrow.gdx.particle.Particle;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Modifier {
    private float life;
    private Boolean outer;
    public Texture texture;
    public Sprite sprite;
    private Boolean isInnerScreen;
    private float x, y, mass, charge;
    public PhysicParticle physicParticle;
    public PointLight light;

    private Particle particle;

    public Modifier() {

    }

    /**
     * This function clear the path you're following. Please don't override it ;)
     * @param particle The famous particle
     */
    public void _applyModifier(Particle particle) {
        this.particle = particle;
        life = particle.life;
        outer = particle.outer;
        texture = particle.texture;
        sprite = particle.sprite;
        isInnerScreen = particle.isInnerScreen;
        this.physicParticle = particle.physicParticle;
        x = physicParticle.x;
        y = physicParticle.y;
        mass = physicParticle.mass;
        charge = physicParticle.charge;
        light = particle.light;

        modify();
    }

    /**
     * Override, modify, edit, write: Use this function as you want!
     * In more serious word this function is called in order to apply the modifier on particles
     */
    public void modify() {}

    public float getLife() {
        return life;
    }

    public Boolean isOuter() {
        return outer;
    }

    public Boolean isInnerScreen() {
        return isInnerScreen;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getMass() {
        return mass;
    }

    public float getCharge() {
        return charge;
    }

    public void setLife(float life) {
        particle.life = life;
    }

    public void setOuter(Boolean outer) {
        particle.outer = outer;
    }

    public void setInnerScreen(Boolean innerScreen) {
        particle.isInnerScreen = innerScreen;
    }

    public void setX(float x) {
        physicParticle.x = x;
    }

    public void setY(float y) {
        physicParticle.y = y;
    }

    public void setMass(float mass) {
        physicParticle.mass = mass;
    }

    public void setCharge(float charge) {
        physicParticle.charge = charge;
    }
}
