package com.alyrow.gdx.particle.tester;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.modifiers.ModifierManager;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLightRandom;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestGame extends Game {
    private World world;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private ParticleSystem system;
    private OrthographicCamera camera;
    private ParticleEmissionLightRandom emissionLight;
    private PhysicManager physicManager;

    private ArrayList<Supplier<PhysicForce>> forces;
    private ArrayList<Supplier<Modifier>> modifiers;
    ArrayList<Function<TestGame, PhysicForce>> forces_wg;
    ArrayList<Function<TestGame, Modifier>> modifiers_wg;
    private HashMap<Integer, Runnable> inputKeys;
    private int pc;
    private boolean lightOn;
    private int emissionNumberMode;
    private float emissionSecondsDelay;
    private final boolean clearScreen;

    public TestGame(ArrayList<Supplier<PhysicForce>> forces, ArrayList<Supplier<Modifier>> modifiers, ArrayList<Function<TestGame, PhysicForce>> forces_wg, ArrayList<Function<TestGame, Modifier>> modifiers_wg, HashMap<Integer, Runnable> inputKeys, int pc, boolean lightsOn, int emissionNumberMode, float emissionSecondsDelay, boolean clearScreen) {
        this.forces = forces;
        this.modifiers = modifiers;
        this.forces_wg = forces_wg;
        this.modifiers_wg = modifiers_wg;
        this.inputKeys = inputKeys;
        this.pc = pc;
        this.lightOn = lightsOn;
        this.emissionNumberMode = emissionNumberMode;
        this.emissionSecondsDelay = emissionSecondsDelay;
        this.clearScreen = clearScreen;
    }

    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(10, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlurNum(3);
        rayHandler.setAmbientLight(new Color(0.1f, 0.1f, 0.1f, 0f));
        rayHandler.setShadows(true);
        debugRenderer = new Box2DDebugRenderer();

        ParticleRules rules = new ParticleRules();
        ParticleEmissionNumber ps = new ParticleEmissionNumber(emissionNumberMode, pc);
        ps.setDelay(emissionSecondsDelay);
        rules.setNumber(ps); //One particle emitted per seconds
        rules.setLife(new ParticleLife(5, true)); //Particles life : 5s. Life will decrease when particles are outside the screen.
        rules.setDuration(new ParticleEmissionDuration(true)); //Infinite duration for the emission
        emissionLight = new ParticleEmissionLightRandom(rayHandler, 16, new Color(0.37647f, 1, 1, 1), 25, 35f);
        rules.setLight(emissionLight); //Add random light distance between 35 and 45.

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 1.0f);
        camera.update();

        system = new ParticleSystem(ParticleType.HALO, null, camera);
        system.setRules(rules);
        system.setParticlesPosition(-2, 0);

        ModifierManager manager = system.getModifierManager();
        modifiers.forEach(sup -> manager.addModifier(sup.get()));
        modifiers_wg.forEach(sup -> manager.addModifier(sup.apply(this)));

        physicManager = new PhysicManager();
        forces.forEach(sup -> physicManager.addForce(sup.get()));
        forces_wg.forEach(sup -> physicManager.addForce(sup.apply(this)));

        system.setPhysicManager(physicManager);

    }

    @Override
    public void render() {
        super.render();

        if (clearScreen) {
            Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        camera.update();
        world.step(1f / 60f, 6, 2);
        system.setParticlesPosition(Math.round(Math.random() * Gdx.graphics.getWidth()), Math.round(Math.random() * Gdx.graphics.getHeight())); //Random position
        emissionLight.color = new Color((float) Math.random() * 0, (float) Math.random() * 0, /*(float) Math.random()*/1, 1);
        system.render();
        if (lightOn) {
            rayHandler.setCombinedMatrix(camera.combined);
            rayHandler.updateAndRender();
        }

        inputKeys.forEach((key, act) -> {
            if (Gdx.input.isKeyJustPressed(key))
                act.run();
        });
    }

    public World getWorld() {
        return world;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public ParticleSystem getSystem() {
        return system;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public ParticleEmissionLightRandom getEmissionLight() {
        return emissionLight;
    }

    public PhysicManager getPhysicManager() {
        return physicManager;
    }


}
