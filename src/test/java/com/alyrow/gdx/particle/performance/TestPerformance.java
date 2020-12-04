package com.alyrow.gdx.particle.performance;

import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.tester.TestGame;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public class TestPerformance {
    TestGame game;

    ArrayList<Supplier<PhysicForce>> forces;
    ArrayList<Supplier<Modifier>> modifiers;
    HashMap<Integer, Runnable> inputKeys;

    int pc = 200;
    boolean lightOn = true;
    int emissionNumberMode = ParticleEmissionNumber.INNER_SCREEN;
    float emissionSecondsDelay = 1;
    boolean clearScreen = true;

    public TestPerformance() {

        forces = new ArrayList<>();
        modifiers = new ArrayList<>();
        inputKeys = new HashMap<>();

    }

    public TestPerformance forModifier(Supplier<Modifier> modifier) {
        modifiers.add(modifier);
        return this;
    }

    public TestPerformance forForce(Supplier<PhysicForce> force) {
        forces.add(force);
        return this;
    }

    public TestPerformance setPc(int p) {
        pc = p;
        return this;
    }

    public TestPerformance setLightOn(boolean p) {
        lightOn = p;
        return this;
    }

    public TestPerformance setEmissionNumberMode(int p) {
        emissionNumberMode = p;
        return this;
    }

    public TestPerformance setEmissionSecondsDelay(float p) {
        emissionSecondsDelay = p;
        return this;
    }

    public TestPerformance clearScreen(boolean p) {
        clearScreen = p;
        return this;
    }

    public TestPerformance addKey(int key, Runnable action) {
        inputKeys.put(key, action);
        return this;
    }

    public TestPerformance addKey(Runnable action) {
        inputKeys.put(Input.Keys.SPACE, action);
        return this;
    }

    public void Test() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Integrated Tests");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new PerfGame(forces, modifiers, inputKeys, pc, lightOn, emissionNumberMode, emissionSecondsDelay, clearScreen), configuration);
    }
}
