package com.alyrow.gdx.particle.performance;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.modifiers.MassProportionalLightRadius;
import com.alyrow.gdx.particle.physics.LinearForce;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLightRandom;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class libgdxLinear  extends Game {

    private World world;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private ParticleEffect effect;

    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(10, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlurNum(3);
        rayHandler.setAmbientLight(new Color(0.1f, 0.1f, 0.1f, 0.3f));
        rayHandler.setShadows(true);
        debugRenderer = new Box2DDebugRenderer();

        //effect = new ParticleEffect();
        //ParticleEmitter emitter = new ParticleEmitter();
        //emitter.duration = 100000;
        //emitter.
        //effect.getEmitters();
        //effect.start();

//Setting the position of the ParticleEffect
        //effect.setPosition(x, y);
    }

    @Override
    public void render() {
        camera.update();
        world.step(1f / 60f, 6, 2);



        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("PERFORMANCE TEST");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new libgdxLinear(), configuration);
    }
}