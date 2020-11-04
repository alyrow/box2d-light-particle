package com.alyrow.gdx.particle.tester;

import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

    TestGame game;

    ArrayList<PhysicForce> forces;
    ArrayList<Modifier> modifiers;

    public Tester() {

        forces = new ArrayList<>();
        modifiers = new ArrayList<>();

    }

    public Tester forModifier(Modifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    public Tester forModifier(Modifier... modifiers) {
        this.modifiers.addAll(Arrays.asList(modifiers));
        return this;
    }

    public Tester addForce(PhysicForce force) {
        forces.add(force);
        return this;
    }

    public Tester addForce(PhysicForce... forces) {
        this.forces.addAll(Arrays.asList(forces));
        return this;
    }

    public void Test() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Integrated Tests");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new TestGame(forces.toArray(new PhysicForce[0]), modifiers.toArray(new Modifier[0])), configuration);
    }

}
