package com.alyrow.gdx.particle;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.modifiers.RandomColors;
import com.alyrow.gdx.particle.physics.*;
import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLightRandom;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.alyrow.gdx.particle.utils.Line;
import com.alyrow.gdx.particle.utils.LineTest;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;

public class JSONtest extends Game {

    private World world;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private ParticleSystem system;
    private OrthographicCamera camera;
    private ParticleEmissionLightRandom emissionLight;
    PhysicManager physicManager;

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
        ParticleEmissionNumber ps = new ParticleEmissionNumber(ParticleEmissionNumber.INNER_SCREEN, 1000);
        ps.setDelay(1f);
        rules.setNumber(ps); //One particle emitted per seconds
        rules.setLife(new ParticleLife(5, true)); //Particles life : 5s. Life will decrease when particles are outside the screen.
        rules.setDuration(new ParticleEmissionDuration(true)); //Infinite duration for the emission
        emissionLight = new ParticleEmissionLightRandom(rayHandler, 16, /*new Color(0.37647f, 1, 1, 1)*/Color.BLACK, 10.5f, 50);
        rules.setLight(emissionLight); //Add random light distance between 35 and 45.

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 1.0f);
        camera.update();
        system = new ParticleSystem(ParticleType.HALO, null, camera);
        system.setRules(rules);
        system.setParticlesPosition(-2, 0);
//        system.disableBlending();

        try {
            system.getModifierManager().addModifier(
                    //                new RandomPositionRectangle(camera.viewportWidth/2, camera.viewportHeight)
                    new RandomColors()
                    //                new RandomMassModifier(10,30),
                    //                new RandomChargeModifier(),
//                                    new MassProportionalLightRadius(2f)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        physicManager = new PhysicManager();

        physicManager.addForce(new BlackHole(3, 5, 25));
        physicManager.addForce(new BlackLine(Line.fromTwoPoints(1,1,5,5), 5, 25));
        physicManager.addForce(new BrownianForce(1, 2, 0.5));
        physicManager.addForce(new Drain(10, 10, 5, 5));
        physicManager.addForce(new Fan(50, 50, 6));
        physicManager.addForce(new LinearForce(12, -5));
        physicManager.addForce(new PointAttract(41, 65, 1));
        physicManager.addForce(new Revolution(15, 65, 2));
        physicManager.addForce(new WhiteHole(600, 600, 0.001f));

        system.setPhysicManager(physicManager);

        String str = new Jsonify().systemToJSON(system);
        System.out.println(str);
        System.out.println(new Jsonify().JSONtoSystem(str));
    }

    boolean g = true;

    @Override
    public void render() {


        camera.update();
        world.step(1f / 60f, 6, 2);
        system.setParticlesPosition(Math.round(Math.random() * Gdx.graphics.getWidth()), Math.round(Math.random() * Gdx.graphics.getHeight())); //Random position
        emissionLight.color = new Color((float) Math.random() * 0, (float) Math.random() * 0, (float) Math.random()*1, 1);
        system.render();
/*
        if(g) {
            physicManager.addForce(new Fan(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 20));
            g = false;
        }

        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();
         */
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("NebulaDemo");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new JSONtest(), configuration);
    }
}
