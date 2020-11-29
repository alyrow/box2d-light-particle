package com.alyrow.gdx.particle.tester;

import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Tester {

    TestGame game;

    ArrayList<Supplier<PhysicForce>> forces;
    ArrayList<Supplier<Modifier>> modifiers;
    ArrayList<Function<TestGame , PhysicForce>> forces_wg;
    ArrayList<Function<TestGame , Modifier>> modifiers_wg;
    HashMap<Integer, Runnable> inputKeys;

    int pc = 200;
    ParticleType type = ParticleType.HALO;
    boolean lightOn = true;
    int emissionNumberMode = ParticleEmissionNumber.INNER_SCREEN;
    float emissionSecondsDelay = 1;
    boolean clearScreen = true;

    public Tester() {

        forces = new ArrayList<>();
        modifiers = new ArrayList<>();
        forces_wg = new ArrayList<>();
        modifiers_wg = new ArrayList<>();
        inputKeys = new HashMap<>();

    }

    public Tester forModifier(Supplier<Modifier> modifier) {
        modifiers.add(modifier);
        return this;
    }

    public Tester forModifier(Function<TestGame, Modifier> modifier) {
        modifiers_wg.add(modifier);
        return this;
    }

    public Tester forForce(Supplier<PhysicForce> force) {
        forces.add(force);
        return this;
    }

    public Tester forForce(Function<TestGame, PhysicForce> force) {
        forces_wg.add(force);
        return this;
    }

    public Tester setPc(int p) {
        pc = p;
        return this;
    }

    public Tester setLightOn(boolean p) {
        lightOn = p;
        return this;
    }

    public Tester setEmissionNumberMode(int p) {
        emissionNumberMode = p;
        return this;
    }

    public Tester setEmissionSecondsDelay(float p) {
        emissionSecondsDelay = p;
        return this;
    }

    public Tester clearScreen(boolean p) {
        clearScreen = p;
        return this;
    }

    public Tester addKey(int key, Runnable action) {
        inputKeys.put(key, action);
        return this;
    }

    public Tester addKey(Runnable action) {
        inputKeys.put(Input.Keys.SPACE, action);
        return this;
    }

    public void Test() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Integrated Tests");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new TestGame(forces, modifiers,forces_wg, modifiers_wg, inputKeys, pc, type, lightOn, emissionNumberMode, emissionSecondsDelay, clearScreen), configuration);
    }

    public Tester setParticleType(ParticleType type) {
        this.type = type;
        return this;
    }
}
