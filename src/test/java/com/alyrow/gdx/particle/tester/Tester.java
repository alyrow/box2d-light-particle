package com.alyrow.gdx.particle.tester;

import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.physics.PhysicForce;
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
    HashMap<Integer, Runnable> inputKeys;

    int pc = 200;
    boolean lightOn = true;

    public Tester() {

        forces = new ArrayList<>();
        modifiers = new ArrayList<>();
        inputKeys = new HashMap<>();

    }

    public Tester forModifier(Supplier<Modifier> modifier) {
        modifiers.add(modifier);
        return this;
    }

    public Tester forForce(Supplier<PhysicForce> force) {
        forces.add(force);
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
        new Lwjgl3Application(new TestGame(forces, modifiers, inputKeys, pc, lightOn), configuration);
    }

}
